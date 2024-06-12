package org.selenium.pom.test;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.LoginUser;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {
    @Test
    public void GuestCheckoutUsingDirectBankTransfer() throws IOException {
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);

        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceOrderBtn();

        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }
    @Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
        String username = "demouser"+ new FakerUtils().generateRandomNumber();
        LoginUser user = new LoginUser().
                setUsername(username).
                setPassword("demouser").
                setEmail(username+"@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(),1);
        Thread.sleep(5000);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(signUpApi.getCookies());
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceOrderBtn();

        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }

    @Test
    public void GuestCheckoutUsingCashOnDelivery(){

    }

    @Test
    public void LoginAndCheckoutUsingCashOnDelivery(){

    }
}
