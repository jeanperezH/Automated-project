package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class GetCookingPage extends Page {

    @FindBy(xpath = "//h1[@class='thank-you-title']")
    public WebElement titleText;

    @FindBy(xpath = "//li[@class='woocommerce-order-overview__order order']/strong")
    private WebElement orderNumber;

    @FindBy(xpath = "//div[@class='bg-line']")
    private WebElement orderDetailsText;

    public GetCookingPage(WebDriver driver) {
        super(driver);
        assertThat(titleText.isDisplayed()).isTrue();
    }

    public String getOrderNumber() { return jsGetTextContent(orderNumber); }

    public void scrollToDetails() {
        jsScrollTo(orderDetailsText);
        sleepUninterruptibly(4, TimeUnit.SECONDS);
    }

    public String expectedLink() {
        String url = "";
        url = String.format("checkout/order-received/%s/?key=wc_order_%s&utm_nooverride=1",getOrderNumber(),getOrderNumber());
        return url;
    }

}
