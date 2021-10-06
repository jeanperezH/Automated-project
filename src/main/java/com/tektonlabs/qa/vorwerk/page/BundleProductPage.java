package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class BundleProductPage extends PageWithCart {

    @FindBy(className = "vuww-add-to-cart-button-container")
    private WebElement addToCartButton;

    public BundleProductPage(WebDriver driver) {
        super(driver);
        assertThat(addToCartButton.isDisplayed()).isTrue();
    }

    public void clickAddToCart() {
        click(addToCartButton);
    }

    public EmailPage addToCartAndCheckout() {
        clickAddToCart();
        return clickCheckout();
    }
}
