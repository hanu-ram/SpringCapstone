package com.hanu.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.hanu.exceptionhandlers.NoDataException;
import com.hanu.model.Account;
import com.hanu.model.Customer;
import com.hanu.services.CustomerService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CustomerControllerTestMock {
	@Mock
	CustomerService custserv;
	
	@InjectMocks
	CustomerController controller;
	
	Customer cust;
	List<Customer> listcust;
	
	@Test
	@Order(1)
	public void getAllCustomerTest() throws NoDataException
	{
		listcust = new ArrayList<Customer>();
		listcust.add(new Customer(40097984, "Hanumanth", new Account(131, "savings", 300.0)));
		listcust.add(new Customer(40097985, "Ram", new Account(132, "savings", 400.0)));
		when(custserv.displayAllCustomer()).thenReturn(listcust);
		ResponseEntity<?> res = controller.getAllCustomer();
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(listcust, res.getBody());
	}

}
