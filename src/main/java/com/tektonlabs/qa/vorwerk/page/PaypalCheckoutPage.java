package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class PaypalCheckoutPage extends Page {

    private static final Logger LOG = LoggerFactory.getLogger(PaypalCheckoutPage.class);

    @FindBy(id = "4aa5ebd51caf1a3a57d07e29a931dc90")
    private WebElement creditUnionOption;

    @FindBy(id = "confirmButtonTop")
    private WebElement payNowButton;

    @FindBy(id = "acceptAllButton")
    private WebElement acceptAllCookiesButton;

    public PaypalCheckoutPage(WebDriver driver) {
        super(driver);
        sleepUninterruptibly(5, TimeUnit.SECONDS);
        assertThat(payNowButton.isDisplayed()).isTrue();
    }

    public void clickAcceptAllCookiesButton() {
        if (acceptAllCookiesButton.isDisplayed()) {
            click(acceptAllCookiesButton);
        }
    }

    public DetailsPaypalPage clickPayNowButton() {
        click(payNowButton);
        return new DetailsPaypalPage(driver);
    }

}
