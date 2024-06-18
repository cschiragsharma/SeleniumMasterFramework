package org.selenium.pom.tests;

import org.junit.Assert;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.LoginUser;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.AccountPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest  {
    @Test
    public void LoginDuringCheckout() throws IOException, InterruptedException {

        String username = "demouser"+ new FakerUtils().generateRandomNumber();
        LoginUser user = new LoginUser().
                setUsername(username).
                setPassword("demouser").
                setEmail(username+"@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(),1);
        Thread.sleep(5000);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        Thread.sleep(5000);
        checkoutPage.showLoginBtn().
                login(user);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));



    }

    @Test
    public void shouldNotLoginWithAnInvalidPassword(){
        String username = "demouser"+ new FakerUtils().generateRandomNumber();
        LoginUser user = new LoginUser(username,"demopwd",username +"@askomdch.com");
        new SignUpApi().register(user);

        AccountPage accountPage = new AccountPage(getDriver()).load();
        accountPage.login(user.getUsername(),"invalidPassword");
        Assert.assertEquals(accountPage.getErrorTxt(), "Error: The password you entered for the username "
                + user.getUsername() + " is incorrect. Lost your password?");

    }

    @Test
    public void shouldNotLoginWithANonExistingUser(){
        String username = "demouser"+ new FakerUtils().generateRandomNumber();
        LoginUser user = new LoginUser(username,"demopwd",username +"@askomdch.com");
        AccountPage accountPage = new AccountPage(getDriver()).load();
        accountPage.login(user.getUsername(),"demopwd");
        Assert.assertEquals(accountPage.getErrorTxt(), "Error: The username " + user.getUsername() +
                " is not registered on this site." +
                " If you are unsure of your username, try your email address instead.");
    }

}
