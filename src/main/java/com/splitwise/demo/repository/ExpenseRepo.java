package com.splitwise.demo.repository;

import com.splitwise.demo.entities.CreditExpenseEntity;
import com.splitwise.demo.entities.GroupEntity;
import com.splitwise.demo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends CrudRepository<CreditExpenseEntity, Integer> {
    List<CreditExpenseEntity> findAllByGroupEntity(GroupEntity groupEntity);
}
