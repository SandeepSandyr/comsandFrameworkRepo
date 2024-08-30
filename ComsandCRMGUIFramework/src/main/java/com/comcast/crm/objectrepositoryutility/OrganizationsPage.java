package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrgLink;
	
	@FindBy(name = "search_text")
	private WebElement searchTF;
	
	@FindBy(name = "search_field")
	private WebElement searchDrpDwn;
	
	@FindBy(name = "submit")
	private WebElement submitButtn;
	
	
	
	public WebElement getSubmitButtn() {
		return submitButtn;
	}



	public WebElement getSearchTF() {
		return searchTF;
	}



	public WebElement getSearchDrpDwn() {
		return searchDrpDwn;
	}



	public WebElement getCreateOrgLink() {
		return createOrgLink;
	}
	
	

	
	

}
