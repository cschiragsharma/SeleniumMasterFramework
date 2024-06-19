package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    protected ThreadLocal <WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(String browser){
        browser = System.getProperty("browser",browser);
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("CURRENT THREAD: "+Thread.currentThread().getId()+", DRIVER: "+getDriver());

    }
    @Parameters("browser")
    @AfterMethod
    public synchronized void quitMethod(@Optional String browser, ITestResult result) throws IOException {
        System.out.println("CURRENT THREAD: "+Thread.currentThread().getId()+", DRIVER: "+getDriver());
        if(result.getStatus() == ITestResult.FAILURE){
            File destFile = new File("screenshot"+ File.separator +browser+ File.separator +
                    result.getTestClass().getRealClass().getSimpleName() +"_"+
                    result.getMethod().getMethodName()+".png");
            takeScreenshotUsingAshot(destFile);
        }

        getDriver().quit();

    }
    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumcookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie:seleniumcookies){
            getDriver().manage().addCookie(cookie);
        }
    }
    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot  takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,destFile);

    }
    private void takeScreenshotUsingAshot(File destFile){
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriver());
        try{
            ImageIO.write(screenshot.getImage(),"PNG",destFile);

        }catch(IOException e){
            e.printStackTrace();

        }
    }

}
