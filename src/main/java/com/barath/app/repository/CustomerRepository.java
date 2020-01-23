package com.barath.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barath.app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByCustomerName(String customerName);

}
