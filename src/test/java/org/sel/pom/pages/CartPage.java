package org.sel.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sel.pom.base.BasePage;

public class CartPage extends BasePage {
    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    private final By cartHeading = By.cssSelector(".has-text-align-center");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.textToBe(cartHeading,"Cart"));
    };

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
//        return driver.findElement(productName).getText();
    }

    public CheckoutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
//        driver.findElement(checkoutBtn).click();
        return new CheckoutPage(driver);
    }
}
