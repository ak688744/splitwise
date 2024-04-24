package com.splitwise.demo.dao;

import com.splitwise.demo.entities.GroupEntity;
import com.splitwise.demo.entities.UserEntity;
import com.splitwise.demo.repository.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class GroupDao {

    @Autowired
    private UserDao userDao;
    @Autowired
    private GroupRepo groupRepo;
    public GroupEntity saveGroup(String groupName, List<String> userName) throws Exception {
        List<UserEntity> userEntity = userDao.getUsersByUserNameIn(userName);
        if(CollectionUtils.isEmpty(userEntity)){
            throw new Exception("Users don't exist");
        }
        GroupEntity groupEntity = groupRepo.findByName(groupName);
        if(Objects.nonNull(groupEntity)){
            throw new Exception("Group already exist");
        }
        GroupEntity newGroupEntity = new GroupEntity(groupName, userEntity);
        return groupRepo.save(newGroupEntity);
    }

    public GroupEntity addUserIngroup(String groupName, List<String> userName) throws Exception {
        List<UserEntity> userEntityList = userDao.getUsersByUserNameIn(userName);
        if(CollectionUtils.isEmpty(userEntityList)){
            throw new Exception("Users don't exist");
        }
        GroupEntity groupEntity = groupRepo.findByName(groupName);
        if(Objects.isNull(groupEntity)){
            throw new Exception("Group does not exist");
        }
        for(UserEntity userEntity : userEntityList){
            if(validateIfUserExistInGroup(userEntity.getName(), groupEntity)){
                throw new Exception("User already present in Group "+ userEntity.getName());
            }
        }
        groupEntity.getUserEntityList().addAll(userEntityList);
        return groupRepo.save(groupEntity);
    }

    public GroupEntity getGroup(String name){
        return groupRepo.findByName(name);
    }

    public boolean validateIfUserExistInGroup(String userName, GroupEntity groupEntity){
        for(UserEntity userEntity : groupEntity.getUserEntityList()){
            if(userEntity.getName().equals(userName)){
                return true;
            }
        }
        return false;
    }
}
