package org.sel.pom.tests;

import org.sel.pom.api.actions.CartApi;
import org.sel.pom.api.actions.SignUpApi;
import org.sel.pom.base.BaseTest;
import org.sel.pom.objects.Product;
import org.sel.pom.objects.User;
import org.sel.pom.pages.CheckoutPage;
import org.sel.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws Exception {
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(3000);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        Thread.sleep(3000);
        checkoutPage.clickHereToLoginLink();

        checkoutPage.
//                login("luigi2","Qwerty12@");
        login(user);
        Thread.sleep(3000);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));

    }
}
