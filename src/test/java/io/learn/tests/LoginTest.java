package io.learn.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.learn.pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test
	public void testLoginFlow() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("standard_user" , "secret_sauce");
		String logoText = loginPage.getLogoText();
		Assert.assertEquals(logoText, "Swag Labs");
			
	}
	
}



























