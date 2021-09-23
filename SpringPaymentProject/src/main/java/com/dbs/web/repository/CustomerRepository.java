package com.dbs.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{
	
	public List<Customer> findAllByCustomertype(String ctype);

}
