package io.learn.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if(inputStream == null) {
                // logger debug statement
                return;
            }
            properties.load(inputStream);
        } catch (Exception e) {
            // logger error statement
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
