package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailPage extends PageWithCart {

    @FindBy(id = "step1")
    private WebElement stepOne;

    @FindBy(id = "nf-field-624")
    private WebElement whatsYourEmailAddressInput;

    @FindBy(id = "nf-field-629")
    private WebElement emailAddressInput;

    @FindBy(name = "nf-field-627")
    private WebElement passwordInput;

    @FindBy(id = "nf-field-625")
    private WebElement continueButton;

    @FindBy(id = "nf-field-626")
    private WebElement loginButton;

    @FindBy(linkText = "Click here")
    private WebElement clickHereButton;

    public EmailPage(WebDriver driver) {
        super(driver);
        assertThat(stepOne.getAttribute("class")).contains("current");
    }

    public BillingDetailsPage inputCredentials(String emailAddress, String password) {
        inputWhatsYourEmailAddress(emailAddress);
        clickContinue();
        inputEmailAddress(emailAddress);
        inputPassword(password);
        clickLogin();
        return new BillingDetailsPage(driver);
    }

    public void inputWhatsYourEmailAddress(String emailAddress) {
        whatsYourEmailAddressInput.clear();
        whatsYourEmailAddressInput.sendKeys(emailAddress);
    }

    public void inputEmailAddress(String emailAddress) {
        emailAddressInput.clear();
        emailAddressInput.sendKeys(emailAddress);
    }

    public void inputPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public LostPasswordPage clickHere() {
        click(clickHereButton);
        return new LostPasswordPage(driver);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void clickLogin() {
        click(loginButton);
    }

}
