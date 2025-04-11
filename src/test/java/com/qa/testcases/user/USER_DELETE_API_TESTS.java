package com.qa.testcases.user;

import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import com.qa.base.BaseTest;
import com.qa.endpoints.UserEndpoints;
import com.qa.payloads.User;
import com.qa.utils.CommonUtil;
import com.qa.utils.ConfigUtil;
import com.qa.utils.DBUtil;
import com.qa.utils.ReportUtil;

import io.restassured.response.Response;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import static org.hamcrest.Matchers.*;

import java.time.Duration;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class USER_DELETE_API_TESTS extends BaseTest {

	private static Logger logger = LogManager.getLogger(USER_DELETE_API_TESTS.class);
	private static ExtentTest extenttest;

	@Test(description = "Validate that API should return API status code within 2XX series", groups = "DELETE")
	public void Test1() throws InterruptedException {

		logger.info("-- Execution started for USER_DELETE_API_TESTS - Test1--");

		// Initializing extenttest reference with current ExtentTest object from
		// ReportUtil
		extenttest = ReportUtil.GetTest();

		UserEndpoints.createUser(user);
		logger.info("-- createUser API got executed with user's username--");
		extenttest.info("POST API got executed with new user payload : " + user);

		CommonUtil.pause(Duration.ofSeconds(5));

		// API call
		Response response = UserEndpoints.deleteUser(user.getUsername());
		logger.info("-- deleteUser API got executed with user's username--");
		extenttest.info("DELETE API got executed with user payload : " + user.getUsername());

		// Hamcrest Matcher
		Matcher<String> matcher1 = Matchers.matchesPattern("^2[0-9][0-9]$");
		logger.info("-- Matcher with pattern accepting 2XX got created--");

		// Assertion
		assertThat("API response status code lies within 2XX",
				matcher1.matches(String.valueOf(response.getStatusCode())));
		logger.info("-- Assertion - response code matches 2XX pattern got executed--");
		extenttest.pass("Assertion got executed where response code matches 2XX pattern");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test1--");

		logger.info("-- Execution ended for USER_DELETE_API_TESTS - Test1--");

	}

	@Test(description = "Validate that API should return API status code = 200. NOTE: Generally, we use 204 - No Content Found as the expected status code for Delete APIs", groups = "DELETE")
	public void Test2() throws InterruptedException {

		logger.info("-- Execution started for USER_DELETE_API_TESTS - Test2--");

		// Initializing extenttest reference with current ExtentTest object from
		// ReportUtil
		extenttest = ReportUtil.GetTest();

		UserEndpoints.createUser(user);
		logger.info("-- createUser API got executed with user's username--");
		extenttest.info("POST API got executed with new user payload : " + user);

		CommonUtil.pause(Duration.ofSeconds(5));

		// API call
		Response response = UserEndpoints.deleteUser(user.getUsername());
		logger.info("-- deleteUser API got executed with user's username--");
		extenttest.info("DELETE API got executed with user payload : " + user.getUsername());

		// Assertion
		assertThat("API response status code is 200.", response.statusCode(), is(200));
		logger.info("-- Assertion - response code is 200--");
		extenttest.pass("Assertion got executed where response code is 200");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test2--");

		logger.info("-- Execution ended for USER_DELETE_API_TESTS - Test2--");

	}

	@Test(description = "Validate that API should return a response payload with Non-empty response body and Non-empty response header", groups = "DELETE")
	public void Test3() throws InterruptedException {

		logger.info("-- Execution started for USER_DELETE_API_TESTS - Test3--");

		// Initializing extenttest reference with current ExtentTest object from
		// ReportUtil
		extenttest = ReportUtil.GetTest();

		UserEndpoints.createUser(user);
		logger.info("-- createUser API got executed with user's username--");
		extenttest.info("POST API got executed with new user payload : " + user);

		CommonUtil.pause(Duration.ofSeconds(5));

		// API call
		Response response = UserEndpoints.deleteUser(user.getUsername());
		logger.info("-- deleteUser API got executed with user's username--");
		extenttest.info("DELETE API got executed with user payload : " + user.getUsername());

		// Assertion
		assertThat("API response body is Non-empty", response.getBody().asPrettyString(), is(not(isEmptyString())));
		logger.info("-- Assertion - response body is non-empty --");
		extenttest.pass("Assertion - Response body is non-empty");

		assertThat("API response header is Non-empty", response.headers().exist(), is(true));
		logger.info("-- Assertion - response header is non-empty --");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test3--");

		logger.info("-- Execution ended for USER_DELETE_API_TESTS - Test3--");

	}

	@Test(description = "Validate that API should should adhere to Response structure as per data model mentioned in API Spec or API Doc.- JSON SCHEMA VALIDATION", groups = "DELETE")
	public void Test4() throws InterruptedException {

		logger.info("-- Execution started for USER_DELETE_API_TESTS - Test4--");

		// Initializing extenttest reference with current ExtentTest object from
		// ReportUtil
		extenttest = ReportUtil.GetTest();

		UserEndpoints.createUser(user);
		logger.info("-- createUser API got executed with user's username--");
		extenttest.info("POST API got executed with new user payload : " + user);

		CommonUtil.pause(Duration.ofSeconds(5));

		// API call
		Response response = UserEndpoints.deleteUser(user.getUsername());
		logger.info("-- deleteUser API got executed with user's username--");
		extenttest.info("DELETE API got executed with user payload : " + user.getUsername());

		// Assertion
		assertThat("API response json schema validation", response.getBody().asPrettyString(),
				JsonSchemaValidator.matchesJsonSchema(ConfigUtil.GetJsonSchema("USER", "DELETE")));
		logger.info("-- Assertion : Response schema validation --");
		extenttest.pass("Assertion - Json Schema validation for response body");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test4--");

		logger.info("-- Execution ended for USER_DELETE_API_TESTS - Test4 --");

	}

}
