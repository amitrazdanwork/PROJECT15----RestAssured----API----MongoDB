package com.qa.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtil {

	protected static ExtentReports reports;
	protected static ExtentSparkReporter reporter;
	public static ExtentTest extenttest;
	protected static String reportname;

	/* Method for configuring extent report file */
	public static void ConfigureExtentReport() {

		reports = new ExtentReports();

		reporter = new ExtentSparkReporter(new File(".//reports//" + ReportUtil.GetDynamicReportName() + ".html"));

		reports.attachReporter(reporter);

		ReportUtil.SetEnvironmentInfo();

	}

	/* Method for getting the Dynamic report name */
	public static String GetDynamicReportName() {

		reportname = new SimpleDateFormat("dd-MM-yyy_hh.mm.ss").format(new Date());

		return reportname;
	}

	/* Method for setting the environment information to report file */
	public static void SetEnvironmentInfo() {

		reports.setSystemInfo("OS", System.getProperty("os.name"));

		reports.setSystemInfo("JAVA VERSION", System.getProperty("java.version"));

	}

	/* Method for creation of new ExtentTest */
	public static void CreateTest(String MethodName) {

		extenttest = reports.createTest(MethodName);

	}

	/* Method for getting of ExtentTest */
	public static ExtentTest GetTest() {

		return extenttest;

	}

	/* Method for assigning groups to ExtentTest */
	public static void AssignCategoryToTest(ExtentTest extenttest, String Category) {

		extenttest.assignCategory(Category);

	}

	/* Method for assigning device to ExtentTest */
	public static void AssignDeviceToTest(ExtentTest extenttest, String DeviceName) {

		extenttest.assignDevice(DeviceName);

	}

	/* Method for assigning author to ExtentTest */
	public static void AssignAuthorToTest(ExtentTest extenttest, String AuthorName) {

		extenttest.assignAuthor(AuthorName);

	}

	/* Method for generation and flush the report file to reports folder */
	public static void GenerateExtentReport() {

		reports.flush();
	}

	public static void AssignResultToTest(ExtentTest extentTest, ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {

			extentTest.log(Status.INFO,
					MarkupHelper.createCodeBlock(CommonUtil.GetResponseString(), CodeLanguage.JSON));
			extentTest.pass("Test got passed !!!");

		} else if (result.getStatus() == ITestResult.FAILURE) {

			extentTest.log(Status.INFO,
					MarkupHelper.createCodeBlock(CommonUtil.GetResponseString(), CodeLanguage.JSON));
			extentTest.fail("Test got failed !!!");

		} else if (result.getStatus() == ITestResult.SKIP) {

			extentTest.log(Status.INFO,
					MarkupHelper.createCodeBlock(CommonUtil.GetResponseString(), CodeLanguage.JSON));
			extentTest.skip("Test got skipped !!!" + CommonUtil.GetResponseString());

		}

	}

}
