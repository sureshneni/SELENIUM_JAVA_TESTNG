package selenium.com.qa.ohrm.tests;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.gargoylesoftware.htmlunit.javascript.host.file.File;

import selenium.com.qa.ohrm.actions.BaseUtil;

public class ExtentConfig extends BaseTest {
	
	static ExtentReports  extent;
	ExtentTest extentTest;
	public static ExtentSparkReporter reporter;
	File file;
	public static String reportFilePath="extent_report.html";
	static BaseUtil baseUtil;
	

	
	public static void getExtentConfig(){
		baseUtil = new BaseUtil();
		String filepath = System.getProperty("user.dir") + "\\test-output\\"+ reportFilePath;
		System.out.println("filepath: "+filepath);
		
			reporter = new ExtentSparkReporter(filepath);
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Browser: ", baseUtil.getConfigValueFromEnvFile("browserName"));
			extent.setSystemInfo("SDET", "SURESH BABU");
//			reporter.onScreenCaptureAdded(test, screenCapture);
	
	}
	
	public static ExtentTest createTest(String name){
        test = reports.createTest(name, "");
        return test;
    }
	
	public static ExtentReports getInstance() {
        if(extent == null) {
        	getExtentConfig();
        }   
        return extent;
    }
	


}
