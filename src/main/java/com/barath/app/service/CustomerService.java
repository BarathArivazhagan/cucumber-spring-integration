package com.barath.app.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.barath.app.entity.Customer;
import com.barath.app.repository.CustomerRepository;

@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer saveCustomer(Customer customer) {

		if (logger.isInfoEnabled()) {
			logger.info("Customer is saved with details {}", customer.toString());
		}
		return this.customerRepository.save(customer);
	}

	public List<Customer> saveCustomers(List<Customer> customers) {

		if (logger.isInfoEnabled()) {
			logger.info("Customer is saved with details {}", customers);
		}
		return this.customerRepository.saveAll(customers);
	}

	public Customer getCustomerWithName(String customerName) {

		if (logger.isInfoEnabled()) {
			logger.info("Getting Customer with name {}", customerName);
		}
		return this.customerRepository.findByCustomerName(customerName);

	}

	public Optional<Customer> getCustomer(Long customerId) {

		if (logger.isInfoEnabled()) {
			logger.info("Getting Customer with Id {}", customerId);
		}
		return this.customerRepository.findById(customerId);

	}

	public List<Customer> getCustomers() {

		return this.customerRepository.findAll();
	}

}
