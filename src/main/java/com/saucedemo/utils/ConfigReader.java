package com.saucedemo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads configuration from config.properties file
 */
public class ConfigReader {

    private static final Properties PROPERTIES = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.out.println("Warning: config.properties not found. Using default values.");
                setDefaultProperties();
                return;
            }

            PROPERTIES.load(input);
            System.out.println("Configuration loaded successfully from " + CONFIG_FILE);

        } catch (IOException e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
            System.out.println("Using default configuration values.");
            setDefaultProperties();
        }
    }

    private static void setDefaultProperties() {
        PROPERTIES.setProperty("browser", "chrome");
        PROPERTIES.setProperty("url", "https://www.saucedemo.com/");
        PROPERTIES.setProperty("implicitWait", "10");
        PROPERTIES.setProperty("pageLoadTimeout", "30");
        PROPERTIES.setProperty("actionDelay", "0");
    }

    public static String getProperty(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }

        return PROPERTIES.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return value != null ? value : defaultValue;
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static String getUrl() {
        return getProperty("url", "https://www.saucedemo.com/");
    }

    public static int getActionDelay() {
        String delay = getProperty("actionDelay", "0");
        try {
            return Integer.parseInt(delay);
        } catch (NumberFormatException e) {
            System.err.println("⚠️ Invalid actionDelay value: " + delay + ". Using 0.");
            return 0;
        }
    }
}

