package com.dbs.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Currency;
import com.dbs.web.beans.Message;
import com.dbs.web.repository.CurrencyRepository;

@Service
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository repo;
	
	
	public long getCount()
	{
		return this.repo.count();
	}
	public boolean insertCurrency(Currency cur)
	{
		if(this.repo.existsById(cur.getCurrencycode()))
			return false;
		try {
			this.repo.save(cur);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Currency object is empty");
		}
		return true;
	}
	public boolean updateCurrency(Currency cur)
	{
		if(! this.repo.existsById(cur.getCurrencycode()))
			return false;
		try {
			this.repo.save(cur);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Currency object is empty");
		}
		return true;
	}
	public boolean deleteCurrency(String cc)
	{
		if(! this.repo.existsById(cc))
			return false;
		try {
			this.repo.deleteById(cc);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Currency object is empty");
		}
		return true;
	}
	public Currency getCurrencyById(String cc)
	{
			return this.repo.findById(cc)
				.orElseThrow(()->  new EntityNotFoundException("Currency does not exist with id: "+cc));
	}
	
	public List<Currency> getCurrencies()
	{
		List<Currency> currencies = new ArrayList<Currency>();
		this.repo.findAll().forEach(cc->currencies.add(cc));
		return currencies;
	}

}
