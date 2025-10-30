package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    @FindBy(className = "pony_express")
    private WebElement ponyExpressImage;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return getText(completeHeader);
    }

    public InventoryPage backToHome() {
        click(backHomeButton);
        return new InventoryPage(driver);
    }

    public boolean isOrderComplete() {
        return isDisplayed(completeHeader) &&
                isDisplayed(completeText) &&
                getCurrentUrl().contains("checkout-complete");
    }

    public boolean isConfirmationImageDisplayed() {
        return isDisplayed(ponyExpressImage);
    }
}

