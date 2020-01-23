package com.barath.app.bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = {
		"html:target/cucumber-report" }, monochrome = true)
public class CucumberRunnerTest {

}
