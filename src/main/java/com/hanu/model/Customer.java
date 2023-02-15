package com.hanu.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
@Entity
@Builder
@Table(name = "customerdata")
@SequenceGenerator(name = "seqgen1",sequenceName = "seqgen1",initialValue = 40097984)
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seqgen1")
	private int id; 
	private String name;
//	private int balance;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account")
	private Account account;
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int id, String name, Account account) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", account=" + account + "]";
	}
	

}
