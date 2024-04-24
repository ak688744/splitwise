package com.splitwise.demo.repository;

import com.splitwise.demo.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<UserEntity,Integer> {
    UserEntity findByName(String name);
    List<UserEntity> findAllByNameIn(List<String> names);
}
