package selenium.com.qa.ohrm.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;


public class BasePage {
	
	public static WebDriver driver;
	public static WebElement element;
	BaseUtil baseUtil;
	public String browser;
	public String rootDriverPath = System.getProperty("user.dir");
	WebDriverWait wait;
	
	protected static final Logger logger = Logger.getLogger(BasePage.class);
	
	public void initialiseDriver() {
		
		baseUtil = new BaseUtil();
		browser = baseUtil.getConfigValueFromEnvFile("browserName");
		logger.info("browser name: "+browser);
		
				
		switch(browser) {
		case "chrome" :
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//drivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		}
		
	}
	
	public void navigateToApplication() {
		baseUtil = new BaseUtil();
		String appURL = baseUtil.getConfigValueFromEnvFile("appUrl");
		logger.info("App url: "+appURL);
		driver.navigate().to(appURL);
		
	}
	
	
	public WebElement getLocator(String locatorType, String locator) {
		
		switch(locatorType) {
		case "id":
			element = driver.findElement(By.id(locator));
			break;
		case "class":
			element = driver.findElement(By.className(locator));
			break;
		case "name":
			element = driver.findElement(By.name(locator));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(locator));
			break;
		case "css":
			element = driver.findElement(By.cssSelector(locator));
			break;
	   default:
		   System.out.println("no locator matched");
		
		}
		return element;
	}
	
	public void click(WebElement element) {
		this.waitForElementToBeVisible(element);
		element.click();
	}
	
	public void sendText(WebElement element, String inputText) {
		this.waitForElementToBeVisible(element);
		element.sendKeys(inputText);
	}
	
	public void selectDropDown(WebElement element, String option, String value) {
		this.waitForElementToBeVisible(element);
		Select select = new Select(element);
		click(element);
		switch(option) {
		case "value":
			select.selectByValue(value);
			break;
		case "index" :
			select.selectByIndex(Integer.valueOf(value));
			break;
		case "text":
			select.selectByVisibleText(value);
			break;
		}
	}
	
	public void waitForElementToBeClickable(WebElement element){
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(element));		
	}
	
	public void waitForElementToBeVisible(WebElement element) {
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void getScreenshot() {
		
		try {
			TakesScreenshot screenshot=(TakesScreenshot)driver;
			File src = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src,  new File("C://code//SELENIUM_JAVA_TESTNG//screenshots//abc.png"));
			
		} catch( IOException ioe) {
			
		}
		
	}
	
	public void mouseMoveElement(WebElement element) {
		logger.info("mouseover = move to element and click");
		this.waitForElementToBeVisible(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();	
	}
	
	public enum ExcelField{
		UserRole,
		EmployeeName,
		Username,
		Status,
		Password,
		ConfirmPassword
	}
	
	public void drysleep(){
		try{
			Thread.sleep(3000);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
