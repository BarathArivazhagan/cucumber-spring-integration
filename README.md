# Cucumber-spring-integration
 ### Spring Boot application integrated with Cucumber Test cases

This example is about writing the cucumber test cases to test the spring boot application using BDD

<b>Step1</b> : To start with cucumber : 

Add the below dependencies to support Spring with Cucumber: 

```
   		<dependency>
			<groupId>info.cukes</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>1.2.5</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>info.cukes</groupId>
			<artifactId>cucumber-spring</artifactId>
			<version>1.2.5</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>info.cukes</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>1.2.5</version>
			<scope>test</scope>
		</dependency>


```

<b>Step 2</b>: Define the Cucumber Runner 

```
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberRunner {

}


```

<b>Step 3 </b> : Create a feature file and define the BDD steps using gherkin language

Example : Feature describing the scenario of saving a customer with customer details

```
Feature: To save the customer with customer details	
   Scenario: client makes call to POST /customer/save to save the customer
	Given the customer with customer name "barath" and customer id 7777
	When the client calls "/customer/save" with the given details
	Then the client receives status code of 200
	And the response contains customer name "barath"


```

<b> Step 4 </b> : When you run this feature file Run as -> Cucumber it generated the BDD style of java code. 
Using which you can write the service logic. 

### SaveCustomerStepDefinitionTest.java : 
```


	@Given("^the customer with customer name \"([^\"]*)\" and customer id (\\d+)$")
	public void the_customer_with_customer_name_and_customer_id(String customerName, int customerId) throws Throwable {
	  
		if(logger.isInfoEnabled()){
			logger.info("Customer to be saved with customer name {} and customer id {}",customerName,customerId);
		}
		this.customerId=customerId;
		this.customerName=customerName;
		
	}

	@When("^the client calls \"([^\"]*)\" with the given details$")
	public void the_client_calls_customer_save_with_the_given_details(String path) throws Throwable {
	   
		if(logger.isInfoEnabled()){
			logger.info("path {}",path);
		}
		String url=buildUrl(HOST, PORT, path);
		Map<String, Object> requestMap=new HashMap<>();
		requestMap.put("customerName", this.customerName);
		requestMap.put("customerId", this.customerId);
		HttpEntity<?> requestEntity=new HttpEntity<>(requestMap,getDefaultHttpHeaders());
		response=invokeRESTCall(url, HttpMethod.POST, requestEntity);
	}

	@Then("^the client receives status code of (\\d+)$")
	public void the_client_receives_status_code_of(int statusCode) throws Throwable {
	   
		if(response !=null && response.getStatusCode().is2xxSuccessful()){
			assertEquals(statusCode, response.getStatusCode().value());
		}
	}

	@Then("^the response contains customer name \"([^\"]*)\"$")
	public void the_response_contains_customer_name(String customerName) throws Throwable {
	    
		if(response !=null && response.getStatusCode().is2xxSuccessful()){
			String responseBody=response.getBody();
			com.fasterxml.jackson.databind.ObjectMapper mapper= new com.fasterxml.jackson.databind.ObjectMapper();
			Map<String,String> responseMap=mapper.readValue(responseBody, Map.class);
			System.out.println(responseMap);
			assertEquals(customerName,responseMap.get("customerName"));
		}
	}

```
