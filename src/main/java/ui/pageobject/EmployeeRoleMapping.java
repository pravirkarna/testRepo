package ui.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class EmployeeRoleMapping extends PageObjectBase {

    @FindBy(xpath = "//span/a[contains(text(),'Create Employee Role Master | Modify')]")
    public static WebElement CreateEmpRole;

    @FindBy(xpath = "//*[@id='pyDescription']")
    public static WebElement OrganizationName;

    @FindBy(xpath = ".//*[@id='po0']")
    public static WebElement dropDown;

    @FindBy(xpath = ".//*[@id='employee_name']")
    public static WebElement EmployeeName;

    @FindBy(xpath = "//button[text()='Search']")
    public static WebElement Search;

    @FindBy(xpath = "(//*[@id='DefaultAG'])[1]")
    public static WebElement Radio;

    @FindBy(xpath = "//button[text()='Map']")
    public static WebElement Map;

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    public static WebElement Create ;

    @FindBy(xpath = "//div[contains(text(),'mapped to the Roles Successfully.')]")
    public static WebElement RoleMapped ;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    public static WebElement Close ;







    public EmployeeRoleMapping() {

        PageFactory.initElements(driver, this);

    }

    public void EnterDataOnRoleMapping() throws Exception {

        helper.highlighter(driver, homePage.EmployeeRoleMapping);
        helper.Click(driver, test, homePage.EmployeeRoleMapping, "Contract DropDown");
        helper.pause(6);
        int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("size:" + size);
        driver.switchTo().frame(1);

        helper.highlighter(driver, CreateEmpRole);
        helper.Click(driver, test, CreateEmpRole, "Create Activity");
        helper.pause(6);

        helper.highlighter(driver,OrganizationName);
        helper.EditBox(OrganizationName,reader.getCellData("EmployeeRoleMapping","OrganizationName",2),test,"OrganizationName");
        helper.pause(1);
        helper.Click(driver,test,dropDown,"DropDown Click");
        helper.pause(2);

        helper.highlighter(driver, EmployeeName);
        helper.EditBox(EmployeeName,reader.getCellData("EmployeeRoleMapping","EmployeeName",2),test,"EmployeeName");
        helper.pause(1);

        helper.highlighter(driver, Search);
        helper.Click(driver, test, Search, "Click Search");
        helper.pause(1);

        helper.highlighter(driver, Radio);
        Radio.click();
        helper.pause(1);

        helper.highlighter(driver,  Map);
        helper.Click(driver, test, Map, "Click Map");
        helper.pause(1);

        helper.highlighter(driver,Create);
        helper.Click(driver,test,Create,"Click on create");
        helper.wait(driver,RoleMapped,20);

        helper.highlighter(driver,Close);
        helper.Click(driver,test,Close,"Click on close ");
    }
}
