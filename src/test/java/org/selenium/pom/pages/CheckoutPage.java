package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.LoginUser;

public class CheckoutPage extends BasePage {

        private final By firstName =  By.cssSelector("#billing_first_name");
        private final By lastName = By.cssSelector("#billing_last_name");
        private final By address1 =  By.cssSelector("#billing_address_1");
        private final By address2 = By.cssSelector("#billing_address_2");
        private final By city = By.cssSelector("#billing_city");
        private final By postcode = By.cssSelector("#billing_postcode");
        private final By email = By.cssSelector("#billing_email");
        private final By showLoginBtn = By.cssSelector(".showlogin");
        private final By username = By.cssSelector("#username");
        private final By password = By.cssSelector("#password");
        private final By loginBtn = By.cssSelector("button[value='Login']");
        private final By placeOrderBtn = By.cssSelector("#place_order");
        private final By successNotice = By.cssSelector(".woocommerce-notice");
        private final By overlay = By.cssSelector(".blockUI.blockOverlay");
        private final By countryDropDown = By.id("billing_country");
        private final By stateDropDown = By.id("billing_state");
        private final By directBankTransferRadioButton = By.id("payment_method_bacs");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public CheckoutPage enterTextInFirstName(String txt) {
        WebElement e = waitForElementToBeVisible(firstName);
        e.clear();
        e.sendKeys(txt);
        return this;
    }
    public CheckoutPage selectCountry(String countryName){
        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);
        return this;
    }
    public CheckoutPage selectState(String stateName){
        Select select = new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);
        return this;
    }

    public CheckoutPage enterTextInLastName(String txt) {
        WebElement e = waitForElementToBeVisible(lastName);
        e.clear();
        e.sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInAddress1(String txt) {
        WebElement e = waitForElementToBeVisible(address1);
        e.clear();
        e.sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInAddress2(String txt) {
        WebElement e = waitForElementToBeVisible(address2);
        e.clear();
        e.sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInCity(String txt) {
        WebElement e = waitForElementToBeVisible(city);
        e.clear();
        e.sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInPostcode(String txt) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(postcode));
        e.clear();
        e.sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInEmail(String txt) {
        WebElement e = waitForElementToBeVisible(email);
        e.clear();
        e.sendKeys(txt);
        return this;
    }
    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return enterTextInFirstName(billingAddress.getFirstName()).
                enterTextInLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterTextInAddress1(billingAddress.getAddress1()).
                enterTextInAddress2(billingAddress.getAddress2()).
                enterTextInCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterTextInPostcode(billingAddress.getPostalCode()).
                enterTextInEmail(billingAddress.getEmail());
    }

    public CheckoutPage clickPlaceOrderBtn(){
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getSuccessNotice(){
        return waitForElementToBeVisible(successNotice).getText();
        //return driver.findElement(successNotice).getText();
    }
    public CheckoutPage showLoginBtn(){
        waitForElementToBeVisible(showLoginBtn).click();
        //driver.findElement(showLoginBtn).click();
        return this;

    }

    private CheckoutPage enterUsername(String txt) {
        WebElement e = waitForElementToBeVisible(username);
        e.clear();
        e.sendKeys(txt);
        return this;
    }

    private CheckoutPage enterPassword(String txt) {
        WebElement e = waitForElementToBeVisible(password);
        e.clear();
        e.sendKeys(txt);
        return this;
    }

    public CheckoutPage login(LoginUser loginUser){

     return   enterUsername(loginUser.getUsername()).
            enterPassword(loginUser.getPassword()).
            clickLoginBtn();

    }
    public CheckoutPage clickLoginBtn(){
        waitForElementToBeVisible(loginBtn).click();
        return this;

    }
    public CheckoutPage selectDirectBankTransfer(){
            WebElement e = waitForElementToBeVisible(directBankTransferRadioButton);
            if(!e.isSelected()){
                e.click();

            }
            return this;
    }

}
