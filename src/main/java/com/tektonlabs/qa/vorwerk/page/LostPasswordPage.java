package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class LostPasswordPage extends Page {

    @FindBy(className = "woocommerce-ResetPassword")
    private WebElement resetPasswordForm;

    @FindBy(id = "user_login")
    private WebElement emailAddressInput;

    @FindBy(xpath = "//form[@class='woocommerce-ResetPassword lost_reset_password']//button")
    private WebElement resetPasswordButton;

    @FindBy(className = "woocommerce-message")
    private WebElement wooCommerceMessage;

    public LostPasswordPage(WebDriver driver) {
        super(driver);
        assertThat(resetPasswordForm.isDisplayed())
                .as("Reset password form should be displayed")
                .isTrue();
    }

    public void inputCredentials(String emailAddress) {
        inputEmailAddress(emailAddress);
        clickResetPassword();
    }

    public void clickResetPassword() {
        click(resetPasswordButton);
    }

    public void inputEmailAddress(String emailAddress) {
        emailAddressInput.clear();
        emailAddressInput.sendKeys(emailAddress);
    }

    public String getMessage() {
        return wooCommerceMessage.getText();
    }

}
