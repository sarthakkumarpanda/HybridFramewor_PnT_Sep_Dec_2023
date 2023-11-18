package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Pages.AccountPage;
import com.tutorialsninja.qa.Pages.HomePage;
import com.tutorialsninja.qa.Pages.LoginPage;
import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.TestData.ExcelCode;
import com.tutorialsninja.qa.Utilities.Util;

public class LoginTest extends TestBase{
	
	public LoginTest() throws Exception {
		super();	
	}

	public WebDriver driver;
	public LoginPage loginpage;
	public HomePage homepage;
	
	@BeforeMethod
	public void setup() {
		driver = initalizeBrowserAndOpenApplication(prop.getProperty("browser"));
		homepage = new HomePage(driver);
		homepage.clickOnMyAccountDropMenu();
		//driver.findElement(By.linkText("My Account")).click();
		homepage.selectLoginOption(); //here system will be re-directed to LoginPage
		//driver.findElement(By.linkText("Login")).click();
		
	}
	
	
	@Test(priority=1, dataProvider = "TNLogin", dataProviderClass = ExcelCode.class)
	public void verifyLoginWithValidCredentials(String username, String password) {
		loginpage.enterEmailAddress(username);
		//driver.findElement(By.id("input-email")).sendKeys(username);
		loginpage.enterPassword(password);
		//driver.findElement(By.id("input-password")).sendKeys(password);
		loginpage.clickOnLoginButton(); //System will be re-directed to AccountPage
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		AccountPage accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.getDisplayStatusOfEditAccountInfo());
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());			
	}
	
	
	@Test(priority=2)
	public void VerifyLoginWithInvalidEmailValidPassword() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Util.emailWithDateTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}
	
	@Test(priority=3)
	public void VerifyLoginWithValidEmailInvalidPassword() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
	}
	
	
	@Test(priority=4)
	public void VerifyLoginWithInvalidCredentials() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Util.emailWithDateTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
	    //driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	
	
	@Test(priority=5)
	public void VerifyLoginWithNoCredentials() {
		loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
