package selenium.com.qa.ohrm.tests;

import org.openqa.selenium.logging.NeedsLocalLogs;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import selenium.com.qa.ohrm.actions.HomePage;
import selenium.com.qa.ohrm.actions.UserManagementPage;

public class HomeTest extends BaseTest {
	
	HomePage homePage;
	UserManagementPage userManagementPage;
	BaseTest baseTest;
	
	static ExtentReports extent;
    static ExtentTest test;
	
    @BeforeClass
	public void setReport(){
		extent = ExtentConfig.getInstance();
	}
    
	@Test (description = "Navigate to Add users page")
	public void navigateToAddUsersSection() {
//		baseTest = new BaseTest();
		test = extent.createTest("Naviagte to Add user sub menu");
		homePage = new HomePage();
		homePage.getAdminMenu();
		homePage.getUserManagementSubMenu();
		homePage.getUsersSubMenu();
//		endReport();
	}
	
//	@Test
//	public void excelTest() {
//		homePage = new HomePage();
//		homePage.testExcel();
//	}
	

	
	@Test(dependsOnMethods = {"navigateToAddUsersSection"})
	public void addUserTest() {
		test = extent.createTest("Navigate to add user section");
		userManagementPage = new UserManagementPage();
		userManagementPage.clickOnAddButton();
		userManagementPage.addUserDetails();	
		userManagementPage.clickOnSaveButton();
//		endReport();
	}
	
	@Test(dependsOnMethods = {"addUserTest"})
	public void searchAndDeleteUser() {
		
		test = extent.createTest("Search and Delete user");
		userManagementPage = new UserManagementPage();
		userManagementPage.searchUser();
		userManagementPage.deleteUser();
//		endReport();
	}
	
	@AfterClass
    public void tear()
    {
        extent.flush();
    }

}
