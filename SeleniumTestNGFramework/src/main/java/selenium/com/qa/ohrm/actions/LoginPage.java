package selenium.com.qa.ohrm.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;


public class LoginPage extends BasePage {
	
   public WebElement element;
   public String username_id = "txtUsername";
   public String password_id = "txtPassword";
   public String loginBtn_id = "btnLogin";
   public String forgorPwd_xpath = "//a[contains(@href,'requestPasswordResetCode')]";
   
   protected static final Logger logger = Logger.getLogger(LoginPage.class);
   
   public void navigation() {
	   navigateToApplication();
   }
   
   public void setUserName() {
	   logger.info("set the username in username field of the login page");
	   baseUtil = new BaseUtil();
	   String uname = baseUtil.getConfigValueFromEnvFile("app_username");
	   this.element = getLocator("id", username_id);
	   sendText(this.element, uname);
   }
   
   public void setPassword() {
	   logger.info("set the password in password field of the login page");
	   baseUtil = new BaseUtil();
	   String pwd = baseUtil.getConfigValueFromEnvFile("app_password");
	   this.element = getLocator("id", password_id);
	   sendText(this.element, pwd);
   }
   
   public void submitCreds() {
	   logger.info("click on submit button on login page");
	   this.element = getLocator("id", loginBtn_id);
	   click(this.element);   
   }
   
}
