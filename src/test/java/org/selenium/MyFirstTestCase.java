package org.selenium;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.LoginUser;
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
    public void GuestCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor="Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage= new HomePage(driver).
                load().
                navigateToStoreUsingMenu();
        storePage.isLoaded();
                storePage.search(searchFor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchFor+"”");

        storePage.clickAddToCartBtn(product.getName());

        CartPage cartPage = storePage.viewCartBtn();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage = cartPage.
                clickCheckoutBtn().
                setBillingAddress(billingAddress).
                clickPlaceOrderBtn();

        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }
    @Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor="Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);
        LoginUser loginUser= new LoginUser("demouser0909","demopass");

        StorePage storePage= new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchFor+"”");

        storePage.clickAddToCartBtn(product.getName());

        CartPage cartPage = storePage.viewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();

        //login functionality

        checkoutPage.showLoginBtn().
                login(loginUser).
                setBillingAddress(billingAddress).
                clickPlaceOrderBtn();

        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }

}
