package io.learn.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.learn.listener.TestListener;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ProductTest extends BaseTest {

    @Test(priority = 1)
    public void testProductDetails() {
        ExtentTest test = TestListener.getTest();

        test.log(Status.INFO, "Verifying products list is displayed.");
        assertTrue(productPage.isProductListDisplayed(), "Products are not displayed");
        test.log(Status.PASS, "Product list is displayed.");

        test.log(Status.INFO, "Verifying product title is displayed.");
        assertTrue(productPage.isProductTitleDisplayed("Sauce Labs Backpack"),
                "Product title is not displayed");
        test.log(Status.PASS, "Product title is displayed.");

        test.log(Status.INFO, "Verifying product price is displayed.");
        assertTrue(productPage.isProductPriceDisplayed("Sauce Labs Backpack"),
                "Product price is not displayed");
        test.log(Status.PASS, "Product price is displayed.");
    }

    @Test(priority = 2)
    public void testAddToCart() {
        ExtentTest test = TestListener.getTest();
        test.log(Status.INFO, "Adding product to cart.");
        productPage.addProductToCart("Sauce Labs Backpack");

        test.log(Status.INFO, "Checking if product is added to cart.");
        assertTrue(productPage.isProductInCart("Sauce Labs Backpack"),
                "Rroduct is not added to the cart");
        test.log(Status.PASS, "Product is added to cart.");
    }
}
