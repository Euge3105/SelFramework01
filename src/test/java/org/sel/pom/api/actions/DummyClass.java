package org.sel.pom.api.actions;

import org.sel.pom.objects.User;
import org.sel.pom.utils.FakerUtils;

public class DummyClass {
    public static void main(String[] args) {

//        new SignUpApi().getAccount();
//        System.out.println(new SignUpApi().fetchRegisterNonceValueUsingJsoup());

/*        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        System.out.println(signUpApi.register(user));
        System.out.println(signUpApi.getCookies());*/

        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        System.out.println("REGISTER COOKIES: " + signUpApi.getCookies());

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215, 1);
        System.out.println("Cart Cookies: " + cartApi.getCookies());

    }
}

// <input type="hidden" id="woocommerce-login-nonce" name="woocommerce-login-nonce" value="46c9d82d88"/>
//<input type="hidden" id="woocommerce-register-nonce" name="woocommerce-register-nonce" value="b63edbb297"/>

// Groovy GPath
// JSpoup - CSS