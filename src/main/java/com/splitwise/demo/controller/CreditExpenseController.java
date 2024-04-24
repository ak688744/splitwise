package com.splitwise.demo.controller;

import com.splitwise.demo.entities.CreditExpenseEntity;
import com.splitwise.demo.models.CreditRequest;
import com.splitwise.demo.models.ExpenseRequest;
import com.splitwise.demo.services.TransactionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "creditExpenseManager")
public class CreditExpenseController {

    @Autowired
    private TransactionManagerService transactionManagerService;
    @PostMapping(value = "/addExpense")
    public ResponseEntity<? extends Object> createExpense(@RequestBody ExpenseRequest expenseRequest){
        try {
            return ResponseEntity.ok(transactionManagerService.createExpense(expenseRequest));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/settleUp")
    public void settleUp(@RequestBody CreditRequest creditRequest){
        try {
            transactionManagerService.createCredit(creditRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
