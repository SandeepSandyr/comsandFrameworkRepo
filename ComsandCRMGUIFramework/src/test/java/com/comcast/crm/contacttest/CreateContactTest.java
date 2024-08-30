package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends Baseclass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {

		String lastName = eu.getDataFromExcel("contact", 1, 2) + ju.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactLink().click();

		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastnameTF().sendKeys(lastName);
		cncp.getContactSaveButton().click();

		ContactInformationPage cip = new ContactInformationPage(driver);
		String actLastName = cip.getActLastName().getText();
		String headerinfo = cip.getHeaderinfo().getText();

		boolean status1 = headerinfo.contains(lastName);
		Assert.assertEquals(status1, true);

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastName);
		soft.assertAll();

	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDate() throws Throwable {
		String lastName = eu.getDataFromExcel("contact", 1, 2) + ju.getRandomNumber();
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

		if (actualStrtDate.equals(startDate)) {
			System.out.println(startDate + "  StartDate is verified====PASS");
		} else {
			System.out.println(startDate + "  StartDate is not verified====FAIL");
		}
		if (actualEndDate.equals(endDate)) {
			System.out.println(endDate + "  EndDate is verified====PASS");
		} else {
			System.out.println(endDate + "  EndDate is not verified====FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void createContactWithOrg() throws Throwable {
		String lastName = eu.getDataFromExcel("contact", 7, 3) + ju.getRandomNumber();
		String orgName = eu.getDataFromExcel("contact", 7, 2) + ju.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLink().click();

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String headerInfo = oip.getHeaderTxt().getText();

		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "  is created====PASS");
		} else {
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

		if (headerinfo.contains(lastName)) {
			System.out.println(lastName + "  Header is verified====PASS");
		} else {
			System.out.println(lastName + "  Header is not verified====FAIL");
		}
		if (actLastName.equals(lastName)) {
			System.out.println(lastName + "  lastname is verified====PASS");
		} else {
			System.out.println(lastName + "  lastname is not verified====FAIL");
		}

	}

}
