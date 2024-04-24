package com.splitwise.demo.models;

import com.splitwise.demo.enums.ExpenseType;

import java.util.List;


public class ExpenseRequest {
    private String title;

    private String createdByUser;
    private List<ExpenseDetails> lenders;
    private List<ExpenseDetails> borrowers;
    private Double totalAmount;
    private ExpenseType expenseType;
    private String groupName;

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }


    public static class ExpenseDetails{
        private String userName;
        private Integer value;

        public ExpenseDetails(String userName, Integer value) {
            this.userName = userName;
            this.value = value;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public ExpenseRequest() {
    }

    public ExpenseRequest(String title, List<ExpenseDetails> lenders, List<ExpenseDetails> borrowers,
            Double totalAmount, ExpenseType expenseType) {
        this.title = title;
        this.lenders = lenders;
        this.borrowers = borrowers;
        this.totalAmount = totalAmount;
        this.expenseType = expenseType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ExpenseDetails> getLenders() {
        return lenders;
    }

    public void setLenders(List<ExpenseDetails> lenders) {
        this.lenders = lenders;
    }

    public List<ExpenseDetails> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<ExpenseDetails> borrowers) {
        this.borrowers = borrowers;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
