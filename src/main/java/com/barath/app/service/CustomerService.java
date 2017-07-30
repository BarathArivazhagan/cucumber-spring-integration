package com.barath.app.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.barath.app.entity.Customer;
import com.barath.app.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private static final Logger logger=LoggerFactory.getLogger(CustomerService.class);
	
	private CustomerRepository customerRepo=null;
	
	public CustomerService(CustomerRepository customerRepo) {
		this.customerRepo=customerRepo;
	}
	
	
	public Customer saveCustomer(Customer customer){
		
		if( logger.isInfoEnabled()){
			logger.info("Customer is saved with details {}",customer.toString());
		}
		return customerRepo.save(customer);
	}
	
	public List<Customer> saveCustomers(List<Customer> customers){
		
		if( logger.isInfoEnabled()){
			logger.info("Customer is saved with details {}",customers);
		}
		return customerRepo.save(customers);
	}
	
	public Customer getCustomerWithName(String customerName){
		
		Customer customer=null;
		if( logger.isInfoEnabled()){
			logger.info("Getting Customer with name {}",customerName);
		}	
		customer= customerRepo.findByCustomerName(customerName);
		return customer;
	}
	
	public Customer getCustomer(Long customerId){
		
		Customer customer=null;
		if( logger.isInfoEnabled()){
			logger.info("Getting Customer with Id {}",customerId);
		}	
		customer= customerRepo.findOne(customerId);
		return customer;
	}
	
	public List<Customer> getCustomers(){
		
		return customerRepo.findAll();
	}

}
