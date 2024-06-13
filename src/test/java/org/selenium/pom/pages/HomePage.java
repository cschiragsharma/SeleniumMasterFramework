package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

import java.io.IOException;

public class HomePage extends BasePage {

    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load() {
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public StorePage navigateToStoreUsingMenu(){

        waitForElementToBeVisible(storeMenuLink).click();
        return new StorePage(driver);
    }

    public ProductPage navigateToTheProduct(int id) throws IOException {
        driver.findElement(By.xpath("//h2[normalize-space()='"+ new Product(id).getName() + "']")).click();
        return new ProductPage(driver);
    }

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    private MyHeader myHeader;
    private ProductThumbnail productThumbnail;

}
