package com.tektonlabs.qa.vorwerk.page.consultant_host_form;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfirmPage extends Page {

    @FindBy(id = "please-confirm")
    private WebElement confirmText;

    @FindBy(id = "gform_submit_button_1")
    private WebElement confirmButton;

    public ConfirmPage(WebDriver driver) {
        super(driver);
        sleepUninterruptibly(2, TimeUnit.SECONDS);
        assertThat(confirmText.getText()).isEqualTo("Please Confirm");
    }

    public ThankYouPage clickConfirm(){
        jsScrollTo(confirmButton);
        click(confirmButton);
        return new ThankYouPage(driver);
    }
}
