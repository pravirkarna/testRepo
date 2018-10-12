package ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.pageobjects.pagehelpers.PageObjectBase;

public class HomePage extends PageObjectBase {
	
	
	@FindBy(xpath = ".//*[@id='RULE_KEY']//div[5]/span/i/img")
	public WebElement Power_Button;
	
	@FindBy(xpath = "//span[contains(text(),'Switch Role')]")
	public WebElement SwitchRole;
	
	@FindBy(xpath = ".//div/ul/li[2]/a/span[2]/span[contains(text(),'Central Admin')]")
	public WebElement CentralAdmin;
	
	@FindBy(xpath = "//div/ul/li/a/span/span[contains(text(),'Employee Master')]")
	public WebElement EmployeeMaster;
	
	@FindBy(xpath = "//td/span[text()='Click to start']")
	public WebElement Clicktostart;
	
	@FindBy(xpath = "html/body/div[8]/div[1]/div[3]/div[1]/div[1]/div[2]/div/div/div[3]/div[3]/div/table/tbody[2]/tr[48]/td/div/div/span[1]/a")////h3[@title='SPA - PAT ' and text()='SPA PAT.xml']//following-sibling::div/span[@title='Create New']
	public WebElement SPA_PATTemplate;
	
	@FindBy(xpath = "//div[@class='title-bar']")
	public WebElement TitleBar;

	
	
	public HomePage() {

		PageFactory.initElements(driver, this);
		
    }
	
	public void clickOnCentralAdmin() throws Exception {

		helper.Click(driver,test,SwitchRole , "Contract DropDown");

		helper.highlighter(driver, SwitchRole);
		helper.Click(driver,test,SwitchRole , "Contract DropDown");
		helper.wait(driver, CentralAdmin, 20);

		helper.highlighter(driver, CentralAdmin);
		helper.Click(driver,test,CentralAdmin , "Contract DropDown");
		helper.wait(driver, EmployeeMaster, 20);
	}
	

	//PAGES
	public EmployesMaster employesMaster() throws InterruptedException {
		helper.highlighter(driver, EmployeeMaster);
		helper.Click(driver,test,EmployeeMaster , "Contract DropDown");
		return new EmployesMaster();
	}

}
