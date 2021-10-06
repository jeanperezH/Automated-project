package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPage extends PageWithCart {

    @FindBy(name = "add-to-cart")
    private WebElement addToCartButton;

    @FindBy(className = "xoo-wsc-plus")
    private WebElement plusIcon;

    @FindBy(id = "xoo-wsc-coupon-code-custom")
    private WebElement couponCodeInput;

    @FindBy(id = "xoo-wsc-coupon-label")
    private WebElement applyButton;

    public ProductPage(WebDriver driver) {
        super(driver);
        assertThat(addToCartButton.isDisplayed())
                .as("The add-to-cart button should be displayed on the product page")
                .isTrue();
    }

    public void clickAddToCart() {
        click(addToCartButton);
        sleepUninterruptibly(3, TimeUnit.SECONDS);
    }

    public void enterCouponCode(String coupon) {
        fillCouponCode(coupon);
        click(applyButton);
        sleepUninterruptibly(3, TimeUnit.SECONDS);
    }

    private void fillCouponCode(String coupon){
        couponCodeInput.clear();
        couponCodeInput.sendKeys(coupon);
    }

    public void clickPlusIcon() {
        jsClick(plusIcon);
    }
}
