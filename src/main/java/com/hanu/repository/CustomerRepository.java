package com.hanu.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hanu.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
@Modifying
@Transactional
@Query(value="update customerdata set name =?1 where id=?2",
		nativeQuery = true
		)
public int updateCustomerName(String name,int id);
}
