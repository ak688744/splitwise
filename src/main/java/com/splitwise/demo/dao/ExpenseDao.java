package com.splitwise.demo.dao;

import com.splitwise.demo.entities.CreditExpenseEntity;
import com.splitwise.demo.entities.GroupEntity;
import com.splitwise.demo.entities.Transaction;
import com.splitwise.demo.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExpenseDao {
    @Autowired
    private ExpenseRepo expenseRepo;
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserDao userDao;

    public CreditExpenseEntity saveExpense(CreditExpenseEntity creditExpenseEntity) throws Exception {
        return expenseRepo.save(creditExpenseEntity);

    }
    public List<CreditExpenseEntity> getAllExpensesOfAGroup(GroupEntity groupName){
        return expenseRepo.findAllByGroupEntity(groupName);
    }
}
