package com.hanu.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanu.model.Account;
import com.hanu.model.Customer;
import com.hanu.services.CustomerService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
@ContextConfiguration
public class CustomerControllerMockitoTest {

	@Mock
	CustomerService service;

	@InjectMocks
	CustomerController controller;

	@Autowired
	MockMvc mvc;

	Customer customer;

	Account account;

	@Autowired
	ObjectMapper mapper;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Order(1)
	@Test
	public void test_addCustomer() throws Exception {

		customer = new Customer(131, "Hanu", new Account(111, "savings", 120.4));
		
		String JSONinput = mapper.writeValueAsString(customer);

		when(service.addCustomer(any(Customer.class))).thenReturn(customer);
		mvc.perform(post("/customer/add").content(JSONinput)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(JSONinput))
				.andExpect(MockMvcResultMatchers.jsonPath(".name").value(customer.getName()));

	}
	
	@Order(3)
	@Test
	public void test_displayAllCustomer() throws Exception {
		List<Customer> listCust = new ArrayList<Customer>();
		listCust.add(new Customer(131, "Hanumanth", new Account(133, "savings", 300.0)));

		when(service.displayAllCustomer()).thenReturn(listCust);

		mvc.perform(get("/customer/display")).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(listCust)))
				.andExpect(MockMvcResultMatchers.jsonPath(".account.id").value(133));

	}
	@Order(2)
	@Test
	public void test_getCustomer() throws Exception {

		customer = new Customer(131, "Hanumanth", new Account(133, "savings", 300.0));

		when(service.getCustomer(customer.getId())).thenReturn(customer);

		mvc.perform(get("/customer/getbyid/{id}", customer.getId())).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(".name").value(customer.getName()));

	}

}
