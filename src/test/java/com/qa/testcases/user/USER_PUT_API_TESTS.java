package com.qa.testcases.user;

import org.testng.annotations.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.github.javafaker.Faker;
import com.qa.base.BaseTest;
import com.qa.endpoints.UserEndpoints;
import com.qa.payloads.User;
import com.qa.utils.CommonUtil;
import com.qa.utils.ConfigUtil;
import com.qa.utils.DBUtil;
import com.qa.utils.ReportUtil;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.qa.endpoints.UserEndpoints;
import com.qa.payloads.User;
import static org.hamcrest.MatcherAssert.*;

import javax.annotation.MatchesPattern;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.restassured.module.jsv.JsonSchemaValidator;
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

public class USER_PUT_API_TESTS extends BaseTest {

	private static Logger logger = LogManager.getLogger(USER_PUT_API_TESTS.class);
	private static ExtentTest extenttest;

	@Test(description = "Validate that API should return API status code within 2XX series", priority = 1, groups = "PUT")
	public void Test1() {

		logger.info("-- Execution started for USER_PUT_API_TESTS - Test1--");

		extenttest = ReportUtil.GetTest();

		Response response = UserEndpoints.updateUser(user.getUsername(), this.user);
		logger.info("-- updateUser API got executed with user's usernam and user payloade--");
		extenttest
				.info("PUT API got executed with username : " + user.getUsername() + " and new user payload : " + user);

		Matcher<String> matcher1 = Matchers.matchesPattern("^20[0-9]$");
		logger.info("-- Matcher with pattern accepting 2XX got created--");

		assertThat("Assertion 1", matcher1.matches(String.valueOf(response.getStatusCode())));
		logger.info("-- Assertion - response code matches 2XX pattern got executed--");
		extenttest.pass("Assertion got executed where response code matches 2XX pattern");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test1--");

		logger.info("-- Execution ended for USER_PUT_API_TESTS - Test1--");

	}

	@Test(description = "Validate that API should return API status code = 200 resource updated successfully or 201 incase record got saved successfully ", priority = 2, groups = "PUT")
	public void Test2() {

		logger.info("-- Execution started for USER_PUT_API_TESTS - Test2--");

		extenttest = ReportUtil.GetTest();
		
		Response response = UserEndpoints.updateUser(this.user.getUsername(), this.user);
		extenttest
				.info("PUT API got executed with username : " + user.getUsername() + " and new user payload : " + user);

		assertThat("Assertion 2", response.getStatusCode(), is(200));
		logger.info("-- Assertion - response status code is 200 - Test2--");
		extenttest.pass("Assertion - Response code is 200 ");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test2--");

		logger.info("-- Execution ended for USER_PUT_API_TESTS - Test2--");

	}

	@Test(description = "Validate that API should return a response payload ie. Non-empty response body and response headers", priority = 3, groups = "PUT")
	public void Test3() {

		logger.info("-- Execution started for USER_PUT_API_TESTS - Test3--");

		extenttest = ReportUtil.GetTest();
		
		Response response = UserEndpoints.updateUser(this.user.getUsername(), this.user);
		extenttest
				.info("PUT API got executed with username : " + user.getUsername() + " and new user payload : " + user);

		System.out.println("PUT : " + response.asPrettyString());

		assertThat("Assertion 1 - Non-empty Response body", response.getBody().asPrettyString(),
				is(not(isEmptyString())));
		logger.info("-- Assertion - response bpdy is non-empty - Test3--");
		extenttest.pass("Assertion - Response body is non-empty ");

		assertThat("Assertion 2 - Non-empty Headers", response.getHeaders().exist(), is(true));
		logger.info("-- Assertion - response headers is non-empty - Test3--");
		extenttest.pass("Assertion - Response headers is non-empty ");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test3--");

		logger.info("-- Execution ended for USER_PUT_API_TESTS - Test3--");
	}

	@Test(description = "Validate that API should return response of type = JSON", priority = 4, groups = "PUT")
	public void Test4() {

		logger.info("-- Execution started for USER_PUT_API_TESTS - Test4--");

		extenttest = ReportUtil.GetTest();
		
		Response response = UserEndpoints.updateUser(this.user.getUsername(), this.user);
		logger.info("-- PUT API got executed - Test4--");
		extenttest
				.info("PUT API got executed with username : " + user.getUsername() + " and new user payload : " + user);

		assertThat("Assertion 1 - Response type is JSON", response.getContentType(), is("application/json"));
		logger.info("-- Assertion - Response type is JSON - Test4--");
		extenttest.pass("Assertion - Response type is JSON ");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test4--");

		logger.info("-- Execution ended for USER_PUT_API_TESTS - Test4--");
	}

	@Test(description = "Validate that API should should adhere to Response structure as per data model mentioned in API Spec or API Doc.- JSON SCHEMA VALIDATION", priority = 5, groups = "PUT")
	public void Test5() {

		logger.info("-- Execution ended for USER_PUT_API_TESTS - Test5--");

		extenttest = ReportUtil.GetTest();
		
		Response response = UserEndpoints.updateUser(this.user.getUsername(), this.user);
		extenttest
				.info("PUT API got executed with username : " + user.getUsername() + " and new user payload : " + user);

		assertThat("Assertion 1 - Non-empty Response body", response.getBody().asPrettyString(),
				JsonSchemaValidator.matchesJsonSchema(ConfigUtil.GetJsonSchema("USER", "PUT")));
		extenttest.info("Assertion - JsonSchema validation for response body content ");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test5--");

		logger.info("-- Execution ended for USER_PUT_API_TESTS - Test5--");
	}

	@Test(description = "Valdiate that API should return response body with content containing details as provided in request body .", priority = 6, groups = "PUT")
	public void Test6() {

		logger.info("-- Execution ended for USER_PUT_API_TESTS - Test6--");

		extenttest = ReportUtil.GetTest();
		
		Response response = UserEndpoints.updateUser(this.user.getUsername(), this.user);
		extenttest
				.info("PUT API got executed with username : " + user.getUsername() + " and new user payload : " + user);

		System.out.println(response.asPrettyString());

		assertThat("Assertion 1 - code attribute is 200", response.jsonPath().getInt("code"), is(200));
		extenttest.info("Assertion - Response body contains attribute code = 200 ");

		assertThat("Assertion 2 - type attribute is unknown", response.jsonPath().getString("type"), is("unknown"));
		extenttest.info("Assertion - Response body contains attribute type = unknown ");

		assertThat("Assertion 3 - message attribute is 20", response.jsonPath().getInt("message"), is(20));
		extenttest.info("Assertion - Response body contains message type = 20 ");

		CommonUtil.CaptureResponse(response);
		logger.info("-- Response got captured - Test6--");

		logger.info("-- Execution ended for USER_PUT_API_TESTS - Test6--");
	}


}
