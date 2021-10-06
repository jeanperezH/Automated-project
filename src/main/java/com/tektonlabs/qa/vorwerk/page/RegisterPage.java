package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterPage extends PageWithCart {

    @FindBy(name = "email")
    private WebElement emailAddressInput;

    @FindBy(name = "nf-field-622")
    private WebElement passwordInput;

    @FindBy(name = "nf-field-623")
    private WebElement repeatPasswordInput;

    @FindBy(xpath = "//div[@data-field-id='632']")
    private WebElement termsFieldInput;

    @FindBy(id = "nf-field-621")
    private WebElement createMyAccountButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
        assertThat(createMyAccountButton.isDisplayed()).isTrue();
    }

    public BillingDetailsPage inputCredentials(String emailAddress, String password) {
        inputEmailAddress(emailAddress);
        inputPassword(password);
        inputRepeatPassword(password);
        acceptTermsAndConditions();
        clickCreateMyAccount();
        return new BillingDetailsPage(driver);
    }

    public void inputEmailAddress(String emailAddress) {
        emailAddressInput.clear();
        emailAddressInput.sendKeys(emailAddress);
    }

    public void inputPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void inputRepeatPassword(String password) {
        repeatPasswordInput.clear();
        repeatPasswordInput.sendKeys(password);
    }

    public void acceptTermsAndConditions() {
        click(termsFieldInput);
    }

    public void clickCreateMyAccount() {
        click(createMyAccountButton);
    }
}
