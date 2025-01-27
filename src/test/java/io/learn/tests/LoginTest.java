package io.learn.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.learn.pages.LoginPage;

public class LoginTest {
	
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		
	}
	
	@Test
	public void testLoginFlow() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("standard_user" , "secret_sauce");
		String logoText = loginPage.getLogoText();
		Assert.assertEquals(logoText, "Swag Labs");
		
		
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
}



























