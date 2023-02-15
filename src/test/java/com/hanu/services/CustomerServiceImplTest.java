package com.hanu.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.hanu.model.Account;
import com.hanu.model.Customer;
import com.hanu.repository.AccountRepository;
import com.hanu.repository.CustomerRepository;
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
	@Mock
	CustomerRepository custrepo;
	@Mock
	AccountRepository accrepo;
	@Autowired
	@InjectMocks
	CustomerServiceImpl custservice;
	
	
	@Test
	void addCustomerTest() {
		Customer cust = new Customer(40097984, "Hanumanth", new Account(131, "savings", 300.0));
		when(custrepo.save(any())).thenReturn(cust);
		Customer addCustomer = custservice.addCustomer(cust);
		assertEquals(cust, addCustomer);
		
		
	}
	@Test
	void getAllCustomerTest()
	{
		Account acc1 = new Account(131, "savings", 300.0);
    	Account acc2 = new Account(145, "savings", 300.0);
    	Customer cust1 = new Customer(40097984, "Hanumanth",acc1 );
    	Customer cust2 = new Customer(40097985, "Ram", acc2);
    	custrepo.save(cust1);
    	custrepo.save(cust2);
    	List<Customer> list = new ArrayList<Customer>();
    	list.add(cust1);
    	list.add(cust2);
    	when(custrepo.findAll()).thenReturn(list);
    	assertEquals(list, custservice.displayAllCustomer());
	}
	@Test
	void getByIdTest()
	{
		Account acc1 = new Account(131, "savings", 300.0);
   
    	Customer cust1 = new Customer(40097984, "Hanumanth",acc1 );
    	
    	custrepo.save(cust1);
    	System.out.println(cust1);
    	//Optional<Customer> op = custrepo.findById(cust1.getId());
    	when(custrepo.findById(cust1.getId())).thenReturn(Optional.ofNullable(cust1));
   	//System.out.println(op);
    	Customer customer = custservice.getCustomer(cust1.getId());

    	assertEquals(cust1,customer );

	}
	@Test
	void deleteByIdTest()
	{
		Account acc1 = new Account(131, "savings", 300.0);
   
    	Customer cust1 = new Customer(40097984, "Hanumanth",acc1 );
    	
    	//custrepo.save(cust1);
    	System.out.println(cust1);
    	//String s = "Customer deleted!";
    	when(custrepo.findById(cust1.getId())).thenReturn(Optional.ofNullable(cust1));
    	System.out.println(custservice.deleteCustomer(cust1.getId()));
    	verify(custrepo,times(1)).deleteById(cust1.getId());;
    	//assertThat(custservice.deleteCustomer(cust1.getId())).isEqualTo(s);
    	
	}
	@Test
	void deleteByIdFalseTest()
	{
		Account acc1 = new Account(131, "savings", 300.0);
   
    	Customer cust1 = new Customer(40097984, "Hanumanth",acc1 );
    	
    	//custrepo.save(cust1);
    	System.out.println(cust1);
    	//String s = "Customer deleted!";
    	when(custrepo.findById(132)).thenReturn(Optional.ofNullable(null));
    	assertEquals(null, custservice.deleteCustomer(132));
    	//System.out.println(custservice.deleteCustomer(cust1.getId()));
    	//verify(custrepo,times(1)).deleteById(cust1.getId());;
    	//assertThat(custservice.deleteCustomer(cust1.getId())).isEqualTo(s);
    	
	}
	@Test
	void deleteAllTest()
	{
		Account acc1 = new Account(131, "savings", 300.0);
    	Account acc2 = new Account(145, "savings", 300.0);
    	Customer cust1 = new Customer(40097984, "Hanumanth",acc1 );
    	Customer cust2 = new Customer(40097985, "Ram", acc2);
    	custrepo.save(cust1);
    	custrepo.save(cust2);
    	System.out.println(cust1);
    	String s = "All Customer data deleted!!";
    	List<Customer> list = new ArrayList<Customer>();
    	list.add(cust1);
    	list.add(cust2);
    	when(custrepo.findAll()).thenReturn(list);
    	System.out.println(custservice.deleteAll());
    	//when(custrepo.findAll().thenReturn(Optional.ofNullable(cust1));
    	assertThat(custservice.deleteAll()).isEqualTo(s);
	}
	@Test
	void updateTest()
	{
		Account acc1 = new Account(131, "savings", 300.0);
    	Account acc2 = new Account(131, "savings", 400.0);
    	Customer cust1 = new Customer(40097984, "Hanumanth",acc1 );
    	Customer cust2 = new Customer(40097984, "Ram", acc2);
    	custrepo.save(cust1);
    	custrepo.save(cust2);
    	cust1.setAccount(cust2.getAccount());
    	cust1.setName(cust2.getName());
    	when(custrepo.findById(cust1.getId())).thenReturn((Optional.ofNullable(cust1)));
		System.out.println(cust1);
		System.out.println(cust2);
		//Customer updateCustomer =
		custservice.updateCustomer(cust2,cust1.getId());
		System.out.println(cust1.getId());
		assertEquals("Ram",cust1.getName());
	
	}
	@Test
	void transferFundsTest()
	{
		Account cust1 = new Account(131,"Savings",500.0);
		Account cust2 = new Account(132,"Savings",500.0);
    	double amt = 100.0;
    	when(accrepo.findById(cust1.getId())).thenReturn(Optional.ofNullable(cust1));
    	when(accrepo.findById(cust2.getId())).thenReturn(Optional.ofNullable(cust2));
    	System.out.println(cust1.getId());
    	System.out.println(cust2.getId());
    	custservice.transferFunds(cust1.getId(),cust2.getId(), amt);
    	System.out.println(cust1);
    	System.out.println(cust2);
    	assertEquals(600,cust2.getBalance() );
    	
	}
	
	
}
