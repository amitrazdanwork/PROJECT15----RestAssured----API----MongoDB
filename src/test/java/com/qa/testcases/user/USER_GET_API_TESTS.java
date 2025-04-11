package com.qa.testcases.user;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import com.qa.base.BaseTest;
import com.qa.endpoints.UserEndpoints;
import com.qa.payloads.User;
import com.qa.utils.CommonUtil;
import com.qa.utils.ConfigUtil;
import com.qa.utils.DBUtil;
import com.qa.utils.ReportUtil;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class USER_GET_API_TESTS extends BaseTest {

	private static Logger logger = LogManager.getLogger(USER_GET_API_TESTS.class.getName());
	private static ExtentTest extenttest;

	@Test(description = "Validate that API should return API status code within 2XX series", groups = "GET")
	public void Test1() {

		logger.info("-- Execution started for USER_GET_API_TESTS - Test1--");

		System.out.println("Username => " + this.user.getUsername());

		extenttest = ReportUtil.GetTest();
		
		// API call
		Response response = UserEndpoints.getUser(user.getUsername());
		logger.info("-- getUser API got executed with user's username--");
		extenttest.info("GET API got executed with username : " + user.getUsername());

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test1--");

		// Hamcrest Matcher
		Matcher<String> matcher1 = Matchers.matchesPattern("^2[0-9][0-9]$");
		logger.info("-- Matcher with pattern accepting 2XX got created--");

		// Assertion
		assertThat("API response status code lies within 2XX",
				matcher1.matches(String.valueOf(response.getStatusCode())));
		logger.info("-- Assertion - response code matches 2XX pattern got executed--");
		extenttest.pass("Assertion - Response status falls under 2XX pattern");

	}

	@Test(description = "Validate that API should return API status code = 200 OK", groups = "GET")
	public void Test2() {

		logger.info("-- Execution started for USER_GET_API_TESTS - Test2--");

		extenttest = ReportUtil.GetTest();
		
		// API call
		Response response = UserEndpoints.getUser(user.getUsername());
		logger.info("-- getUser API got executed with user's username--");
		extenttest.info("GET API got executed with username : " + user.getUsername());

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test2--");

		assertThat(response.getStatusCode(), is(200));
		logger.info("-- Assertion - response code is 200--");
		extenttest.pass("Assertion - Response cpde is 200 Ok");

		logger.info("-- Execution ended for USER_GET_API_TESTS - Test2--");

	}

	@Test(description = "Validate that API should return a response payload ie. Non-empty response body and response header", groups = "GET")
	public void Test3() {

		logger.info("-- Execution started for USER_GET_API_TESTS - Test3--");
		
		extenttest = ReportUtil.GetTest();

		// API call
		Response response = UserEndpoints.getUser(user.getUsername());
		logger.info("-- getUser API got executed with user's username--");
		extenttest.info("GET API got executed with username : " + user.getUsername());

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test3--");

		// Assertion 1: Non-empty response body
		assertThat(response.getBody().asPrettyString(), is(not(isEmptyString())));
		logger.info("-- Assertion : Response body is non-empty --");
		extenttest.pass("Assertion - Response body is non-empty");

		// Assertion 2: Non-empty header
		assertThat(response.getHeaders().exist(), is(true));
		logger.info("-- Assertion : Response header is non-empty --");
		extenttest.pass("Assertion - Response header is non-empty");

		logger.info("-- Execution ended for USER_GET_API_TESTS - Test3--");

	}

	@Test(description = "Validate that API should return response of type = JSON", groups = "GET")
	public void Test4() {

		logger.info("-- Execution started for USER_GET_API_TESTS - Test4--");

		extenttest = ReportUtil.GetTest();
		
		// API call
		Response response = UserEndpoints.getUser(user.getUsername());
		logger.info("-- getUser API got executed with user's username--");
		extenttest.info("GET API got executed with username : " + user.getUsername());

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test4--");

		// Assertion 1: Non-empty response body
		assertThat(response.getContentType(), is("application/json"));
		logger.info("-- Assertion : Response content type is JSON --");
		extenttest.pass("Assertion - Response type is JSON");

		logger.info("-- Execution ended for USER_GET_API_TESTS - Test4--");

	}

	@Test(description = "Validate that API should should adhere to Response structure as per data model mentioned in API Spec or API Doc.- JSON SCHEMA VALIDATION", groups = "GET")
	public void Test5() {

		logger.info("-- Execution started for USER_GET_API_TESTS - Test5--");

		extenttest = ReportUtil.GetTest();
		
		// API call
		Response response = UserEndpoints.getUser(user.getUsername());
		logger.info("-- getUser API got executed with user's username--");
		extenttest.info("GET API got executed with username : " + user.getUsername());

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test5--");

		// Assertion 1: JsonSchema validation
		assertThat(response.jsonPath().prettyPrint(),
				JsonSchemaValidator.matchesJsonSchema(ConfigUtil.GetJsonSchema("USER", "GET")));
		logger.info("-- Assertion : Response schema validation --");
		extenttest.pass("Assertion -Json schema validation for response body");

		logger.info("-- Execution ended for USER_GET_API_TESTS - Test5--");

	}

	@Test(description = "Validate that API should return response body with details of existing user", groups = "GET")
	public void Test6() {

		logger.info("-- Execution started for USER_GET_API_TESTS - Test6--");

		extenttest = ReportUtil.GetTest();
		
		// API call
		Response response = UserEndpoints.getUser(user.getUsername());
		logger.info("-- getUser API got executed with user's username--");
		extenttest.info("GET API got executed with username : " + user.getUsername());

		// Assertions - value
		assertThat(response.jsonPath().getInt("id"), is(equalTo(user.getId()))); // id assertion
		logger.info("-- Assertion : Response body, id attribute value --");
		extenttest.pass("Assertion -Response content 'id' attribute is 10");

		assertThat(response.jsonPath().getString("username"), is(user.getUsername())); // id assertion
		logger.info("-- Assertion : Response body, username attribute value --");
		extenttest.pass("Assertion -Response content 'username' attribute is amituser");

		assertThat(response.jsonPath().getString("firstName"), is(user.getFirstName())); // id assertion
		logger.info("-- Assertion : Response body, firstName attribute value --");
		extenttest.pass("Assertion -Response content 'firstName' attribute is amit");

		assertThat(response.jsonPath().getString("lastName"), is(user.getLastName())); // id assertion
		logger.info("-- Assertion : Response body, lastName attribute value --");
		extenttest.pass("Assertion -Response content 'lastName' attribute is user");

		assertThat(response.jsonPath().getString("email"), is(user.getEmail())); // id assertion
		logger.info("-- Assertion : Response body, email attribute value --");
		extenttest.pass("Assertion -Response content 'email' attribute is amituser@gmail.com");

		assertThat(response.jsonPath().getString("password"), is(user.getPassword())); // id assertion
		logger.info("-- Assertion : Response body, password attribute value --");
		extenttest.pass("Assertion -Response content 'password' attribute is password1");

		assertThat(response.jsonPath().getString("phone"), is(user.getPhone())); // id assertion
		logger.info("-- Assertion : Response body, phone attribute value --");
		extenttest.pass("Assertion -Response content 'phone' attribute is 124587512");

		assertThat(response.jsonPath().getInt("userStatus"), is(equalTo(user.getUserStatus()))); // id assertion
		logger.info("-- Assertion : Response body, userStatus attribute value --");
		extenttest.pass("Assertion -Response content 'userStatus' attribute is 1");

		logger.info("-- Execution ended for USER_GET_API_TESTS - Test6--");

	}

}
