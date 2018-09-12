package ui.pageobjects.pagehelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class FunctionalLibrary extends PageObjectBase{
	
	

	public static String ExpectedResults="<font color=\"blue\"><b>Expected Result :</b ></font>";
	public static String ActualResults="<br /><font color=\"blue\"><b>Actual Result: </b></font>";
	
	
	
	@Parameters({"environment"})
	public void openPage(String environment) throws IOException{
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		
		prop= new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Environment.properties");
		prop.load(file);
	    String devurl=prop.getProperty("ENV_DEV");
		String testurl=prop.getProperty("ENV_TEST");
		String productionurl=prop.getProperty("ENV_PRODUCTION");
		  
		  if(environment.equalsIgnoreCase("dev")){
			  baseUrl = devurl; 
		  }
		  
		  else if(environment.equalsIgnoreCase("test")){
			  baseUrl = testurl;
		  }
		  
		  else if(environment.equalsIgnoreCase("production")){
			  baseUrl = productionurl; 
		  }
		 
		  driver.get(baseUrl);
	}
	
  public String [] getCredentials(){
		
	    String ar[] =new String[2];
		String Url=driver.getCurrentUrl();
		
		
		if(Url.contains("devsvr2")){       
	        ar[0]=reader.getCellData("Credentials","Username", 2);
	        ar[1]=reader.getCellData("Credentials","Password", 2);   
	       
	    }else if(Url.contains("httptst1")){
	    	ar[0]=reader.getCellData("Credentials","Username", 4);
		    ar[1]=reader.getCellData("Credentials","Password", 4);  
		    
	    }else if(Url.contains("")){
	    	ar[0]=reader.getCellData("Credentials","Username", 5);
		    ar[1]=reader.getCellData("Credentials","Password", 5);          
	    }
		
		return ar;
	}

	public  void setStyle(WebDriver driver, WebElement element, String attName, String value) {

		for (int i = 0; i < 3; i++) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].setAttribute('style',
		// arguments[1]);",driver.findElement(Locator),
		// "color: red; border: 2px solid red;");
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, value);

		}

	}
	
	public  void highlighter(WebDriver driver, WebElement element) {
		final int wait = 1000;
		String orgStyle = element.getAttribute("style");
		try {

		setStyle(driver, element, "style", "background-color: yellow; outline: 1px solid rgb(136, 255, 136);;");
		Thread.sleep(wait);
		setStyle(driver, element, "style", orgStyle);

		} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
	
	public void pause(Integer second){
	    try {
	        TimeUnit.SECONDS.sleep(second);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public  void EditBox(WebElement element ,String xlsReader,ExtentTest test,String desc) throws InterruptedException{
		
		try{
		element.clear();
		pause(2);
		element.sendKeys(xlsReader);
		test.log(LogStatus.PASS,desc+"should be entered", desc+" is entered Successfully");
		pause(1);
		}
		
		catch(Exception e)
		  {
			  String imagePath = helper.capture_Screenshot();
			  test.log(LogStatus.FAIL,desc+"should be entered", desc+" is not entered");
		  }
				
	}
	
	
	// CHECKBOX CHECKBOX CHECKBOX CHECKBOX CHECKBOX CHECKBOX CHECKBOX CHECKBOX
		// CHECKBOX CHECKBOX CHECKBOX CHECKBOX CHECKBOX CHECKBOX CHECKBOX

		// **********************************************
		// Object Category: CheckBox
		// Method Name :CHK_Setcheck
		// Parameter: WebElement:CheckBox, boolean: true=check false= Uncheck
		// Returns: boolean
		// Purpose: To check or Uncheck a checkBox type element
		// **********************************************

		public void CHKBOX_SetCheck(WebElement element, boolean checkOnOff) {
		try {

		if (checkOnOff) {
			if (!(element.isSelected())) {
				element.click();
				System.out.println("CHK_SetCheck: CheckBox was checked");
			}
		} else {
			if (element.isSelected()) {
				element.click();
				System.out.println("CHK_SetCheck: CheckBox was Un-checked");
			}
		}
		} catch (NoSuchElementException e) {
			System.out.println("SYNC_FindElement: NoSuchElementException thrown");
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

		// DROPDOWN COMBOBOX DROPDOWN COMBOBOX DROPDOWN COMBOBOX DROPDOWN COMBOBOX
		// DROPDOWN COMBOBOX DROPDOWN COMBOBOX DROPDOWN COMBOBOX DROPDOWN

		/* Methods to select a DropDown item based on Value, Index and Text */
		// **********************************************
		// Object Category: ComboBox, DropDown
		// Method Name ropDown_SelectByText
		// Parameter: WebElement, String: Input Value
		// Returns: void
		// Purpose: To Select a drop down Item by a string Text
		// **********************************************
		public static void DropDown_SelectByText(WebDriver driver,ExtentTest test,WebElement element, String Value) {
			
			if (element != null) {
			Select select = new Select(element);
			select.selectByVisibleText(Value);
			System.out.println("Value " + Value +  " is selected");
			}
		}

		public static void DropDown_SelectByIndex(WebElement element, int Indexvalue) {

			try{ 
			Select select = new Select(element);
			select.selectByIndex(Indexvalue); 
			}

			catch(Exception e){
			e.printStackTrace();
			}
		}
		
		public static void selectfromDropDown( WebDriver driver,ExtentTest test, WebElement Element, String Value, String ElementDesc) throws InterruptedException {
			try{
			helper.wait(driver, Element, 60);
			DropDown_SelectByText(driver, test, Element, Value); 
			test.log(LogStatus.PASS, "Select '"+ElementDesc+"' from the DropDown " ,ExpectedResults+ElementDesc+ " Should be able to select from the DropDown"  +ActualResults +ElementDesc+ " is Selected from  DropDown ");
			
			}catch(Exception e){ 
			String imagePath=null; 
			imagePath = helper.capture_Screenshot();
			test.log(LogStatus.FAIL, "Select '"+ElementDesc+"' from the DropDown " ,ExpectedResults+ElementDesc+ " Should be able to select from the DropDown"  +ActualResults +ElementDesc+ " is not Selected from  DropDown ");
	
			}
		}
	
	public  void Click(WebDriver driver,ExtentTest test,WebElement element,String desc) throws InterruptedException{
		
		  try
		  {
			Verify_VisibleEnabled_Element(element);				            
			element.click();
			test.log(LogStatus.PASS, "Click on " +desc, ExpectedResults+desc+ " should be clicked " +ActualResults+desc+" is Clicked");
		  }
		  catch(NoSuchElementException e)
		  {
			  String imagePath = helper.capture_Screenshot();
			  test.log(LogStatus.FAIL,"Click on " +desc,ExpectedResults+desc+ "should be clicked "+ ActualResults+desc+" element is not Clickable");
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  String imagePath = helper.capture_Screenshot();
			  test.log(LogStatus.FAIL,"Click on " +desc,ExpectedResults+desc+ "should be clicked " +ActualResults+desc+" element is not Clicked");
		  }
		 
		
	}
	

	
	public  void wait(WebDriver driver, WebElement element, long waittime) {

		try {
			new WebDriverWait(driver, waittime).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY
		// VERIFY

		// **********************************************
		// Object Category: VERIFY
		// Method Name YNC_FindElement
		// Parameter: WebElement: Any type of WebElement
		// Returns: boolean
		// Purpose: To verify object exists, is enabled and is displayed on screen
		// **********************************************
		public  boolean Verify_VisibleEnabled_Element(WebElement element) {
		try {

		if (!(element.equals(null)) || (element.isEnabled() && element.isDisplayed())) {
		System.out.println("Verify_VisibleEnabled_Element :"+element.getText()+" Element exist");
		return true;
		} else {
		System.out.println("Verify_VisibleEnabled_Element :"+element.getText()+" Element does not exist");
		return false;
		}
		} catch (NoSuchElementException e) {
		System.out.println("Verify_VisibleEnabled_Element: NoSuchElementException thrown");
		return false;
		} catch (Exception e) {
		System.out.println("Verify_VisibleEnabled_Element: Element does NOT exist");
		return false;
		}
		}

		// VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY VERIFY
		// VERIFY

		// **********************************************
		// Object Category: VERIFY
		// Method Name YNC_FindElement
		// Parameter: WebElement: Any type of WebElement
		// Returns: boolean
		// Purpose: To verify object exists,  is displayed on screen but disabled
		// **********************************************
		public  boolean Verify_VisibleDisabled_Element(WebElement element,String desc) {
			try {
	
				if (!(element.isEnabled()) && (element.isDisplayed() || !(element.equals(null)) )) {
					System.out.println("Verify Disabled_Element :"+element.getText()+" Element exist and is disabled");
					test.log(LogStatus.PASS,"Verify" +desc  ,ExpectedResults+desc+ " should be present and enabled " +ActualResults+ desc+" is present and is DISABLED");
					return true;
				} else {
					System.out.println("Verify Disabled_Element :"+element.getText()+" Element exist and is not disabled");
					test.log(LogStatus.FAIL,"Verify " +desc, ExpectedResults+desc+ " should be present and enabled " +ActualResults+ desc+" is not present and not enabled");
					return false;
				}
			} catch (NoSuchElementException e) {
				System.out.println("Verify Disabled_Element: NoSuchElementException thrown");
				return false;
			} catch (Exception e) {
				System.out.println("Verify Disabled_Element: Element does NOT exist");
				return false;
			}
		}

		
      public  void Verify_Button(WebElement element,String expected, String desc) {
    	  
    	  try {
		
    		  System.out.println("Get Text is :"+element.getText());	
  			  System.out.println("Get Value is :"+element.getAttribute("value"));				
			if ((element.isEnabled() && element.isDisplayed() && (element.getText().equals(expected) || element.getAttribute("value").equals(expected)))) {
				test.log(LogStatus.PASS,"Verify" +desc + "button"  ,ExpectedResults+desc+ " button should be present and enabled " +ActualResults+ desc+" button is present and ENABLED");
				
			} else if((!(element.isEnabled()) || (element.isDisplayed()) && (element.getText().equals(expected) || element.getAttribute("value").equals(expected)))){
				test.log(LogStatus.PASS,"Verify" +desc+ "button"  ,ExpectedResults+desc+ " button should be present and enabled " +ActualResults+ desc+" button is present and is DISABLED");
				
			}else{
				test.log(LogStatus.FAIL,"Verify " +desc+ "button", ExpectedResults+desc+ " button should be present and enabled " +ActualResults+ desc+" button is not present and not enabled");	
			}
		
			} catch (NoSuchElementException e) {
				System.out.println("Verify_Button_Element: NoSuchElementException thrown");
				
			} catch (Exception e) {
				System.out.println("Verify_Button_Element: Element does NOT exist");
				
			}
      }


	public void switchToAnyWindow(int windowNumber) {
		
	    Set < String > s = driver.getWindowHandles();   
	    Iterator < String > ite = s.iterator();
	    int i = 1;
	    while (ite.hasNext() && i < 10) {
	        String popupHandle = ite.next().toString();
	        System.out.println(popupHandle);
	        driver.switchTo().window(popupHandle);
	        System.out.println("Window title is : "+driver.getTitle());
	        if (i == windowNumber) break;
	        i++;
	    }
	}
	
	
	
	public void switchtoParent(String parentwindow){
		
		driver.switchTo().window(parentwindow);
		helper.pause(1);
	}
	
   public String parentwindow(){
		
		String parent=driver.getWindowHandle();
		return parent;
		
	}
	
	
	public String handleOtherWindow() throws InterruptedException{
	
		String winHandleBefore = driver.getWindowHandle();
		System.out.println("wi:"+winHandleBefore);
		
		// Switch to new window opened
		Set<String> s= driver.getWindowHandles();
		System.out.println("Size:"+s.size());
		
		for(String winHandle :s){
			//System.out.println("After :"+winHandle);
			driver.switchTo().window(winHandle);
		}
		return winHandleBefore;
		
	}
	
	
	public String handleOtherWindowList() throws InterruptedException{
		
		String winHandleBefore = driver.getWindowHandle();
		System.out.println("wi:"+winHandleBefore);
		
		// Switch to new window opened
		Set<String> s= driver.getWindowHandles();
		System.out.println("Size:"+s.size());
		
		for(String winHandle :s){
			//System.out.println("After :"+winHandle);
			driver.switchTo().window(winHandle);
		}
		return winHandleBefore;
		
	}
	
	
	public void AcceptAlert(){
	 Alert alert=driver .switchTo().alert();
	 alert.accept();
	}
	
	public  void existsElement(WebDriver driver, WebElement element,String expectedText,String Elementdesc) {
		
		try {
			System.out.println("Get Text is :"+element.getText());	
			System.out.println("Get Value is :"+element.getAttribute("value"));
			
		   if (element.isDisplayed() && (element.getText().contains(expectedText) || element.getAttribute("value").contains(expectedText)))  {
				test.log(LogStatus.PASS, "Verify "+Elementdesc + " is present" ,ExpectedResults+" "+Elementdesc+ " Should be Present " +ActualResults +Elementdesc+ " is Present ");
			}
			
			else{
				test.log(LogStatus.FAIL, "Verify "+Elementdesc + " is present" ,ExpectedResults+" "+Elementdesc+ " Should be Present " +ActualResults +Elementdesc+ " is not Present ");
			}
						
		} catch (NoSuchElementException e) {
			System.out.println("Exception Occured");
			e.printStackTrace();
			
		}	
		
	}
	
	public  void verifyTextbox(WebDriver driver, WebElement element,String Elementdesc) {
		
		try {
			
			
		   if (Verify_VisibleEnabled_Element(element)) {
				test.log(LogStatus.PASS, "Verify "+Elementdesc + " textbox is present" ,ExpectedResults+" "+Elementdesc+ " textbox Should be Present " +ActualResults +Elementdesc+ " textbox is Present ");
			}
			
			else{
				test.log(LogStatus.FAIL, "Verify "+Elementdesc + " textbox is present" ,ExpectedResults+" "+Elementdesc+ " textbox Should be Present " +ActualResults +Elementdesc+ " textbox is not Present ");
			}
						
		} catch (NoSuchElementException e) {
			System.out.println("Exception Occured");
			e.printStackTrace();
			
		}	
		
	}
	
	public  void verifyValue(WebDriver driver, WebElement element, String value, String Elementdesc) {
		
	try {
		System.out.println("Get Text is :"+element.getText());	
		System.out.println("Get Value is :"+element.getAttribute("value"));
		
	   if ( (element.getText().contains(value) || element.getAttribute("value").contains(value)))  {
			test.log(LogStatus.PASS, "Verify "+Elementdesc + " is matched" ,ExpectedResults+" "+Elementdesc+ " Should be matched " +ActualResults +Elementdesc+ " matched ");
		}
		
		else{
			test.log(LogStatus.FAIL, "Verify "+Elementdesc + " is matched" ,ExpectedResults+" "+Elementdesc+ " Should be matched " +ActualResults +Elementdesc+ " is not matched ");
		}
					
	} catch (NoSuchElementException e) {
		System.out.println("Exception Occured");
		e.printStackTrace();
		
	}	
		
	}
		

	 public  String  capture_Screenshot() throws InterruptedException {

		System.out.println("Inside After Hook for Capturing screnshot  ");

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File newFile2 = new File(
		"output/"  + "Error_" + System.currentTimeMillis() + "_error.png");

		if (newFile2.exists()) {
		newFile2.delete();
		}

		screenshot.renameTo(newFile2);

		System.out.println("Functional Library saved screenshot in  " + newFile2.getPath());

	    return  newFile2.getAbsolutePath();
		}
	 
	 
	 //Switch to last window
	 public int switchToNewWindowList() {
		Set<String> s = driver.getWindowHandles();
		// Iterator<String> itr = s.iterator();
		// String w1 = (String) itr.next();
		// String w2 = (String) itr.next();
		// Utils.addLog.debug("Switching to new window :" + w2 + " ," + w1);

		List<String> ls = new ArrayList<String>(s);

		for(int i=0;i<ls.size();i++){
		         
		driver.switchTo().window(ls.get(i));

		}
		return ls.size();

	}
	 //wait till new window
	 public void waitForNumberOfWindowsToEqual(final int numberOfWindows) {
		    new WebDriverWait(driver, 20) {
		    }.until(new ExpectedCondition<Boolean>() {
		    
		        public Boolean apply(WebDriver driver) {                        
		            return (driver.getWindowHandles().size() == numberOfWindows);
		        }
		    });
		}

	// wait.Until(driver => webDriver.WindowHandles.Count() == numberOfWindows);
	 
	 public boolean closeAllOtherWindows( String openWindowHandle) {
			Set<String> allWindowHandles = driver.getWindowHandles();
			for (String currentWindowHandle : allWindowHandles) {
				if (!currentWindowHandle.equals(openWindowHandle)) {
					driver.switchTo().window(currentWindowHandle);
					driver.close();
				}
			}
			
			driver.switchTo().window(openWindowHandle);
			if (driver.getWindowHandles().size() == 1)
				return true;
			else
				return false;
		}

}//Class ENDS
