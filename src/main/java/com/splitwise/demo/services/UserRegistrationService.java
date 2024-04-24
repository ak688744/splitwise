package com.splitwise.demo.services;

import com.splitwise.demo.dao.UserDao;
import com.splitwise.demo.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    private UserDao userDao;
    public UserEntity addUser(String userName) throws Exception {
        return userDao.saveUser(userName);
    }

}
