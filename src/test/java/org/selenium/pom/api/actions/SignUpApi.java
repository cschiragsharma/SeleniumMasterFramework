package org.selenium.pom.api.actions;

import com.google.common.collect.HashBiMap;
import groovy.lang.GroovyObjectSupport;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.objects.LoginUser;
import org.selenium.pom.utils.configLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi extends GroovyObjectSupport {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }
    private String fetchRegisterNonceValueUsingGroovy(){
        Response response = getAccount();
        //groovy Gpath
        return response.htmlPath().getString("**.findAll { it.@name =='woocommerce-register-nonce' }.@value");
    }
    public String fetchRegisterNonceValueUsingJsoup(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        assert element != null;
        return element.attr("value");

    }
    private String fetchFormNameUsingJsoup(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("button[value='Register']");
        assert element != null;
        return element.attr("value");
    }
    private Response getAccount(){
        Cookies cookies = new Cookies();
       Response response = given().
                baseUri(configLoader.getInstance().getBaseUrl()).
                cookies(cookies).
       when().
                get("/account").
       then().
               log().all().
                extract().
                response();
       if(response.getStatusCode()!=200){
           throw new RuntimeException("Failed to fetch the account, HTTP Status code: " + response.getStatusCode());
       }
       return response;
    }
    public Response register(LoginUser user){
        Cookies cookies = new Cookies();
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String,String> formParams = new HashMap<>();
        formParams.put("username", user.getUsername());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJsoup());
        formParams.put("register",fetchFormNameUsingJsoup());
        Response response = given().
                baseUri(configLoader.getInstance().getBaseUrl()).
                headers(headers).
                formParams(formParams).
                cookies(cookies).
                log().all().
        when().
                post("/account").
        then().
                log().all().
                extract().
                response();
        if(response.getStatusCode()!=302){
            throw new RuntimeException("Failed to register the account, HTTP Status code: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
