# Cucumber-spring-integration
Spring Boot application integrated with Cucumber Test cases

This example is about writing the cucumber test cases to test the spring boot application using BDD

*Step1* : To start with cucumber : 

Add the below dependencies: 


*Step 2*: Define the Cucumber Runner 

```
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberRunner {

}


```

