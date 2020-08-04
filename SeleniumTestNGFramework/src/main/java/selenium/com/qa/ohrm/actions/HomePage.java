package selenium.com.qa.ohrm.actions;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class HomePage extends BasePage{

	WebElement element;
	public String admin_id = "menu_admin_viewAdminModule";
	public String admin_user_mgmt_id = "menu_admin_UserManagement";
	public String admin_users_id = "menu_admin_viewSystemUsers";
	
	public void getDashboardUrl() {
		baseUtil = new BaseUtil();
		String dashboardURl = driver.getCurrentUrl();
		String expectedUrl = baseUtil.getConfigValueFromEnvFile("appUrl");
		Assert.assertEquals(dashboardURl, expectedUrl+"index.php/dashboard");	
	}
	
	public void getAdminMenu() {
		logger.info("user mouse over to Admin menu");
		Actions actions = new Actions(driver);
		this.element = getLocator("id", admin_id);
		
		
		actions.moveToElement(this.element).perform();
		try{
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void getUserManagementSubMenu() {
		logger.info("user mouse over to user management sub menu under admin menu");
		this.element = getLocator("id", admin_user_mgmt_id);
		
		mouseMoveElement(element);
		try{
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void getUsersSubMenu() {
		logger.info("user mouse over to user sub menu under user management sub menu and click on it");
		this.element = getLocator("id", admin_users_id);
		element.click();
		try{
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void testExcel() {
		baseUtil = new BaseUtil();
		String role = baseUtil.readDataFromExcel("userMgmt", ExcelField.UserRole.toString());
//		baseUtil.readDataFromExcel("userMgmt", "Password");
		System.out.println("role: "+role);
	}
	
	
}
