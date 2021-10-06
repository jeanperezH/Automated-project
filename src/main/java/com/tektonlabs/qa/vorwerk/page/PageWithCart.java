package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public abstract class PageWithCart extends Page {

    public static final String CART_MODAL_LOADING_CLASS = "xoo-wsc-loading";
    public static final String CART_MODAL_ACTIVE_CLASS = "xoo-wsc-cart-active";
    public static final String CART_MODAL_CLOSE_BUTTON_CLASS = "xoo-wsch-close";
    public static final String DELETE_ITEM_ICON_CLASS = "xoo-wsc-smr-del";
    public static final String CART_HAS_CONTENT_CLASS = "cart-has-content";

    @FindBy(className = "vuww-minicart")
    private WebElement cartIcon;

    @FindBy(xpath = "//span[@class='cart-content-count']")
    private WebElement cartContentCount;

    @FindBy(className = CART_MODAL_CLOSE_BUTTON_CLASS)
    private WebElement cartModalCloseButton;

    @FindBy(className = "xoo-wsc-notice-success")
    private WebElement greenNotice;

    @FindBy(className = "xoo-wsc-modal")
    private WebElement modal;

    @FindBy(className = "xoo-wsc-ft-btn-checkout")
    protected WebElement checkoutButton;

    public PageWithCart(WebDriver driver) {
        super(driver);
        new WebDriverWait(driver, WAIT_TIME_IN_SECONDS).until(not(stalenessOf(cartIcon)));
        new WebDriverWait(driver, WAIT_TIME_IN_SECONDS).until(elementToBeClickable(cartIcon));
        assertThat(cartIcon.isDisplayed())
                .as("The cart icon should be displayed")
                .isTrue();
    }

    public void clickCartIcon() {
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(not(stalenessOf(cartIcon)));
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(elementToBeClickable(cartIcon));
        jsClick(cartIcon);
    }

    public void closeCartModal() {
        if (!modal.getAttribute("class").contains(CART_MODAL_ACTIVE_CLASS)) {
            return;
        }
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(presenceOfElementLocated(By.className(CART_MODAL_CLOSE_BUTTON_CLASS)));
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(elementToBeClickable(cartModalCloseButton));
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(invisibilityOf(greenNotice));
        click(cartModalCloseButton);
    }

    public EmailPage clickCheckout() {
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(not(stalenessOf(checkoutButton)));
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(elementToBeClickable(checkoutButton));
        sleepUninterruptibly(4, TimeUnit.SECONDS);
        click(checkoutButton);
        return new EmailPage(driver);
    }

    public BillingDetailsPage clickCheckoutButton() {
        sleepUninterruptibly(4, TimeUnit.SECONDS);
        click(checkoutButton);
        return new BillingDetailsPage(driver);
    }

    public int getCartContentCount() {
        return cartIcon.getAttribute("class").contains(CART_HAS_CONTENT_CLASS) ? parseInt(cartContentCount.getText()) : 0;
    }

    public void ClearCart() {
        do {
            jsClick(this.driver.findElement(By.className(DELETE_ITEM_ICON_CLASS)));
            new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(invisibilityOf(greenNotice));
            new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(not(attributeContains(modal, "class", CART_MODAL_LOADING_CLASS)));
        } while (getCartContentCount() > 0);
    }

}
