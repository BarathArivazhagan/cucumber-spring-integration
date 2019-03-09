# cucumber-spring-integration
 ### spring boot application integrated with cucumber test cases

This sample demonstrates writing the cucumber test cases to test the spring boot application using BDD style.

### Branch Matrix

choose the branch before proceeding to further sections.

<table>

 <tr>
    <th style="text-align:left">Branch</th>
    <th style="text-align:left">Spring Boot Version</th> 
    <th style="text-align:left">Cucumber Version</th>
  </tr>
  <tr>
    <td>master</td>
    <td>2.1.3.RELEASE</td>
    <td>4.2.5</td>
  </tr>
  <tr>
    <td>1.0.0.RELEASE</td>
    <td>2.0.3.RELEASE</td>
    <td>1.2.5</td>
  </tr>
  <tr>
    <td>1.5.6</td>
    <td>1.5.6.RELEASE</td>
    <td>1.2.5</td>
  </tr>
  
</table>


### Development Steps : 


<b>Step1</b> : To start with cucumber : 

Add the below dependencies to support Spring with Cucumber: 

```
	<properties>		
		<cucumber.version>4.2.5</cucumber.version>
	</properties>

	<dependencies>
   		<dependency>
		    <groupId>io.cucumber</groupId>
		    <artifactId>cucumber-java8</artifactId>
		    <version>${cucumber.version}</version>
		    <scope>test</scope>
		</dependency>
		<dependency>
		    <groupId>io.cucumber</groupId>
		    <artifactId>cucumber-java</artifactId>
		    <version>${cucumber.version}</version>
		    <scope>test</scope>
		</dependency>
		<dependency>
	        <groupId>io.cucumber</groupId>
	        <artifactId>cucumber-junit</artifactId>
	        <version>${cucumber.version}</version>
	        <scope>test</scope>
	    </dependency>
    <dependencies>
```

<b>Step 2</b>: Define the Cucumber Runner Test

```
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class CucumberRunnerTest {

}
```

<b>Step 3 </b> : Create a feature file and define the BDD steps using gherkin language

Example : Feature describing the scenario of creating a customer with customer details

```
Feature: To save the customer with customer details

  Scenario: client makes call to POST /customers/new to save the customer
    Given the customer with customer name "barath" and customer id 7777
    When the client calls "/customer" with the given details
    Then the client receives status code of 200
    And the response contains customer name "barath"
```

<b> Step 4 </b> : When you run this feature file ```Run as -> Cucumber``` it generates the BDD style of java code. 
Using which you can write the service logic. 


### SaveCustomerStepDefinitionTest.java : 
```


	@Given("^the customer with customer name \"([^\"]*)\" and customer id (\\d+)$")
	public void the_customer_with_customer_name_and_customer_id(String customerName, int customerId) throws Throwable {	  
		
	}

	@When("^the client calls \"([^\"]*)\" with the given details$")
	public void the_client_calls_customer_save_with_the_given_details(String path) throws Throwable {
	
	}

	@Then("^the client receives status code of (\\d+)$")
	public void the_client_receives_status_code_of(int statusCode) throws Throwable {
	
	}

	@Then("^the response contains customer name \"([^\"]*)\"$")
	public void the_response_contains_customer_name(String customerName) throws Throwable {
	  
	}

```

> **_NOTE:_**  Run cucumber tests after starting the application.