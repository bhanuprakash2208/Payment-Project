package com.dbs.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Customeruser;
import com.dbs.web.repository.CustomeruserRepository;

@Service
public class CustomerUserService {
	
	@Autowired
	private CustomeruserRepository repo;
	
	public long getCount()
	{
		return this.repo.count();
	}
	
	public boolean insertUser(Customeruser user)
	{
		if(this.repo.existsById(user.getUserid()))
			return false;
		try {
			this.repo.save(user);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("User object is empty");
		}
		return true;
	}
	public boolean deleteUser(int uid)
	{
		if(! this.repo.existsById(uid))
			return false;
		try {
			this.repo.deleteById(uid);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("User object is empty");
		}
		return true;
	}
	public Customeruser getUserById(int uid)
	{
			return this.repo.findById(uid)
				.orElseThrow(()->  new EntityNotFoundException("User does not exist with id "+uid));
	}
	
	public List<Customeruser> getUsers()
	{
		List<Customeruser> users = new ArrayList<Customeruser>();
		this.repo.findAll().forEach(user->users.add(user));
		return users;
	}
	
	
	public Customeruser getUserByUserName(String username,String password) {
		
		return this.repo.findByUsernameAndUserpassword(username,password);
		
	}

}
