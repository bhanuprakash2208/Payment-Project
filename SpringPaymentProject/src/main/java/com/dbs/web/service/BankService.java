package com.dbs.web.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Bank;
import com.dbs.web.repository.BankRepository;

@Service
public class BankService {
	
	@Autowired
	private BankRepository repo;
	
	public long getCount()
	{
		return this.repo.count();
	}
	public boolean insertBank(Bank bank)
	{
		if(this.repo.existsById(bank.getBic()))
			return false;
		try {
			this.repo.save(bank);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Bank object is empty");
		}
		return true;
	}
	public boolean updateBank(Bank bank)
	{
		if(! this.repo.existsById(bank.getBic()))
			return false;
		try {
			this.repo.save(bank);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Bank object is empty");
		}
		return true;
	}
	public boolean deleteBank(String bid)
	{
		if(! this.repo.existsById(bid))
			return false;
		try {
			this.repo.deleteById(bid);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Bank object is empty");
		}
		return true;
	}
	public Bank getBankById(String bid)
	{
			return this.repo.findById(bid)
				.orElseThrow(()->  new EntityNotFoundException("Bank does not exist with id "+bid));
	}

}
