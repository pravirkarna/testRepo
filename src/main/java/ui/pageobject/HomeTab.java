package ui.pageobject;

import java.util.function.DoubleConsumer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.pageobjects.pagehelpers.PageObjectBase;

public class HomeTab extends PageObjectBase {
	
	
	@FindBy(xpath = "//button[contains(text(),'Contracts ')]")
	public WebElement ContractDropDown;
	
	@FindBy(xpath = "//div/ul/li/a[text()='Contracts']")
	public WebElement ContractSubMenu;
	
	@FindBy(xpath = "//span[text()='Sites']")
	public WebElement Site;
	
	@FindBy(xpath = "//a[@title='Test']")
	public WebElement Test;
	
	@FindBy(xpath = "//td/span[text()='Click to start']")
	public WebElement Clicktostart;
	
	@FindBy(xpath = "html/body/div[8]/div[1]/div[3]/div[1]/div[1]/div[2]/div/div/div[3]/div[3]/div/table/tbody[2]/tr[48]/td/div/div/span[1]/a")////h3[@title='SPA - PAT ' and text()='SPA PAT.xml']//following-sibling::div/span[@title='Create New']
	public WebElement SPA_PATTemplate;
	
	@FindBy(xpath = "//div[@class='title-bar']")
	public WebElement TitleBar;
	
	/*@FindBy(xpath = "//input[@value='Clear']")
	public WebElement Clear;
	
	@FindBy(xpath = "//input[5][@value='Exit' and @type='button']")//  //div[2]//tbody/tr[5]/td/input[6]
	public WebElement Exit;
	
	@FindBy(xpath = "html/body/div[1]/div[1]/form/font")
	public WebElement Error;
	
	*/
	
	
	public HomeTab() {

		PageFactory.initElements(driver, this);
		
    }
	
	public void ClickOnContractDropDown() throws Exception {
		
		helper.highlighter(driver, ContractDropDown);
		helper.Click(driver,test, ContractDropDown, "Contract DropDown");
		helper.wait(driver, ContractSubMenu, 20);
	}
	

	public void ClickOnContract() throws Exception {
		helper.highlighter(driver, ContractSubMenu);
		helper.Click(driver,test, ContractSubMenu, "Contract Menu");
		helper.pause(2);
	}
	
	public void tillcontract() throws Exception {
		
		helper.highlighter(driver, homePage.Site);
		helper.Click(driver, test, homePage.Site, "Site Menu");
		helper.wait(driver, homePage.Test, 20);
		//helper.pause(2);
		
		helper.highlighter(driver, homePage.Test);
		helper.Click(driver, test, homePage.Test, "Test Menu");
		helper.wait(driver, homePage.Clicktostart, 20);
		//helper.pause(6);
		
		helper.highlighter(driver, homePage.Clicktostart);
		helper.Click(driver, test, homePage.Clicktostart, "Click to start");
		helper.wait(driver, homePage.SPA_PATTemplate, 20);
		//helper.pause(3);
		
		helper.highlighter(driver, homePage.SPA_PATTemplate);
		helper.Click(driver, test, homePage.SPA_PATTemplate, "SPA_PATTemplate");
		helper.wait(driver, homePage.TitleBar, 20);
		helper.pause(6);
	}
	

}
