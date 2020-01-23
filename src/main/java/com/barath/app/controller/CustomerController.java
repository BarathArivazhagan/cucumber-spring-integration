package com.barath.app.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barath.app.entity.Customer;
import com.barath.app.service.CustomerService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping(value = "/customer")
	public Customer createCustomer(@RequestBody Customer customer) {

		if (customer != null) {

			customer = customerService.saveCustomer(customer);
		}

		return customer;
	}

	@PostMapping(value = "/customers")
	public List<Customer> createCustomers(@RequestBody List<Customer> customers) {

		Assert.notEmpty(customers, "customers to be saved cannot be empty");
		return customerService.saveCustomers(customers);

	}

	@GetMapping(value = "/customer/{customerId}")
	public Optional<Customer> findCustomer(@PathVariable Long customerId) {

		Assert.notNull(customerId, "customerId cannot be empty");
		return customerService.getCustomer(customerId);

	}

	@GetMapping(value = "/customer/byName")
	public Customer findCustomerByName(@RequestParam(name = "name") String customerName) {

		Assert.notNull(customerName, "customerName cannot be empty");
		return customerService.getCustomerWithName(customerName);
	}

	@GetMapping(value = "/customers")
	public List<Customer> findAllCustomers() {
		return customerService.getCustomers();
	}

}
