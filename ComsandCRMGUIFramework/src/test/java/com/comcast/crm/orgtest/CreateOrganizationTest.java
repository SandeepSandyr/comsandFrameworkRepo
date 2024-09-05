package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listnerutility.ListenerImplement;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends Baseclass {

	@Test(groups = "smokeTest")
	public void createOrgTest() throws Throwable {
		//UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		String orgName = eu.getDataFromExcel("org", 1, 2) + ju.getRandomNumber();

		//UtilityClassObject.getTest().log(Status.INFO, "Navigate to Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		//UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create Org Page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLink().click();

		//UtilityClassObject.getTest().log(Status.INFO, "Create Org");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verify orgName Info Expected Result

		UtilityClassObject.getTest().log(Status.INFO, orgName+"Verifty OrgName");
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String headerInfo = oip.getHeaderTxt().getText();

		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "  is created====PASS");
		} else {
			System.out.println(orgName + "  is not created====FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void createOrgTestWithIndustryTest() throws Throwable {
		String orgName = eu.getDataFromExcel("org", 4, 2) + ju.getRandomNumber();
		String industy = eu.getDataFromExcel("org", 4, 3);
		String type = eu.getDataFromExcel("org", 4, 4);
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLink().click();

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName, industy, type);

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actualIndustry = oip.getActualIndustry().getText();
		if (actualIndustry.equals(industy)) {
			System.out.println(industy + "  information is verified====PASS");
		} else {
			System.out.println(orgName + "  information is not verified====FAIL");
		}
		String actualType = oip.getActualType().getText();
		if (actualType.equals(type)) {
			System.out.println(type + "  information is verified====PASS");
		} else {
			System.out.println(type + "  information is not verified====FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void createOrgTestWithPhoneNumberTest() throws Throwable {
		String orgName = eu.getDataFromExcel("org", 7, 2) + ju.getRandomNumber();
		String phoneNumber = eu.getDataFromExcel("org", 7, 3);

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLink().click();

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName, phoneNumber);

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actPhoneNo = oip.getActPhoneNo().getText();

		if (actPhoneNo.equals(phoneNumber)) {
			System.out.println(actPhoneNo + "  is created====PASS");
		} else {
			System.out.println(actPhoneNo + "  is not created====FAIL");
		}
	}

	@Test(groups = "smokeTest")
	public void deleteOrgTest() throws Throwable {
		String orgName = eu.getDataFromExcel("org", 10, 2) + ju.getRandomNumber();

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

		hp.getOrgLink().click();
		op.getSearchTF().sendKeys(orgName);
		wu.select(op.getSearchDrpDwn(), "Organization Name");
		op.getSubmitButtn().click();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']/../..//a[text()='del']")).click();

		driver.switchTo().alert().accept();

	}
}
