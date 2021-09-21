package com.dbs.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.ResponsePage;
import com.dbs.web.beans.SdnResponse;
import com.dbs.web.service.ReceiverNameService;

@RestController
@CrossOrigin
@RequestMapping("/receivername")
public class ReceiverNameRestController {

	@Autowired
	private ReceiverNameService receiverNameService;

	@GetMapping("/{ReceiverName}")
	public ResponseEntity<Object> searchNameInSdn(@PathVariable String ReceiverName) {
		boolean result = false;
		try { 
			//Bank bank= this.receiverNameService.getBankById(name);
			
			result = this.receiverNameService.searchNameInFile(ReceiverName);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SdnResponse(ReceiverName,result));
			

		}catch (Exception e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("failure", e.getMessage()));
		}
		
	}

}
