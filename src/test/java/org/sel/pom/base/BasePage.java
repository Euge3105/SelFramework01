package org.sel.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sel.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
//    protected WebDriverWait waitLong;
//    protected WebDriverWait waitShort;
    protected WebDriverWait wait;

    public  BasePage(WebDriver driver){
        this.driver = driver;
//        waitLong = new WebDriverWait(driver, Duration.ofSeconds(15));
//        waitShort = new WebDriverWait(driver, Duration.ofSeconds(15));
          wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void load(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public void waitForOverlaysToDisappear(By overlay) {
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("OVERLAY SIZE: " + overlays.size());
        if (overlays.size() > 0) {
            wait.until(
                    ExpectedConditions.invisibilityOfAllElements(overlays)
            );
            System.out.println("OVERLAY ARE INVISIBLE");
        } else {
            System.out.println("Overlay Not Found");
        }
    }

//    public WebElement waitForElementToBeVisible(By element){
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
//    }

//        if(overlays.size() > 0){
//            new WebDriverWait(driver, Duration.ofSeconds(15)).until(
//                    ExpectedConditions.invisibilityOfAllElements(overlays)
//            );
//            System.out.println("OVERLAY ARE INVISIBLE");
//        }else{
//            System.out.println("Overlay Not Found");
//        }
//    }




}
