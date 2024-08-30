package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	WebDriver driver;
	public OrganizationInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headerTxt;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement actualIndustry;
	
	@FindBy(id = "dtlview_Type")
	private WebElement actualType;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement actPhoneNo;
	
	
	
	public WebElement getActPhoneNo() {
		return actPhoneNo;
	}



	public WebElement getActualIndustry() {
		return actualIndustry;
	}



	public WebElement getActualType() {
		return actualType;
	}



	public WebElement getHeaderTxt() {
		return headerTxt;
	}
	
	

}
