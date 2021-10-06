package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class PartsPage extends PageWithCart {

    private static final String ADD_TO_CART_BUTTON_CLASS = "add_to_cart_button";

    @FindBy(id = "closed")
    private WebElement body;

    @FindBy(className = ADD_TO_CART_BUTTON_CLASS)
    private WebElement firstAddToCartButton;

    public PartsPage(WebDriver driver) {
        super(driver);
        assertThat(body.getAttribute("class"))
                .as("Should be on Thermomix Parts page")
                .contains("term-thermomix-parts");
    }

    public void addFirstProductToCart() {
        click(firstAddToCartButton);
    }
}
