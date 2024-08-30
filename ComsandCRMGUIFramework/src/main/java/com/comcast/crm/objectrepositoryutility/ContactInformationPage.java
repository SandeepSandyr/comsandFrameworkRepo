package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	
	WebDriver driver;
	public ContactInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement actLastName;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerinfo;
	
	@FindBy(id = "mouseArea_Support Start Date")
	private WebElement actualStrtDate;
	
	@FindBy(id = "mouseArea_Support End Date")
	private WebElement actualEndDate;
	
	
	
	
	public WebElement getActualStrtDate() {
		return actualStrtDate;
	}

	public WebElement getActualEndDate() {
		return actualEndDate;
	}

	public WebElement getActLastName() {
		return actLastName;
	}

	public WebElement getHeaderinfo() {
		return headerinfo;
	}
	
	

}
