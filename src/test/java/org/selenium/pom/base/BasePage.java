package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.configLoader;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver){

        this.driver= driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(15));
    }

    public void load(String endPoint){
        driver.get(configLoader.getInstance().getBaseUrl() + endPoint);

    }
    public void waitForOverlaysToDisappear(By overlay){
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("OVERLAY SIZE: " +overlays.size());
        if(overlays.size()>0){
            //explicit wait
            wait.until(
                    ExpectedConditions.invisibilityOfAllElements(overlays)
            );
        }
        else{
            System.out.println("OVERLAY NOT FOUND");
        }
    }
    public WebElement waitForElementToBeVisible(By element){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(element));

    }

}
