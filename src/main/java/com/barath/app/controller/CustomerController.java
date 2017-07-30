package com.barath.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RequestMapping(value="/customer")
public class CustomerController {
	
	private static final Logger logger=LoggerFactory.getLogger(CustomerService.class);
	
	
	@Autowired
	private CustomerService customerService;
	
	
	
	@PostMapping(value="/save")
	public Customer createCustomer(@RequestBody Customer customer){
		
		if( customer !=null){	
			
			customer=customerService.saveCustomer(customer);
		}
		
		return customer;
	}
	
	@PostMapping(value="/saves")
	public List<Customer> createCustomers(@RequestBody List<Customer> customers){
		
		if( customers !=null){	
			
			customers=customerService.saveCustomers(customers);
		}
		
		return customers;
	}
	
	@GetMapping(value="/get/{customerId}")
	public Customer findCustomer(@PathVariable Long customerId){
		
		Customer customer=null;
		if( customerId !=null){	
			
			customer=customerService.getCustomer(customerId);
		}
		
		return customer;
	}
	
	
	
	@GetMapping(value="/get")
	public Customer findCustomerWithName(@RequestParam(name="name") String customerName){
		
		Customer customer=null;
		if( !StringUtils.isEmpty(customerName)){				
			customer=customerService.getCustomerWithName(customerName);
		}
		
		return customer;
	}
	
	@GetMapping(value="/getall")
	public List<Customer> findAllCustomers(){
		
		return customerService.getCustomers();
	}
	
	

}
