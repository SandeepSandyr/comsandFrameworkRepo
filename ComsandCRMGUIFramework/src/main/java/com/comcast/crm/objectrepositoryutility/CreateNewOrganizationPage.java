package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgNameTF;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement orgSaveButton;
	
	@FindBy(name = "industry")
	private WebElement industryDrpdwn;
	
	@FindBy(name = "accounttype")
	private WebElement typeDrpdwn;
	
	@FindBy(id = "phone")
	private WebElement phoneTF;
	
	
	
	public WebElement getPhoneTF() {
		return phoneTF;
	}



	public WebElement getOrgNameTF() {
		return orgNameTF;
	}



	public WebElement getOrgSaveButton() {
		return orgSaveButton;
	}



	public WebElement getIndustryDrpdwn() {
		return industryDrpdwn;
	}



	public WebElement getTypeDrpdwn() {
		return typeDrpdwn;
	}



	public void createOrg(String orgName) {
		
		orgNameTF.sendKeys(orgName);
		orgSaveButton.click();
		
	}
	
    public void createOrg(String orgName , String industry , String type) {
		
		orgNameTF.sendKeys(orgName);
		Select sel1 = new Select(industryDrpdwn);
		sel1.selectByValue(industry);
		Select sel2 = new Select(typeDrpdwn);
		sel2.selectByValue(type);
		orgSaveButton.click();
		
	}
    
    public void createOrg(String orgName , String phoneNumber) {
    	
    	orgNameTF.sendKeys(orgName);
    	phoneTF.sendKeys(phoneNumber);
    	orgSaveButton.click();
    }
	
	

}
