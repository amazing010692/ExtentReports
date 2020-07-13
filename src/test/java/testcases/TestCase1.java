package testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {
	
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	@BeforeTest
	public void setReport() {
		htmlReporter = new ExtentSparkReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("JJSG'S Test Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Test Automation Engineer", "Janielle Joy Gregorio");
		extent.setSystemInfo("Organization", "Jan's Group of Testers");
		extent.setSystemInfo("Build No.", "JJSG-amazing010692");
	}
	
	@AfterTest
	public void endReport() {
		extent.flush();
	}
	
	//Pass, Fail and Skip a Test.
	
	@Test
	public void doLogin() {
		test = extent.createTest("Login Test");
		System.out.println("Executing Login Test");
	}
	
	@Test
	public void doUserReg() {
		test = extent.createTest("User Registration Test");
		Assert.fail("User Registartion Test Failed");
	}
	
	@Test
	public void isSkip() {
		test = extent.createTest("Skip Test");
		throw new SkipException("Skipping the test case");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			
		} else if(result.getStatus() == ITestResult.SKIP) {
			
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + "  PASSED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.pass(m);
		}
	}

}
