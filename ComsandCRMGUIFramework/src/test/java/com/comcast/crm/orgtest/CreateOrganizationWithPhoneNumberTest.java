package com.comcast.crm.orgtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


public class CreateOrganizationWithPhoneNumberTest {
public static void main(String[] args) throws Throwable {
	FileUtility fu = new FileUtility();
	ExcelUtility eu = new ExcelUtility();
	JavaUtility ju = new JavaUtility();
	WebDriverUtility wu = new WebDriverUtility();
	
	
	String BROWSER = fu.getDataFromPropertiesFile("browser");
	String URL = fu.getDataFromPropertiesFile("url");
	String USERNAME = fu.getDataFromPropertiesFile("username");
	String PASSWORD = fu.getDataFromPropertiesFile("password");
	
	String orgName = eu.getDataFromExcel("org", 7, 2) + ju.getRandomNumber();
	String phoneNumber = eu.getDataFromExcel("org", 7, 3);
	
	 
	 WebDriver driver;
	 
	 if(BROWSER.equals("chrome")) {
		 driver = new ChromeDriver();
	 }else if(BROWSER.equals("firefox")) {
		 driver = new FirefoxDriver();}
	 else if(BROWSER.equals("edge")) {
		 driver = new EdgeDriver();}
	 else {
		 driver = new ChromeDriver();
	 }
	 
	 wu.waitForPageToLoad(driver);
	 driver.get(URL);
	 
	 LoginPage lp = new LoginPage(driver);
	 lp.loginToApp(USERNAME, PASSWORD);	 
	 HomePage hp = new HomePage(driver);
	 hp.getOrgLink().click();
	 
	 OrganizationsPage op = new OrganizationsPage(driver);
	 op.getCreateOrgLink().click();
	 
	 CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
	 cnop.createOrg(orgName, phoneNumber);
	 
	 OrganizationInformationPage oip = new OrganizationInformationPage(driver);
	 String actPhoneNo = oip.getActPhoneNo().getText();
		
		if(actPhoneNo.equals(phoneNumber)) {
			System.out.println(actPhoneNo + "  is created====PASS");
		}
		else {
			System.out.println(actPhoneNo + "  is not created====FAIL");
		}
		 
		driver.quit();
}

}
