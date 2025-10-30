package com.saucedemo.tests.regresion;

import com.saucedemo.base.BaseTest;
import com.saucedemo.constants.TestConstants;
import com.saucedemo.enums.Product;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OrderPlacementTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful order placement with single item")
    public void testOrderPlacementWithSingleItem() {

        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        assertTrue(inventoryPage.isInventoryPageDisplayed(),
                "Inventory page should be displayed after login");

        inventoryPage.addItemToCart(Product.BACKPACK);
        assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart should contain 1 item");

        inventoryPage.goToCart();
        assertEquals(cartPage.getCartItemCount(), 1, "Cart should contain 1 item");

        cartPage.proceedToCheckout();
        checkoutStepOnePage.fillCheckoutInformation(
                TestConstants.CHECKOUT_FIRSTNAME,
                TestConstants.CHECKOUT_LASTNAME,
                TestConstants.CHECKOUT_ZIPCODE
        );

        assertTrue(checkoutStepTwoPage.getCurrentUrl().contains("checkout-step-two"),
                "Should be on checkout overview page");

        assertEquals(checkoutStepTwoPage.getOrderItemCount(), 1, "Order should contain 1 item");

        checkoutStepTwoPage.finishCheckout();

        assertTrue(checkoutCompletePage.isOrderComplete(), "Order should be completed successfully");
        assertEquals(checkoutCompletePage.getConfirmationHeader(), TestConstants.ORDER_COMPLETE_HEADER,
                "Order confirmation header should match");
    }

    @Test(priority = 2, description = "Verify successful order placement with multiple items")
    public void testOrderPlacementWithMultipleItems() {

        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        inventoryPage.addItemToCart(Product.BACKPACK);
        inventoryPage.addItemToCart(Product.BIKE_LIGHT);
        inventoryPage.addItemToCart(Product.BOLT_TSHIRT);
        assertEquals(inventoryPage.getCartBadgeCount(), "3", "Cart should contain 3 items");

        inventoryPage.goToCart();
        assertEquals(cartPage.getCartItemCount(), 3, "Cart should contain 3 items");

        cartPage.proceedToCheckout();
        checkoutStepOnePage.fillCheckoutInformation(
                TestConstants.CHECKOUT_FIRSTNAME,
                TestConstants.CHECKOUT_LASTNAME,
                TestConstants.CHECKOUT_ZIPCODE
        );

        assertEquals(checkoutStepTwoPage.getOrderItemCount(), 3, "Order should contain all 3 items");

        checkoutStepTwoPage.finishCheckout();
        assertTrue(checkoutCompletePage.isOrderComplete(), "Order should be completed successfully");
    }

    @Test(priority = 3, description = "Verify order placement after removing items from cart")
    public void testOrderPlacementAfterRemovingItems() {

        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        inventoryPage.addItemToCart(Product.BACKPACK);
        inventoryPage.addItemToCart(Product.BIKE_LIGHT);

        inventoryPage.goToCart();
        assertEquals(cartPage.getCartItemCount(), 2, "Cart should contain 2 items");

        cartPage.removeItemFromCart(Product.BIKE_LIGHT);
        cartPage.proceedToCheckout();
        checkoutStepOnePage.fillCheckoutInformation(
                TestConstants.CHECKOUT_FIRSTNAME,
                TestConstants.CHECKOUT_LASTNAME,
                TestConstants.CHECKOUT_ZIPCODE
        );

        checkoutStepTwoPage.finishCheckout();
        assertTrue(checkoutCompletePage.isOrderComplete(), "Order should be completed successfully");
    }

    @Test(priority = 4, description = "Verify checkout cancellation from step one")
    public void testCheckoutCancellationStepOne() {

        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        inventoryPage.addItemToCart(Product.BACKPACK);

        inventoryPage.goToCart();
        cartPage.proceedToCheckout();

        checkoutStepOnePage.cancel();
        assertTrue(cartPage.getCurrentUrl().contains("cart"), "Should return to cart page after cancellation");
        assertEquals(cartPage.getCartItemCount(), 1, "Cart should still contain the item");
    }

    @Test(priority = 5, description = "Verify checkout cancellation from step two")
    public void testCheckoutCancellationStepTwo() {
        // Login and add item
        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        inventoryPage.addItemToCart(Product.BACKPACK);

        // Go through checkout steps
        inventoryPage.goToCart();
        cartPage.proceedToCheckout();
        checkoutStepOnePage.fillCheckoutInformation(
                TestConstants.CHECKOUT_FIRSTNAME,
                TestConstants.CHECKOUT_LASTNAME,
                TestConstants.CHECKOUT_ZIPCODE
        );

        // Cancel from overview page
        checkoutStepTwoPage.cancel();

        // Verify returned to inventory
        assertTrue(inventoryPage.getCurrentUrl().contains("inventory"),
                "Should return to inventory page after cancellation");
    }

    @Test(priority = 6, description = "Verify error message for empty first name")
    public void testCheckoutWithEmptyFirstName() {
        // Login and add item
        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        inventoryPage.addItemToCart(Product.BACKPACK);

        // Go to checkout
        inventoryPage.goToCart();
        cartPage.proceedToCheckout();

        // Fill only last name and postal code
        checkoutStepOnePage.enterLastName(TestConstants.CHECKOUT_LASTNAME);
        checkoutStepOnePage.enterPostalCode(TestConstants.CHECKOUT_ZIPCODE);
        checkoutStepOnePage.clickContinue();

        // Verify error message
        assertTrue(checkoutStepOnePage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertTrue(checkoutStepOnePage.getErrorMessage().contains("First Name"), "Error should mention First Name");
    }

    @Test(priority = 7, description = "Verify error message for empty last name")
    public void testCheckoutWithEmptyLastName() {

        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        inventoryPage.addItemToCart(Product.BACKPACK);

        inventoryPage.goToCart();
        cartPage.proceedToCheckout();

        checkoutStepOnePage.enterFirstName(TestConstants.CHECKOUT_FIRSTNAME);
        checkoutStepOnePage.enterPostalCode(TestConstants.CHECKOUT_ZIPCODE);
        checkoutStepOnePage.clickContinue();

        assertTrue(checkoutStepOnePage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertTrue(checkoutStepOnePage.getErrorMessage().contains("Last Name"),
                "Error should mention Last Name");
    }

    @Test(priority = 8, description = "Verify error message for empty postal code")
    public void testCheckoutWithEmptyPostalCode() {

        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        inventoryPage.addItemToCart(Product.BACKPACK);

        inventoryPage.goToCart();
        cartPage.proceedToCheckout();

        checkoutStepOnePage.enterFirstName(TestConstants.CHECKOUT_FIRSTNAME);
        checkoutStepOnePage.enterLastName(TestConstants.CHECKOUT_LASTNAME);
        checkoutStepOnePage.clickContinue();

        assertTrue(checkoutStepOnePage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertTrue(checkoutStepOnePage.getErrorMessage().contains("Postal Code"), "Error should mention Postal Code");
    }

    @Test(priority = 9, description = "Verify continue shopping from cart")
    public void testContinueShoppingFromCart() {

        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        inventoryPage.addItemToCart(Product.BACKPACK);

        inventoryPage.goToCart();

        cartPage.continueShopping();

        assertTrue(inventoryPage.getCurrentUrl().contains("inventory"), "Should return to inventory page");
        assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart should still contain the item");
    }

    @Test(priority = 10, description = "Verify order total calculation")
    public void testOrderTotalCalculation() {

        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);
        inventoryPage.addItemToCart(Product.BACKPACK);
        inventoryPage.addItemToCart(Product.BIKE_LIGHT);

        inventoryPage.goToCart();
        cartPage.proceedToCheckout();
        checkoutStepOnePage.fillCheckoutInformation(
                TestConstants.CHECKOUT_FIRSTNAME,
                TestConstants.CHECKOUT_LASTNAME,
                TestConstants.CHECKOUT_ZIPCODE
        );

        String subtotal = checkoutStepTwoPage.getSubtotal();
        String tax = checkoutStepTwoPage.getTax();
        String total = checkoutStepTwoPage.getTotal();

        Assert.assertNotNull(subtotal, "Subtotal should be displayed");
        Assert.assertNotNull(tax, "Tax should be displayed");
        Assert.assertNotNull(total, "Total should be displayed");
        assertTrue(subtotal.contains("$"), "Subtotal should contain currency symbol");
        assertTrue(tax.contains("$"), "Tax should contain currency symbol");
        assertTrue(total.contains("$"), "Total should contain currency symbol");
    }
}

