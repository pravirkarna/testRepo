package ui.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class EmployesMaster extends PageObjectBase {


    @FindBy(xpath = "//span/a[contains(text(),'Create Employee')]")
    public static WebElement CreateEmployee;

    @FindBy(xpath = ".//input[@id='aadhar_number']")
    public static WebElement AdharNumber;

    @FindBy(xpath = ".//input[@id='employee_name']")
    public static WebElement EmployeeName;

    @FindBy(xpath = ".//input[@id='employee_code']")
    public static WebElement EmployeeCode;

    @FindBy(xpath = ".//*[@id='employee_id']")
    public static WebElement EmployeeID;

    @FindBy(xpath = ".//*[@id='organization_name']")
    public static WebElement OrganisationName;

    @FindBy(xpath = ".//*[@id='Designation_name']")
    public static WebElement DesignationName;


    @FindBy(xpath = "//button[contains(text(),'Create')]")
    public static WebElement Create ;

    @FindBy(xpath = "//div[contains(text(),'Employee data is created successfully.')]")
    public static WebElement Created ;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    public static WebElement Close ;







    public EmployesMaster() {

        PageFactory.initElements(driver, this);

    }



    public void EnterDataOnCreateEmployes() throws Exception {

        helper.highlighter(driver, homePage.EmployeeMaster);
        helper.Click(driver,test,homePage.EmployeeMaster , "Contract DropDown");
        helper.pause(6);
        int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("size:"+size);
        driver.switchTo().frame(1);

        helper.highlighter(driver,CreateEmployee);
        helper.Click(driver,test,CreateEmployee,"Create Employee");
        helper.pause(6);

        helper.highlighter(driver,AdharNumber);
        helper.EditBox(AdharNumber,reader.getCellData("EmployeeMaster","AdharNumber",2),test,"Adhar Number");
        helper.pause(1);

        helper.highlighter(driver,EmployeeName);
        helper.EditBox(EmployeeName,reader.getCellData("EmployeeMaster","EmployeeName",2),test,"EmployeeName");
        helper.pause(1);

        helper.highlighter(driver,EmployeeCode);
        helper.EditBox(EmployeeCode,reader.getCellData("EmployeeMaster","EmployeeCode",2),test,"EmployeeCode");
        helper.pause(1);

        helper.highlighter(driver,EmployeeID);
        helper.EditBox(EmployeeID,reader.getCellData("EmployeeMaster","EmployeeID",2),test,"EmployeeID");
        helper.pause(1);

        helper.highlighter(driver,OrganisationName);
        helper.EditBox(OrganisationName,reader.getCellData("EmployeeMaster","OrganisationName",2),test,"OrganisationName");
        helper.pause(1);

        helper.highlighter(driver,DesignationName);
        helper.EditBox(DesignationName,reader.getCellData("EmployeeMaster","DesignationName",2),test,"DesignationName");
        helper.pause(5);

        helper.highlighter(driver,Create);
        helper.Click(driver,test,Create,"Click on create");
        helper.wait(driver,Created,20);

        helper.highlighter(driver,Close);
        helper.Click(driver,test,Close,"Click on close ");

    }


}
