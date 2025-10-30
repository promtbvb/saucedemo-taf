package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.constants.TestConstants;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with standard user")
    public void testSuccessfulLoginWithStandardUser() {
        loginPage.login(TestConstants.STANDARD_USER, TestConstants.PASSWORD);

        assertTrue(inventoryPage.isInventoryPageDisplayed(), "User should be redirected to inventory page after successful login");
        assertEquals(inventoryPage.getCurrentUrl(), TestConstants.INVENTORY_URL, "URL should be inventory page URL");
    }

    @Test(priority = 2, description = "Verify login failure with locked out user")
    public void testLoginWithLockedOutUser() {
        loginPage.login(TestConstants.LOCKED_OUT_USER, TestConstants.PASSWORD);

        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for locked out user");
        assertEquals(loginPage.getErrorMessage(), TestConstants.LOCKED_USER_ERROR, "Error message should indicate user is locked out");
    }

    @Test(priority = 3, description = "Verify login failure with invalid username")
    public void testLoginWithInvalidUsername() {
        loginPage.login("invalid_user", TestConstants.PASSWORD);

        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid username");
        assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"), "Error message should indicate invalid credentials");
    }

    @Test(priority = 4, description = "Verify login failure with invalid password")
    public void testLoginWithInvalidPassword() {
        loginPage.login(TestConstants.STANDARD_USER, "invalid_password");

        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid password");
        assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"), "Error message should indicate invalid credentials");
    }

    @Test(priority = 5, description = "Verify login failure with empty username")
    public void testLoginWithEmptyUsername() {
        loginPage.enterPassword(TestConstants.PASSWORD);
        loginPage.clickLogin();

        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for empty username");
        assertTrue(loginPage.getErrorMessage().contains("Username is required"), "Error message should indicate username is required");
    }

    @Test(priority = 6, description = "Verify login failure with empty password")
    public void testLoginWithEmptyPassword() {
        loginPage.enterUsername(TestConstants.STANDARD_USER);
        loginPage.clickLogin();

        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for empty password");
        assertTrue(loginPage.getErrorMessage().contains("Password is required"), "Error message should indicate password is required");
    }

    @Test(priority = 7, description = "Verify login failure with empty credentials")
    public void testLoginWithEmptyCredentials() {
        loginPage.clickLogin();

        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for empty credentials");
        assertTrue(loginPage.getErrorMessage().contains("Username is required"), "Error message should indicate username is required");
    }

}

