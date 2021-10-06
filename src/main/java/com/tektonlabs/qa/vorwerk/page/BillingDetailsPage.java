package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingDetailsPage extends PageWithCart {

    @FindBy(id = "vuww-billing")
    private WebElement continueToPaymentButton;

    @FindBy(id = "billing_first_name")
    private WebElement firstNameInput;

    @FindBy(id = "billing_last_name")
    private WebElement lastNameInput;

    @FindBy(id = "billing_phone")
    private WebElement phoneInput;

    @FindBy(id = "billing_address_1")
    private WebElement streetAddressInput;

    @FindBy(id = "billing_city")
    private WebElement cityInput;

    @FindBy(id = "billing_state")
    private WebElement billingStateInput;

    @FindBy(id = "billing_postcode")
    private WebElement zipInput;

    @FindBy(xpath = "//label[@for='shipping_method_0_ups203']")
    private WebElement upsPrice;

    @FindBy(xpath = "//label[@for='shipping_method_0_canada_postdom-rp']")
    private WebElement canPostPrice;

    @FindBy(xpath = "//label[@for='shipping_method_0_advanced_flat_rate_shipping3139497']")
    private WebElement freeShippingElement;

    public BillingDetailsPage(WebDriver driver) {
        super(driver);
        assertThat(continueToPaymentButton.isDisplayed()).isTrue();
    }

    public PaymentPage inputAddressDetails(String firstName, String lastName, String phone, String streetAddress, String city, String billingState, String zip) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillPhone(phone);
        fillStreetAddress(streetAddress);
        fillCity(city);
        fillBillingState(billingState);
        fillZip(zip);
        click(continueToPaymentButton);
        return new PaymentPage(driver);
    }

    public String expectedUpsText(){
        String text = "Ground (5-7 Business Days): $11.85";
        return text;
    }

    public String expectedCanPostText(){
        String text="Standard (Ships in 9-12 Business Days): CAD$42.39";
        return text;
    }

    public String expectedFreeShippingText(){
        String text="Free Shipping – New Consultant";
        return text;
    }

    public String getUpsPrice(){ return jsGetTextContent(upsPrice); }

    public String getCanPostPrice(){ return jsGetTextContent(canPostPrice); }

    public String getFreeShippingText(){ return jsGetTextContent(freeShippingElement); }


    public void fillFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void fillPhone(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void fillStreetAddress(String streetAddress) {
        streetAddressInput.clear();
        streetAddressInput.sendKeys(streetAddress);
    }

    public void fillCity(String city) {
        cityInput.clear();
        cityInput.sendKeys(city);
    }

    public void fillBillingState(String billingState) {
        billingStateInput.sendKeys(billingState);
    }

    public void fillZip(String zip) {
        zipInput.clear();
        zipInput.sendKeys(zip);
    }

}
