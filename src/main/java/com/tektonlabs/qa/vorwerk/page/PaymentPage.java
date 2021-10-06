package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("UnusedReturnValue")
public class PaymentPage extends Page {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentPage.class);

    @FindBy(id = "payment_method_stripe")
    private WebElement stripeRadioButton;

    @FindBy(id = "payment_method_bread_finance")
    private WebElement breadRadioButton;

    @FindBy(id = "payment_method_paypal")
    private WebElement paypalRadioButton;

    @FindBy(xpath = "//iframe[@title='Secure card number input frame']")
    private WebElement cardNumberIframe;

    @FindBy(xpath = "//iframe[@title='Secure expiration date input frame']")
    private WebElement expiryDateIframe;

    @FindBy(xpath = "//iframe[@title='Secure CVC input frame']")
    private WebElement cvcIframe;

    @FindBy(xpath = "//input[@data-elements-stable-field-name='cardNumber']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[@data-elements-stable-field-name='cardExpiry']")
    private WebElement expiryDateInput;

    @FindBy(xpath = "//input[@data-elements-stable-field-name='cardCvc']")
    private WebElement cvcInput;

    @FindBy(id = "place_order")
    private WebElement submitOrderButton;

    @FindBy(id = "terms")
    private WebElement termsFieldInput;

    @FindBy(name = "apply_coupon")
    private WebElement applyCouponButton;

    @FindBy(xpath = "//div[@class='bpa-warning']")
    private WebElement bpaWarning;

    @FindBy(xpath = "//span[@class='woocommerce-Price-amount amount']")
    private WebElement priceAmount;

    @FindBy(xpath = "//label[@for='shipping_method_0_advanced_flat_rate_shipping3258583']/span[@class='woocommerce-Price-amount amount']")
    private WebElement tm6flatRate;

    @FindBy(id = "button-login")
    private WebElement loginButtonInBread;

    @FindBy(id = "phone")
    private WebElement phoneInputInLogin;

    @FindBy(id = "loginButton")
    private WebElement loginButtonAfterPhone;

    @FindBy(id = "code")
    private WebElement codeInput;

    @FindBy(id = "authorizeButton")
    private WebElement submitTokenButton;

    @FindBy(id = "radio-0")
    private WebElement twelveMonthsOption;

    @FindBy(id = "confirmBtn")
    private WebElement continueToCheckoutButton;

    @FindBy(id = "review-terms-agreement")
    private WebElement termsCheckbox;

    @FindBy(id = "bread-review-submit-button")
    private WebElement acceptAndCheckOutButton;

    @FindBy(xpath = "div[@id='app']")
    private WebElement breadFrame;

    @FindBy(xpath = "//a[@class='woocommerce-remove-coupon']")
    public WebElement discountWithCouponCode;

    public PaymentPage(WebDriver driver) {
        super(driver);
        sleepUninterruptibly(5, TimeUnit.SECONDS);
        assertThat(submitOrderButton.isDisplayed()).isTrue();
    }

    public PaymentPage enterPaymentDetails(String cardNumber, String expiryDate, String cvc) {
        fillCardNumber(cardNumber);
        fillExpiryDate(expiryDate);
        fillCvc(cvc);
        return this;
    }

    public void fillCardNumber(String cardNumber) {
        driver.switchTo().frame(cardNumberIframe);
        cardNumberInput.clear();
        cardNumberInput.sendKeys(cardNumber);
        driver.switchTo().defaultContent();
    }

    public void fillExpiryDate(String expiryDate) {
        driver.switchTo().frame(expiryDateIframe);
        expiryDateInput.clear();
        expiryDateInput.sendKeys(expiryDate);
        driver.switchTo().defaultContent();
    }

    public void fillCvc(String cvc) {
        driver.switchTo().frame(cvcIframe);
        cvcInput.clear();
        cvcInput.sendKeys(cvc);
        driver.switchTo().defaultContent();
    }

    public PaymentPage acceptTermsAndConditions() {
        LOG.debug("Clicking {}", termsFieldInput.toString());
        jsClick(termsFieldInput);
        LOG.debug("Clicking {}", submitOrderButton.toString());
        submitOrderButton.click();
        return new PaymentPage(driver);
    }

    public PaymentPage clickSubmitOrderButton() {
        LOG.debug("Clicking {}", submitOrderButton.toString());
        jsClick(submitOrderButton);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new PaymentPage(driver);
    }

    public PaypalPage clickProceedToPaypalButton() {
        LOG.debug("Clicking {}", submitOrderButton.toString());
        jsClick(submitOrderButton);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new PaypalPage(driver);
    }

    public PaymentPage clickFirstLoginButtonInBread() {
        driver.switchTo().frame(breadFrame);
        LOG.debug("Clicking {}", loginButtonInBread.toString());
        click(loginButtonInBread);
        //loginButtonInBread.click();
        driver.switchTo().defaultContent();
        return new PaymentPage(driver);
    }

    public PaymentPage enterPhoneNumber(String phoneNumber) {
        fillPhoneNumber(phoneNumber);
        LOG.debug("Clicking {}", loginButtonAfterPhone.toString());
        loginButtonAfterPhone.click();
        return new PaymentPage(driver);
    }

    private void fillPhoneNumber(String phoneNumber){
        driver.switchTo().frame(breadFrame);
        phoneInputInLogin.clear();
        phoneInputInLogin.sendKeys(phoneNumber);
        driver.switchTo().defaultContent();
    }

    public String getBpaWarningMessage() {
        return bpaWarning.getText();
    }

    public PaymentPage clickStripePaymentMethod() {
        LOG.debug("Clicking {}", stripeRadioButton.toString());
        jsClick(stripeRadioButton);
        return new PaymentPage(driver);
    }

    public PaymentPage clickBreadPaymentMethod() {
        LOG.debug("Clicking {}", breadRadioButton.toString());
        jsClick(breadRadioButton);
        return new PaymentPage(driver);
    }

    public PaymentPage clickPaypalMethod() {
        LOG.debug("Clicking {}", paypalRadioButton.toString());
        jsClick(paypalRadioButton);
        return new PaymentPage(driver);
    }

    public PaymentPage enterCode(String code){
        LOG.debug("Typing {}", codeInput.toString());
        fillBreadCode(code);
        LOG.debug("Clicking {}", submitTokenButton.toString());
        submitTokenButton.click();
        return new PaymentPage(driver);
    }
    private void fillBreadCode(String code){
        driver.switchTo().frame(breadFrame);
        codeInput.clear();
        codeInput.sendKeys(code);
        driver.switchTo().defaultContent();
    }

    public PaymentPage choosePlan(){
        driver.switchTo().frame(breadFrame);
        jsClick(twelveMonthsOption);
        LOG.debug("Clicking {}", continueToCheckoutButton.toString());
        continueToCheckoutButton.click();
        driver.switchTo().defaultContent();
        return new PaymentPage(driver);
    }

    public GetCookingPage acceptBreadTerms(){
        driver.switchTo().frame(breadFrame);
        LOG.debug("Clicking {}", termsCheckbox.toString());
        jsClick(termsCheckbox);
        LOG.debug("Clicking {}", acceptAndCheckOutButton.toString());
        acceptAndCheckOutButton.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        return new GetCookingPage(driver);
    }

    public String getPriceAmount() {
        return jsGetTextContent(priceAmount);
    }

    public String getTm6FlatRate() { return jsGetTextContent(tm6flatRate); }
}
