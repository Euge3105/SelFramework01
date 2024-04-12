package org.sel.pom.tests;

import org.openqa.selenium.By;
import org.sel.pom.base.BaseTest;
import org.sel.pom.objects.BillingAddress;
import org.sel.pom.objects.Product;
import org.sel.pom.objects.User;
import org.sel.pom.pages.CartPage;
import org.sel.pom.pages.CheckoutPage;
import org.sel.pom.pages.HomePage;
import org.sel.pom.pages.StorePage;
import org.sel.pom.utils.ConfigLoader;
import org.sel.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
@Ignore
public class MyFirstTestCase extends BaseTest {

//    @Parameters({"url","email","password"})


    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
       /* BillingAddress billingAddress = new BillingAddress().
                setFirstName("demo").
                setLastName("user").
                setAddressLineOne("San Francisco").
                setCity("San Francisco").
                setPostalCode("94188").
                setEmail("user1234user@gmail.com");*/

//        BillingAddress billingAddress = new BillingAddress("demo", "user", "San Francisco", "San Francisco",
//               "94188","user1234user@gmail.com" );
//        InputStream is = getClass().getClassLoader().getResourceAsStream("myBillingAddress.json");
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);


        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu().
                search(searchFor);
//                enterTextInSearchFld("Blue").
//                clickSearchBtn();
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+ searchFor + "”");

        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.checkout().
                setBillingAddress(billingAddress).
                selectDirecttBankTransfer().
//                enterFirstName("demo").
//                enterLastName("user").
//                enterAddressLineOne("San Francisco").
//                enterCity("San Francisco").
//                enterPostCode("94188").
//                enterEmail("user1234user@gmail.com").
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

    }

    @Test(enabled=false)
    public void loginAndCheckoutUsingDircetBankTransfer() throws InterruptedException, IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
//        User user = new User("luigi2","Qwerty12@");
        User user = new User(ConfigLoader.getInstance().getUsername(),
                ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu();
        storePage.isLoaded();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+ searchFor + "”");

        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        cartPage.isLoaded();

        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
//        luigi1990@gmail.com luigi2 Qwerty12@

        checkoutPage.clickHereToLoginLink();

        checkoutPage.
//                                login("luigi2","Qwerty12@").
                login(user).
                setBillingAddress(billingAddress).
                selectDirecttBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

    }
}
