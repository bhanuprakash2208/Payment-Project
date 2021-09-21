package com.dbs.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Customer;
import com.dbs.web.repository.CustomerRepository;

@Service
public class CustomerService {
	//69652133523248
	
	@Autowired
	private CustomerRepository repo;
	
	public long getCount()
	{
		return this.repo.count();
	}
	public boolean insertCustomer(Customer customer)
	{
		if(this.repo.existsById(customer.getCustomerid()))
			return false;
		try {
			this.repo.save(customer);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Customer is empty");
		}
		return true;
	}
	public boolean updateCustomer(Customer customer)
	{
		if(! this.repo.existsById(customer.getCustomerid()))
			return false;
		try {
			this.repo.save(customer);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("customer is empty");
		}
		return true;
	}
	public boolean deleteCustomer(String cid)
	{
		if(! this.repo.existsById(cid))
			return false;
		try {
			this.repo.deleteById(cid);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Customer is empty");
		}
		return true;
	}
	public Customer getCustomerById(String cid)
	{
			return this.repo.findById(cid)
				.orElseThrow(()->  new EntityNotFoundException("Customer does not exist with id "+cid));
	}
	
	public List<Customer> getCustomers()
	{
		List<Customer> customers = new ArrayList<Customer>();
		this.repo.findAll().forEach(cus->customers.add(cus));
		return customers;
	}
	
	public List<Customer> getCustomersByTransferTypes(String transferType){
		return this.repo.findAllByCustomertype(transferType);
	}
}
