package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class PaypalPage extends Page {

    private static final Logger LOG = LoggerFactory.getLogger(PaypalPage.class);

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "acceptAllButton")
    private WebElement acceptAllCookiesButton;

    public PaypalPage(WebDriver driver) {
        super(driver);
        sleepUninterruptibly(5, TimeUnit.SECONDS);
        assertThat(loginButton.isDisplayed()).isTrue();
    }

    public PaypalCheckoutPage clickLogin(String email, String password){
        fillEmail(email);
        fillPassword(password);
        loginButton.click();
        return new PaypalCheckoutPage(driver);
    }

    public void clickAcceptAllCookiesButton() {
        if (acceptAllCookiesButton.isDisplayed()) {
            click(acceptAllCookiesButton);
        }
    }

    private void fillEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    private void fillPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

}
