package org_test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic_Utility.FileUtility;
import generic_Utility.WebDriverUtility;
import objectRepo.CreateOrganizationPage;
import objectRepo.LoginPage;

public class CreateOrgandIndustryTest {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		/*WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		
		FileInputStream file=new FileInputStream("C:\\Users\\Asus\\eclipse-workspace\\TestNG Workspace\\advanced_selenium_a30\\src\\main\\resources\\commonData.properties");
		Properties oBJ=new Properties();
		oBJ.load(file);
		
		
		String URL=oBJ.getProperty("url");
		String BRO=oBJ.getProperty("bro");
		String UN=oBJ.getProperty("un");
		String PASS=oBJ.getProperty("pwd");
		int ran=(int)(Math.random()*999);
		String ORG=oBJ.getProperty("org")+ran;
		
		/*oBJ.setProperty("industry","Biotechnology");
		FileOutputStream fos=new FileOutputStream("C:\\Users\\Asus\\eclipse-workspace\\TestNG Workspace\\advanced_selenium_a30\\src\\main\\resources\\commonData.properties");
		oBJ.store(fos, "Added industry to property file");
		String IND=oBJ.getProperty("industry");*/		

		//Step1) Load commonData through properties file
		
		FileUtility file=new FileUtility();
		String URL=file.getDataFromPropertiesFile("url");
		String BRO=file.getDataFromPropertiesFile("bro");
		String UN=file.getDataFromPropertiesFile("un");
		String PASS=file.getDataFromPropertiesFile("pwd");
		String PHNO=file.getDataFromPropertiesFile("phone");
		//String IND=file.getDataFromPropertiesFile("industry");
		
		
		//Step2) Load test script data from excel file
		
		String IND=file.getDataFromExcelFileWithoutRandomNo("org", 1, 1);
		String ORG=file.getDataFromExcelFile("org", 1, 0);
		
		
		//Step3) Launch the browser according to properties file
		WebDriver driver = null;

		if (BRO.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BRO.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BRO.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		//Step4) Use WebDriver utility object to access the WebDriver methods
		
		WebDriverUtility wdlib=new WebDriverUtility(driver);
		wdlib.maximize(driver);
		wdlib.waitForElement(driver);
					
		
		//Step5) Use POM classes of LoginPage and ContactPage to get the required locators
		
		LoginPage lp=new LoginPage(driver);
		CreateOrganizationPage op=new CreateOrganizationPage(driver);

		
		//Step6) Launch the browser
		driver.get(URL);
		

		
		/*driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UN);
		driver.findElement(By.name("user_password")).sendKeys(PASS);
		driver.findElement(By.id("submitButton")).click();*/
		
		
		//Step7) Login to the Vtiger website
		lp.getUsername().sendKeys(UN);
		lp.getPassword().sendKeys(PASS);
		lp.getSubmitButton().click();


		
		/*driver.findElement(By.xpath("//td[@class='tabUnSelected']//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();*/
		
		//Step 8) Click on Origanizations-->Select the add button
		op.getOrganizationsButton().click();
		op.getCreateOrgButton().click();

		
		
		/*driver.findElement(By.name("accountname")).sendKeys(ORG);
		WebElement element=driver.findElement(By.name("industry"));*/
		/*Select dropdown=new Select(element);
		dropdown.selectByValue(IND);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();*/
		
		
		//Step9) Enter the organization name-->Select the given industry from dopdown--->Click on Save button
		op.getOrgNameField().sendKeys(ORG);
		WebElement element=op.getIndustry();
		wdlib.handleDropDown(IND, element);
		op.getSaveButton().click();
	
		
		/*String actOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
		String actIndustryName=driver.findElement(By.id("dtlview_Industry")).getText();
		System.out.println("Selected Organization Name: "+actOrgName);
		System.out.println("Selected Industry Name: "+actIndustryName);
		if(actOrgName.contains(ORG))
			System.out.println("Organization Name Verified!!");
		if(actIndustryName.contains(IND))
			System.out.println("Industry Name Verifiied!!");*/
		
		//Step10) Verify the entered Organization and selected industry
		
		String actOrgName=op.getActOrgName().getText();
		String actIndustryName=op.getActIndustryName().getText();
		System.out.println("Selected Organization Name: "+actOrgName);
		System.out.println("Selected Industry Name: "+actIndustryName);
		if(actOrgName.contains(ORG))
			System.out.println("Organization Name Verified!!");
		if(actIndustryName.contains(IND))
			System.out.println("Industry Name Verifiied!!");
	
	}

}

