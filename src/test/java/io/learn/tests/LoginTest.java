package io.learn.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.learn.pages.LoginPage;

public class LoginTest {
	
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		
	}
	
	@Test
	public void testLoginFlow() {
		LoginPage loginPage = new LoginPage(driver);
		
	}
}