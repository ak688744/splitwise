package com.splitwise.demo.dao;

import com.splitwise.demo.entities.UserEntity;
import com.splitwise.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserDao {
    @Autowired
    private UserRepo userRepo;

    public UserEntity saveUser(String userName) throws Exception {
        UserEntity userEntity = userRepo.findByName(userName);
        if(Objects.isNull(userEntity)){
            UserEntity newUserEntity = new UserEntity(userName);
            return userRepo.save(newUserEntity);
        }
        throw new Exception("User already exists");
    }


    public List<UserEntity> getUsersByUserNameIn(List<String> userNames){
        return userRepo.findAllByNameIn(userNames);
    }

    public UserEntity getUserByUserName(String userName){
        return userRepo.findByName(userName);
    }
}
