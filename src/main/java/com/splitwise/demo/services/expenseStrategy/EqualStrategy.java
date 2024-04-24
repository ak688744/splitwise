package com.splitwise.demo.services.expenseStrategy;

import com.splitwise.demo.entities.Transaction;
import com.splitwise.demo.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EqualStrategy implements ExpenseStrategyInterface {

    @Override public List<Transaction> calculateExpenses(Map<UserEntity, Integer> borrowersMap, Map<UserEntity, Integer> lendersMap,
            Double amount) {
        List<Transaction> transactionList = new ArrayList<>();
        for (UserEntity lender : lendersMap.keySet()) {
            Double payPercentage = amount / lendersMap.get(lender);
            for (UserEntity borrower : borrowersMap.keySet()) {
                if (lender.getName().equals(borrower.getName())) {
                    continue;
                }
                Double borrowerTotalOweMoney = amount / borrowersMap.size();
                transactionList.add(new Transaction(lender.getName(), borrower.getName(),
                        borrowerTotalOweMoney * payPercentage));
            }
        }
        return transactionList;
    }
}
