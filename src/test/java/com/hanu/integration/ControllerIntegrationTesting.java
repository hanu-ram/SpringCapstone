package com.hanu.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hanu.model.Account;
import com.hanu.model.Customer;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTesting {
	@LocalServerPort
	private int localport;

	Customer customer = new Customer(localport, "Hanu", new Account(localport, "savings", 5000.00));

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void addCustomerTest() {
		ResponseEntity<String> response = testRestTemplate
				.postForEntity("http://localhost:" + localport + "/customer/add", customer, String.class);
		System.out.println(response.getStatusCode());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

	@Test
	public void getAllCustomerTest() {
		ResponseEntity<?> response = testRestTemplate
				.getForEntity("http://localhost:" + localport + "/customer/display", String.class);
		if (response.getBody().toString().equalsIgnoreCase("NO DATA FOUND!"))
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		else
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void getAccountBalanceByIdTest() {
		ResponseEntity<?> response = testRestTemplate
				.getForEntity("http://localhost:" + localport + "/customer/getaccount/7", String.class);
		if (response.getBody().toString().equalsIgnoreCase("NO ACCOUNT IS THERE BY THIS GIVEN ID!"))
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		else
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void getCustomerByIdTest() {
		ResponseEntity<?> response = testRestTemplate
				.getForEntity("http://localhost:" + localport + "/customer/getbyid/7", String.class);
		if (response.getBody().toString().equalsIgnoreCase("NO CUSTOMER IS THERE BY THIS GIVEN ID!"))
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		else
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void getDeleteCustomerByIdTest() {
		String expected = "";
		try {
			testRestTemplate.delete("http://localhost:" + localport + "/customer/delete/1");

			expected = "delete";
		} catch (Exception e) {

		}

		assert (expected.equalsIgnoreCase("delete"));
	}

	@Test
	public void getDeleteAllCustomerTest() {
		String expected = "";
		try {
			testRestTemplate.delete("http://localhost:" + localport + "/account/deleteAll");

			expected = "deleted";
		} catch (Exception e) {

		}

		assert (expected.equalsIgnoreCase("deleted"));
	}
	@Test
	public void updateCustomerTest() {
		String s = "";
		try {
			testRestTemplate.put("http://localhost:" + localport + "/customer/update/2", customer);
			s = "update";
		} catch (Exception e) {

		}

		assert (s.equalsIgnoreCase("update"));

	}

	@Test
	public void transferFundsAccountTest() {
		String s = "";
		try {
			testRestTemplate.put("http://localhost:" + localport + "/customer/transfer/204/205/1000", "");
			s = "transfered";

		} catch (Exception e) {

		}

		assert (s.equalsIgnoreCase("transfered"));

	}

}