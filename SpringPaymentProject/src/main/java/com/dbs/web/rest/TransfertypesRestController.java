package com.dbs.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Transfertypes;
import com.dbs.web.service.TransfertypesService;

@RestController
@CrossOrigin
public class TransfertypesRestController {
	
	@Autowired
	private TransfertypesService transfertypesService;
	
	@GetMapping("/transfertypes")
	public List<Transfertypes> findTransfertypes(){
		
		return this.transfertypesService.getTransfertypes();
	}

}
