package com.hanu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanu.exceptionhandlers.AccountIsNotFoundException;
import com.hanu.exceptionhandlers.CustomerNotFoundException;
import com.hanu.exceptionhandlers.NoDataException;
import com.hanu.exceptionhandlers.TransferFailedExceptoin;
import com.hanu.model.Account;
import com.hanu.model.Customer;
import com.hanu.services.AccountService;
import com.hanu.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService service;
	@Autowired
	AccountService accservice;

	@SuppressWarnings("deprecation")
	@PostMapping("/add")
	public ResponseEntity<?> addCustomer(@RequestBody Customer cust) throws HttpMessageNotReadableException {
		Customer cust1 = service.addCustomer(cust);
		if (cust1 != null) {
			return new ResponseEntity<>(cust1, HttpStatus.CREATED);
		}
		throw new HttpMessageNotReadableException("No Customer Object is there to add");
		// return new ResponseEntity<>("NO CUSTOMER CREATED!!",HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer c, @PathVariable int id)
			throws CustomerNotFoundException {
		Customer cust1 = service.updateCustomer(c, id);
		if (cust1 != null) {
			return new ResponseEntity<>(cust1, HttpStatus.OK);
		}
		throw new CustomerNotFoundException("NO CUSTOMER IS THERE TO UPDATE!");
	}

	@PutMapping("/updatename/{name}/{id}")
	public ResponseEntity<?> updateCustomerName(@PathVariable String name, @PathVariable int id)
			throws CustomerNotFoundException {
		int cust1 = service.updateCustomerName(name, id);
		if (cust1 == 1) {
			return new ResponseEntity<>("Name has been updated!", HttpStatus.OK);
		}
		throw new CustomerNotFoundException("NO CUSTOMER IS THERE TO UPDATE!");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id) throws CustomerNotFoundException {
		String msg = service.deleteCustomer(id);
		if (msg != null) {
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		throw new CustomerNotFoundException("NO CUSTOMER IS THERE TO DELETE!");
	}

	@GetMapping("/display")
	public ResponseEntity<?> getAllCustomer() throws NoDataException {
		List<Customer> cust = service.displayAllCustomer();
		if (!(cust.isEmpty())) {
			return new ResponseEntity<>(cust, HttpStatus.OK);
		} else {
			throw new NoDataException("NO DATA FOUND!");
			// return new ResponseEntity<>("No Customers are there to
			// show",HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable int id) throws CustomerNotFoundException {
		Customer cust = service.getCustomer(id);
		if (cust != null) {
			return new ResponseEntity<>(cust, HttpStatus.OK);
		} else {
			throw new CustomerNotFoundException("NO CUSTOMER IS THERE BY THIS GIVEN ID!");
		}
	}

	@GetMapping("/getaccount/{id}")
	public ResponseEntity<?> getAccountDetails(@PathVariable int id) throws AccountIsNotFoundException {
		Account cust = accservice.getAccount(id);
		if (cust != null) {
			return new ResponseEntity<>(cust, HttpStatus.OK);
		} else {
			throw new AccountIsNotFoundException("NO ACCOUNT IS THERE BY THIS GIVEN ID!");
		}
	}

	@DeleteMapping("/deleteaccount/{accno}")
	public ResponseEntity<?> deleteById(@PathVariable int accno) throws DataIntegrityViolationException {
		String accn = accservice.deleteById(accno);
		if (accn != null) {
			return new ResponseEntity<>("Account Deleted Successfully!", HttpStatus.OK);
		}
		// throw new AccountIsNotFoundException("NO ACCOUNT IS THERE BY THIS GIVEN ID");
		throw new DataIntegrityViolationException("cannot delete the account");
	}

	@GetMapping("/transfer/{from}/{to}/{amount}")
	public ResponseEntity<?> transferFunds(@PathVariable int from, @PathVariable int to, @PathVariable double amount)
			throws TransferFailedExceptoin {
		String acc1 = service.transferFunds(from, to, amount);
		if (acc1 != null) {
			return new ResponseEntity<>(acc1, HttpStatus.OK);
		} else {
			throw new TransferFailedExceptoin("Please provide valid details");
		}
	}

	@DeleteMapping("/deleteall")
	public ResponseEntity<?> custDeleteAll() throws CustomerNotFoundException {
		String msg = service.deleteAll();
		if (msg != null) {
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		throw new CustomerNotFoundException("NO CUSTOMER IS THERE TO DELETE!");
	}

}
