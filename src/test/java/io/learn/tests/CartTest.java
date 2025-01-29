package io.learn.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.learn.listener.TestListener;
import io.learn.pages.CartPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    CartPage cartPage;

    @Test(priority = 1)
    public void testAddingProductToCart() {
        ExtentTest test = TestListener.getTest();
        test.log(Status.INFO, "Adding product to cart.");
        productPage.addProductToCart("Sauce Labs Backpack");

        test.log(Status.INFO, "Navigating to cart.");
        cartPage = productPage.goToCart();

        test.log(Status.INFO, "Verifying product is in the cart.");
        assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "Product is not added to cart");
        test.log(Status.PASS, "Product is in the cart.");
    }

    @Test(priority = 2)
    public void testRemoveFromCart() {
        ExtentTest test = TestListener.getTest();

        test.log(Status.INFO, "Removing product from cart.");
        cartPage.removeProductFromCart("Sauce Labs Backpack");

        test.log(Status.INFO, "Verifying product is removed from the cart.");
        assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"));
        test.log(Status.PASS, "Product is removed from the cart.");
    }
}
