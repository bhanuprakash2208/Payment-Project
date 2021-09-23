package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Logger {
	@Id
	private Integer loggerid;
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customerid;
	@ManyToOne
	@JoinColumn(name="userid")
	private Customeruser userid;
	private Integer employeeid;
	private String screenname;
	private String action;
	private String ipaddress;
	
	public Logger() {
		// TODO Auto-generated constructor stub
	}
	
	public Logger(Integer loggerid, Customer customerid, Customeruser userid, Integer employeeid, String screenname,
			String action, String ipaddress) {
		super();
		this.loggerid = loggerid;
		this.customerid = customerid;
		this.userid = userid;
		this.employeeid = employeeid;
		this.screenname = screenname;
		this.action = action;
		this.ipaddress = ipaddress;
	}
	
	
	public Integer getLoggerid() {
		return loggerid;
	}
	public void setLoggerid(Integer loggerid) {
		this.loggerid = loggerid;
	}
	public Customer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Customer customerid) {
		this.customerid = customerid;
	}
	public Customeruser getUserid() {
		return userid;
	}
	public void setUserid(Customeruser userid) {
		this.userid = userid;
	}
	public Integer getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}
	public String getScreenname() {
		return screenname;
	}
	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
	@Override
	public String toString() {
		return "Logger [loggerid=" + loggerid + ", customerid=" + customerid + ", userid=" + userid + ", employeeid="
				+ employeeid + ", screenname=" + screenname + ", action=" + action + ", ipaddress=" + ipaddress + "]";
	}
	
	
	

}
