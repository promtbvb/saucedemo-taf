package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BasePage {

    @FindBy(className = "inventory_details_name")
    private WebElement productName;

    @FindBy(className = "inventory_details_desc")
    private WebElement productDescription;

    @FindBy(className = "inventory_details_price")
    private WebElement productPrice;

    @FindBy(css = "button[id^='add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(css = "button[id^='remove']")
    private WebElement removeButton;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

}

