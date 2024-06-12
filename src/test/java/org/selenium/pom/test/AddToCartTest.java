package org.selenium.pom.test;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void AddToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                clickAddToCartBtn(product.getName()).
                viewCartBtn();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test
    public void AddFeaturedProductToCart(){

    }

    @Test
    public void AddToCartFromProductPage(){

    }
}
