package com.hanu.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
@Entity
@Builder
@Table(name="accountdata")
@SequenceGenerator(name="seqgen",sequenceName = "seqgen",initialValue = 2073425*1034)
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seqgen")
	private int id;
	private String accounttype;
	private double balance;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int id, String accounttype, double balance) {
		super();
		this.id = id;
		this.accounttype = accounttype;
		this.balance = balance;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", accounttype=" + accounttype + ", balance=" + balance + "]";
	}
	
}
