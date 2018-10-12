package ui.pageobject;

import org.openqa.selenium.support.PageFactory;
import ui.pageobjects.pagehelpers.PageObjectBase;

public class EmployesMaster extends PageObjectBase {


    public EmployesMaster() {

        PageFactory.initElements(driver, this);

    }
}
