package com.home.qa.myrunners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
	features = { "src/test/resources/com/features" },
	glue = {
		"com/home/qa/hooks",
		"com/home/qa/stepdefinitions"
	},
	plugin = {
		"pretty",
		"json:target/myreports/report_json.json",
		"html:target/myreports/cucumber_report.html"
	},
	tags = "@home-1"
)

public class Runner extends AbstractTestNGCucumberTests {

}