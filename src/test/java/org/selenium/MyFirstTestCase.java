package org.selenium;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void GuestCheckoutUsingDirectBankTransfer() throws InterruptedException {

        StorePage storePage= new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");

        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(10000);
        CartPage cartPage = storePage.viewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();
        checkoutPage.enterTextInFirstName("demo").
                enterTextInLastName("user").
                enterTextInAddress1("San Francisco").
                enterTextInAddress2("San Francisco").
                enterTextInCity("San Francisco").
                enterTextInPostcode("94188").
                enterTextInEmail("cschiragsharma@gmail.com");
        Thread.sleep(5000);
        checkoutPage.clickPlaceOrderBtn();
        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }
    @Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {

        StorePage storePage= new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");

        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(10000);
        CartPage cartPage = storePage.viewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();

        //login functionality

        checkoutPage.showLoginBtn();
        Thread.sleep(5000);

        checkoutPage.
                login("demouser0909","demopass").
                enterTextInFirstName("demo").
                enterTextInLastName("user").
                enterTextInAddress1("San Francisco").
                enterTextInAddress2("San Francisco").
                enterTextInCity("San Francisco").
                enterTextInPostcode("94188").
                enterTextInEmail("cschiragsharma@gmail.com");
        Thread.sleep(5000);
        checkoutPage.clickPlaceOrderBtn();
        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }

}
