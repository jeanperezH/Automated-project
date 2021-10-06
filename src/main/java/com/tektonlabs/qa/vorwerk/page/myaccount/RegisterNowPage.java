package com.tektonlabs.qa.vorwerk.page.myaccount;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RegisterNowPage extends Page {

    @FindBy(xpath = "//h1[@class='entry-title']")
    private WebElement titleText;

    @FindBy(id = "nf-field-433")
    private WebElement firstNameInput;

    @FindBy(id = "nf-field-28")
    private WebElement lastNameInput;

    @FindBy(id = "nf-field-35")
    private WebElement emailInput;

    @FindBy(id = "nf-field-49")
    private WebElement passwordInput;

    @FindBy(id = "nf-field-522")
    private WebElement rePasswordInput;

    @FindBy(xpath = "//label[@id='nf-label-field-157']")
    private WebElement termsAndConditions;

    @FindBy(id = "nf-field-37")
    private WebElement registerButton;

    public RegisterNowPage(WebDriver driver) {
        super(driver);
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(visibilityOf(titleText));
        assertThat(titleText.getText()).isEqualTo("Register now");
    }

    public RegisterNowPage fillForm(String firstName, String lastName, String emailAddress, String password, String pass) {
        inputFirstName(firstName);
        inputLastName(lastName);
        inputEmailAddress(emailAddress);
        inputPassword(password);
        inputConfirmPassword(pass);
        jsClick(termsAndConditions);
        return new RegisterNowPage(driver);
    }

    public ThankYouPage clickRegisterButton(){
        click(registerButton);
        return new ThankYouPage(driver);
    }

    private void inputFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    private void inputLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    private void inputEmailAddress(String emailAddress) {
        emailInput.clear();
        emailInput.sendKeys(emailAddress);
    }

    private void inputPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    private void inputConfirmPassword(String pass) {
        rePasswordInput.clear();
        rePasswordInput.sendKeys(pass);
    }

    private String getTitle(){ return jsGetTextContent(titleText); }
}
