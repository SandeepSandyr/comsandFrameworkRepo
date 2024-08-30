package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


public class CreateContactWithOrgTest {
	public static void main(String[] args) throws Throwable {
		FileUtility fu = new FileUtility();
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		WebDriverUtility wu = new WebDriverUtility();
		
		
		String BROWSER = fu.getDataFromPropertiesFile("browser");
		String URL = fu.getDataFromPropertiesFile("url");
		String USERNAME = fu.getDataFromPropertiesFile("username");
		String PASSWORD = fu.getDataFromPropertiesFile("password");
		
		String lastName = eu.getDataFromExcel("contact", 7, 3) + ju.getRandomNumber();
		String orgName = eu.getDataFromExcel("contact", 7, 2) + ju.getRandomNumber();
		
		 
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
         cnop.createOrg(orgName);
		 
         OrganizationInformationPage oip = new OrganizationInformationPage(driver);
         String headerInfo =oip.getHeaderTxt().getText();
		
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName + "  is created====PASS");
		}
		else {
			System.out.println(orgName + "  is not created====FAIL");
		}
		
		hp.getContactLink().click();
		 
		 ContactsPage cp = new ContactsPage(driver);
		 cp.getCreateContactLink().click();
		 
		 CreateNewContactPage cncp = new CreateNewContactPage(driver);
		 cncp.getLastnameTF().sendKeys(lastName);
		 cncp.getAddOrgLink().click();
	
         wu.switchToTabOnURL(driver, "module=Accounts");
         
         driver.findElement(By.linkText(orgName)).click();
         
         wu.switchToTabOnURL(driver, "Contacts&action");
         cncp.getContactSaveButton().click();
         
         ContactInformationPage cip = new ContactInformationPage(driver);
		 String actLastName = cip.getActLastName().getText();
		 String headerinfo = cip.getHeaderinfo().getText();
		 
		 if(headerinfo.contains(lastName)) {
				System.out.println(lastName + "  Header is verified====PASS");
			}
			else {
				System.out.println(lastName + "  Header is not verified====FAIL");
			}
		 if(actLastName.equals(lastName)) {
				System.out.println(lastName + "  lastname is verified====PASS");
			}
			else {
				System.out.println(lastName + "  lastname is not verified====FAIL");
			}
	}

}
