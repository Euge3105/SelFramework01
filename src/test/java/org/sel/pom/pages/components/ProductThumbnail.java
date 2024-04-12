package org.sel.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sel.pom.base.BasePage;
import org.sel.pom.pages.CartPage;
import org.sel.pom.pages.HomePage;
import org.sel.pom.pages.StorePage;

public class ProductThumbnail extends BasePage {
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCardBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName +  "” to your cart']");
    }

    public ProductThumbnail clickAddToCartBtn(String productName){
        By addToCartBtn  = getAddToCardBtnElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);

    }
}
