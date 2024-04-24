package com.splitwise.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class GroupEntity {
    @Column(name = "name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<UserEntity> userEntityList;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CreditExpenseEntity> creditExpenses;

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    public List<CreditExpenseEntity> getCreditExpenses() {
        return creditExpenses;
    }

    public void setCreditExpenses(List<CreditExpenseEntity> creditExpenses) {
        this.creditExpenses = creditExpenses;
    }

    public GroupEntity(String name, List<UserEntity> userEntityList) {
        this.name = name;
        this.userEntityList = userEntityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    public GroupEntity() {
    }
}
