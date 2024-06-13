package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.ProductThumbnail;

import java.io.IOException;

public class StorePage extends BasePage {
    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");
    private final By infoTxt = By.cssSelector(".woocommerce-info");



    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);

    }
    private StorePage enterTextInSearchField(String txt){
        waitForElementToBeVisible(searchFld).sendKeys(txt);
        return this;
    }
    public StorePage load(){
        load("/store");
        return this;
    }
    public Boolean isLoaded(){
       return wait.until(ExpectedConditions.urlContains("/store"));
    }

    public StorePage search(String txt){
        enterTextInSearchField(txt).clickSearchBtn();
        return this;
    }

    private StorePage clickSearchBtn(){
        waitForElementToBeVisible(searchBtn).click();
        return this;
    }
    public String getTitle(){
        return waitForElementToBeVisible(title).getText();

    }

    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “"+ productName +"” to your cart']");
    }

    public StorePage clickAddToCartBtn(String productName){
        By addToCartBtn = getAddToCartBtnElement(productName);
        waitForElementToBeVisible(addToCartBtn).click();
        return this;
    }
    public CartPage viewCartBtn(){
        waitForElementToBeVisible(viewCartLink).click();
        return new CartPage(driver);
    }

    public ProductPage navigateToTheProduct(Integer id) throws IOException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[normalize-space()='"+ new Product(id).getName() + "']"))).click();
        return new ProductPage(driver);
    }

    private StorePage enterTextInSearchFld(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(txt);
        return this;
    }

    public ProductPage searchExactMatch(String txt){
        enterTextInSearchFld(txt).clickSearchBtn();
        return new ProductPage(driver);
    }
    public String getInfo(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(infoTxt)).getText();
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    private ProductThumbnail productThumbnail;



}
