package org.selenium.pom.utils;

import java.io.InputStream;
import java.util.Properties;

public class configLoader {
    private final Properties properties;
    private static configLoader configLoader;

    private configLoader() {

        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }
//singleton design pattern
    public static configLoader getInstance(){
        if(configLoader == null){
            configLoader = new configLoader();
        }
        return configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null)
            return prop;
        else
            throw new RuntimeException("Property baseUrl is not specified in config file");
    }

    public String getUsername(){
        String prop = properties.getProperty("username");
        if(prop != null)
            return prop;
        else
            throw new RuntimeException("Property username is not specified in config file");
    }

    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop != null)
            return prop;
        else
            throw new RuntimeException("Property password is not specified in config file");
    }
}
