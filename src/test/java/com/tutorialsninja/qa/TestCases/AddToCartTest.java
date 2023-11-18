package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.TestBase.TestBase;

public class AddToCartTest extends TestBase{
public AddToCartTest() throws Exception {
		super();
	}

public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = initalizeBrowserAndOpenApplication(prop.getProperty("browser"));
	}
	
	@Test
	public void checkingOutValidProduct() throws Exception {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		driver.findElement(By.xpath("//div[@class = 'caption']/following-sibling::div/child::button[1]")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(), 'Product Code:Product 21')]")).isDisplayed());
		driver.findElement(By.xpath("//button[@id = 'button-cart']")).click();
		String expectedMessage = dataProp.getProperty("productAddedSuccessfullyToCartMessage");
		Thread.sleep(3000);
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		driver.findElement(By.xpath("//div[@id = 'cart']")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Checkout")).click();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
