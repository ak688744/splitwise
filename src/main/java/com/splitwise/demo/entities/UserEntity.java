package com.splitwise.demo.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<GroupEntity> groupEntityList;

    public UserEntity(String name) {
        this.name = name;
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

    public List<GroupEntity> getGroupEntityList() {
        return groupEntityList;
    }

    public void setGroupList(List<GroupEntity> groupEntityList) {
        this.groupEntityList = groupEntityList;
    }

    public UserEntity() {
    }
}
