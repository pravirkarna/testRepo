package testcases;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import ui.pageobject.LoginPage;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class TC_Login extends PageObjectBase{
	@Test
	public void testLogin_ValidCredentials() throws Exception{
		LoginPage loginPage =new LoginPage();
		
		test.log(LogStatus.INFO, "Verify Login Credentials");
		loginPage.setLoginCredentials();
		
		homePage=loginPage.HomeTab();
		
		homePage.ClickOnContractDropDown();
		homePage.ClickOnContract();
		homePage.tillcontract();
		
	}
}