package io.learn.pages;

//import io.learn.exceptions.LoginFailedException;
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
    

    public LoginPage(WebDriver driver) {
    	this.driver = driver;
    }
    
    //actions
    
    public void login(String username, String password) {
    	this.enterUsername(username);
    	this.enterPassword(password);
    	this.clickLogin();
    }
    
    
    private void enterUsername(String name) {
    	driver.findElement(userNameField).clear();
    	driver.findElement(userNameField).sendKeys(name);
    }
    
    private void enterPassword(String password) {
    	driver.findElement(passwordField).clear();
    	driver.findElement(passwordField).sendKeys(password);
    }
    
    
    private void clickLogin() {
    	driver.findElement(loginButton).click();
    }
}