package com.saucedemo.pages;

import com.saucedemo.enums.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CartPage extends BasePage {

    private static final String REMOVE_BUTTON_PREFIX = "remove-";

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "cart_quantity")
    private List<WebElement> quantities;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public CartPage removeItemFromCart(Product product) {
        String buttonId = REMOVE_BUTTON_PREFIX + product.getProductId();
        WebElement removeButton = driver.findElement(By.id(buttonId));
        click(removeButton);
        return this;
    }

    public InventoryPage continueShopping() {
        click(continueShoppingButton);
        return new InventoryPage(driver);
    }

    public CheckoutStepOnePage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }

    public List<String> getItemNames() {
        return itemNames.stream()
                .map(this::getText)
                .collect(toList());
    }

    public boolean isItemInCart(String productName) {
        return getItemNames().contains(productName);
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }
}

