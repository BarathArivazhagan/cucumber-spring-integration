package com.barath.app.bdd;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SaveCustomerStepDefinitionTest extends AbstractSpringConfigurationTest {

	private static final Logger logger = LoggerFactory.getLogger(SaveCustomerStepDefinitionTest.class);

	private String customerName = null;
	private int customerId;
	private ResponseEntity<String> response = null;

	@Given("^the customer with customer name \"([^\"]*)\" and customer id (\\d+)$")
	public void the_customer_with_customer_name_and_customer_id(String customerName, int customerId) throws Throwable {

		if (logger.isInfoEnabled()) {
			logger.info("Customer to be saved with customer name {} and customer id {}", customerName, customerId);
		}
		this.customerId = customerId;
		this.customerName = customerName;

	}

	@When("^the client calls \"([^\"]*)\" with the given details$")
	public void the_client_calls_customer_save_with_the_given_details(String path) throws Throwable {

		if (logger.isInfoEnabled()) {
			logger.info("path {}", path);
		}
		String url = buildUrl(HOST, PORT, path);
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("customerName", this.customerName);
		requestMap.put("customerId", this.customerId);
		HttpEntity<?> requestEntity = new HttpEntity<>(requestMap, getDefaultHttpHeaders());
		response = invokeRESTCall(url, HttpMethod.POST, requestEntity);
	}

	@Then("^the client receives status code of (\\d+)$")
	public void the_client_receives_status_code_of(int statusCode) throws Throwable {

		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			assertEquals(statusCode, response.getStatusCode().value());
		}
	}

	@Then("^the response contains customer name \"([^\"]*)\"$")
	public void the_response_contains_customer_name(String customerName) throws Throwable {

		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			String responseBody = response.getBody();
			com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
			Map<String, String> responseMap = mapper.readValue(responseBody, Map.class);
			System.out.println(responseMap);
			assertEquals(customerName, responseMap.get("customerName"));
		}
	}

}
