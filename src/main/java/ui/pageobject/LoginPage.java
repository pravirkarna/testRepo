package ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.LogStatus;

import ui.pageobjects.pagehelpers.FunctionalLibrary;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class LoginPage extends PageObjectBase{
		
	@FindBy(xpath = "//input[@name='username']")
	public static WebElement Username_TextBox;
	
	@FindBy(xpath = "//input[@name='password']")
	public static WebElement Password_TextBox;
	
	@FindBy(xpath = "//button[text()='Sign In']")
	public static WebElement LoginButton;
	
	@FindBy(xpath = "//div[@title='Home']")
	public static WebElement HomeTab;
	
	@FindBy(xpath = "//*[@id='tablist']/li[1]/a")
	public static WebElement DocumentTab;
	
	@FindBy(xpath = "//html/body//font")
	public static WebElement err_Msg;
	

	
	
	
	public LoginPage() {

		PageFactory.initElements(driver, this);
		
      }
	
	
	public void setLoginCredentials() throws Exception {
		
		//String credentials[]= helper.getCredentials();
		
		helper.highlighter(driver, Username_TextBox);
		//Username_TextBox.sendKeys(credentials[1]);
		helper.EditBox(Username_TextBox, reader.getCellData("Credentials","Username", 2), test, "Username");
		
		helper.highlighter(driver, Password_TextBox);
		//Password_TextBox.sendKeys(credentials[1]);
		helper.EditBox(Password_TextBox, reader.getCellData("Credentials","Password", 2), test, "Password");
		
		helper.highlighter(driver, LoginButton);
		helper.Click(driver, test, LoginButton, "Login Button");
		
		
		helper.wait(driver, HomeTab, 20);
		if((helper.Verify_VisibleEnabled_Element(LoginPage.HomeTab))){
			
			test.log(LogStatus.PASS, "Verify valid Credential"  ,FunctionalLibrary.ExpectedResults +" Credential should be valid" + FunctionalLibrary.ActualResults+ "Credential is  valid ");
	
		} else {
			
			test.log(LogStatus.FAIL, "Verify valid Credential"  ,FunctionalLibrary.ExpectedResults +"Credential should be valid " + FunctionalLibrary.ActualResults+ "Credential is not valid ");
		}
		
	}
			

	public HomeTab HomeTab() throws InterruptedException {
		helper.highlighter(driver, HomeTab);
		return new HomeTab();
	}

	

}
