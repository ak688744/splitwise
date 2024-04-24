package com.splitwise.demo.dao;

import com.splitwise.demo.entities.GroupEntity;
import com.splitwise.demo.entities.Transaction;
import com.splitwise.demo.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class TransactionDao {

    @Autowired
    private TransactionRepo transactionRepo;


    public List<Transaction> getTransactions(String payorUsername,String payeeUsername){
        return transactionRepo.findByPayorAndPayee(payorUsername, payeeUsername);
    }

    public List<Transaction> getTransactions(String payorUsername){
        List<Transaction> transactionList= new ArrayList<>();
        transactionList.addAll(transactionRepo.findByPayor(payorUsername));
        transactionList.addAll(transactionRepo.findByPayee(payorUsername));
//        transactionList.forEach(e->e.getExpense().setTransactionList(null));
        return transactionList;
    }


    public List<Transaction> getTransactionsAsPayor(String payorUsername){
        List<Transaction> transactionList= new ArrayList<>();
        transactionList.addAll(transactionRepo.findByPayor(payorUsername));
        return transactionList;
    }

    public List<Transaction> getTransactionsAsPayee(String payeeUsername){
        List<Transaction> transactionList= new ArrayList<>();
        transactionList.addAll(transactionRepo.findByPayee(payeeUsername));
        return transactionList;
    }

}
