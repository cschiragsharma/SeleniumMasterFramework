package org.selenium;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void GuestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setFirstName("demo");
        billingAddress.setLastName("user");
        billingAddress.setAddress1("San Francisco");
        billingAddress.setAddress2("San Francisco");
        billingAddress.setCity("San Francisco");
        billingAddress.setPostalCode("94188");
        billingAddress.setEmail("cschiragsharma@gmail.com");

        StorePage storePage= new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");

        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(10000);
        CartPage cartPage = storePage.viewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage = cartPage.
                clickCheckoutBtn().
                setBillingAddress(billingAddress);
        Thread.sleep(5000);
        checkoutPage.clickPlaceOrderBtn();
        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }
    @Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setFirstName("demo");
        billingAddress.setLastName("user");
        billingAddress.setAddress1("San Francisco");
        billingAddress.setAddress2("San Francisco");
        billingAddress.setCity("San Francisco");
        billingAddress.setPostalCode("94188");
        billingAddress.setEmail("cschiragsharma@gmail.com");

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
                setBillingAddress(billingAddress);
        Thread.sleep(5000);
        checkoutPage.clickPlaceOrderBtn();
        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }

}
