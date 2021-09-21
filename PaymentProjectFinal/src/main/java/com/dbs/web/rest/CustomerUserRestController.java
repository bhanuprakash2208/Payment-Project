package com.dbs.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Customeruser;
import com.dbs.web.beans.Login;
import com.dbs.web.beans.ResponsePage;
import com.dbs.web.service.CustomerUserService;


@RestController
@CrossOrigin
@RequestMapping("/login")
public class CustomerUserRestController {
	
	@Autowired
	private CustomerUserService userService; 

	@PostMapping
	public ResponseEntity<Object> authenticaton(@RequestBody Login userDetails)
	{
		System.out.println(userDetails);
		try {
		Customeruser user = this.userService.getUserByUserName(userDetails.getUsername(), userDetails.getPassword());
		if(user==null || !(userDetails.getUsername().equals(user.getUsername()) && userDetails.getPassword().equals(user.getUserpassword())))
		{
			return  ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
		else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(user.getCustomerid());
		}
		
		}
		catch(Exception e) {
			System.out.println("error: "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("failure", e.getMessage()));
			
		}
		
	}
}
