package com.dbs.web.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Customer;
import com.dbs.web.beans.ResponsePage;
import com.dbs.web.service.CustomerService;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping
	public List<Customer> findCustomers(){
		
			return this.customerService.getCustomers();			
		
	}
	@GetMapping("/{cid}")
	public ResponseEntity<Object> findCustomerById(@PathVariable String cid){
		
		try { 
			Customer cus= this.customerService.getCustomerById(cid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(cus);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("failure", e.getMessage()));
		}
		
			
	}
	

}
