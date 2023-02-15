package com.hanu.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanu.model.Account;
import com.hanu.model.Customer;
import com.hanu.services.AccountService;
import com.hanu.services.CustomerService;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(controllers = CustomerController.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
@ContextConfiguration
public class CustomerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CustomerService service;

	@MockBean
	AccountService accservice;
	@Autowired
	ObjectMapper mapper;

	@org.junit.jupiter.api.Test
	public void addCustomer() throws Exception {
		Customer cust = new Customer(40097984, "Hanumanth", new Account(131, "savings", 300.0));
		when(service.addCustomer(any(Customer.class))).thenReturn(((cust)));
		mockMvc.perform(MockMvcRequestBuilders.post("/customer/add").accept(MediaType.ALL)
				.content(mapper.writeValueAsString(cust)).contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().is(201))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(cust)));

	}

	@Test
	public void updateCustomerTest() throws JsonProcessingException, Exception {
		Customer cust = new Customer(40097984, "Hanumanth", new Account(131, "savings", 300.0));

		Customer custupdate = new Customer(40097984, "Ram", new Account(131, "Current", 5000.0));
//		Customer cust = new Customer();
//		Customer cust1 = new Customer();
//		Account acc = new Account();
//		Account acc1 = new Account();
//		acc.setBalance(3000.0);
//		acc.setAccounttype("Savings");
//		cust.setAccount(acc);
//		cust.setName("Hanu");
//		acc1.setBalance(500.0);
//		acc1.setAccounttype("Current");
//		cust1.setAccount(acc1);
//		cust1.setName("Hanumant");

		when(service.updateCustomer(any(Customer.class), any(Integer.class))).thenReturn((custupdate));

		mockMvc.perform(MockMvcRequestBuilders.put("/customer/update/" + cust.getId()).accept(MediaType.ALL)
				.content(mapper.writeValueAsString(custupdate)).contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(custupdate)));

	}

	@Test
	public void deleteCustomerTest() throws JsonProcessingException, Exception {
		Customer cust = new Customer(40097984, "Hanumanth", new Account(131, "savings", 300.0));

		String del = "Account Deleted Successfully!!";

		when(service.deleteCustomer(any(Integer.class))).thenReturn("Account Deleted Successfully!!");

		mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete/" + cust.getId()).accept(MediaType.ALL)
				.contentType("application/json")).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(del));

	}

	@Test
	public void deleteAll() throws JsonProcessingException, Exception {
		Customer cust = new Customer(40097984, "Hanumanth", new Account(131, "savings", 300.0));

		String del = "All Customer data deleted!!";

		when(service.deleteAll()).thenReturn("All Customer data deleted!!");

		mockMvc.perform(MockMvcRequestBuilders.delete("/customer/deleteall/").accept(MediaType.ALL)
				.content(mapper.writeValueAsString(cust))
				.contentType("application/json"))
		.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(del));

	}

	@Test
	public void displayAllTest() throws Exception {
		Customer cust = new Customer(40097984, "Hanumanth", new Account(131, "savings", 300.0));
		List<Customer> customer = new ArrayList<>();
		customer.add(cust);

		when(service.displayAllCustomer()).thenReturn(customer);

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/display").accept(MediaType.ALL)
				.content(mapper.writeValueAsBytes(customer)).contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(customer)));
	}

	@Test
	public void getByIdTest() throws Exception {
		Customer cust = new Customer(40097984, "Hanumanth", new Account(131, "savings", 300.0));
	
		when(service.getCustomer(any(Integer.class))).thenReturn(cust);	

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/getbyid/"+cust.getId()).accept(MediaType.ALL)
				.content(mapper.writeValueAsBytes(cust)).contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(cust)));
	}
	@Test
	public void getAccountTest() throws Exception {
		Account acc = new Account(13242,"Savings",300.00);
	
		when(accservice.getAccount(any(Integer.class))).thenReturn(acc);	

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/getaccount/"+acc.getId()).accept(MediaType.ALL)
				.content(mapper.writeValueAsBytes(acc)).contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(acc)));
	}


    @Test
    public void transferfundsTest() throws JsonProcessingException, Exception {
    	Account acc1 = new Account(131, "savings", 300.0);
    	Account acc2 = new Account(145, "savings", 300.0);
    	Customer cust1 = new Customer(40097984, "Hanumanth",acc1 );
    	Customer cust2 = new Customer(40097985, "Ram", acc2);
    	double money = 100.0;
    	cust2.getAccount().setBalance(acc2.getBalance()+money);;
    	
        when(service.transferFunds(acc1.getId(),acc2.getId() ,money)).thenReturn(mapper.writeValueAsString(cust2));
 
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/transfer/"+acc1.getId()+"/"+acc2.getId()+"/"+money)
                .accept(MediaType.ALL).content(mapper.writeValueAsBytes(cust1)).content(mapper.writeValueAsString(cust2))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(cust2)));
        
    }

    @Test
    public void deleteAccountByIdTest() throws JsonProcessingException, Exception {
       
        String del="Account Deleted Successfully!";
        Account acc1 = new Account(131, "savings", 300.0);  
        int accno = acc1.getId();
        when(accservice.deleteById(accno)).thenReturn("Account Deleted Successfully!!");

        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/deleteaccount/" + accno).accept(MediaType.ALL)
				.contentType("application/json")).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(del));


    }
 

}
