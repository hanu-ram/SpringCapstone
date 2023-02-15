package com.hanu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanu.model.Account;
import com.hanu.model.Customer;
import com.hanu.repository.AccountRepository;
import com.hanu.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository custramp;
    @Autowired
    AccountRepository accramp;

    @Override
    public Customer addCustomer(Customer e) {

        return custramp.save(e);
//		return null;
    }

    @Override
    public Customer updateCustomer(Customer e, int id) {
        Customer cust = null;
        Optional<Customer> op = custramp.findById(id);
        if (op.isPresent()) {
            cust = (Customer) op.get();
            cust.setAccount(e.getAccount());
            cust.setName(e.getName());
            return custramp.save(cust);
        }
        return null;
    }
    

    @Override
    public String deleteCustomer(int eid) {
        Optional<Customer> op = custramp.findById(eid);
        if (op.isPresent()) {
            custramp.deleteById(eid);
            return "Customer deleted!";
        }
        return null;
    }

    @Override
    public List<Customer> displayAllCustomer() {

        return custramp.findAll();
    }

    @Override
    public Customer getCustomer(int eid) {
        Optional<Customer> cust = custramp.findById(eid);
        if (cust.isPresent()) {
            return (Customer) cust.get();
        }
        return null;
    }

    @Override
    public String transferFunds(int from, int to, double amt) {
        if (amt > 0) {
            Optional<Account> aop1 = accramp.findById(from);
            Account acc = null;
            double balance1 = 0, balance2 = 0;
            if (aop1.isPresent()) {
                acc = (Account) aop1.get();
                balance1 = acc.getBalance();
            }
            Optional<Account> acnt2 = accramp.findById(to);
            System.out.println(acnt2);
            Account acc2 = null;
            if (acnt2.isPresent()) {
                acc2 = (Account) acnt2.get();
                // System.out.println(acc2);
                balance2 = acc2.getBalance();
                // System.out.println("A/c balance1:"+balance2);
                double con = acc.getBalance() - amt;
                if (from == to) {
                    return "From and To could not be same!!";
                } else if ((con >= 0)) {
                    balance1 = balance1 - amt;
                    balance2 = balance2 + amt;
                    acc.setBalance(balance1);
                    acc2.setBalance(balance2);
                    // System.out.println(balance2);
                    accramp.save(acc2);
                    accramp.save(acc);
                    return "money transfered from: " + from + "  to: " + to + " Successfully!";
                }

                //if ((aop1.isPresent()) && (acnt2.isPresent())) {


                //}
                else {
                    return "Insufficient Funds";
                }

            }
            return null;
        } else {
            return "Amount should be greater than 0 (0 and - values cannot accept)";
        }
    }

    @Override
    public String deleteAll() {
        List<Customer> custlist = new ArrayList<Customer>();
        custlist = (custramp.findAll());
        if (!(custlist.isEmpty())) {
            custramp.deleteAll();
            return "All Customer data deleted!!";
        }
        return null;

    }

	@Override
	public int updateCustomerName(String name, int id) {
		 Customer cust = null;
	        Optional<Customer> op = custramp.findById(id);
	        if (op.isPresent()) {
	        	return custramp.updateCustomerName(name,id);
	        	 
	        }
	        return 0;
	}

}
