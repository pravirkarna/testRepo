package testcases;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import ui.pageobject.LoginPage;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class TestEmployeeMaster extends PageObjectBase{
	@Test
	public void testLogin_EmployeeMaster() throws Exception {

        loginPage = new LoginPage();
        loginPage.clickonLOGIN_Button();
        test.log(LogStatus.INFO, "Verify Login Credentials");
        loginPage.setLoginCredentials();

        homePage = loginPage.Homepage();
        homePage.clickOnCentralAdmin();

        employesMaster = homePage.employesMaster();
        employesMaster.EnterDataOnCreateEmployes();



    }


}