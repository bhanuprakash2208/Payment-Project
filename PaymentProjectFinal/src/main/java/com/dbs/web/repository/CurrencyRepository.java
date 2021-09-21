package com.dbs.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, String>{

}
