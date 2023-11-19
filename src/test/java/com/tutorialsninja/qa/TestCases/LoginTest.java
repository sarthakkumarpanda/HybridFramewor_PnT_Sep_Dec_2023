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
	public AccountPage accountpage;
	
	@BeforeMethod
	public void setup() {
		driver = initalizeBrowserAndOpenApplication(prop.getProperty("browser"));
		homepage = new HomePage(driver);
		homepage.clickOnMyAccountDropMenu();
		loginpage = homepage.selectLoginOption(); //here system will be re-directed to LoginPage
	}
	
	
	@Test(priority=1, dataProvider = "TNLogin", dataProviderClass = ExcelCode.class)
	public void verifyLoginWithValidCredentials(String username, String password) {
		accountpage = loginpage.navigateToLoginPage(username, password);
		Assert.assertTrue(accountpage.getDisplayStatusOfEditAccountInfo());		
	}
	
	
	@Test(priority=2)
	public void VerifyLoginWithInvalidEmailValidPassword() {
		loginpage.navigateToLoginPage(Util.emailWithDateTimeStamp(), prop.getProperty("validPassword"))	;
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarningMessage")));
	}
	
	@Test(priority=3)
	public void VerifyLoginWithValidEmailInvalidPassword() {
		loginpage.navigateToLoginPage(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarningMessage")));
		
	}
	
	
	@Test(priority=4)
	public void VerifyLoginWithInvalidCredentials() {
		loginpage.navigateToLoginPage(Util.emailWithDateTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarningMessage")));
	}
	
	
	@Test(priority=5)
	public void VerifyLoginWithNoCredentials() {
		loginpage.clickOnLoginButton();
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarningMessage")));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
