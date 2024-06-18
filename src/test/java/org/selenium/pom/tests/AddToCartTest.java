package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.selenium.pom.utils.JacksonUtils.deserializeJson;

public class AddToCartTest extends BaseTest {

    @Test
    public void AddToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().getProductThumbnail().
                clickAddTCartBtn(product.getName()).clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test(dataProvider = "getFeaturedProducts",dataProviderClass = MyDataProvider.class)
    public void AddFeaturedProductToCart(Product product){
        CartPage cartPage = new HomePage(getDriver()).
                                load().
                                getProductThumbnail().
                                clickAddTCartBtn(product.getName()).
                                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

    }

    @Test
    public void AddToCartFromProductPage() throws IOException {
        Product product = new Product(1215);
        String productNameSeparatedByDash = product.getName().toLowerCase().replaceAll(" ","-");

        ProductPage productPage = new ProductPage(getDriver()).
                                        loadProduct(productNameSeparatedByDash).
                                        addToCart();
        Assert.assertTrue(productPage.getAlert().contains("“" + product.getName() +"” has been added to your cart."));
    }
}
