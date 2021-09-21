package com.dbs.web.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionid;
	private String customerid;
	@ManyToOne
	@JoinColumn(name="currencycode")
	private Currency currencycode;
	@ManyToOne
	@JoinColumn(name="senderbic")
	private Bank senderbic;
	@ManyToOne
	@JoinColumn(name="receiverBIC")
	private Bank receiverBIC;
	private String receiveraccountholdernumber;
	private String receiveraccountholdername;
	@ManyToOne
	@JoinColumn(name="transfertypecode")
	private Transfertypes transfertypecode;
	@ManyToOne
	@JoinColumn(name="messagecode")
	private Message messagecode;
	private Float currencyamount;
	private Float transferfees;
	private Float inramount;
	private LocalDate transferdate;
	
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}


	public Transaction(Integer transactionid, String customerid, Currency currencycode, Bank senderbic, Bank receiverBIC,
			String receiveraccountholdernumber, String receiveraccountholdername, Transfertypes transfertypecode,
			Message messagecode, Float currencyamount, Float transferfees, Float inramount, LocalDate transferdate) {
		super();
		this.transactionid = transactionid;
		this.customerid = customerid;
		this.currencycode = currencycode;
		this.senderbic = senderbic;
		this.receiverBIC = receiverBIC;
		this.receiveraccountholdernumber = receiveraccountholdernumber;
		this.receiveraccountholdername = receiveraccountholdername;
		this.transfertypecode = transfertypecode;
		this.messagecode = messagecode;
		this.currencyamount = currencyamount;
		this.transferfees = transferfees;
		this.inramount = inramount;
		this.transferdate = transferdate;
	}


	public Integer getTransactionid() {
		return transactionid;
	}


	public void setTransactionid(Integer transactionid) {
		this.transactionid = transactionid;
	}


	public String getCustomerid() {
		return customerid;
	}


	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}


	public Currency getCurrencycode() {
		return currencycode;
	}


	public void setCurrencycode(Currency currencycode) {
		this.currencycode = currencycode;
	}


	public Bank getSenderbic() {
		return senderbic;
	}


	public void setSenderbic(Bank senderbic) {
		this.senderbic = senderbic;
	}


	public Bank getReceiverBIC() {
		return receiverBIC;
	}


	public void setReceiverBIC(Bank receiverBIC) {
		this.receiverBIC = receiverBIC;
	}


	public String getReceiveraccountholdernumber() {
		return receiveraccountholdernumber;
	}


	public void setReceiveraccountholdernumber(String receiveraccountholdernumber) {
		this.receiveraccountholdernumber = receiveraccountholdernumber;
	}


	public String getReceiveraccountholdername() {
		return receiveraccountholdername;
	}


	public void setReceiveraccountholdername(String receiveraccountholdername) {
		this.receiveraccountholdername = receiveraccountholdername;
	}


	public Transfertypes getTransfertypecode() {
		return transfertypecode;
	}


	public void setTransfertypecode(Transfertypes transfertypecode) {
		this.transfertypecode = transfertypecode;
	}


	public Message getMessagecode() {
		return messagecode;
	}


	public void setMessagecode(Message messagecode) {
		this.messagecode = messagecode;
	}


	public Float getCurrencyamount() {
		return currencyamount;
	}


	public void setCurrencyamount(Float currencyamount) {
		this.currencyamount = currencyamount;
	}


	public Float getTransferfees() {
		return transferfees;
	}


	public void setTransferfees(Float transferfees) {
		this.transferfees = transferfees;
	}


	public Float getInramount() {
		return inramount;
	}


	public void setInramount(Float inramount) {
		this.inramount = inramount;
	}


	public LocalDate getTransferdate() {
		return transferdate;
	}


	public void setTransferdate(LocalDate transferdate) {
		this.transferdate = transferdate;
	}


	@Override
	public String toString() {
		return "Transaction [transactionid=" + transactionid + ", customerid=" + customerid + ", currencycode="
				+ currencycode + ", senderbic=" + senderbic + ", receiverBIC=" + receiverBIC
				+ ", receiveraccountholdernumber=" + receiveraccountholdernumber + ", receiveraccountholdername="
				+ receiveraccountholdername + ", transfertypecode=" + transfertypecode + ", messagecode=" + messagecode
				+ ", currencyamount=" + currencyamount + ", transferfees=" + transferfees + ", inramount=" + inramount
				+ ", transferdate=" + transferdate + "]";
	}
	
	
	
	
	
	
	

}
