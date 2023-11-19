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
public HomePage homepage;
public SearchPage searchpage;
	
	@BeforeMethod
	public void registerSetup() {
		driver = initalizeBrowserAndOpenApplication(prop.getProperty("browser"));
	}
	
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		homepage = new HomePage(driver);
		homepage.enterProductDetail(dataProp.getProperty("validProduct"));
		searchpage = homepage.clickOnSearchIcon(); //System will be re-directed to SearchPage
	    Assert.assertFalse(searchpage.verifyDisplayStatusOfValidProduct()); //deliberate failure
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		homepage = new HomePage(driver);
		homepage.enterProductDetail(dataProp.getProperty("invalidProduct"));
		searchpage = homepage.clickOnSearchIcon();
		Assert.assertTrue(searchpage.verifyDisplayStatusOfInValidProduct());
	}
	
	@Test(priority=3)
	public void verifySearchWithNoProduct() {
		homepage = new HomePage(driver);
		searchpage = homepage.clickOnSearchIcon();
		Assert.assertTrue(searchpage.verifyDisplayStatusOfInValidProduct());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
