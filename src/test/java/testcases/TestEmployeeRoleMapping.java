package testcases;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import ui.pageobject.LoginPage;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class TestEmployeeRoleMapping extends PageObjectBase {
    @Test
    public void testEmployeeRoleMapping() throws Exception {

        loginPage = new LoginPage();
        loginPage.clickonLOGIN_Button();
        test.log(LogStatus.INFO, "Verify Login Credentials");
        loginPage.setLoginCredentials();

        homePage = loginPage.Homepage();
        homePage.clickOnCentralAdmin();

        employeeRoleMapping = homePage.employeeRoleMapping();
        employeeRoleMapping.EnterDataOnRoleMapping();



    }




}
