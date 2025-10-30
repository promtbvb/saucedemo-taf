package com.saucedemo.base;

import com.saucedemo.factory.DriverFactory;
import com.saucedemo.pages.*;
import com.saucedemo.pages.*;
import com.saucedemo.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base Test class that all test classes extend
 * Contains common setup and teardown methods
 */
public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected ProductDetailPage productDetailPage;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;
    protected CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver using Factory pattern
        driver = DriverFactory.createDriver(ConfigReader.getBrowser());

        // Navigate to application URL
        driver.get(ConfigReader.getUrl());

        // Initialize page objects
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}

