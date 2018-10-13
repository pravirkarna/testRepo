package ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;

import ui.pageobjects.pagehelpers.FunctionalLibrary;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class LoginPage extends PageObjectBase{
		
	@FindBy(xpath = "//li/a[contains(text(),'LOGIN')]")
	public static WebElement LOGIN_Button;
	
	@FindBy(xpath = ".//select[@id='Authentication']")
	public static WebElement DropDown;
	
	@FindBy(xpath = ".//input[@id='phoneNum']")
	public static WebElement MobileNumber;
	
	@FindBy(xpath = ".//input[@id='phoneOTP']")
	public static WebElement Password;
	
	@FindBy(xpath = ".//button[@id='sub_1']")
	public static WebElement Login;

	@FindBy(xpath = "//*[@id='RULE_KEY']//div[5]/span/i/img")
	public static WebElement power;
	
	public LoginPage() {

		PageFactory.initElements(driver, this);
		
      }

	public void clickonLOGIN_Button()throws Exception{
		helper.highlighter(driver,LOGIN_Button);
		helper.Click(driver, test,LOGIN_Button,"LOGIN BUTTON");
		helper.wait(driver,DropDown,30);
	}
	
	public void setLoginCredentials() throws Exception {
		

		helper.highlighter(driver, DropDown);
		helper.selectfromDropDown(driver,test,DropDown,"Login with Mobile Number","Drop Down value");
		helper.wait(driver,MobileNumber,30);

		helper.highlighter(driver, MobileNumber);
		helper.EditBox(MobileNumber, reader.getCellData("Credentials","MobileNumber", 2), test, "Mobile Number");


		helper.highlighter(driver, Password);
		helper.EditBox(Password, reader.getCellData("Credentials","Password", 2), test, "Password");
		
		helper.highlighter(driver, Login);
		helper.Click(driver, test, Login, "Login Button");
		
		
		helper.wait(driver, power, 20);
		if((helper.Verify_VisibleEnabled_Element(power))){
			
			test.log(LogStatus.PASS, "Verify valid Credential"  ,FunctionalLibrary.ExpectedResults +" Credential should be valid" + FunctionalLibrary.ActualResults+ "Credential is  valid ");
	
		} else {
			
			test.log(LogStatus.FAIL, "Verify valid Credential"  ,FunctionalLibrary.ExpectedResults +"Credential should be valid " + FunctionalLibrary.ActualResults+ "Credential is not valid ");
		}
		
	}
			

	public HomePage Homepage() throws InterruptedException {

		return new HomePage();
	}

	

}
