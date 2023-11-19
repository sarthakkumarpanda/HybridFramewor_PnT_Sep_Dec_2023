package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.Pages.AccountSuccessPage;
import com.tutorialsninja.qa.Pages.HomePage;
import com.tutorialsninja.qa.Pages.RegisterPage;
import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.Utilities.Util;

public class RegisterTest extends TestBase{
	
public RegisterTest() throws Exception {
		super();
	}


public WebDriver driver;
public HomePage homepage;
public RegisterPage registerpage;
public AccountSuccessPage accountsuccesspage;
	
	@BeforeMethod
	public void setup() {
		driver = initalizeBrowserAndOpenApplication(prop.getProperty("browser"));
		homepage = new HomePage(driver);
		homepage.clickOnMyAccountDropMenu();
		registerpage = homepage.selectRegisterOption(); //system will be re-directed to RegisterPage
		
	}
	
	@Test(priority=1)
	public void verifyingRegisterWithMandatoryFields() {
		accountsuccesspage = registerpage.registerPageMandatoryDetails(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), 
				Util.emailWithDateTimeStamp(), dataProp.getProperty("mobile"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
			
		Assert.assertEquals(accountsuccesspage.retrieveAccountSuccessMessage(), dataProp.getProperty("accountSuccessMessage"));
			
	}
	
	@Test(priority=2)
	public void verifyingRegisterWithAllFields() throws Exception {
		accountsuccesspage = registerpage.registerPageAllDetails(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Util.emailWithDateTimeStamp(), dataProp.getProperty("mobile"), prop.getProperty("validPassword"), 
				prop.getProperty("validPassword"));

		Assert.assertEquals(accountsuccesspage.retrieveAccountSuccessMessage(), dataProp.getProperty("accountSuccessMessage"));
			
	}
	
	@Test(priority=3)
	public void verifyingRegisterWithExistingEmail() {
		registerpage.registerPageAllDetails(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				prop.getProperty("validEmail"), dataProp.getProperty("mobile"), prop.getProperty("validPassword"), 
				prop.getProperty("validPassword"));
		Assert.assertTrue(registerpage.retrieveDuplicateEmailWarning().contains(dataProp.getProperty("duplicateEmailWarningMessage")));		
	}
	
	@Test(priority=4)
		public void verifyingRegisterWithMismatchPassword() {	
		registerpage.registerPageAllDetails(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Util.emailWithDateTimeStamp(), dataProp.getProperty("mobile"), prop.getProperty("validPassword"), 
				dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(registerpage.retrievePasswordMismatchWarning().contains(dataProp.getProperty("passwordMismatchWarningMessage")));		
	}
	
	@Test(priority=5)
	public void verifyingRegisterWithNoFields() {
		registerpage.clickOnContinueButton();
		
		Assert.assertTrue(registerpage.retrieveAllWarningMessageStatus(dataProp.getProperty("privacyPolicyWarningMessage"), 
				dataProp.getProperty("firstNameWarningMessage"), dataProp.getProperty("lastNameWarningMessage"), 
				dataProp.getProperty("emailWarningMessage"), dataProp.getProperty("telephoneWarningMessage"), 
				dataProp.getProperty("passwordWarningMessage")));				
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
