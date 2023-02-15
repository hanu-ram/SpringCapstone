package com.hanu;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.hanu.model.Account;
import com.hanu.model.Customer;
import com.hanu.repository.CustomerRepository;

@SpringBootApplication
public class SpringCapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCapstoneApplication.class, args);
	}
	@Autowired
	CustomerRepository repo;
	
	@PostConstruct
	public void existingData() {
		System.out.println("adding data into the database........");
		Customer cust = new Customer(131,"Hanu", new Account(141,"savings",300));
		Customer cust1 = new Customer(132,"Ram", new Account(142,"savings",3000));
		repo.save(cust);
		repo.save(cust1);
	}
}
