package testcases;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import ui.pageobject.LoginPage;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class TestActivity  extends PageObjectBase {
    @Test
    public void testActivity() throws Exception {

        loginPage = new LoginPage();
        loginPage.clickonLOGIN_Button();
        test.log(LogStatus.INFO, "Verify Login Credentials");
        loginPage.setLoginCredentials();

        homePage = loginPage.Homepage();
        homePage.clickOnCentralAdmin();

        activity = homePage.Activity();
        activity.EnterDataOnActivity();
    }
}
