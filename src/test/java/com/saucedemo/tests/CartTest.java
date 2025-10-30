package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.constants.TestConstants;
import com.saucedemo.enums.Product;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeTest() {
        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
    }

    @Test(priority = 1, description = "Verify adding single item to cart")
    public void testAddSingleItemToCart() {
        inventoryPage.addItemToCart(Product.BACKPACK);

        assertEquals(inventoryPage.getCartBadgeCount(), "1",
                "Cart badge should show 1 item");

        inventoryPage.goToCart();
        assertEquals(cartPage.getCartItemCount(), 1,
                "Cart should contain 1 item");
        assertTrue(cartPage.isItemInCart(Product.BACKPACK.getDisplayName()),
                "Cart should contain the added item");
    }

    @Test(priority = 2, description = "Verify adding multiple items to cart")
    public void testAddMultipleItemsToCart() {
        inventoryPage.addItemToCart(Product.BACKPACK);
        inventoryPage.addItemToCart(Product.BIKE_LIGHT);
        inventoryPage.addItemToCart(Product.BOLT_TSHIRT);

        assertEquals(inventoryPage.getCartBadgeCount(), "3",
                "Cart badge should show 3 items");

        inventoryPage.goToCart();
        assertEquals(cartPage.getCartItemCount(), 3,
                "Cart should contain 3 items");
    }

    @Test(priority = 3, description = "Verify removing item from cart on inventory page")
    public void testRemoveItemFromInventoryPage() {
        inventoryPage.addItemToCart(Product.BACKPACK);
        assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart badge should show 1 item");

        inventoryPage.removeItemFromCart(Product.BACKPACK);
        assertEquals(inventoryPage.getCartBadgeCount(), "0", "Cart badge should show 0 items after removal");

        inventoryPage.goToCart();
        assertTrue(cartPage.isCartEmpty(), "Cart should be empty after removing item");
    }

    @Test(priority = 4, description = "Verify removing item from cart page")
    public void testRemoveItemFromCartPage() {
        inventoryPage.addItemToCart(Product.BACKPACK);
        inventoryPage.addItemToCart(Product.BIKE_LIGHT);

        inventoryPage.goToCart();
        assertEquals(cartPage.getCartItemCount(), 2, "Cart should contain 2 items");

        cartPage.removeItemFromCart(Product.BACKPACK);
        assertEquals(cartPage.getCartItemCount(), 1, "Cart should contain 1 item after removal");
        assertFalse(cartPage.isItemInCart(Product.BACKPACK.getDisplayName()), "Removed item should not be in cart");
    }

    @Test(priority = 5, description = "Verify empty cart")
    public void testEmptyCart() {
        inventoryPage.goToCart();

        assertTrue(cartPage.isCartEmpty(), "Cart should be empty initially");
        assertEquals(cartPage.getCartItemCount(), 0, "Cart item count should be 0");
    }

    @Test(priority = 6, description = "Verify cart persistence across pages")
    public void testCartPersistenceAcrossPages() {

        inventoryPage.addItemToCart(Product.BACKPACK);
        assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart badge should show 1 item");

        inventoryPage.goToCart();
        cartPage.continueShopping();

        assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart should persist after navigation");
    }

}

