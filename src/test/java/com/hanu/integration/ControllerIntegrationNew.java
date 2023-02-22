//package com.hanu.integration;
//
//import org.json.JSONException;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import com.hanu.model.Account;
//import com.hanu.model.Customer;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@TestMethodOrder(OrderAnnotation.class)
//@SpringBootTest
//public class ControllerIntegrationNew {
//	
//	@Test
//	@Order(1)
//	public void getAllCustomerIntegrationTest() throws JSONException {
//		
//		String expected = "[\r\n"
//				+ "    {\r\n"
//				+ "        \"id\": 40097984,\r\n"
//				+ "        \"name\": \"Hanu\",\r\n"
//				+ "        \"account\": {\r\n"
//				+ "            \"id\": 2143921450,\r\n"
//				+ "            \"accounttype\": \"savings\",\r\n"
//				+ "            \"balance\": 300.0\r\n"
//				+ "        }\r\n"
//				+ "    },\r\n"
//				+ "    {\r\n"
//				+ "        \"id\": 40097985,\r\n"
//				+ "        \"name\": \"Ram\",\r\n"
//				+ "        \"account\": {\r\n"
//				+ "            \"id\": 2143921451,\r\n"
//				+ "            \"accounttype\": \"savings\",\r\n"
//				+ "            \"balance\": 3000.0\r\n"
//				+ "        }\r\n"
//				+ "    }\r\n"
//				+ "]";
//		
//		TestRestTemplate restTemplate = new TestRestTemplate();
//		
//		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8092/customer/display", String.class);
//		
//		System.out.println(response.getBody());
//		
//		JSONAssert.assertEquals(expected, response.getBody(), false);
//		
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//	}
//		
//	@Test
//	@Order(2)
//	public void addCustomerIntegrationTest() throws JSONException {
//		
//		String expected = "{\r\n"
//				+ "    \"id\": 40097986,\r\n"
//				+ "    \"name\": \"Person2\",\r\n"
//				+ "    \"account\": {\r\n"
//				+ "        \"id\": 2143921452,\r\n"
//				+ "        \"accounttype\": \"savings\",\r\n"
//				+ "        \"balance\": 409.0\r\n"
//				+ "    }\r\n"
//				+ "}";
//		Customer cust = new Customer(133,"Person2", new Account(143,"savings",409));
//		
//		TestRestTemplate restTemplate = new TestRestTemplate();
//		
//		HttpHeaders headers = new HttpHeaders();
//		
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		
//		HttpEntity<Customer> request = new HttpEntity<Customer>(cust,headers);
//		
//		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8092/customer/add", cust, String.class);
//		
//		System.out.println(response.getBody());
//		
//		JSONAssert.assertEquals(expected, response.getBody(), false);
//		
//		assertEquals(HttpStatus.CREATED, response.getStatusCode());
//		
//	}
//	
//	@Test
//	@Order(3)
//	public void updateCustomerIntegrationTest() throws JSONException {
//		
//		String expected = "{\r\n"
//				+ "    \"id\": 40097986,\r\n"
//				+ "    \"name\": \"Person\",\r\n"
//				+ "    \"account\": {\r\n"
//				+ "        \"id\": 2143921452,\r\n"
//				+ "        \"accounttype\": \"savings\",\r\n"
//				+ "        \"balance\": 400.0\r\n"
//				+ "    }\r\n"
//				+ "}";
//		Customer cust = new Customer(40097986,"Person", new Account(2143921452,"savings",400));
//		
//		TestRestTemplate restTemplate = new TestRestTemplate();
//		
//		HttpHeaders headers = new HttpHeaders();
//		
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		
//		HttpEntity<Customer> request = new HttpEntity<Customer>(cust,headers);
//		
//		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8092/customer/update/40097986",HttpMethod.PUT ,request, String.class);
//		
//		System.out.println(response);
//		
//		JSONAssert.assertEquals(expected, response.getBody(), false);
//		
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		
//	}
//	
//	@Test
//	@Order(4)
//	public void deleteCustomerIntegrationTest() {
//		
//		String expected = "Customer deleted!";
//		
//		Customer cust = new Customer(40097986,"Person2", new Account(2143921452,"savings",409));
//		
//		TestRestTemplate restTemplate = new TestRestTemplate();
//		
//		HttpHeaders headers = new HttpHeaders();
//		
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		
//		HttpEntity<Customer> request = new HttpEntity<Customer>(cust,headers);
//		
//		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8092/customer/delete/40097986",HttpMethod.DELETE,request,String.class);
//		
//		assertEquals(expected, response.getBody());
//	}
//	
//	
//}
