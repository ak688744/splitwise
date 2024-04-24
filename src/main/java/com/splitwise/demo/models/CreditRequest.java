package com.splitwise.demo.models;

public class CreditRequest {
    private String payorUser;
    private String payeeUser;
    private Double amount;

    private String groupName;
    public CreditRequest(String payorUser, String payeeUser, Double amount, String groupName) {
        this.payorUser = payorUser;
        this.payeeUser = payeeUser;
        this.amount = amount;
        this.groupName= groupName;
    }

    public CreditRequest() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPayorUser() {
        return payorUser;
    }

    public void setPayorUser(String payorUser) {
        this.payorUser = payorUser;
    }

    public String getPayeeUser() {
        return payeeUser;
    }

    public void setPayeeUser(String payeeUser) {
        this.payeeUser = payeeUser;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
