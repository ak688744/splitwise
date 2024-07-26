package com.splitwise.demo.models;

import com.splitwise.demo.enums.ExpenseType;

import java.util.List;

public class LedgerResponse {
    String title;
    String createdByUser;
    Double amount;

    ExpenseType expenseType;

    List<TransactionElement> transactionList;
    public static class TransactionElement{
        String borrowerUsername;
        String lendorUsername;
        Double amount;

        public TransactionElement(String borrowerUsername, String lendorUsername, Double amount) {
            this.borrowerUsername = borrowerUsername;
            this.lendorUsername = lendorUsername;
            this.amount = amount;
        }

        public String getBorrowerUsername() {
            return borrowerUsername;
        }

        public void setBorrowerUsername(String borrowerUsername) {
            this.borrowerUsername = borrowerUsername;
        }

        public String getLendorUsername() {
            return lendorUsername;
        }

        public void setLendorUsername(String lendorUsername) {
            this.lendorUsername = lendorUsername;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }

    public LedgerResponse() {
    }

    public LedgerResponse(String title, String createdByUser, Double amount, List<TransactionElement> transactionList, ExpenseType expenseType) {
        this.title = title;
        this.createdByUser = createdByUser;
        this.amount = amount;
        this.transactionList = transactionList;
        this.expenseType=expenseType;
    }

    public String getTitle() {


        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<TransactionElement> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionElement> transactionList) {
        this.transactionList = transactionList;
    }
}
