package com.hanu.services;


import org.springframework.stereotype.Service;

import com.hanu.model.Account;

@Service
public interface AccountService {
	public Account getAccount(int accno);
	public String deleteById(int accno);
}
