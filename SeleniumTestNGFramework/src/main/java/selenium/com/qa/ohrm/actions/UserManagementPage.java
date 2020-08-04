package selenium.com.qa.ohrm.actions;

import java.lang.reflect.Executable;

import org.openqa.selenium.WebElement;

import selenium.com.qa.ohrm.actions.BasePage.ExcelField;

public class UserManagementPage extends BasePage {
	
	WebElement element;
	public String add_btn_name = "btnAdd";
	public String save_btn_name = "btnSave";
	public String search_btn_class = "searchbutton";
	public String delete_btn_id = "btnDelete";
	
	public String user_role_select_id = "systemUser_userType";
	public String emp_name_id = "systemUser_employeeName_empName";
	public String username_id = "systemUser_userName";
	public String status_select_id = "systemUser_status";
	public String password_id = "systemUser_password";
	public String confirm_pwd_id = "systemUser_confirmPassword";
	
	public String search_username_id = "searchSystemUser_userName";
	public String search_results_xpath = "//input[contains(@id, 'ohrmList_chkSelectRecord')]";
	public String deleteDialogOk_id = "dialogDeleteBtn";
	
	public void clickOnAddButton() {
		this.element = getLocator("name", add_btn_name);
		click(element);
	}
	
	public void clickOnSaveButton() {
		this.element = getLocator("name", save_btn_name);
		click(element);
	}
	
	public void clickOnSearchButton() {
		this.element = getLocator("class", search_btn_class);
		click(element);
	}
	
	public void addUserDetails() {
		baseUtil = new BaseUtil();
		String userrole = baseUtil.readDataFromExcel("userMgmt", ExcelField.UserRole.toString());
		String empName =  baseUtil.readDataFromExcel("userMgmt", ExcelField.EmployeeName.toString());
		String uname =  baseUtil.readDataFromExcel("userMgmt", ExcelField.Username.toString());
		String status =  baseUtil.readDataFromExcel("userMgmt", ExcelField.Status.toString());
		String pwd =  baseUtil.readDataFromExcel("userMgmt", ExcelField.Password.toString());
		String confirmpwd =  baseUtil.readDataFromExcel("userMgmt", ExcelField.ConfirmPassword.toString());
		
		this.element = getLocator("id", user_role_select_id);
		selectDropDown(element, "text", userrole);
		drysleep();
		
		this.element = getLocator("id", emp_name_id);
		sendText(element, empName);
		drysleep();
		
		this.element = getLocator("id", username_id);
		sendText(element, uname);
		drysleep();
		
		this.element = getLocator("id", status_select_id);
		selectDropDown(element, "text", "Enabled");
		drysleep();
		
		this.element = getLocator("id", password_id);
		sendText(element, pwd);
		drysleep();
		
		this.element = getLocator("id", confirm_pwd_id);
		sendText(element, confirmpwd);
	}
	
	public void searchUser() {
		baseUtil = new BaseUtil();
		String uname =  baseUtil.readDataFromExcel("userMgmt", ExcelField.Username.toString());
		this.element = getLocator("id", search_username_id);
		sendText(element, uname);
		drysleep();
		this.clickOnSearchButton();	
		drysleep();
	}
	
	public void deleteUser() {
		this.element = getLocator("xpath", search_results_xpath);
		click(element);
		drysleep();
		this.element = getLocator("id", delete_btn_id);
		click(element);
		drysleep();
		this.element = getLocator("id", deleteDialogOk_id);
		click(element);
		drysleep();
	}

}
