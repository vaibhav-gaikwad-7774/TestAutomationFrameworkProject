package io.learn.pages;

import io.learn.exceptions.LoginFailedException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    // elements
    private By userNameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector(".error-message-container.error");
    private By appLogoText = By.xpath("//div[@class='app_logo']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // actions
    public void enterCredentials(String username, String password) {
        this.enterUsername(username);
        this.enterPassword(password);
    }

    public String getLogoText() {
        return driver.findElement(appLogoText).getText();
    }

    private void enterUsername(String name) {
        driver.findElement(userNameField).clear();
        driver.findElement(userNameField).sendKeys(name);
    }

    private void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public ProductPage clickLogin() {
        driver.findElement(loginButton).click();
        if(isLoginErrorDisplayed()) {
            throw new LoginFailedException("Login failed due to incorrect username or password.");
        }
        return new ProductPage(driver);
    }

    public boolean isLoginErrorDisplayed() {
        try {
            WebElement element = driver.findElement(errorMessage);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            // if the error message is not found, there was no error - login successful
            return false;
        }
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElement(userNameField).isDisplayed();
    }
}
