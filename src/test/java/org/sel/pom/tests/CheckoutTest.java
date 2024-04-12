package org.sel.pom.tests;

import org.sel.pom.api.actions.CartApi;
import org.sel.pom.api.actions.SignUpApi;
import org.sel.pom.base.BaseTest;
import org.sel.pom.objects.BillingAddress;
import org.sel.pom.objects.Product;
import org.sel.pom.objects.User;
import org.sel.pom.pages.CheckoutPage;
import org.sel.pom.utils.FakerUtils;
import org.sel.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @Test
    public void GuestCheckoutUsingDircetBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectDirecttBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }

    @Test
    public void LoginAndCheckoutUsingDircetBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(3000);
        injectCookiesToBrowser(signUpApi.getCookies());

        checkoutPage.load();
                Thread.sleep(3000);
        checkoutPage.setBillingAddress(billingAddress).
                selectDirecttBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

    }
}
