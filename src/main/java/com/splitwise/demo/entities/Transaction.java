package com.splitwise.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String payor;
    private String payee;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "credit_expense_id")
    private CreditExpenseEntity creditExpenseEntity;
    private Double amount;

    public Transaction( String payor, String payee, Double amount) {
        this.payor = payor;
        this.payee = payee;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayor() {
        return payor;
    }

    public void setPayor(String payor) {
        this.payor = payor;
    }

    public String getPayee() {
        return payee;
    }

    public CreditExpenseEntity getCreditExpenseEntity() {
        return creditExpenseEntity;
    }

    public void setCreditExpenseEntity(CreditExpenseEntity creditExpenseEntity) {
        this.creditExpenseEntity = creditExpenseEntity;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }



    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Transaction() {
    }

    @Override public String toString() {
        return "Transaction{" + "id=" + id + ", payor='" + payor + '\'' + ", payee='" + payee + '\''
                + ", creditExpenseEntity=" + creditExpenseEntity + ", amount=" + amount + '}';
    }
}
