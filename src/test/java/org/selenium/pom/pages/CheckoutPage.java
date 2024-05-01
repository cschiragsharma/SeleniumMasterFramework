package org.selenium.pom.pages;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.LoginUser;

import java.time.Duration;
import java.util.List;

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

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public CheckoutPage enterTextInFirstName(String txt) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInLastName(String txt) {
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInAddress1(String txt) {
        driver.findElement(address1).clear();
        driver.findElement(address1).sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInAddress2(String txt) {
        driver.findElement(address2).clear();
        driver.findElement(address2).sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInCity(String txt) {
        driver.findElement(address2).clear();
        driver.findElement(city).sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInPostcode(String txt) {
        driver.findElement(postcode).clear();
        driver.findElement(postcode).sendKeys(txt);
        return this;
    }

    public CheckoutPage enterTextInEmail(String txt) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(txt);
        return this;
    }
    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return enterTextInFirstName(billingAddress.getFirstName()).
                enterTextInLastName(billingAddress.getLastName()).
                enterTextInAddress1(billingAddress.getAddress1()).
                enterTextInAddress2(billingAddress.getAddress2()).
                enterTextInCity(billingAddress.getCity()).
                enterTextInPostcode(billingAddress.getPostalCode()).
                enterTextInEmail(billingAddress.getEmail());
    }

    public CheckoutPage clickPlaceOrderBtn(){
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("OVERLAY SIZE: " +overlays.size());
        if(overlays.size()>0){
            //explicit wait
          new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                  ExpectedConditions.invisibilityOfAllElements(overlays)
          );
        }
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getSuccessNotice(){
        return driver.findElement(successNotice).getText();
    }
    public CheckoutPage showLoginBtn(){
        driver.findElement(showLoginBtn).click();
        return this;

    }

    private CheckoutPage enterUsername(String txt) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(txt);
        return this;
    }

    private CheckoutPage enterPassword(String txt) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(txt);
        return this;
    }

    public CheckoutPage login(LoginUser loginUser){

     return   enterUsername(loginUser.getUsername()).
            enterPassword(loginUser.getPassword()).
            clickLoginBtn();

    }
    public CheckoutPage clickLoginBtn(){
        driver.findElement(loginBtn);
        return this;

    }

}
