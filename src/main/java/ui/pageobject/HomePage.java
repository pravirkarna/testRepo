package ui.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	@FindBy(xpath = "//span[text()='Employee Master']")
	public WebElement EmployeeMaster;

	@FindBy(xpath = "//span[text()='Role']")
	public WebElement Role;

	@FindBy(xpath = "//span[text()='Activity']")
	public WebElement Activity;

	@FindBy(xpath = "//span[text()='Employee Role Mapping']")
	public WebElement EmployeeRoleMapping;




	
	
	public HomePage() {

		PageFactory.initElements(driver, this);
		
    }
	
	public void clickOnCentralAdmin() throws Exception {

		helper.highlighter(driver,Power_Button);
		helper.Click(driver,test,Power_Button , "Contract DropDown");
		helper.wait(driver, SwitchRole, 20);


		helper.highlighter(driver, SwitchRole);

		Actions action = new Actions(driver);
		action.moveToElement(SwitchRole).build().perform();

		//helper.Click(driver,test,SwitchRole , "Contract DropDown");
		helper.wait(driver, CentralAdmin, 20);

		helper.highlighter(driver, CentralAdmin);
		helper.Click(driver,test,CentralAdmin , "Contract DropDown");
		helper.wait(driver, EmployeeMaster, 20);
	}
	

	//PAGES
	public EmployesMaster employesMaster() throws InterruptedException {

		return new EmployesMaster();
	}

	public Role Role() throws InterruptedException {

		return new Role();
	}

	public Activity Activity() throws InterruptedException {

		return new Activity();
	}

	public EmployeeRoleMapping employeeRoleMapping() throws InterruptedException {

		return new EmployeeRoleMapping();
	}
}

