package io.learn.pages;

import io.learn.exceptions.CartOperationException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    private By removeButton = By.id("remove-sauce-labs-backpack");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductInCart(String productName) {
        return driver.getPageSource().contains(productName);
    }

    public void removeProductFromCart(String productName) {
        try {
            driver.findElement(By
                    .xpath("//div[text()='" + productName + "']/parent::a/following-sibling::div[2]/div/following-sibling::button")).click();
        } catch (NoSuchElementException e) {
            throw new CartOperationException("Failed to remove product: " + productName + " from the cart.");
        }
    }
}
