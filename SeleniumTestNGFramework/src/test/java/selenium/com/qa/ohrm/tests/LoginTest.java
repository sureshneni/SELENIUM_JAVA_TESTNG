package selenium.com.qa.ohrm.tests;


import org.testng.annotations.Test;
import selenium.com.qa.ohrm.actions.LoginPage;


public class LoginTest {
	LoginPage loginPage;
	
	@Test
	public void loginOhrm() {
		loginPage = new LoginPage();
		loginPage.navigateToApplication();
		loginPage.setUserName();
		loginPage.setPassword();
		loginPage.submitCreds();
	}

}
