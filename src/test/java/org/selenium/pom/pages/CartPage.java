package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

//    private final By productName = By.cssSelector("td[class='product-name'] a");
//    private final By clickCheckoutBtn = By.cssSelector(".checkout-button");
//    private final By cartHeading = By.cssSelector(".has-text-align-center");
    //page factory
    @FindBy(css = "td[class='product-name'] a") private By  productName;
    @FindBy(css = ".checkout-button") private By clickCheckoutBtn;
    @FindBy(css = ".has-text-align-center") private By cartHeading;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.textToBe(cartHeading,"Cart"));
    }
    public String getProductName(){
        return waitForElementToBeVisible(productName).getText();
        //return driver.findElement(productName).getText();

    }
    public CheckoutPage clickCheckoutBtn(){
        waitForElementToBeVisible(clickCheckoutBtn)
        .click();
        //driver.findElement(clickCheckoutBtn).click();
        return new CheckoutPage(driver);
    }
}
