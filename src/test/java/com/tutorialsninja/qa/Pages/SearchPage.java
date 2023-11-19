package com.tutorialsninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	public WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validProduct;
	
	@FindBy(xpath = "//p[text() = 'There is no product that matches the search criteria.']")
	private WebElement invalidProductWarningMessage;
	
	@FindBy(xpath = "//div[@class = 'caption']/following-sibling::div/child::button[1]")
	private WebElement addToCartButton;
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyDisplayStatusOfValidProduct() {
		boolean presenceValidProduct = validProduct.isDisplayed();
		return presenceValidProduct;
	}
	
	public boolean verifyDisplayStatusOfInValidProduct() {
		boolean presenceInvalidProduct = invalidProductWarningMessage.isDisplayed();
		return presenceInvalidProduct;
	}
	
	public ProductInfoPage clickOnAddToCartButton() {
		addToCartButton.click();
		return new ProductInfoPage(driver);
	}
	
}
