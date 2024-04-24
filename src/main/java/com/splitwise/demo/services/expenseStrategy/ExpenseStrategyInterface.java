package com.splitwise.demo.services.expenseStrategy;

import com.splitwise.demo.entities.Transaction;
import com.splitwise.demo.entities.UserEntity;

import java.util.List;
import java.util.Map;

public interface ExpenseStrategyInterface {
    public List<Transaction> calculateExpenses(Map<UserEntity, Integer> borrowersMap, Map<UserEntity, Integer>lendersMap, Double amount);

}
