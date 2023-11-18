package com.tutorialsninja.qa.TestBase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.Utilities.Util;

public class TestBase {
	
	public WebDriver driver;
	public ChromeOptions options;
	public Properties prop;
	public FileInputStream ip;
	public FileInputStream idatap;
	public Properties dataProp;
	
	//Create a Constructor
	public TestBase() throws Exception {
		prop = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorialsninja\\qa\\Config\\config.properties");
		prop.load(ip);
		
		dataProp = new Properties();
		idatap = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorialsninja\\qa\\TestData\\systemgeneratedResponseData_UserDefinedData.properties");
		dataProp.load(idatap);
	}
	
	
	public WebDriver initalizeBrowserAndOpenApplication(String browserName) {
		
		if(browserName.equals(prop.getProperty("browser"))) {
			options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			options.addArguments("start-maximized");
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}else if(browserName.equals("Firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equals("Edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.PAGE_LOAD_TIME));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Util.SCRIPT_TIME));
        driver.get(prop.getProperty("url"));
        
        return driver;
	}

}
