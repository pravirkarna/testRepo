package testcases;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import ui.pageobject.LoginPage;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class TestRole extends PageObjectBase {
    @Test
    public void testLogin_EmployeeMaster() throws Exception {

        loginPage = new LoginPage();
        loginPage.clickonLOGIN_Button();
        test.log(LogStatus.INFO, "Verify Login Credentials");
        loginPage.setLoginCredentials();

        homePage = loginPage.Homepage();
        homePage.clickOnCentralAdmin();

        role = homePage.Role();
        role.EnterDataOnRole();
    }
}
