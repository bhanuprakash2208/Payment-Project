package com.dbs.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

	List<Transaction> findAllByCustomeridOrderByTransactionidDesc(String cid);
}
