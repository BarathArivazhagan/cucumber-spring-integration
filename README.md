# Cucumber-spring-integration
Spring Boot application integrated with Cucumber Test cases

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
