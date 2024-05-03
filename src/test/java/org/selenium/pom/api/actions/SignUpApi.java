package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.utils.configLoader;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }
    private String fetchRegisterNonceValueUsingGroovy(){
        Response response = getAccount();
        //groovy Gpath
        return response.htmlPath().getString("**.findAll { it.@name =='woocommerce-register-nonce' }.@value");
    }
    private String fetchRegisterNonceValueUsingJsoup(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
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
}
