package io.learn.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.learn.exceptions.LoginFailedException;
import io.learn.listener.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test(priority = 1, groups = { "smoke" })
    public void testLoginSuccess() {
        ExtentTest test = TestListener.getTest();

        // verify that the user is on the Products page
        String logoText = loginPage.getLogoText();
        assertEquals(logoText, "Swag Labs");
        test.log(Status.INFO, "Verifying the Products page is displayed");
        assertTrue(productPage.isProductsPageDisplayed(), "Products page is not displayed");
        test.log(Status.PASS, "Products page is displayed");

        // verify the page URL
        String currentUrl = productPage.getCurrentUrl();
        test.log(Status.INFO, "Verifying the current page URL:" +currentUrl);
        assertEquals(currentUrl, getExpectedUrl(), "Page URL is incorrect");
        test.log(Status.PASS, "Correct URL is displayed");

        // verify that the product list is displayed
        test.log(Status.INFO, "Verifying the product list is displayed");
        assertTrue(productPage.isProductListDisplayed(), "Product list is not displayed");
        test.log(Status.PASS, "Product list page is displayed");

        test.log(Status.INFO, "Test testLoginSuccess passed");
    }

    @Test(priority = 2)
    public void testLoginFailure() {
        ExtentTest test = TestListener.getTest();
        test.log(Status.INFO, "Starting Test: testLoginFailure");

        test.log(Status.INFO, "Logging out from the application");
        productPage.logout();
        test.log(Status.PASS, "Logout is successful");

        test.log(Status.INFO, "Checking if the user is on the login page");
        assertTrue(loginPage.isLoginPageDisplayed(), "User is not redirected to login page after the logout");
        test.log(Status.PASS, "User is on the Login page");

        test.log(Status.INFO, "Entering Wrong credentials");
        loginPage.enterCredentials("wrong_user", "wrong_password");

        try {
            productPage = loginPage.clickLogin();
            test.log(Status.FAIL, "Login was successful, but it should have failed");
        } catch (LoginFailedException e) {
            test.log(Status.PASS, "Login failed as expected: " + e.getMessage());
        }

        test.log(Status.INFO, "Verifying error message is displayed on login failure");
        assertTrue(loginPage.isLoginErrorDisplayed(), "Error message is not displayed, check user credentials");
        test.log(Status.PASS, "Error message is displayed successfully");

        test.log(Status.INFO, "The testLoginFailure passed successfully");
    }

    private String getExpectedUrl() {
        logger.debug("Fetching expected page URL from config");
        return configReader.getProperty("app.url") + "inventory.html";
    }

}
