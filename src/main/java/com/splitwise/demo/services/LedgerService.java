package com.splitwise.demo.services;

import com.splitwise.demo.dao.ExpenseDao;
import com.splitwise.demo.dao.GroupDao;
import com.splitwise.demo.dao.TransactionDao;
import com.splitwise.demo.dao.UserDao;
import com.splitwise.demo.entities.CreditExpenseEntity;
import com.splitwise.demo.entities.GroupEntity;
import com.splitwise.demo.entities.Transaction;
import com.splitwise.demo.entities.UserEntity;
import com.splitwise.demo.models.BalanceResponse;
import com.splitwise.demo.models.LedgerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LedgerService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private TransactionDao transactionDao;

    public List<Transaction> getUserLedger(String userName){
        UserEntity userEntity= userDao.getUserByUserName(userName);

        return transactionDao.getTransactions(userName);
    }

    public List<LedgerResponse> getGroupLedger(String groupName){
        GroupEntity groupEntity= groupDao.getGroup(groupName);
        List<CreditExpenseEntity>creditExpenses= expenseDao.getAllExpensesOfAGroup(groupEntity);

        List<LedgerResponse> ledgerResponses= new ArrayList<>();

        for(CreditExpenseEntity creditExpense: creditExpenses){
            LedgerResponse ledgerResponse = new LedgerResponse(creditExpense.getTitle(),
                    creditExpense.getCreatedByUser(), creditExpense.getAmount(), new ArrayList<>(), creditExpense.getExpenseType());

            for(Transaction transaction: creditExpense.getTransactionList()){
                ledgerResponse.getTransactionList()
                        .add(new LedgerResponse.TransactionElement(transaction.getPayee(), transaction.getPayor(),
                                transaction.getAmount()));
            }
            ledgerResponses.add(ledgerResponse);
        }
        return ledgerResponses;
    }

    public List<BalanceResponse> getBalanceForUser(String userName){
        List<Transaction> credits= transactionDao.getTransactionsAsPayor(userName);
        List<Transaction> debits= transactionDao.getTransactionsAsPayee(userName);

        Map<String, Double>creditMap= new HashMap<>();
        Map<String, Double>debitMap= new HashMap<>();

        for(Transaction transaction: credits){
            creditMap.put(transaction.getPayee(), creditMap.getOrDefault(transaction.getPayee(), 0D)+transaction.getAmount());
        }

        for(Transaction transaction: debits){
            debitMap.put(transaction.getPayor(),
                    debitMap.getOrDefault(transaction.getPayor(), 0D) + transaction.getAmount());
        }

        List<BalanceResponse> balanceResponses= new ArrayList<>();
        for(String user: debitMap.keySet()){
            if(creditMap.containsKey(user)){
                String lenderUsername= debitMap.get(user)>creditMap.get(user)?user:userName;
                String borrowerUsername= debitMap.get(user)<creditMap.get(user)?user:userName;
                Double finalAmount= Math.abs(creditMap.get(user)-debitMap.get(user));
                balanceResponses.add(new BalanceResponse(lenderUsername, borrowerUsername, finalAmount));
                creditMap.remove(user);
            }
        }

        for(String user: creditMap.keySet()){
            String lenderUsername= userName;
            String borrowerUsername= user;
            Double finalAmount= creditMap.get(user);
            balanceResponses.add(new BalanceResponse(lenderUsername, borrowerUsername, finalAmount));
            creditMap.remove(user);
        }

        return balanceResponses;
    }


    public List<BalanceResponse> getBalanceForGroup(String groupName){
        GroupEntity groupEntity= groupDao.getGroup(groupName);
        List<CreditExpenseEntity> creditExpenseList= expenseDao.getAllExpensesOfAGroup(groupEntity);
        List<Transaction> transactionList= new ArrayList<>();
        for(CreditExpenseEntity creditExpense:creditExpenseList ){
            transactionList.addAll(creditExpense.getTransactionList());
        }
        Map<String, Map<String, Double>> transactionMap= new HashMap<>();
        for(Transaction transaction: transactionList){
            transactionMap.putIfAbsent(transaction.getPayor(), new HashMap<>());
            transactionMap.get(transaction.getPayor()).put(transaction.getPayee(),
                    transactionMap.get(transaction.getPayor()).getOrDefault(transaction.getPayee(), 0D)
                            + transaction.getAmount());
        }

        List<BalanceResponse> balanceResponses= new ArrayList<>();
        for(String payorUser: transactionMap.keySet()){
            for(String payeeUser: transactionMap.get(payorUser).keySet()){
                if(transactionMap.containsKey(payeeUser)&&transactionMap.get(payeeUser).containsKey(payorUser)){
                    Double finalAmount =
                            transactionMap.get(payorUser).get(payeeUser) - transactionMap.get(payeeUser).get(payorUser);
                    if(finalAmount==0){
                        continue;
                    }
                    String lender= finalAmount<0?payeeUser:payorUser;
                    String borrower= finalAmount>0?payeeUser:payorUser;
                    balanceResponses.add(new BalanceResponse(lender, borrower, Math.abs(finalAmount)));
                    transactionMap.get(payeeUser).remove(payorUser);
                } else {
                    String lender= payorUser;
                    String borrower= payeeUser;
                    balanceResponses.add(new BalanceResponse(lender, borrower,
                            Math.abs(transactionMap.get(payorUser).get(payeeUser))));
                }
            }
        }
        return balanceResponses;
    }
}
