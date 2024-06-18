package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import org.selenium.pom.objects.LoginUser;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {
    public static void main(String[] args) {
        //signup for a new user using registeration form
/*        SignUpApi signUpApi = new SignUpApi();
        //signUpApi.fetchRegisterNonceValueUsingJsoup();
        FakerUtils fakerUtils = new FakerUtils();
        String username = "demouser"+ fakerUtils.generateRandomNumber();
        LoginUser user = new LoginUser().
                setUsername(username).
                setPassword("demouser").
                setEmail(username+"@askomdch.com");

        System.out.println(signUpApi.register(user));
        System.out.println(signUpApi.getCookies());*/

        //add to cart without login
        /*CartApi cartApi = new CartApi();
        cartApi.addToCart(1198,1);

        System.out.println(cartApi.getCookies());*/

        //add to cart after login
        SignUpApi signUpApi = new SignUpApi();
        FakerUtils fakerUtils = new FakerUtils();
        String username = "demouser"+ fakerUtils.generateRandomNumber();
        LoginUser user = new LoginUser().
                setUsername(username).
                setPassword("demouser").
                setEmail(username+"@askomdch.com");

        signUpApi.register(user);
        Cookies cookies = signUpApi.getCookies();
        CartApi cartApi = new CartApi(cookies);
        cartApi.addToCart(1198,1);

        System.out.println(cartApi.getCookies());

    }
}
