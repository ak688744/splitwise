package com.splitwise.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.splitwise.demo.enums.ExpenseType;
import com.splitwise.demo.enums.TransactionType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CreditExpenseEntity {
    private String createdByUser;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double amount;

    private ExpenseType expenseType;
    private String title;

    private TransactionType transactionType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private GroupEntity groupEntity;

    public GroupEntity getGroup() {
        return groupEntity;
    }

    public void setGroup(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "creditExpenseEntity")
    @JsonBackReference
    private List<Transaction> transactionList;

    public CreditExpenseEntity(String createdByUser, Double amount, ExpenseType expenseType, String title,
            List<Transaction> transactionList, TransactionType transactionType, GroupEntity groupEntity) {
        this.createdByUser = createdByUser;
        this.amount = amount;
        this.expenseType = expenseType;
        this.title = title;
        this.transactionList = transactionList;
        this.transactionType=transactionType;
        this.groupEntity=groupEntity;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public CreditExpenseEntity() {
    }
}
