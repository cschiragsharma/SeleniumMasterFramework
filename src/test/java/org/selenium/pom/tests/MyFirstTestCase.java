package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.LoginUser;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.selenium.pom.utils.configLoader;
import org.testng.Assert;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

  // @Test
    public void GuestCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor="Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage= new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu();
        storePage.isLoaded();
                storePage.search(searchFor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchFor+"”");

        storePage.getProductThumbnail().clickAddTCartBtn(product.getName());

        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage = cartPage.
                clickCheckoutBtn().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceOrderBtn();

        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }
    //@Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor="Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);
        LoginUser loginUser= new LoginUser(configLoader.getInstance().getUsername(),configLoader.getInstance().getPassword());

        StorePage storePage= new HomePage(getDriver()).
                load().
                getMyHeader().navigateToStoreUsingMenu().search(searchFor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchFor+"”");

        storePage.getProductThumbnail().clickAddTCartBtn(product.getName());

        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();

        //login functionality

        checkoutPage.showLoginBtn().
                login(loginUser).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceOrderBtn();

        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

    }

}
