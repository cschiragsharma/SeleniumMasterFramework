package org.selenium.pom.utils;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {
    public List<Cookie> convertRestAssuredCookiesToSeleniumCookies(Cookies cookies){
        List<io.restassured.http.Cookie> restAssuredCookies = new ArrayList<>();
        restAssuredCookies = cookies.asList();
        List<Cookie> seleniumCookie = new ArrayList<>();
        for(io.restassured.http.Cookie cookie: restAssuredCookies){
            seleniumCookie.add(new Cookie(cookie.getName(),
                                            cookie.getValue(),
                                            cookie.getDomain(),
                                            cookie.getPath(),
                                            cookie.getExpiryDate(),
                                            cookie.isSecured(),
                                            cookie.isHttpOnly(),
                                            cookie.getSameSite()));
        }

        return seleniumCookie;
    }
}
