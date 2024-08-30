package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	
	WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastnameTF;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement contactSaveButton;
	
	@FindBy(xpath = "(//img[@alt='Select'])[1]")
	private WebElement addOrgLink;
	
	@FindBy(name = "support_start_date")
	private WebElement strtdateTF;
	
	@FindBy(name = "support_end_date")
	private WebElement endDateTF;
	
	
	
	public WebElement getStrtdateTF() {
		return strtdateTF;
	}




	public WebElement getEndDateTF() {
		return endDateTF;
	}




	public WebElement getAddOrgLink() {
		return addOrgLink;
	}




	public WebElement getContactSaveButton() {
		return contactSaveButton;
	}




	public WebElement getLastnameTF() {
		return lastnameTF;
	}
	
	

}
