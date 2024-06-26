package org.sel.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sel.pom.base.BasePage;
import org.sel.pom.objects.BillingAddress;
import org.sel.pom.objects.User;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {

    private final By  firstnameFld= By.id("billing_first_name");
    private final By  lastnameFld= By.id("billing_last_name");
    private final By  addressLineOneFld= By.id("billing_address_1");
    private final By  billingCityFld= By.id("billing_city");
    private final By  billingPostCodeFld= By.id("billing_postcode");
    private final By  billingEmailFld= By.id("billing_email");
    private final By  placeOrderBtn= By.id("place_order");
    private final By  successNotice= By.cssSelector(".woocommerce-notice");
    private final By  clickHereToLoginLink = By.cssSelector(".showlogin");
    private final By  usernameFld = By.id("username");
    private final By  passwordFld = By.id("password");
    private final By  loginBtn = By.name("login");
    private final By  overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By  countryDropDown = By.id("billing_country");
    private final By  stateDropDown = By.id("billing_state");

    private  final By alternateCountryDropDown = By.id("select2-billing_country-container");
    private  final By alternateStateDropDown = By.id("select2-billing_state-container");

    private final By  directBankTransferRadioBtn = By.id("payment_method_bacs");

    private final By  productName = By.cssSelector("td[class='product-name']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load(){
        load("/checkout");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameFld));
        e.clear();
        e.sendKeys(firstName);
//        driver.findElement(firstnameFld).clear();
//        driver.findElement(firstnameFld).sendKeys(firstName);
        return this;
    }
    public CheckoutPage enterLastName(String lastName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastnameFld));
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName){
/*        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);*/
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage selectState(String stateName){
/*        Select select = new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);*/
        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }


    public CheckoutPage enterAddressLineOne(String addressLineOne){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneFld));
        e.clear();
        e.sendKeys(addressLineOne);
        return this;
    }
    public CheckoutPage enterCity(String city){
        WebElement ee = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityFld));
        ee.clear();
        ee.sendKeys(city);
        return this;
    }
    public CheckoutPage enterPostCode(String postCode){
        driver.findElement(billingPostCodeFld).clear();
        driver.findElement(billingPostCodeFld).sendKeys(postCode);
        return this;
    }
    public CheckoutPage enterEmail(String email){
        driver.findElement(billingEmailFld).clear();
        driver.findElement(billingEmailFld).sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                enterCity(billingAddress.getCity()).
                selectCountry(billingAddress.getCountry()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                selectState(billingAddress.getState()).
                enterPostCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage placeOrder(){
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickHereToLoginLink(){
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }

    public CheckoutPage enterUserName(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

//    public CheckoutPage login(String username, String password){
//        return enterUserName(username)
//                .enterPassword(password)
//                .clickLoginBtn();
//    }

    private CheckoutPage waitLoginBtnToDisappear(){
         wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
         return this;
    };

    public CheckoutPage login(User user){
        return enterUserName(user.getUsername())
                .enterPassword(user.getUsername())
                .clickLoginBtn().waitLoginBtnToDisappear();
    }

    public CheckoutPage selectDirecttBankTransfer(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }

    public String getProductName() throws Exception {
//        wait.until(ExpectedConditions.stalenessOf())
        int i = 5;
        int timeout = 30;
        while(i > 0) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
            } catch (StaleElementReferenceException e) {
                System.out.println("Not found. Trying again" + e);
            }
            Thread.sleep(5000);
            i--;
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
        }
        throw new Exception("Element not found");
    }
}
