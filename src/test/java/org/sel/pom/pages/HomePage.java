package org.sel.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sel.pom.base.BasePage;
import org.sel.pom.pages.components.MyHeader;
import org.sel.pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {
    private MyHeader myHeader;
    private ProductThumbnail productThumbnail;

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public HomePage(WebDriver driver) {
        super(driver);
        myHeader = new MyHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public HomePage load(){
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

}
