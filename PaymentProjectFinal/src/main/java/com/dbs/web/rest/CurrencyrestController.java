package com.dbs.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Currency;
import com.dbs.web.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyrestController {

	@Autowired
	private CurrencyService currencyService;
	
	@GetMapping
	public List<Currency> findCurrencies(){
		
		return this.currencyService.getCurrencies();
		
	}
}
