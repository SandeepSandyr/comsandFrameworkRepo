package com.comcast.crm.contacttest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDate {
	public static void main(String[] args) throws Throwable {
		FileUtility fu = new FileUtility();
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		WebDriverUtility wu = new WebDriverUtility();
		
		
		String BROWSER = fu.getDataFromPropertiesFile("browser");
		String URL = fu.getDataFromPropertiesFile("url");
		String USERNAME = fu.getDataFromPropertiesFile("username");
		String PASSWORD = fu.getDataFromPropertiesFile("password");
		
		String lastName = eu.getDataFromExcel("contact", 1, 2) + ju.getRandomNumber();
		 
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
		 hp.getContactLink().click();
		 
		 ContactsPage cp = new ContactsPage(driver);
		 cp.getCreateContactLink().click();
		 
		 CreateNewContactPage cncp = new CreateNewContactPage(driver);
		 cncp.getLastnameTF().sendKeys(lastName);
			 
		 String startDate = ju.getSystemDateyyyyMMdd();
		 String endDate = ju.getRequiredDtaeyyyyMMdd(30);
		 
		 
		 WebElement strtdateTF = cncp.getStrtdateTF();
		 strtdateTF.clear();
		 strtdateTF.sendKeys(startDate);
		 WebElement endDateTF = cncp.getEndDateTF();
		 endDateTF.clear();
		 endDateTF.sendKeys(endDate);
		 
		 cncp.getContactSaveButton().click();
		 
		 ContactInformationPage cip = new ContactInformationPage(driver);
		 
		 String actualStrtDate = cip.getActualStrtDate().getText().trim();
		 String actualEndDate = cip.getActualEndDate().getText().trim();
		 
		 if(actualStrtDate.equals(startDate)) {
				System.out.println(startDate + "  StartDate is verified====PASS");
			}
			else {
				System.out.println(startDate + "  StartDate is not verified====FAIL");
			}
		 if(actualEndDate.equals(endDate)) {
				System.out.println(endDate + "  EndDate is verified====PASS");
			}
			else {
				System.out.println(endDate + "  EndDate is not verified====FAIL");
			}
	}

}
