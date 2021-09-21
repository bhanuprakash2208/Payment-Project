package com.dbs.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dbs.web.service.TransactionService;


@SpringBootApplication
public class PaymentProjectFinalApplication {

	public static void main(String[] args) {
		ApplicationContext context = 
		SpringApplication.run(PaymentProjectFinalApplication.class, args);
		
		
		System.out.println("Application Started");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//04-sep-2021 5:33
//		CustomerService service = context.getBean(CustomerService.class);
//		Customer cust =service.getCustomerById("83020817828620");
//		System.out.println(); 
//		List<Customer> bankAccounts = service.getCustomersByTransferTypes("B");	
//		System.out.println(bankAccounts);
//		List<String> bankAccNos = new ArrayList<String>();
//		for(Customer i:bankAccounts) {
//			bankAccNos.add(i.getCustomerid());
//		}
//		System.out.println(bankAccounts.get(bankAccNos.indexOf("69652133523248")));
//		cust.setClearbalance((float) 56000);
//		System.out.println(service.updateCustomer(cust));
//		
//		System.out.println("Number of records in Customer Table: "+service.getCount());
//		System.out.println("Getting Customer By Id: "+service.getCustomerById("69652133523248"));
//		
//		BankService bank = context.getBean(BankService.class);
//		
//		System.out.println("Number of records in bank: "+bank.getCount());
//		System.out.println("Getting bank by id: "+bank.getBankById("ABBLINBBXXX"));
//
//		MessageService msg = context.getBean(MessageService.class);
//		
//		System.out.println("Number of records in msgs: "+msg.getCount());
//		System.out.println("Getting msg code by id: "+msg.getMessageById("CHQB"));
//		System.out.println();
//		for(Message m : msg.getMessages())
//			System.out.println(m);
//		System.out.println();
//
//		TransfertypesService ttp = context.getBean(TransfertypesService.class);
//		
//		System.out.println("Number of records in Transfer: "+ttp.getCount());
//		System.out.println("Getting Transfer Type by id: "+ttp.getTransfertypeById("B"));
//		System.out.println();
//		for(Transfertypes t : ttp.getTransfertypes())
//			System.out.println(t);
//		System.out.println();
//		
//		CurrencyService cs = context.getBean(CurrencyService.class);
//		
//		System.out.println("Number of records in bank: "+cs.getCount());
//		System.out.println("Getting Currency by id: "+cs.getCurrencyById("EUR"));
//		System.out.println();
//		for(Currency c : cs.getCurrencies())
//			System.out.println(c);
//		System.out.println();
		
		TransactionService ts = context.getBean(TransactionService.class);
		System.out.println(ts.getTransactionsForCustomerId("83020817828620"));
//		System.out.println("Number of records in Transaction: "+ts.getCount());
//		System.out.println("Getting Transaction by id: "+ts.getTransactionById(1));
//		System.out.println();
//		for(Transaction t : ts.getTransactions())
//			System.out.println(t);
//		System.out.println();
		
//		CustomerUserService us = context.getBean(CustomerUserService.class);
//		System.out.println(us.getUserByUserName("Bhanusai","Bhanusai"));
//		
//		System.out.println("Number of records in Transaction: "+us.getCount());
//		System.out.println("Getting Transaction by id: "+us.getUserById(2));
//		System.out.println();
//		for(Customeruser u : us.getUsers())
//			System.out.println(u);
//		System.out.println();
		
	}

}
