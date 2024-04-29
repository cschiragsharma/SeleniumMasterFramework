package org.selenium;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void GuestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage= new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");

        storePage.clickAddToCartBtn(product.getName());
        Thread.sleep(10000);
        CartPage cartPage = storePage.viewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage = cartPage.
                clickCheckoutBtn().
                setBillingAddress(billingAddress);
        Thread.sleep(5000);
        checkoutPage.clickPlaceOrderBtn();
        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }
    @Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage= new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");

        storePage.clickAddToCartBtn(product.getName());
        Thread.sleep(10000);
        CartPage cartPage = storePage.viewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
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
