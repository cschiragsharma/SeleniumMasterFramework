package org.selenium;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void GuestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver","/Users/thechiragsharma/Documents/chromedriver.exe");
        //WebDriver driver = new ChromeDriver();
        //driver.manage().window().maximize();
        driver.get("https://askomdch.com");

        HomePage homePage= new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();

        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(10000);
        CartPage cartPage = storePage.viewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();
        checkoutPage.enterTextInFirstName("demo").
                enterTextInLastName("user").
                enterTextInAddress1("San Francisco").
                enterTextInAddress2("San Francisco").
                enterTextInCity("San Francisco").
                enterTextInPostcode("94188").
                enterTextInEmail("cschiragsharma@gmail.com");
        Thread.sleep(5000);
        checkoutPage.clickPlaceOrderBtn();
        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");





        //driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();
//        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
//        driver.findElement(By.cssSelector("button[value='Search']")).click();
//        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),"Search results: “Blue”");
//        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.cssSelector("a[title='View cart']")).click();
//        Assert.assertEquals(driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),"Blue Shoes");
//        driver.findElement(By.cssSelector(".checkout-button")).click();
//        driver.findElement(By.cssSelector("#billing_first_name")).sendKeys("demo");
//        driver.findElement(By.cssSelector("#billing_last_name")).sendKeys("user");
//        driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("San Francisco");
//        driver.findElement(By.cssSelector("#billing_address_2")).sendKeys("San Francisco");
//        driver.findElement(By.cssSelector("#billing_city")).sendKeys("San Francisco");
//        driver.findElement(By.cssSelector("#billing_postcode")).sendKeys("94188");
//        driver.findElement(By.cssSelector("#billing_email")).sendKeys("cschiragsharma@gmail.com");
//        Thread.sleep(5000);
//        driver.findElement(By.cssSelector("#place_order")).click();
//        Thread.sleep(5000);
//        Assert.assertEquals(
//                driver.findElement(By.cssSelector(".woocommerce-notice")).getText(),"Thank you. Your order has been received."
//        );


    }
    @Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {

        driver.get("https://askomdch.com");

        HomePage homePage= new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();

        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(10000);
        CartPage cartPage = storePage.viewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();

        //login functionality

        checkoutPage.showLoginBtn();
        Thread.sleep(5000);

        checkoutPage.
                enterUsername("demouser0909").
                enterPassword("demopass").
                clickLoginBtn();

        checkoutPage.enterTextInFirstName("demo").
                enterTextInLastName("user").
                enterTextInAddress1("San Francisco").
                enterTextInAddress2("San Francisco").
                enterTextInCity("San Francisco").
                enterTextInPostcode("94188").
                enterTextInEmail("cschiragsharma@gmail.com");
        Thread.sleep(5000);
        checkoutPage.clickPlaceOrderBtn();
        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");
        //System.setProperty("webdriver.chrome.driver","/Users/thechiragsharma/Documents/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//
//        driver.get("https://askomdch.com");
//        driver.manage().window().maximize();
//        driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();
//        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
//        driver.findElement(By.cssSelector("button[value='Search']")).click();
//        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),"Search results: “Blue”");
//        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.cssSelector("a[title='View cart']")).click();
//        Assert.assertEquals(driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),"Blue Shoes");
//        driver.findElement(By.cssSelector(".checkout-button")).click();
//        driver.findElement(By.cssSelector(".showlogin")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.cssSelector("#username")).sendKeys("demouser0909");
//        driver.findElement(By.cssSelector("#password")).sendKeys("demopass");
//        driver.findElement(By.cssSelector("button[value='Login']")).click();

//
//        driver.findElement(By.cssSelector("#billing_first_name")).sendKeys("demo");
//        driver.findElement(By.cssSelector("#billing_last_name")).sendKeys("user");
//        driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("San Francisco");
//        driver.findElement(By.cssSelector("#billing_address_2")).sendKeys("San Francisco");
//        driver.findElement(By.cssSelector("#billing_city")).sendKeys("San Francisco");
//        driver.findElement(By.cssSelector("#billing_postcode")).sendKeys("94188");
        //driver.findElement(By.cssSelector("#billing_email")).sendKeys("cschiragsharma@gmail.com");

        //to scroll to top of page
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, 0)");
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//button[@id='place_order']")).click();
//        Thread.sleep(5000);
//        Assert.assertEquals(
//                driver.findElement(By.cssSelector(".woocommerce-notice")).getText(),"Thank you. Your order has been received."
//        );

        //driver.close();
        //driver.quit();
    }

}
