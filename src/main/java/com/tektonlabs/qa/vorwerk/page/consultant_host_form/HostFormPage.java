package com.tektonlabs.qa.vorwerk.page.consultant_host_form;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class HostFormPage extends Page {

    @FindBy(xpath = "//h1[@class='et_pb_module_header']")
    private WebElement hostFormTitle;

    @FindBy(id = "input_1_2")
    private WebElement hostFirstName;

    @FindBy(id = "input_1_3")
    private WebElement hostLastName;

    @FindBy(id = "input_1_31_1")
    private WebElement streetAddress;

    @FindBy(id = "input_1_31_2")
    private WebElement streetAddressContinued;

    @FindBy(id = "input_1_31_3")
    private WebElement hostCity;

    @FindBy(id = "input_1_31_4")
    private WebElement hostState;

    @FindBy(id = "input_1_32")
    private WebElement hostZip;

    @FindBy(id = "input_1_11")
    private WebElement hostPhone;

    @FindBy(id = "input_1_10")
    private WebElement hostEmail;

    @FindBy(id = "input_1_34")
    private WebElement exclusiveOffer;

    @FindBy(id = "input_1_17-1-1")
    private WebElement guestOneFirstName;

    @FindBy(id = "input_1_18-1-1")
    private WebElement guestOneLastName;

    @FindBy(id = "input_1_30-1-1")
    private WebElement guestOneZip;

    @FindBy(id = "input_1_20-1-1")
    private WebElement guestOneEmail;

    @FindBy(id = "input_1_21-1-1")
    private WebElement guestOnePhone;

    @FindBy(id = "input_1_17-1-2")
    private WebElement guestSecondFirstName;

    @FindBy(id = "input_1_18-1-2")
    private WebElement guestSecondLastName;

    @FindBy(id = "input_1_30-1-2")
    private WebElement guestSecondZip;

    @FindBy(id = "input_1_20-1-2")
    private WebElement guestSecondEmail;

    @FindBy(id = "input_1_21-1-2")
    private WebElement guestSecondPhone;

    @FindBy(id = "gform_next_button_1_37")
    private WebElement submitButton;

    public HostFormPage(WebDriver driver) {
        super(driver);
        sleepUninterruptibly(3, TimeUnit.SECONDS);
        assertThat(hostFormTitle.getText()).isEqualTo("Host Form");
    }

    public ConfirmPage clickSubmit(String street, String city, String state, String zip, String phone, String email){
        fillHostForm(street,city,state,zip,phone,email);
        fillGuestOneForm();
        fillGuestTwoForm();
        click(submitButton);
        return new ConfirmPage(driver);
    }

    private void fillHostForm(String street, String city, String state, String zip, String phone, String email){
        hostFirstName.clear();
        hostFirstName.sendKeys("Percy Test");
        hostLastName.clear();
        hostLastName.sendKeys("Campos Test");
        streetAddress.clear();
        streetAddress.sendKeys(street);
        streetAddressContinued.clear();
        streetAddressContinued.sendKeys("Brooklyn");
        hostCity.clear();
        hostCity.sendKeys(city);
        hostState.sendKeys(state);
        hostZip.clear();
        hostZip.sendKeys(zip);
        hostPhone.clear();
        hostPhone.sendKeys(phone);
        hostEmail.clear();
        hostEmail.sendKeys(email);
        exclusiveOffer.sendKeys("TM6™ Mixing Bowl");
    }

    private void fillGuestOneForm(){
        guestOneFirstName.clear();
        guestOneFirstName.sendKeys("Guest One");
        guestOneLastName.clear();
        guestOneLastName.sendKeys("For Test");
        guestOneZip.clear();
        guestOneZip.sendKeys("50360");
        guestOneEmail.clear();
        guestOneEmail.sendKeys("guestOneForTest1@gmail.com");
        guestOnePhone.clear();
        guestOnePhone.sendKeys("(020) 202-0202");
    }

    private void fillGuestTwoForm(){
        guestSecondFirstName.clear();
        guestSecondFirstName.sendKeys("Guest Two");
        guestSecondLastName.clear();
        guestSecondLastName.sendKeys("For Test");
        guestSecondZip.clear();
        guestSecondZip.sendKeys("50360");
        guestSecondEmail.clear();
        guestSecondEmail.sendKeys("guestTwoForTest2@gmail.com");
        guestSecondPhone.clear();
        guestSecondPhone.sendKeys("(035) 102-1405");
    }
}
