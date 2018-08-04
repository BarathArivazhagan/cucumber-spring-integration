package com.barath.app.bdd;

import static org.junit.Assert.assertEquals;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.BDDAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetCustomerStepDefinitionTest extends AbstractSpringConfigurationTest{
	
	private static final Logger logger=LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private String customerName=null;
	private int customerId;
	private ResponseEntity<String> response=null;
	
	
	@Given("^the customer saved with customer name \"([^\"]*)\" and customer id (\\d+)$")
	public void the_customer_saved_with_customer_name_and_customer_id(String customerName, int customerId) throws Throwable {
		
		String url=buildUrl(HOST, PORT, "/customer/save");
		logger.info("url {}",url);
		Map<String, Object> requestMap=new HashMap<>();
		requestMap.put("customerName", this.customerName);
		requestMap.put("customerId", this.customerId);
		HttpEntity<?> requestEntity=new HttpEntity<>(requestMap,getDefaultHttpHeaders());
		response=invokeRESTCall(url, HttpMethod.POST, requestEntity);		
		
	}
	
	
	@When("^the client calls GET \"([^\"]*)\" with customer id as (\\d+)$")
	public void the_client_calls_GET_with_customer_id_as(String path, int customerId) throws Throwable {

		Map<String,String> uriVariables=new HashMap<>();
		uriVariables.put("customerId", String.valueOf(customerId));
		String url=buildUrl(HOST, PORT, path,uriVariables);
		logger.info("url {}",url);
		response=invokeRESTCall(url, HttpMethod.GET,null);
	}
	
	@When("^the client calls GET \"([^\"]*)\" with customer name as \"([^\"]*)\" in query param$")
	public void the_client_calls_GET_with_customer_name_as_in_query_param(String path,String customerName) throws Throwable {
		Map<String,String> uriVariables=new HashMap<>();
		uriVariables.put("customerName", customerName);
		String url=buildUrl(HOST, PORT, path,uriVariables);
		logger.info("url {}",url);
		response=invokeRESTCall(url, HttpMethod.GET,null);
	}
	
	
}
