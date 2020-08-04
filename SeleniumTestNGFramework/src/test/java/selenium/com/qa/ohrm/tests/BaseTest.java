package selenium.com.qa.ohrm.tests;

import java.io.File;
import selenium.com.qa.ohrm.actions.BasePage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public class BaseTest extends BasePage {
	
	@AfterSuite
	public void closeBrowser() {
		driver.close();
	}
	
	@BeforeTest
	public void dirverCreation() {
		initialiseDriver();
	}
	
	@AfterMethod
	public void getScreenshotOnFail(ITestResult result) {
		
		if(ITestResult.FAILURE == result.getStatus()) {
			
			try {
				TakesScreenshot screenshot = (TakesScreenshot)driver;
				File srcPath = screenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcPath, new File("C://code//SELENIUM_JAVA_TESTNG//screenshots//abc.png"));
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		
	}

}
