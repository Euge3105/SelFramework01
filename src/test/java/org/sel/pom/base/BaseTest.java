package org.sel.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sel.pom.constants.BrowserType;
import org.sel.pom.factory.DriverManagerFactory;
import org.sel.pom.factory.DriverManagerOriginal;
import org.sel.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
//    protected WebDriver driver;
    private ThreadLocal <WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser){
//        driver = new DriverManagerOriginal().initializeDriver(browser);
        browser = System.getProperty("browser", browser);
//        if(browser == null) browser = "CHROME";
//        setDriver(new DriverManagerOriginal().initializeDriver(browser));
        setDriver(DriverManagerFactory.getManager(BrowserType.valueOf(browser)).createDriver());
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());

    }
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws InterruptedException, IOException {
//        driver.quit();
        Thread.sleep(500);
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());
        if(result.getStatus() == ITestResult.FAILURE){
            File destFile = new File("scr" + File.separator + browser + File.separator +
                    result.getTestClass().getRealClass().getSimpleName() + " " +
                    result.getMethod().getMethodName() + ".png");
//            takeScreenshot(destFile);
            takeScreenshot(destFile);
        }
        getDriver().quit();

    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,destFile);
    }

//    private void takeScreenshoyUsingAShot(File destFile){
//        Screenshot screenshot = new AShot()
//                .shootingStrategy(ShootingStrategies.viewportPasting(100))
//                .takeScreenshot(getDriver());
//        try{
//            ImageIO.write(screenshot.getImage(),"PNG", destFile);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }

}
