package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class Product {
    private int id;
    private String name;

    public Product(){

    }
    public Product(int id) throws IOException {
       Product[] products = JacksonUtils.deserializeJson("products.json",Product[].class);
       for(Product product: products){
           if(product.id == id){
               this.id=getId();
               this.name=product.getName();
           }
       }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
