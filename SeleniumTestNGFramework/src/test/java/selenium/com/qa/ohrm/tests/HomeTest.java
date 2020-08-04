package selenium.com.qa.ohrm.tests;

import org.openqa.selenium.logging.NeedsLocalLogs;
import org.testng.annotations.Test;

import selenium.com.qa.ohrm.actions.HomePage;
import selenium.com.qa.ohrm.actions.UserManagementPage;

public class HomeTest {
	
	HomePage homePage;
	UserManagementPage userManagementPage;
	
	@Test (description = "Navigate to Add users page")
	public void navigateToAddUsersSection() {
		homePage = new HomePage();
		homePage.getAdminMenu();
		homePage.getUserManagementSubMenu();
		homePage.getUsersSubMenu();
	}
	
//	@Test
//	public void excelTest() {
//		homePage = new HomePage();
//		homePage.testExcel();
//	}
	

	
	@Test(dependsOnMethods = {"navigateToAddUsersSection"})
	public void addUserTest() {
		userManagementPage = new UserManagementPage();
		userManagementPage.clickOnAddButton();
		userManagementPage.addUserDetails();	
		userManagementPage.clickOnSaveButton();
	}
	
	@Test(dependsOnMethods = {"addUserTest"})
	public void searchAndDeleteUser() {
		userManagementPage = new UserManagementPage();
		userManagementPage.searchUser();
		userManagementPage.deleteUser();
	}

}
