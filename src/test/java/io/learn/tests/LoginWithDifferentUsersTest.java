package io.learn.tests;

import io.learn.dataprovider.UserDataProvider;
import io.learn.exceptions.LoginFailedException;
import io.learn.pages.CartPage;
import io.learn.pages.LoginPage;
import io.learn.pages.ProductPage;
import io.learn.utils.Browser;
import io.learn.utils.ConfigReader;
import io.learn.utils.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginWithDifferentUsersTest {

    private WebDriver driver;
    private static final ConfigReader configReader = new ConfigReader();
    private static final Logger logger = LogManager.getLogger(LoginWithDifferentUsersTest.class);

    protected LoginPage loginPage;
    protected ProductPage productPage;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(ITestContext context, String browserName) {
        logger.debug("Initialize the WebDriver for browser: {}", browserName);
        Browser browser = Browser.valueOf(browserName.toUpperCase());

        logger.debug("Initialize the WebDriver");
        driver = WebDriverFactory.createDriver(browser);
        context.setAttribute("driver", driver);

        logger.info("Navigating to the base URL: {}", configReader.getProperty("app.url"));
        driver.get(configReader.getProperty("app.url"));

        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "userData")
    public void testLoginWithDifferentUsers(String username, String password, boolean shouldLoginSucceed) {
        loginPage.enterCredentials(username, password);
        productPage = loginPage.clickLogin();

        if(shouldLoginSucceed) {
            assertTrue(productPage.isProductsPageDisplayed(), "Products page is not displayed");
            productPage.logout();
            assertTrue(loginPage.isLoginPageDisplayed(), "User is not on the login page");
        } else {
            assertTrue(loginPage.isLoginErrorDisplayed(), "Error message is not present");
        }
    }

    @DataProvider(name = "userData")
    private Object[][] userData() {
        return UserDataProvider.userData();
    }


}
