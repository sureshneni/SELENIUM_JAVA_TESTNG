package selenium.com.qa.ohrm.tests;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import selenium.com.qa.ohrm.actions.LoginPage;


public class LoginTest extends BaseTest{
	
	LoginPage loginPage;
	ExtentConfig testReport;
	BaseTest baseTest;
	static ExtentReports extent;
    static ExtentTest test;

	
	@BeforeClass
	public void setReport(){
		extent = ExtentConfig.getInstance();
	}
	
	@Test
	public void loginOhrm() {
//		baseTest = new BaseTest();
		test = extent.createTest("Login to OHRM application");
		loginPage = new LoginPage();
		loginPage.navigateToApplication();
		loginPage.setUserName();
		loginPage.setPassword();
		loginPage.submitCreds();
	}
	
	@Test
	public void letsFail() {
		test = extent.createTest("Purposely make it fail to capture screenshot");
		test.fail("Make it fail");
	}
	
	@AfterClass
    public void tear()
    {
        extent.flush();
    }
	
}
