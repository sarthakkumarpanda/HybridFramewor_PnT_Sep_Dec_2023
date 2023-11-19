package com.tutorialsninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {
	
	public WebDriver driver;
	
	@FindBy(xpath = "//li[contains(text(), 'Product Code:Product 21')]")
	private WebElement ProductCodeInfo;
	
	@FindBy(id = "button-cart")
	private WebElement addToCartButtonInProductInfoPage;
	
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnAddToCartButtonInProdInfo() {
		addToCartButtonInProductInfoPage.click();
	}
	
	public boolean validateDisplayStatusProdInfo() {
		boolean displayStatus = ProductCodeInfo.isDisplayed();
		return displayStatus;
	}

}
