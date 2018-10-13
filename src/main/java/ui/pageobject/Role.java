package ui.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class Role extends PageObjectBase {

    @FindBy(xpath = "//span/a[contains(text(),'Create Role')]")
    public static WebElement CreateRole;

    @FindBy(xpath = ".//*[@id='organization_name']")
    public static WebElement DepartmentName ;

    @FindBy(xpath = ".//div[@id='gridLayoutWrapper']")
    public static WebElement dropDown;

    @FindBy(xpath = ".//*[@id='role_name']")
    public static WebElement RoleName;

    @FindBy(xpath = ".//*[@id='role_desc']")
    public static WebElement RoleDesc;

    @FindBy(xpath = "//*[@id='Role_level_id']")
    public static WebElement Rolelevel;

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    public static WebElement Create ;

    @FindBy(xpath = "//div[contains(text(),'Your role is created successfully')]")
    public static WebElement RoleCreated ;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    public static WebElement Close ;

    public Role() {

        PageFactory.initElements(driver, this);

    }



    public void EnterDataOnRole() throws Exception {

        helper.highlighter(driver,homePage.Role);
        helper.Click(driver,test,homePage.Role,"Click on Role ");
        helper.pause(6);
        int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("size:"+size);
        driver.switchTo().frame(1);

        helper.highlighter(driver,CreateRole);
        helper.Click(driver,test,CreateRole,"Create Employee");
        helper.pause(6);

        helper.highlighter(driver,DepartmentName);
        helper.EditBox(DepartmentName,reader.getCellData("Role","DepartmentName",2),test,"DepartmentName");
        helper.pause(1);
        helper.Click(driver,test,dropDown,"DropDown Click");

        helper.highlighter(driver,RoleName);
        helper.EditBox(RoleName,reader.getCellData("Role","RoleName",2),test,"RoleName");
        helper.pause(1);

        helper.highlighter(driver,RoleDesc);
        helper.EditBox(RoleDesc,reader.getCellData("Role","RoleDesc",2),test,"RoleDesc");
        helper.pause(1);

        helper.highlighter(driver,Rolelevel);
        helper.selectfromDropDown(driver,test,Rolelevel,reader.getCellData("Role","RoleLevel",2),"Rolelevel Drop Down value");
        helper.pause(1);

        helper.highlighter(driver,Create);
        helper.Click(driver,test,Create,"Click on create");
        helper.wait(driver,RoleCreated,20);

        helper.highlighter(driver,Close);
        helper.Click(driver,test,Close,"Click on close ");


    }
}
