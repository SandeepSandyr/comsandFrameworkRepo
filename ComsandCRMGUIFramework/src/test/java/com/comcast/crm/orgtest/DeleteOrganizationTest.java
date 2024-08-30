package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
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

public class DeleteOrganizationTest {
	public static void main(String[] args) throws Throwable {
		FileUtility fu = new FileUtility();
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		WebDriverUtility wu = new WebDriverUtility();
		
		
		String BROWSER = fu.getDataFromPropertiesFile("browser");
		String URL = fu.getDataFromPropertiesFile("url");
		String USERNAME = fu.getDataFromPropertiesFile("username");
		String PASSWORD = fu.getDataFromPropertiesFile("password");
		
		String orgName = eu.getDataFromExcel("org", 10, 2) + ju.getRandomNumber();
		 
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
		 
		 //verify orgName Info Expected Result
         
         OrganizationInformationPage oip = new OrganizationInformationPage(driver);
         String headerInfo =oip.getHeaderTxt().getText();
		
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName + "  is created====PASS");
		}
		else {
			System.out.println(orgName + "  is not created====FAIL");
		}
		
        hp.getOrgLink().click();
        op.getSearchTF().sendKeys(orgName);
        wu.select(op.getSearchDrpDwn(), "Organization Name");
        op.getSubmitButtn().click();
        
        driver.findElement(By.xpath("//a[text()='"+orgName+"']/../..//a[text()='del']")).click();
        
        driver.switchTo().alert().accept();
		
		hp.logout();
		driver.quit();
	}
}
