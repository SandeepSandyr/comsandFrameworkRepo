package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

@Listeners(com.comcast.crm.listnerutility.ListenerImplement.class)
public class Baseclass {
	
	public DataBaseUtility du = new DataBaseUtility();
	public FileUtility fu = new FileUtility();
	public ExcelUtility eu = new ExcelUtility();
	public JavaUtility ju = new JavaUtility();
	public WebDriverUtility wu = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	

	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void configBS() throws SQLException {
		
		System.out.println("=====Connect to DB , Report config=======");
		du.getDbConnection();
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest", "regressionTest"})
	public void configBC(/*String browser*/) throws Throwable {
	
		System.out.println("=====Launch the Browser=======");
		//String BROWSER = browser;
		String BROWSER = fu.getDataFromPropertiesFile("browser");
		String URL = fu.getDataFromPropertiesFile("url");

		
		 if(BROWSER.equals("chrome")) {
			 driver = new ChromeDriver();
		 }else if(BROWSER.equals("firefox")) {
			 driver = new FirefoxDriver();}
		 else if(BROWSER.equals("edge")) {
			 driver = new EdgeDriver();}
		 else {
			 driver = new ChromeDriver();
		 }
		 sdriver = driver;
		 UtilityClassObject.setDriver(driver);
		 driver.manage().window().maximize();
		 wu.waitForPageToLoad(driver);
       	 driver.get(URL);
		
	}
	
	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void configBM() throws Throwable {
		
		System.out.println("=====Login=======");
		String USERNAME = fu.getDataFromPropertiesFile("username");
		String PASSWORD = fu.getDataFromPropertiesFile("password");
        LoginPage lp = new LoginPage(driver);
        lp.getUsernameEdt().sendKeys(USERNAME);
        lp.getPasswordEdt().sendKeys(PASSWORD);
        lp.getLoginButton().click();
		
	}
	
	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void configAM() {
		
		System.out.println("=====Logout=======");
		HomePage hp = new HomePage(driver);
		hp.logout();

		
	}
	
	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void configAC() {
		
		System.out.println("=====Close the Browser=======");
		driver.quit();
        
		
	}
	
	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void configAS() throws SQLException {
		
		System.out.println("=====Close the DB Connection=======");
        du.closeDbConnection();
		
	}

}
