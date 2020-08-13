package selenium.com.qa.ohrm.tests;

import java.io.File;
import selenium.com.qa.ohrm.actions.BasePage;
import selenium.com.qa.ohrm.actions.BaseUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentBDDReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class BaseTest extends BasePage {
	
	public static ExtentSparkReporter reporter;
	public static ExtentReports reports;
	public static ExtentTest test;
	BaseUtil baseUtil;
	public String reportFilePath="extent_report.html";
	
	@AfterSuite
	public void closeBrowser() {
		driver.close();
	}
	
	@BeforeSuite
	public void dirverCreation() {
		initialiseDriver();
	}
	
		
	@AfterSuite(dependsOnMethods = "closeBrowser")
	public void endReport(){
		System.out.println("flush the extent report");
		reports.flush();
	}
	
	@AfterMethod
	public void getScreenshotOnFail(ITestResult result) {
		String screenshotsPath = System.getProperty("user.dir") + "//test-output//screenshots//";
		System.out.println("screenshotsPath: "+screenshotsPath);
		System.out.println("ITestResult.FAILURE: "+ITestResult.FAILURE);
		System.out.println("result.getStatus(): "+result.getStatus());
		if(ITestResult.FAILURE == result.getStatus()) {
			System.out.println("Result status: "+result.getStatus());
			try {
				TakesScreenshot screenshot = (TakesScreenshot)driver;
				File srcPath = screenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcPath, new File(screenshotsPath + "//" + result.getName() + ".png"));
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		
	}

}
