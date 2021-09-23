package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity

public class Customeruser {
	@Id
	private Integer userid;
	private String username;
	@OneToOne
	@JoinColumn(name="customerid")
	private Customer customerid;
	private String userpassword;
	
	public Customeruser() {
		// TODO Auto-generated constructor stub
	}
	
	public Customeruser(Integer userid, String username, Customer customerid, String userpassword) {
		super();
		this.userid = userid;
		this.username = username;
		this.customerid = customerid;
		this.userpassword = userpassword;
	}
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Customer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Customer customerid) {
		this.customerid = customerid;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	
	
	@Override
	public String toString() {
		return "Customeruser [userid=" + userid + ", username=" + username + ", customerid=" + customerid
				+ ", userpassword=" + userpassword + "]";
	}
	
	


}
