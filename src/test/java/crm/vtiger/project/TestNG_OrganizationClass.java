package crm.vtiger.project;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic_Utility.FileUtility;
import generic_Utility.JavaUtility;
import generic_Utility.WebDriverUtility;
import objectRepo.CreateOrganizationPage;
import objectRepo.LoginPage;
import objectRepo.LogoutPage;


@Listeners(ExtentReportClass.class)

public class TestNG_OrganizationClass {

	public String URL, BRO, UN, PWD, ORG, IND, PHNO;
	static WebDriver driver;
	
	

	public TestNG_OrganizationClass() throws IOException {
		FileUtility file = new FileUtility();
		this.URL = file.getDataFromPropertiesFile("url");
		this.BRO = file.getDataFromPropertiesFile("bro");
		this.UN = file.getDataFromPropertiesFile("un");
		this.PWD = file.getDataFromPropertiesFile("pwd");
		this.ORG = file.getDataFromExcelFile("org", 1, 0);
		this.IND=file.getDataFromExcelFileWithoutRandomNo("org", 1, 1);
		this.PHNO=file.getDataFromPropertiesFile("phone");

	}

	@BeforeClass
	public void launchBrowser() {
		

		if (BRO.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BRO.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BRO.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.get(URL);
		WebDriverUtility wdlib = new WebDriverUtility(driver);
		wdlib.maximize(driver);
		wdlib.waitForElement(driver);
	}

	@BeforeMethod
	public void loginPage() {

		LoginPage lp = new LoginPage(driver);
		CreateOrganizationPage op = new CreateOrganizationPage(driver);
		lp.getUsername().sendKeys(UN);
		lp.getPassword().sendKeys(PWD);
		lp.getSubmitButton().click();

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
	@AfterMethod
	public void logOut() {
		
		WebElement element=driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		WebDriverUtility wdlib = new WebDriverUtility(driver);
		wdlib.mouseHover(element);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
	}

	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

}
