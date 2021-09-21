package com.dbs.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dbs.web.beans.Bank;
import com.dbs.web.beans.Customer;
import com.dbs.web.beans.ResponsePage;
import com.dbs.web.beans.Transaction;
import com.dbs.web.beans.Transfertypes;
import com.dbs.web.repository.TransactionRepository;


@Service
@CrossOrigin
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository repo; 

	@Autowired
	private CustomerService customerService; 

	public long getCount()
	{
		return this.repo.count();
	}
	public boolean insertTransaction(Transaction trans)
	{
		try {
			this.repo.save(trans);

		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Transaction object is empty");
		}
		return true;

	}
	public boolean updateTransaction(Transaction trans)
	{
		if(! this.repo.existsById(trans.getTransactionid()))
			return false;
		try {
			this.repo.save(trans);

		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Transaction object is empty");
		}
		return true;
	}
	public boolean deleteTransaction(Integer transid)
	{
		if(! this.repo.existsById(transid))
			return false;
		try {
			this.repo.deleteById(transid);

		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Transaction object is empty");
		}
		return true;
	}
	public Transaction getTransactionById(Integer transid)
	{
		return this.repo.findById(transid)
				.orElseThrow(()->  new EntityNotFoundException("Transaction does not exist with id: "+transid));
	}
	
	//It returns all transactions in Database
	public List<Transaction> getTransactions()
	{
		List<Transaction> transactions = new ArrayList<Transaction>();
		this.repo.findAll().forEach(trans->transactions.add(trans));
		return transactions;
	}
	
	public List<Transaction> getTransactionsForCustomerId(String custId){
		
		return this.repo.findAllByCustomeridOrderByTransactionidDesc(custId);
		
 	}

	//Bussiness Logic


	public ResponseEntity<Object> makeTransaction(Transaction trans) {
		try {
			List<Customer> bankAccounts = customerService.getCustomersByTransferTypes("B");
			List<String> bankAccNos = new ArrayList<String>();
			String customerId = trans.getCustomerid();
			Float InrAmount = trans.getInramount();
			Bank bank = trans.getReceiverBIC();
			String receiverBIC = bank.getBic();
			String receiverAccNo = trans.getReceiveraccountholdernumber();
			int index = 0;
			Customer customer = this.customerService.getCustomerById(customerId);
			Customer recipientCustomer;
			String customerType = customer.getCustomertype();
			int overDraft = customer.getOverdraftflag();
			Float clearBalance = customer.getClearbalance();
			Transfertypes tt = trans.getTransfertypecode();
			String ttc = tt.getTransfertypecode();
			Float transferFee = (float) Math.round(InrAmount*0.25) / 100;
			Float amount = InrAmount + transferFee;

		
			for(Customer i:bankAccounts) {
				bankAccNos.add(i.getCustomerid());
			}
			index = bankAccNos.indexOf(receiverAccNo);
			System.out.println(bankAccNos);
			if( customerType.equals("B") && ttc.equals("B") && receiverBIC.toUpperCase().startsWith("HDFC"))
			{
				if(overDraft==1 ||(clearBalance - amount) > 0 ) {

					if(index!=-1) 
					{
						recipientCustomer = bankAccounts.get(index);
						customer.setClearbalance((float) (clearBalance - amount));
						recipientCustomer.setClearbalance(recipientCustomer.getClearbalance() + InrAmount);
						boolean debited = this.customerService.updateCustomer(customer);
						boolean credited = this.customerService.updateCustomer(recipientCustomer);
						boolean inserted = this.insertTransaction(trans);
						if( debited && credited && inserted )
							return  ResponseEntity
									.status(HttpStatus.OK)
									.body(trans);
						//success
						else {
							return  ResponseEntity
									.status(HttpStatus.NOT_FOUND)
									.body(new ResponsePage("Error","Unable to Process Transaction."));
						}
					}
					else {
						return  ResponseEntity
								.status(HttpStatus.NOT_FOUND)
								.body(new ResponsePage("Error","Receiver Account Number is Invalid."));
						//return false; // no Account exists with Acc No;
					}
				}
				else {
					return  ResponseEntity
							.status(HttpStatus.NOT_FOUND)
							.body(new ResponsePage("Error","Insufficient Account Balance."));
					//return false; // insufficient balance;
				}

			}
			else if(customerType.equals("B") && ttc.equals("B") && !receiverBIC.toUpperCase().startsWith("HDFC")){
				return  ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ResponsePage("Error","Wrong Receiver BIC."));
				//return false; // Wrong Receiver BIC
			}
			else if(customerType.equals("B") && !ttc .equals("B") && receiverBIC.toUpperCase().startsWith("HDFC") ) {
				return  ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ResponsePage("Error","Selected Wrong Transfer type."));
				//return false; // Selected Wrong Transfer type
			}
			else if(customerType.equals("B") && !ttc .equals("B") && !receiverBIC.toUpperCase().startsWith("HDFC") ) {
				return  ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ResponsePage("Error","Selected Wrong Transfer type and Wrong BIC code."));
				//return false; // Selected Wrong Transfer type and Wrong BIC code
			}
			//			else if(senderName.toUpperCase().contains("HDFC BANK") && !receiverBIC.toUpperCase().startsWith("HDFC") && !(ttc == "B")) {
			//				return false; //selected Wrong Transfer Type and Wrong Receiver BIC
			//			}
			else if(customerType.equals("C")  && ttc.equals("C")) {
				if(overDraft==1 ||(clearBalance - amount) > 0 ) {
					customer.setClearbalance((float) (clearBalance - amount));
					boolean debited = this.customerService.updateCustomer(customer);
					boolean inserted = this.insertTransaction(trans);
					if(debited && inserted)
						return  ResponseEntity
								.status(HttpStatus.OK)
								.body(trans);
					//return true; //success
					else {
						return  ResponseEntity
								.status(HttpStatus.NOT_FOUND)
								.body(new ResponsePage("Error","Unable to Process Transaction."));
					}

				}
				else {
					return  ResponseEntity
							.status(HttpStatus.NOT_FOUND)
							.body(new ResponsePage("Error","Insufficient Account Balance."));
					//return false; //Insufficient Balance;
				}
			}
			else if(customerType.equals("C")  && !ttc.equals("C")) {
				return  ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ResponsePage("Error","Selected Wrong Transfer Type"));
				//return false; // Selected Wrong Transfer Type;
			}
			else {
				return  ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ResponsePage("Error","Something Eent Wrong"));
				//return false; //Invalid Transaction or something went Wrong;
			}
		}
		catch(Exception e) {
			return  ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("Error",e.getMessage())); 
			//caught Exception and something went wrong;
		}
	}
}
