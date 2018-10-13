package ui.pageobjects.pagehelpers;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ui.pageobject.*;


public class PageObjectBase {
	
	
	protected static WebDriver driver ;
	
	public static String baseUrl;
	public static Properties prop ;
	
	public static ExtentReports extent;
	public static ExtentTest test ;

	public static LoginPage loginPage;
	public static HomePage homePage ;
	public static EmployesMaster employesMaster;
	public static Role role;
	public static Activity activity;
	
	public static FunctionalLibrary helper;
	
	public  Method method;
	
	public static XlsReader reader;
	public static XlsReader TestSuite= null;
	
	public DesiredCapabilities capabilities = null;
	
	
	
	public static void setDriver(WebDriver driver) {
		PageObjectBase.driver = driver;
	}
	
	

	@Parameters({"browser","environment"})
	@BeforeClass
	public void  setup(String browser, String environment) throws Exception {
		
		//Report.ExtentReportConfig();

		extent = new ExtentReports("output/report" + System.currentTimeMillis() + ".html", true);
		
		helper = new FunctionalLibrary();
		
		//Excel path configuration
		reader= new XlsReader(System.getProperty("user.dir")+"/TestData.xlsx");
	
		if(browser.equalsIgnoreCase("Firefox")){
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
			setDriver(new FirefoxDriver(capabilities));
			System.out.println("-----Firefox Browser Launched----- ");


		}
		
		else if(browser.equalsIgnoreCase("Chrome")){
			capabilities = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver","src\\test\\resources\\drivers\\chromedriver.exe");
			setDriver(new ChromeDriver(capabilities));
		    System.out.println("-----Chrome Browser Launched----- ");		
		}
		
		else if(browser.equalsIgnoreCase("Edge")){
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("ignoreZoomSetting", true);
			System.setProperty("webdriver.ie.driver","src\\test\\resources\\drivers\\IEDriverServer.exe");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			
			driver= new InternetExplorerDriver(capabilities);
			System.out.println("-----I.E Browser Launched----- ");	
			
		}
		
		else if(browser.equalsIgnoreCase("Safari")){
			capabilities = DesiredCapabilities.safari();
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"/drivers/MicrosoftWebDriver.exe");
			setDriver(new SafariDriver(capabilities));
			System.out.println("-----Safari Browser Launched----- ");	
		}
		
		helper.openPage(environment);
			
  }
	
		
	
	   @BeforeMethod
		public void config(Method method) throws IOException {

		test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName());

		}
		

	   @AfterMethod
		public void tearDown(ITestResult testResult) throws InterruptedException {
			extent.endTest(test);
		
			if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getStatus());
		
			String imagePath = helper.capture_Screenshot();
		
			test.log(LogStatus.FAIL,"The testcase is failed.Please find screenshot below   :" + test.addScreenCapture(imagePath));
	
		}

	   }
	   
	   @Parameters("browser")
	   @AfterClass
	   public static void tearDown(String browser) {
			
			System.out.println("Inside PageObject to Close browser ");
			driver.quit();
			extent.flush();
			test.log(LogStatus.INFO, "End of Report ");
			extent.close();
		}

	
}
