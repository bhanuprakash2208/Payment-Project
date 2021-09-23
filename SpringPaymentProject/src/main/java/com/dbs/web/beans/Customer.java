package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Customer {
	
	@Id
	private String customerid;
	private String accountholdername;
	private Integer overdraftflag;
	private Float clearbalance;
	//private String customeraddress;
	//private String customercity;
	private String customertype;
	//private String username;
	//private String userpassword;
	//@ManyToOne
	//@JoinColumn(name="bic")
	//private Bank BIC;
	
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String customerid, String accountholdername, Integer overdraftflag, Float clearbalance,
			String customertype) {
		super();
		this.customerid = customerid;
		this.accountholdername = accountholdername;
		this.overdraftflag = overdraftflag;
		this.clearbalance = clearbalance;
		this.customertype = customertype;
	}


	

	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getAccountholdername() {
		return accountholdername;
	}
	public void setAccountholdername(String accountholdername) {
		this.accountholdername = accountholdername;
	}
	public Integer getOverdraftflag() {
		return overdraftflag;
	}
	public void setOverdraftflag(Integer overdraftflag) {
		this.overdraftflag = overdraftflag;
	}
	public Float getClearbalance() {
		return clearbalance;
	}
	public void setClearbalance(Float clearbalance) {
		this.clearbalance = clearbalance;
	}
	public String getCustomertype() {
		return customertype;
	}
	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", accountholdername=" + accountholdername + ", overdraftflag="
				+ overdraftflag + ", clearbalance=" + clearbalance + ", customertype=" + customertype + "]";
	}
	

	
	
	
	
	
	
}
