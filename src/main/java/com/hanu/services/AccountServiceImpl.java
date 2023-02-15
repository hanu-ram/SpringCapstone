package com.hanu.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanu.model.Account;
import com.hanu.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountRepository accramp;
	@Override
	public Account getAccount(int accno) {
		Optional<Account> acc = accramp.findById(accno);
		if(acc.isPresent())
		{
			return (Account) acc.get();		
		}
		return null; 
	}
	@Override 
	public String deleteById(int accno) {

	Optional<Account>acnt1=accramp.findById(accno);
	if(acnt1.isPresent()) {
	accramp.delete((Account)acnt1.get());
	return "Account Deleted Successfully!!"+ " " +accno;
	}
	return null;
	}

}
