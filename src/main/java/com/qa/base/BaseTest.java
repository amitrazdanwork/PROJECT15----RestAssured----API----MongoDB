package com.qa.base;

import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.payloads.User;
import com.qa.utils.ConfigUtil;
import com.qa.utils.DBUtil;
import com.qa.utils.ReportUtil;

public class BaseTest {

	private static Logger logger = LogManager.getLogger(BaseTest.class);

	public static ConfigUtil config;
	public static ReportUtil reportutil;
	public static User user;

	@BeforeSuite
	public void Suite_Setup() {

		System.out.println("Before suite");
		ReportUtil.ConfigureExtentReport(); // Setup Extent report.

		config = new ConfigUtil();

		DBUtil.Setup_DB();

	}

	@AfterSuite
	public void Suite_Teardown() {

		System.out.println("After suite");
		ReportUtil.GenerateExtentReport(); // Generate report and flush the same to reports folder

	}

	@BeforeTest
	public void Setup(ITestContext context) {

		logger.info("-- BeforeTest execution started : USER_PUT_API_TESTS --");

		DBUtil.Collection_Setup(context.getCurrentXmlTest().getParameter("module"));

		logger.info("-- BeforeTest execution ended : USER_PUT_API_TESTS --");

	}

	@BeforeMethod
	public void Method_Setup(Method m, ITestContext context) {

		System.out.println("Before method");

		String[] grps = m.getAnnotation(Test.class).groups();

		DBUtil.Document_Setup(grps[0]);
		
		DBUtil.InitializePOJO(context.getCurrentXmlTest().getParameter("module"));

		// Create ExtentTest object
		ReportUtil.CreateTest(m.getName());

		// Get test groups.
		String[] MethodGroups = m.getAnnotation(Test.class).groups();

		// Assign category to extent-test
		for (String string : MethodGroups) {
			ReportUtil.AssignCategoryToTest(ReportUtil.GetTest(), string);
		}

		// Assign Author to extent-Test
		ReportUtil.AssignAuthorToTest(ReportUtil.GetTest(), context.getCurrentXmlTest().getParameter("author"));

		// Assign Device to extentTest
		ReportUtil.AssignDeviceToTest(ReportUtil.GetTest(), context.getCurrentXmlTest().getParameter("device"));

		// Initialize POJO class with DB document data
		DBUtil.InitializePOJO(context.getCurrentXmlTest().getParameter("module"));

	}

	@AfterMethod
	public void Method_Teardown(ITestResult result) {

		System.out.println("after method");

		ReportUtil.AssignResultToTest(ReportUtil.GetTest(), result);

	}

}
