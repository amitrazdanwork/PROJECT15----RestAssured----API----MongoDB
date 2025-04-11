package com.qa.testcases.user;

import org.testng.annotations.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
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

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import static org.hamcrest.MatcherAssert.*;

import javax.annotation.MatchesPattern;
import io.restassured.response.Response;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.github.javafaker.Faker;
import com.qa.endpoints.UserEndpoints;
import com.qa.payloads.User;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class USER_POST_API_TESTS extends BaseTest {

	private static Logger logger = LogManager.getLogger(USER_POST_API_TESTS.class.getName());
	private static ExtentTest extenttest;

	@Test(description = "Validate that API should return API status code within 2XX series", priority = 1, groups = "POST")
	public void Test1() {

		logger.info("-- Execution started for USER_POST_API_TESTS - Test1--");

		// Setting up extenttest object with current extentTest value
		extenttest = ReportUtil.GetTest();

		Response response = UserEndpoints.createUser(user);
		logger.info("-- CreateUser API got executed with user payload--");
		extenttest.info("POST API got executed with new user payload : " + user);

		Matcher<String> matcher1 = Matchers.matchesPattern("^20[0-9]$");
		logger.info("-- Matcher with pattern accepting 2XX got created--");

		assertThat("Assertion 1", matcher1.matches(String.valueOf(response.getStatusCode())));
		logger.info("-- Assertion - response code matches 2XX pattern got executed--");
		extenttest.pass("Assertion - Response code matches 2XX pattern");

		//Capturing response to include the same on final report file
		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test1--");

		logger.info("-- Execution ended for USER_POST_API_TESTS - Test1--");

	}

	@Test(description = "Validate that API should return API status code = 200 created. NOTE: Usually POST req leads to 201 created response status code, but for this API as per API doc, we have to use 200", priority = 2, groups = "POST")
	public void Test2() {

		logger.info("-- Execution started for USER_POST_API_TESTS - Test2--");

		// Setting up extenttest object with current extentTest value
		extenttest = ReportUtil.GetTest();

		Response response = UserEndpoints.createUser(user);
		logger.info("-- CreateUser API got executed with user payload--");
		extenttest.info("POST API got executed with new user payload : " + user);

		assertThat("Assertion 2", response.getStatusCode(), is(200));
		logger.info("-- Assertion - response code is 200 got executed--");
		extenttest.pass("Assertion - Response code is 200 Ok");

		//Capturing response to include the same on final report file
		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test2--");

		logger.info("-- Execution ended for USER_POST_API_TESTS - Test2--");

	}

	@Test(description = "Validate that API should return a response payload ie. Non-empty response body and response header", priority = 3, groups = "POST")
	public void Test3() {

		logger.info("-- Execution started for USER_POST_API_TESTS - Test3--");

		// Setting up extenttest object with current extentTest value
		extenttest = ReportUtil.GetTest();

		Response response = UserEndpoints.createUser(user);
		logger.info("-- CreateUser API got executed with user payload--");
		extenttest.info("POST API got executed with new user payload : " + user);

		assertThat("Assertion 1 - Response body is Not null", response.getBody().asPrettyString(),
				is(not(isEmptyString())));
		logger.info("-- Assertion - response body is non-empty got executed--");
		extenttest.pass("Assertion - Response body is Non-empty");

		assertThat("Assertion 2 - Response header is Not null", response.getHeaders().exist(), is(true));
		logger.info("-- Assertion - response header is non-empty got executed--");
		extenttest.pass("Assertion - Response header is Non-empty");

		//Capturing response to include the same on final report file
		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test3--");

		logger.info("-- Execution ended for USER_POST_API_TESTS - Test3--");

	}

	@Test(description = "Validate that API should return response of type = JSON", priority = 4, groups = "POST")
	public void Test4() {

		logger.info("-- Execution started for USER_POST_API_TESTS - Test4--");

		// Setting up extenttest object with current extentTest value
		extenttest = ReportUtil.GetTest();

		Response response = UserEndpoints.createUser(user);
		logger.info("-- CreateUser API got executed with user payload--");
		extenttest.info("POST API got executed with new user payload : " + user);

		assertThat("Assertion 1 - Response type is JSON", response.getContentType(), is("application/json"));
		logger.info("-- Assertion - response type is Json got executed--");
		extenttest.pass("Assertion - Response type is JSON");

		//Capturing response to include the same on final report file
		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test4--");

		logger.info("-- Execution ended for USER_POST_API_TESTS - Test4--");

	}

	@Test(description = "Validate that API should should adhere to Response structure as per data model mentioned in API Spec or API Doc.- JSON SCHEMA VALIDATION\r\n", priority = 5, groups = "POST")
	public void Test5() {

		logger.info("-- Execution started for USER_POST_API_TESTS - Test5--");

		// Setting up extenttest object with current extentTest value
		extenttest = ReportUtil.GetTest();

		Response response = UserEndpoints.createUser(user);
		logger.info("-- CreateUser API got executed with user payload--");
		extenttest.info("POST API got executed with new user payload : " + user);

		assertThat("JSON SCHEMA VALIDATION", response.getBody().asPrettyString(),
				JsonSchemaValidator.matchesJsonSchema(ConfigUtil.GetJsonSchema("USER", "POST")));
		logger.info("-- Assertion - response schema validationn got executed--");
		extenttest.pass("Assertion - Response schema validation for response body");

		//Capturing response to include the same on final report file
		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test5--");

		logger.info("-- Execution ended for USER_POST_API_TESTS - Test5--");

	}

	@Test(description = "Validate that API should returns response body with content containing details as provided in request body along with Id key with new/unique generated id", priority = 6, groups = "POST")
	public void Test6() {

		logger.info("-- Execution started for USER_POST_API_TESTS - Test6--");

		// Setting up extenttest object with current extentTest value
		extenttest = ReportUtil.GetTest();

		Response response = UserEndpoints.createUser(user);
		logger.info("-- CreateUser API got executed with user payload--");
		extenttest.info("POST API got executed with new user payload : " + user);

		assertThat(response.jsonPath().getInt("code"), is(200));
		logger.info("-- Assertion - response code is 200 got executed--");
		extenttest.pass("Assertion - Response contains attribute 'code' as 200");

		assertThat(response.jsonPath().getString("type"), is("unknown"));
		logger.info("-- Assertion - type attribute in response body is unknown got executed--");
		extenttest.pass("Assertion - Response contains attribute 'type' as unknown");
		
		//Capturing response to include the same on final report file
		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test6--");

		logger.info("-- Execution ended for USER_POST_API_TESTS - Test6--");

	}

}
