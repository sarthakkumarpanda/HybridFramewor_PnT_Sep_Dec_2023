package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.By;
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
		//driver.findElement(By.linkText("My Account")).click();
		homepage.selectRegisterOption(); //system will be re-directed to RegisterPage
		//driver.findElement(By.linkText("Register")).click();
		
	}
	
	@Test(priority=1)
	public void verifyingRegisterWithMandatoryFields() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		//driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'firstname']")).sendKeys(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		//driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'lastname']")).sendKeys(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Util.emailWithDateTimeStamp());
		//driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'email']")).sendKeys(Util.emailWithDateTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("mobile"));
		//driver.findElement(By.xpath("//aside[@id = 'column-right']/preceding::input[@id = 'input-telephone']")).sendKeys(dataProp.getProperty("mobile"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@name = 'telephone']/following::input[@name = 'password']")).sendKeys(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@name = 'telephone']/following::input[@name = 'confirm']")).sendKeys(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyCheckBox();
		//driver.findElement(By.xpath("//div[@class = 'pull-right']/child::a[1]/following-sibling::input[@name = 'agree']")).click();
		registerpage.clickOnContinueButton(); //System will re-direct to AccountSuccessPage
		//driver.findElement(By.xpath("//div[@class = 'pull-right']/descendant::input[@class = 'btn btn-primary']")).click();
		accountsuccesspage = new AccountSuccessPage(driver);
		String expectedSuccessMessage = dataProp.getProperty("accountSuccessMessage");
		String actualSuccessMessage = accountsuccesspage.retrieveAccountSuccessMessage();
		//String actualSuccessMessage = driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! Your new account has been successfully created!')]")).getText();
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
			
	}
	
	@Test(priority=2)
	public void verifyingRegisterWithAllFields() throws Exception {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Util.emailWithDateTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("mobile"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnNewsLetterYesOptionRadioButton();
	    registerpage.clickOnPrivacyPolicyCheckBox();
		registerpage.clickOnContinueButton();
		accountsuccesspage = new AccountSuccessPage(driver);
		String expectedSuccessMessage = dataProp.getProperty("accountSuccessMessage");
		String actualSuccessMessage = accountsuccesspage.retrieveAccountSuccessMessage();
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
			
	}
	
	@Test(priority=3)
	public void verifyingRegisterWithExistingEmail() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(prop.getProperty("validEmail"));
		registerpage.enterTelephone(dataProp.getProperty("mobile"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnNewsLetterYesOptionRadioButton();
	    registerpage.clickOnPrivacyPolicyCheckBox();
		registerpage.clickOnContinueButton();
		String actualDuplicateWarningMessage = registerpage.retrieveDuplicateEmailWarning();
		String expectedDuplicateWarningMessage = dataProp.getProperty("duplicateEmailWarningMessage");
		Assert.assertTrue(actualDuplicateWarningMessage.contains(expectedDuplicateWarningMessage));		
	}
	
	@Test(priority=4)
		public void verifyingRegisterWithMismatchPassword() {	
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Util.emailWithDateTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("mobile"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(dataProp.getProperty("invalidPassword"));
		registerpage.clickOnNewsLetterYesOptionRadioButton();
	    registerpage.clickOnPrivacyPolicyCheckBox();
		registerpage.clickOnContinueButton();
		String actualPasswordMismatchWarningMessage = registerpage.retrievePasswordMismatchWarning();
		String expectedPasswordMismatchWarningMessage = dataProp.getProperty("passwordMismatchWarningMessage");
		Assert.assertTrue(actualPasswordMismatchWarningMessage.contains(expectedPasswordMismatchWarningMessage));		
	}
	
	@Test(priority=5)
	public void verifyingRegisterWithNoFields() {
		registerpage = new RegisterPage(driver);
		registerpage.clickOnContinueButton();
		
		String actualPrivacyPolicyWarningMessage = registerpage.retrievePrivacyPolicyWarning();
		String expectedPrivacyPolicyWarningMessage = dataProp.getProperty("privacyPolicyWarningMessage");
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));
		
		String actualFirstNameWarningMessage = registerpage.retrieveFirstNameWarning();
		String expectedFirstNameWarningMessage = dataProp.getProperty("firstNameWarningMessage");
		Assert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
		
		String actualLastNameWarningMessage = registerpage.retrieveLastNameWarning();
		String expectedLastNameWarningMessage = dataProp.getProperty("lastNameWarningMessage");
		Assert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));
		
		String actualEmailWarningMessage = registerpage.retrieveEmailWarning();
		String expectedEmailWarningMessage = dataProp.getProperty("emailWarningMessage");
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = registerpage.retrieveTelephoneWarning();
		String expectedTelephoneWarningMessage = dataProp.getProperty("telephoneWarningMessage");
		Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		String actualPasswordWarningMessage = registerpage.retrievePasswordWarning();
		String expectedPasswordWarningMessage = dataProp.getProperty("passwordWarningMessage");
		Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
		
		
			
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
