package ui.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class Activity extends PageObjectBase {


    @FindBy(xpath = "//span/a[contains(text(),'Create Activity')]")
    public static WebElement CreateActivity;

    @FindBy(xpath = ".//*[@id='Organization_Name']")
    public static WebElement DepartmentName ;

    @FindBy(xpath = ".//div[@id='gridLayoutWrapper']")
    public static WebElement dropDown;

    @FindBy(xpath = ".//*[@id='Activity_Name']")
    public static WebElement ActivityName ;

    @FindBy(xpath = ".//*[@id='AccessGroup']")
    public static WebElement AccessGroup ;

    @FindBy(xpath = "//*[@id='Actvity_Description']")
    public static WebElement ActvityDescription ;

    @FindBy(xpath = " .//*[@id='Activity_Level_ID']")
    public static WebElement ActivityLevelID ;

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    public static WebElement Create ;

    @FindBy(xpath = "//div[contains(text(),'Your Activity is created successfully')]")
    public static WebElement ActivityCreated ;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    public static WebElement Close ;

    public Activity() {

        PageFactory.initElements(driver, this);

    }

    public void EnterDataOnActivity() throws Exception {

        helper.highlighter(driver, homePage.Activity);
        helper.Click(driver, test, homePage.Activity, "Contract DropDown");
        helper.pause(6);
        int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("size:" + size);
        driver.switchTo().frame(1);

        helper.highlighter(driver, CreateActivity);
        helper.Click(driver, test, CreateActivity, "Create Activity");
        helper.pause(6);

        helper.highlighter(driver,DepartmentName);
        helper.EditBox(DepartmentName,reader.getCellData("Activity","DepartmentName",2),test,"DepartmentName");
        helper.pause(1);
        helper.Click(driver,test,dropDown,"DropDown Click");
        helper.pause(2);

        helper.highlighter(driver,ActivityName);
        helper.EditBox(ActivityName,reader.getCellData("Activity","ActivityName",2),test,"ActivityName");
        helper.pause(1);

        helper.highlighter(driver,AccessGroup);
        helper.selectfromDropDown(driver,test,AccessGroup,reader.getCellData("Activity","AccessGroup",2),"AccessGroup Drop Down value");
        helper.pause(1);

        helper.highlighter(driver,ActvityDescription);
        helper.EditBox(ActvityDescription,reader.getCellData("Activity","ActvityDescription",2),test,"ActvityDescription");
        helper.pause(1);

        helper.highlighter(driver,ActivityLevelID);
        helper.selectfromDropDown(driver,test,ActivityLevelID,reader.getCellData("Activity","ActivityLevelID",2),"ActivityLevelIDLevel Drop Down value");
        helper.pause(1);

        helper.highlighter(driver,Create);
        helper.Click(driver,test,Create,"Click on create");
        helper.wait(driver,ActivityCreated,20);

        helper.highlighter(driver,Close);
        helper.Click(driver,test,Close,"Click on close ");
    }

}
