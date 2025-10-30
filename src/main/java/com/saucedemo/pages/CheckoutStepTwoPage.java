package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CheckoutStepTwoPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(className = "summary_info")
    private WebElement paymentAndShippingInfo;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public String getSubtotal() {
        return getText(subtotalLabel);
    }

    public String getTax() {
        return getText(taxLabel);
    }

    public String getTotal() {
        return getText(totalLabel);
    }

    public int getOrderItemCount() {
        return cartItems.size();
    }

    public List<String> getItemNames() {
        return itemNames.stream()
                .map(this::getText)
                .collect(toList());
    }

    public CheckoutCompletePage finishCheckout() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public InventoryPage cancel() {
        click(cancelButton);
        return new InventoryPage(driver);
    }

    public boolean isItemInOrder(String productName) {
        return getItemNames().contains(productName);
    }
}

