package com.saucedemo.enums;

/**
 * Enum for all products in the inventory
 */
public enum Product {

    BACKPACK(
            "sauce-labs-backpack",
            "Sauce Labs Backpack",
            "$29.99",
            "carry.allTheThings() with the sleek, streamlined Sly Pack"
    ),

    BIKE_LIGHT(
            "sauce-labs-bike-light",
            "Sauce Labs Bike Light",
            "$9.99",
            "A red light isn't the desired state in testing"
    ),

    BOLT_TSHIRT(
            "sauce-labs-bolt-t-shirt",
            "Sauce Labs Bolt T-Shirt",
            "$15.99",
            "Get your testing superhero on with the Sauce Labs bolt T-shirt"
    ),

    FLEECE_JACKET(
            "sauce-labs-fleece-jacket",
            "Sauce Labs Fleece Jacket",
            "$49.99",
            "It's not every day that you come across a midweight quarter-zip fleece jacket"
    ),

    ONESIE(
            "sauce-labs-onesie",
            "Sauce Labs Onesie",
            "$7.99",
            "Rib snap infant onesie for the junior automation engineer in development"
    ),

    RED_TSHIRT(
            "test.allthethings()-t-shirt-(red)",
            "Test.allTheThings() T-Shirt (Red)",
            "$15.99",
            "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard"
    );

    private final String productId;
    private final String displayName;
    private final String price;
    private final String description;

    Product(String productId, String displayName, String price, String description) {
        this.productId = productId;
        this.displayName = displayName;
        this.price = price;
        this.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

}

