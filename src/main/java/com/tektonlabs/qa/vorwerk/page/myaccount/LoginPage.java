package com.tektonlabs.qa.vorwerk.page.myaccount;

import com.tektonlabs.qa.vorwerk.page.LostPasswordPage;
import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends Page {

    @FindBy(className = "login")
    private WebElement loginForm;

    @FindBy(id = "username")
    private WebElement usernameOrEmailAddressInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "login")
    private WebElement loginButton;

    @FindBy(xpath = "//p[@class='lost_password']/a")
    private WebElement lostPasswordButton;

    @FindBy(className = "woocommerce-error")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[@class='button btn-orange']")
    private WebElement registerNowButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(visibilityOf(loginForm));
        sleepUninterruptibly(3, TimeUnit.SECONDS);
        assertThat(loginForm.isDisplayed())
                .as("Login form should be displayed")
                .isTrue();
    }

    public LostPasswordPage clickLostPassword() {
        click(lostPasswordButton);
        return new LostPasswordPage(driver);
    }

    public MyAccountPage inputCredentials(String emailAddress, String password) {
        inputEmailAddress(emailAddress);
        inputPassword(password);
        clickLogin();
        return new MyAccountPage(driver);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void inputEmailAddress(String emailAddress) {
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(usernameOrEmailAddressInput));
        usernameOrEmailAddressInput.clear();
        usernameOrEmailAddressInput.sendKeys(emailAddress);
    }

    public void inputPassword(String password) {
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public RegisterNowPage clickRegisterNow(){
        click(registerNowButton);
        return new RegisterNowPage(driver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}