package com.splitwise.demo.services;

import com.splitwise.demo.dao.ExpenseDao;
import com.splitwise.demo.dao.GroupDao;
import com.splitwise.demo.dao.TransactionDao;
import com.splitwise.demo.dao.UserDao;
import com.splitwise.demo.entities.CreditExpenseEntity;
import com.splitwise.demo.entities.GroupEntity;
import com.splitwise.demo.entities.Transaction;
import com.splitwise.demo.entities.UserEntity;
import com.splitwise.demo.enums.ExpenseType;
import com.splitwise.demo.enums.TransactionType;
import com.splitwise.demo.models.CreditRequest;
import com.splitwise.demo.models.ExpenseRequest;
import com.splitwise.demo.services.expenseStrategy.EqualStrategy;
import com.splitwise.demo.services.expenseStrategy.ExpenseStrategyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionManagerService {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private UserDao userDao;

    @Autowired EqualStrategy equalStrategy;

    @Autowired ExpenseDao expensedao;

    @Autowired TransactionDao transactionDao;

    public CreditExpenseEntity createExpense(ExpenseRequest expenseRequest) throws Exception {
        GroupEntity groupEntity = groupDao.getGroup(expenseRequest.getGroupName());
        Map<UserEntity, Integer> borrowersMap = new HashMap<>();
        Map<UserEntity, Integer> lendersMap = new HashMap<>();
        for (ExpenseRequest.ExpenseDetails borrowers : expenseRequest.getBorrowers()) {
            if (!groupDao.validateIfUserExistInGroup(borrowers.getUserName(), groupEntity)) {
                throw new Exception("User does not exist in group " + borrowers.getUserName());
            }
            UserEntity userEntity = userDao.getUserByUserName(borrowers.getUserName());
            borrowersMap.put(userEntity, borrowers.getValue());
        }
        for (ExpenseRequest.ExpenseDetails lenders : expenseRequest.getLenders()) {
            if (!groupDao.validateIfUserExistInGroup(lenders.getUserName(), groupEntity)) {
                throw new Exception("User does not exist in group " + lenders.getUserName());
            }
            UserEntity userEntity = userDao.getUserByUserName(lenders.getUserName());
            lendersMap.put(userEntity, lenders.getValue());
        }

        UserEntity createdByUserEntity = userDao.getUserByUserName(expenseRequest.getCreatedByUser());
        List<Transaction> transactionList = new ArrayList<>();
        CreditExpenseEntity creditExpenseEntity = new CreditExpenseEntity(createdByUserEntity.getName(), expenseRequest.getTotalAmount(),
                expenseRequest.getExpenseType(), expenseRequest.getTitle(), transactionList, TransactionType.DEBIT, groupEntity);
        transactionList.addAll(createTransactions(borrowersMap, lendersMap,
                expenseRequest.getExpenseType(), expenseRequest.getTotalAmount()));
        transactionList.forEach(e->e.setCreditExpenseEntity(creditExpenseEntity));
        return expensedao.saveExpense(creditExpenseEntity);
    }

    public List<Transaction> createTransactions(Map<UserEntity, Integer> borrowers, Map<UserEntity, Integer> lenders, ExpenseType expenseType, Double amount){
        ExpenseStrategyInterface expenseStrategy= getExpenseStrategy(expenseType);

        return expenseStrategy.calculateExpenses(borrowers, lenders, amount);
    }

    public ExpenseStrategyInterface getExpenseStrategy(ExpenseType expenseType){
        switch (expenseType){
        case EQUAL -> {
            return equalStrategy;
        }
        }
        return equalStrategy;
    }

    public void createCredit(CreditRequest creditRequest) throws Exception {
        List<Transaction> borrowedTransactions = transactionDao.getTransactions(creditRequest.getPayeeUser(),
                creditRequest.getPayorUser());

        Map<String, List<Transaction>> groupBorrowing= new HashMap<>();

        for (Transaction transaction: borrowedTransactions){
            if (Objects.nonNull(creditRequest.getGroupName()) && !creditRequest.getGroupName()
                    .equals(transaction.getCreditExpenseEntity().getGroup().getName())){
                continue;
            }
            groupBorrowing.putIfAbsent(transaction.getCreditExpenseEntity().getGroup().getName(), new ArrayList<>());
            groupBorrowing.get(transaction.getCreditExpenseEntity().getGroup().getName()).add(transaction);
        }

        List<Transaction> lenderTransactions = transactionDao.getTransactions(creditRequest.getPayorUser(),
                creditRequest.getPayeeUser());

        Map<String, List<Transaction>> groupLendig= new HashMap<>();

        for (Transaction transaction: lenderTransactions){
            if (Objects.nonNull(creditRequest.getGroupName()) && !creditRequest.getGroupName()
                    .equals(transaction.getCreditExpenseEntity().getGroup().getName())){
                continue;
            }
            groupLendig.putIfAbsent(transaction.getCreditExpenseEntity().getGroup().getName(), new ArrayList<>());
            groupLendig.get(transaction.getCreditExpenseEntity().getGroup().getName()).add(transaction);
        }
        Double amount=creditRequest.getAmount();
        List<CreditExpenseEntity> creditExpenseEntities = new ArrayList<>();
        Double settleAmount = 0D;
        for (String group : groupBorrowing.keySet()) {
            if(amount<0){
                break;
            }
            Double borrowedAmount = groupBorrowing.get(group).stream().mapToDouble(e -> e.getAmount()).sum();
            Double lendingAmount = 0D;
            if (groupLendig.containsKey(group)) {
                lendingAmount = groupLendig.get(group).stream().mapToDouble(e -> e.getAmount()).sum();
            }
            settleAmount = borrowedAmount - lendingAmount;
            if(settleAmount<0){
                continue;
            }

            Transaction transaction = new Transaction(creditRequest.getPayorUser(), creditRequest.getPayeeUser(),
                    amount);
            GroupEntity groupEntity= groupDao.getGroup(group);
            CreditExpenseEntity creditExpenseEntity = new CreditExpenseEntity(creditRequest.getPayorUser(),
                    creditRequest.getAmount(), ExpenseType.EQUAL, "Settled up", Arrays.asList(transaction),
                    TransactionType.CREDIT,groupEntity);
            transaction.setCreditExpenseEntity(creditExpenseEntity);
            expensedao.saveExpense(creditExpenseEntity);
            amount = amount - settleAmount;
        }
    }
}
