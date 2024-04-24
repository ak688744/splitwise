package com.splitwise.demo.repository;

import com.splitwise.demo.entities.GroupEntity;
import com.splitwise.demo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction,Integer> {

    List<Transaction> findByPayorAndPayee(String payor, String payee);

    List<Transaction> findByPayor(String payor);

    List<Transaction> findByPayee(String payor);


}
