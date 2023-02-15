package com.hanu.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.hanu.model.Customer;

@Service
public interface CustomerService {
	public Customer addCustomer(Customer e);
	public Customer updateCustomer(Customer e, int id);
	public String deleteCustomer(int eid);
	public List<Customer> displayAllCustomer();
	public Customer getCustomer(int eid); 
	public String transferFunds(int from, int to, double amt);
	public String deleteAll();
	public int updateCustomerName(String name, int id);


}
