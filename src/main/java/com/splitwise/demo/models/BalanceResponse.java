package com.splitwise.demo.models;


public class BalanceResponse {
    private String lenderUserName;
    private String borrowerUserName;
    private Double amount;


    public BalanceResponse() {
    }

    public BalanceResponse(String lenderUserName, String borrowerUserName, Double amount) {
        this.lenderUserName = lenderUserName;
        this.borrowerUserName = borrowerUserName;
        this.amount = amount;
    }

    public String getLenderUserName() {
        return lenderUserName;
    }

    public void setLenderUserName(String lenderUserName) {
        this.lenderUserName = lenderUserName;
    }

    public String getBorrowerUserName() {
        return borrowerUserName;
    }

    public void setBorrowerUserName(String borrowerUserName) {
        this.borrowerUserName = borrowerUserName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override public String toString() {
        return "BalanceResponse{" + "lenderUserName='" + lenderUserName + '\'' + ", borrowerUserName='"
                + borrowerUserName + '\'' + ", amount=" + amount + '}';
    }
}
