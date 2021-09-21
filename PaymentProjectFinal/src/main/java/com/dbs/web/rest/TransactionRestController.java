package com.dbs.web.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.ResponsePage;
import com.dbs.web.beans.Transaction;
import com.dbs.web.service.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionRestController {

	@Autowired
	private TransactionService transactionService; 

	@GetMapping
	public List<Transaction> gettransactions()
	{
		return this.transactionService.getTransactions();
	}

	@GetMapping("/tid/{tid}")
	public ResponseEntity<Object> findBankById(@PathVariable Integer tid) {

		try { 
			Transaction trans= this.transactionService.getTransactionById(tid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(trans);

		}catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("failure", e.getMessage()));
		}
	}
	@PostMapping("/transfer")
	public ResponseEntity<Object> insertTransaction(@RequestBody Transaction transaction)
	{
		System.out.println("\ninsert");
		System.out.println(transaction);
		try {
//			boolean inserted = this.transactionService.insertTransaction(transaction);
//			if( inserted )
//				return  ResponseEntity
//						.status(HttpStatus.ACCEPTED)
//						.body(transaction);
			//.body("Transaction inserted with id "+id);
			
			//return ResponseEntity.status(HttpStatus.ACCEPTED).body(transaction);
			return this.transactionService.makeTransaction(transaction);
		}
		catch(Exception e) {
			return  ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("Error",e.getMessage()));
		}
//		return  ResponseEntity
//				.status(HttpStatus.NOT_FOUND)
//				.body("Transaction not inserted with id "+transaction);
	}

}
