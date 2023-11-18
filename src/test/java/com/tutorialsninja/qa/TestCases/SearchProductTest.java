package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Pages.HomePage;
import com.tutorialsninja.qa.Pages.SearchPage;
import com.tutorialsninja.qa.TestBase.TestBase;

public class SearchProductTest extends TestBase{
public SearchProductTest() throws Exception {
		super();
	}

public WebDriver driver;
	
	@BeforeMethod
	public void registerSetup() {
		driver = initalizeBrowserAndOpenApplication(prop.getProperty("browser"));
	}
	
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterProductDetail(dataProp.getProperty("validProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		homepage.clickOnSearchIcon(); //System will be re-directed to SearchPage
		//driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		SearchPage searchpage = new SearchPage(driver);
	    Assert.assertTrue(searchpage.verifyDisplayStatusOfValidProduct());
		//Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterProductDetail(dataProp.getProperty("invalidProduct"));
		homepage.clickOnSearchIcon();
		SearchPage searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.verifyDisplayStatusOfInValidProduct());
	}
	
	@Test(priority=3)
	public void verifySearchWithNoProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.clickOnSearchIcon();
		SearchPage searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.verifyDisplayStatusOfInValidProduct());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
