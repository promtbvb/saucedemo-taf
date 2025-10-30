package com.saucedemo.pages;

import com.saucedemo.enums.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BasePage {

    private static final String ADD_TO_CART_BUTTON_PREFIX = "add-to-cart-";
    private static final String REMOVE_BUTTON_PREFIX = "remove-";

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getInventoryPageTitle() {
        return getText(pageTitle);
    }

    public InventoryPage addItemToCart(Product product) {
        String buttonId = ADD_TO_CART_BUTTON_PREFIX + product.getProductId();
        WebElement addButton = driver.findElement(By.id(buttonId));
        click(addButton);
        return this;
    }

    public InventoryPage removeItemFromCart(Product product) {
        String buttonId = REMOVE_BUTTON_PREFIX + product.getProductId();
        WebElement removeButton = driver.findElement(By.id(buttonId));
        click(removeButton);
        return this;
    }

    public InventoryPage addFirstNItemsToCart(int count) {
        List<WebElement> addButtons = driver.findElements(By.cssSelector("button[id^='add-to-cart']"));
        for (int i = 0; i < Math.min(count, addButtons.size()); i++) {
            click(addButtons.get(i));
        }
        return this;
    }

    public String getCartBadgeCount() {
        try {
            // Use findElements which returns empty list immediately if not found (no wait)
            List<WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));
            if (!badges.isEmpty() && badges.get(0).isDisplayed()) {
                return badges.get(0).getText();
            }
            return "0";
        } catch (Exception e) {
            // Element doesn't exist when cart is empty
            return "0";
        }
    }

    public CartPage goToCart() {
        click(shoppingCartLink);
        return new CartPage(driver);
    }

    public InventoryPage sortProducts(String sortOption) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(sortOption);
        return this;
    }

    public int getInventoryItemCount() {
        return inventoryItems.size();
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }

    public boolean isInventoryPageDisplayed() {
        return isDisplayed(pageTitle) && getCurrentUrl().contains("inventory");
    }
}

