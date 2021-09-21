package com.dbs.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Customeruser;

public interface CustomeruserRepository extends CrudRepository<Customeruser, Integer>{

	public Customeruser findByUsernameAndUserpassword(String uname,String password);
}
