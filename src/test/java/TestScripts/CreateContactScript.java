package TestScripts;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import generic_Utility.BaseTest;
import generic_Utility.JavaUtility;
import generic_Utility.WebDriverUtility;
import objectRepo.CreateContactClass;

public class CreateContactScript extends BaseTest{
	
	public CreateContactScript() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Test(priority=1)
	public void createContactWithDateandLastName() throws InterruptedException 
	{
		WebDriverUtility wdlib = new WebDriverUtility(driver);
		CreateContactClass cp=new CreateContactClass(driver);
		cp.getContactsButton().click();
		cp.getCreateContactButton().click();
		Thread.sleep(2000);
		cp.getLastNameField().sendKeys(LN);
		Thread.sleep(2000);
		WebElement supportStartDateButton=cp.getSupportStartDateButton();
		wdlib.toScrollWindow(supportStartDateButton);
		JavaUtility ju=new JavaUtility();
		String currentDate=ju.currentdate();
		String newAddedDate=ju.newAddedDate(30); //date after added 30 days
		//Step11) Send the current date and added new date to the respective fields and save the contact info
		Thread.sleep(2000);
		cp.getSupportStartDateField().clear();
		cp.getSupportStartDateField().sendKeys(currentDate);
		Thread.sleep(2000);
		cp.getSupportEndDateField().clear();
		cp.getSupportEndDateField().sendKeys(newAddedDate);
		Thread.sleep(2000);
		cp.getSaveButton().click();
	}
	@Test(priority=2)
	public void createContactWithLastName()
	{
		WebDriverUtility wdlib = new WebDriverUtility(driver);
		CreateContactClass cp=new CreateContactClass(driver);
		cp.getContactsButton().click();
		cp.getCreateContactButton().click();
		cp.getLastNameField().sendKeys(LN);
		cp.getMobileTextField().sendKeys(PHNO);
		cp.getSaveButton().click();
		String actMobileNo=cp.getActMobileNo().getText();
		String actLastName=cp.getActLastName().getText();
		System.out.println("Entered LastName:"+actLastName);
		System.out.println("Entered Mobile Number:"+actMobileNo);
		if(actMobileNo.contains(PHNO))
			System.out.println("Mobile number verified!!!");
		if(actLastName.contains(LN))
			System.out.println("Last name verified!!");

		
	}

}
