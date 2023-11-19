package com.tutorialsninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	public WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id = "input-email")
	private WebElement emailField;
	
	@FindBy(id = "input-telephone")
	private WebElement telehoneField;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(name = "agree")
	private WebElement privacyPolicyCheckBox;
	
	@FindBy(css = "input.btn.btn-primary")
	private WebElement continueButton;
	
	@FindBy(xpath = "//input[@name = 'newsletter' and @value = '1']")
	private WebElement newsLetterRadioButton;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-confirm']/following-sibling::div")
	private WebElement passwordMismatchWarningMessage;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id = 'input-firstname']/following-sibling::div[1]")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//input[@id = 'input-lastname']/following-sibling::div[1]")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//input[@id = 'input-email']/following-sibling::div[1]")
	private WebElement emailWarning;
	
	@FindBy(xpath = "//input[@id = 'input-telephone']/following-sibling::div[1]")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//input[@id = 'input-password']/following-sibling::div[1]")
	private WebElement passwordWarning;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstnametext) {
		firstNameField.sendKeys(firstnametext);
	}
	
	public void enterLastName(String lastnametext) {
		lastNameField.sendKeys(lastnametext);
	}
	
	public void enterEmail(String emailtext) {
		emailField.sendKeys(emailtext);
	}
	
	public void enterTelephone(String telephonetext) {
		telehoneField.sendKeys(telephonetext);
	}
	
	public void enterPassword(String passwordtext) {
		passwordField.sendKeys(passwordtext);
	}
	
	public void enterConfirmPassword(String confirmpasswordtext) {
		confirmPasswordField.sendKeys(confirmpasswordtext);
	}
	
	public void clickOnNewsLetterYesOptionRadioButton() {
		newsLetterRadioButton.click();
	}
	
	public void clickOnPrivacyPolicyCheckBox() {
		privacyPolicyCheckBox.click();
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerPageMandatoryDetails(String firstnametext, String lastnametext, String emailtext, String telephonetext, String passwordtext, String confirmpasswordtext) {
		firstNameField.sendKeys(firstnametext);
		lastNameField.sendKeys(lastnametext);
		emailField.sendKeys(emailtext);
		telehoneField.sendKeys(telephonetext);
		passwordField.sendKeys(passwordtext);
		confirmPasswordField.sendKeys(confirmpasswordtext);
		privacyPolicyCheckBox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerPageAllDetails(String firstnametext, String lastnametext, String emailtext, String telephonetext, String passwordtext, String confirmpasswordtext) {
		firstNameField.sendKeys(firstnametext);
		lastNameField.sendKeys(lastnametext);
		emailField.sendKeys(emailtext);
		telehoneField.sendKeys(telephonetext);
		passwordField.sendKeys(passwordtext);
		confirmPasswordField.sendKeys(confirmpasswordtext);
		newsLetterRadioButton.click();
		privacyPolicyCheckBox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String retrieveDuplicateEmailWarning() {
		String duplicateEmailWarning = duplicateEmailWarningMessage.getText();
		return duplicateEmailWarning;
		
	}
	
	public String retrievePasswordMismatchWarning() {
		String passwordMismatchWarning = passwordMismatchWarningMessage.getText();
		return passwordMismatchWarning;
		
	}
	
	public String retrievePrivacyPolicyWarning() {
		String PPolicyWarning = privacyPolicyWarning.getText();
		return PPolicyWarning;	
	}
	
	public String retrieveFirstNameWarning() {
		String FNWarning = firstNameWarning.getText();
		return FNWarning;	
	}
	
	public String retrieveLastNameWarning() {
		String LNWarning = lastNameWarning.getText();
		return LNWarning;	
	}
	
	public String retrieveEmailWarning() {
		String EmWarning = emailWarning.getText();
		return EmWarning;	
	}
	
	public String retrieveTelephoneWarning() {
		String TWarning = telephoneWarning.getText();
		return TWarning;	
	}
	
	public String retrievePasswordWarning() {
		String PwdWarning = passwordWarning.getText();
		return PwdWarning;	
	}
	
	public boolean retrieveAllWarningMessageStatus(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning, String expectedLastNameWarning,
			String expectedEmailWarning, String expectedTelephoneWarning, String expectedPasswordWarning) {
		
		boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
		boolean firstNameWarningStatus = firstNameWarning.getText().contains(expectedFirstNameWarning);
		boolean lastNameWarningStatus = lastNameWarning.getText().contains(expectedLastNameWarning);
		boolean emailWarningStatus = emailWarning.getText().contains(expectedEmailWarning);
		boolean telephoneWarningStatus = telephoneWarning.getText().contains(expectedTelephoneWarning);
		boolean passwordWarningStatus = passwordWarning.getText().contains(expectedPasswordWarning);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus &&
				passwordWarningStatus;
	}
}
