package io.learn.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LogoutTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LogoutTest.class);

    @Test(groups = { "smoke" })
    public void testLogout() {
        logger.info("Verifying product title is displayed.");
        assertTrue(productPage.isProductTitleDisplayed("Sauce Labs Backpack"),
                "Product title is not displayed");

        logger.info("Performing logout.");
        productPage.logout();

        logger.info("Verifying redirection to login page after logout.");
        assertTrue(loginPage.isLoginPageDisplayed(), "User is not redirected to the login page");

        logger.info("Logout test completed successfully.");
    }
}
