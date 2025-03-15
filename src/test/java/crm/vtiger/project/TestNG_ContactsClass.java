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
import objectRepo.CreateContactClass;
import objectRepo.CreateOrganizationPage;
import objectRepo.LoginPage;
import objectRepo.LogoutPage;


@Listeners(ExtentReportClass.class)

public class TestNG_ContactsClass {

	String URL, BRO, UN, PWD, ORG, IND, PHNO, LN;
	static WebDriver driver;
	
	

	public TestNG_ContactsClass() throws IOException {
		FileUtility file = new FileUtility();
		this.URL = file.getDataFromPropertiesFile("url");
		this.BRO = file.getDataFromPropertiesFile("bro");
		this.UN = file.getDataFromPropertiesFile("un");
		this.PWD = file.getDataFromPropertiesFile("pwd");
		this.ORG = file.getDataFromExcelFile("org", 1, 0);
		this.IND=file.getDataFromExcelFileWithoutRandomNo("org", 1, 1);
		this.PHNO=file.getDataFromPropertiesFile("phone");
		this.LN=file.getDataFromExcelFile("contact", 1, 2);

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
			System.out.println("Mobile number verified!!");
		if(actLastName.contains(LN))
			System.out.println("Last name verified!!");

		
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
