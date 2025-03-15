package TestScripts;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Utility.BaseTest;
import generic_Utility.JavaUtility;
import generic_Utility.WebDriverUtility;
import objectRepo.CreateOrganizationPage;

public class CreateOrganizationTestScript extends BaseTest{

	public CreateOrganizationTestScript() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Test(priority=1)
	public void createOrganizationMethod() {
		CreateOrganizationPage op = new CreateOrganizationPage(driver);
		op.getOrganizationsButton().click();
		op.getCreateOrgButton().click();
		op.getOrgNameField().sendKeys(ORG + JavaUtility.generateRandomNumer());
		op.getSaveButton().click();
		String actOrgName = op.getActOrgName().getText();
		System.out.println(actOrgName);
		if (actOrgName.contains(ORG)) {
			Reporter.log("Organization name Verified!!!",true);
		}

	}
	@Test(priority=2)
	public void createOrganizationWithIndustry()
	{
		CreateOrganizationPage op = new CreateOrganizationPage(driver);
		WebDriverUtility wdlib = new WebDriverUtility(driver);
		op.getOrganizationsButton().click();
		op.getCreateOrgButton().click();
		op.getOrgNameField().sendKeys(ORG + (int)(Math.random()*999));
		WebElement element=op.getIndustry();
		wdlib.handleDropDown(IND, element);
		op.getSaveButton().click();
		String actOrgName=op.getActOrgName().getText();
		String actIndustryName=op.getActIndustryName().getText();
		System.out.println("Selected Organization Name: "+actOrgName);
		System.out.println("Selected Industry Name: "+actIndustryName);
		if(actOrgName.contains(ORG))
			System.out.println("Organization Name Verified!!");
		if(actIndustryName.contains(IND))
			System.out.println("Industry Name Verifiied!!");
	}
	
	
	@Test(priority=3)
	public void createOrganizationWithIndustryAndPhone()
	{
		CreateOrganizationPage op=new CreateOrganizationPage(driver);
		op.getOrganizationsButton().click();
		op.getCreateOrgButton().click();
		op.getOrgNameField().sendKeys(ORG);
		WebElement element=op.getIndustry();
		Select dropdown=new Select(element);
		dropdown.selectByValue(IND);
		op.getSaveButton().click();
		op.getPhoneNo().sendKeys(PHNO);
		String actOrgName=op.getActOrgName().getText();
		String actIndustryName=op.getActIndustryName().getText();
		String actPhoneNumber=op.getActPhoneNo().getText();
		System.out.println("Selected Organization Name: "+actOrgName);
		System.out.println("Selected Industry Name: "+actIndustryName);
		System.out.println("Selected Phone No: "+actPhoneNumber);
		if(actOrgName.contains(ORG))
			System.out.println("Organization Name Verified!!");
		if(actIndustryName.contains(IND))
			System.out.println("Industry Name Verifiied!!");
		if(actPhoneNumber.contains(PHNO))
			System.out.println("Phone Number Verified!!");
		
	}

}
