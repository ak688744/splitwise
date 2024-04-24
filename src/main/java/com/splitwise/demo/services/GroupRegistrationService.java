package com.splitwise.demo.services;

import com.splitwise.demo.dao.GroupDao;
import com.splitwise.demo.entities.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupRegistrationService {
    @Autowired
    private GroupDao groupDao;


    public GroupEntity createGroup(String name, List<String> userNames) throws Exception {
        return groupDao.saveGroup(name, userNames);
    }

    public GroupEntity addPeopleInGroup(String name, List<String> userNames) throws Exception {
        return groupDao.addUserIngroup(name, userNames);
    }
}
