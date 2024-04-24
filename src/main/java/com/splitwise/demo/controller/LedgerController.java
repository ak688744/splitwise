package com.splitwise.demo.controller;

import com.splitwise.demo.entities.CreditExpenseEntity;
import com.splitwise.demo.entities.Transaction;
import com.splitwise.demo.models.BalanceResponse;
import com.splitwise.demo.models.LedgerResponse;
import com.splitwise.demo.services.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "ledger")
public class LedgerController {
    @Autowired LedgerService ledgerService;
    @GetMapping("/transactions/user/{user_id}")
    public ResponseEntity<? extends Object> getUserTransactions(@PathVariable("user_id") String userId){
        return ResponseEntity.ok(ledgerService.getUserLedger(userId));
    }

    @GetMapping("/transactions/group/{group_id}")
    public ResponseEntity<? extends Object> getGroupTranscations(@PathVariable("group_id") String groupId){
        return ResponseEntity.ok(ledgerService.getGroupLedger(groupId));
    }


    @GetMapping("/balances/group/{group_id}")
    public ResponseEntity<? extends Object> getGroupBalances(@PathVariable("group_id") String groupId){
        return ResponseEntity.ok(ledgerService.getBalanceForGroup(groupId));
    }

    @GetMapping("/balances/user/{user_id}")
    public ResponseEntity<? extends Object> getUserBalances(@PathVariable("user_id") String userId){
        return ResponseEntity.ok(ledgerService.getBalanceForUser(userId));
    }
}
