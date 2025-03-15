package generic_Utility;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import objectRepo.CreateContactClass;
import objectRepo.CreateOrganizationPage;
import objectRepo.LoginPage;

public class BaseTest {


		String URL, BRO, UN, PWD;
		protected String ORG;
		public String IND;
		protected String PHNO;
		protected String LN;
		public static WebDriver driver;
		
		

		public BaseTest() throws IOException {
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
