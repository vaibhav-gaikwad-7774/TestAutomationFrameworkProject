package io.learn.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.learn.utils.ConfigReader;

public class BaseTest {
	
	protected WebDriver driver;
	ConfigReader configReader = new ConfigReader();
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.get(configReader.getProperty("app.url"));
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
