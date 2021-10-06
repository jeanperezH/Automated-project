package com.tektonlabs.qa.vorwerk.page.consultant_host_form;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class ThankYouPage extends Page {

    @FindBy(id = "thank-you")
    private WebElement thankYouTitle;

    @FindBy(xpath = "//div[@id='gform_confirmation_message_1']/p")
    private WebElement text;

    public ThankYouPage(WebDriver driver) {
        super(driver);
        sleepUninterruptibly(2, TimeUnit.SECONDS);
        assertThat(thankYouTitle.getText()).isEqualTo("Thank You");
    }

    public String textContent(){ return jsGetTextContent(text);}

    public String expectedText(){
        String text = "Your Host will receive a confirmation email when qualified Host Gifts ship.";
        return text;
    }

}
