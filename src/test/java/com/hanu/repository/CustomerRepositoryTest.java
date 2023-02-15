package com.hanu.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hanu.model.Account;
import com.hanu.model.Customer;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerRepositoryTest {
	@Autowired
	CustomerRepository custrepo;
	@Autowired
	AccountRepository accrepo;

	@Test
	void testSaving() {
		// Account account =
		// Account.builder().accounttype("savings").balance(3000).build();

		// Customer customer = Customer.builder().id(1).name("Hanu").build();
		Customer customer = new Customer();
		customer.setName("Ram");
		custrepo.save(customer);
		Customer cust2 = custrepo.findById(customer.getId()).get();
		// Optional<Customer> custfind = custrepo.findById(131);
		System.out.println("Testing...................");
		// Customer custa = (Customer) custfind.get();

		System.out.println(cust2.getName());
		assertEquals("Ram", cust2.getName());

	}

	@Test
	void findByID() {
		Customer cust1 = new Customer();
		Account acc = new Account();
		acc.setAccounttype("savings");
		acc.setBalance(3000.0);
		cust1.setAccount(acc);
		cust1.setName("Hanu");
		custrepo.save(cust1);
		Customer customer = custrepo.findById(cust1.getId()).get();
		// Customer cust = (Customer) customer.get();
		// System.out.println(cust.getAccount().getBalance());
		// assertEquals(3000.0, cust.getAccount().getBalance());
		assertEquals(cust1, customer);
	}

//	@Test
//
//	void deleteByID() {
//		Customer cust1 = new Customer();
//		Account acc = new Account();
//		acc.setAccounttype("savings");
//		acc.setBalance(3000.0);
//		cust1.setAccount(acc);
//		cust1.setName("Hanu");
//		custrepo.save(cust1);
//		//System.out.println(cust1.getAccount().getBalance());
//		// custrepo.deleteById(cust1.getId());
//		custrepo.deleteById(cust1.getId());
//		List<Customer> data = custrepo.findAll();
//		List<?> dummy = new ArrayList<>();
//		System.out.println(cust1.getAccount().getBalance());
//		assertEquals(dummy, data);
//
//	}

	
//
//	@Test
//	void findAll() {
//		Customer cust1 = new Customer();
//		Account acc = new Account();
//		acc.setAccounttype("savings");
//		acc.setBalance(3000.0);
//		cust1.setAccount(acc);
//		cust1.setName("Ram");
//		custrepo.save(cust1);
//		Customer cust2 = new Customer();
//		Account acc2 = new Account();
//		acc2.setAccounttype("savings");
//		acc2.setBalance(5000.0);
//		cust2.setAccount(acc);
//		cust2.setName("Hanu");
//		custrepo.save(cust2);
//		List<Customer> custactual = new ArrayList<Customer>();
//		custactual.add(cust1);
//		custactual.add(cust2);
//		List<Customer> customer = custrepo.findAll();
//		System.out.println(customer);
////		Customer cust = (Customer) customer.get(0);
////		Customer cust0 = (Customer) customer.get(1);
//		// assertEquals(3000.0, cust0.getAccount().getBalance());
//		assertEquals(custactual, customer);
//	}
	@Test
	void delete() {
		Customer cust1 = new Customer();
		Account acc = new Account();
		acc.setAccounttype("savings");
		acc.setBalance(3000.0);
		cust1.setAccount(acc);
		cust1.setName("Ram");
		custrepo.save(cust1);
		Customer cust2 = new Customer();
		Account acc2 = new Account();
		acc2.setAccounttype("savings");
		acc2.setBalance(5000.0);
		cust2.setAccount(acc);
		cust2.setName("Hanu");
		custrepo.save(cust2);
		custrepo.deleteAll();
		System.out.println(cust1.getAccount().getBalance());
		List<Customer> custex = custrepo.findAll();
		List<?> dummy = new ArrayList<>();
		System.out.println(custex);
//		custactual.add(cust1);
//		custactual.add(cust2);
		assertEquals(dummy, custex);
	}

	}
