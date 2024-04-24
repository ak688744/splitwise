package com.splitwise.demo.repository;

import com.splitwise.demo.entities.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends CrudRepository<GroupEntity, Integer> {
    GroupEntity findByName(String group);
}
