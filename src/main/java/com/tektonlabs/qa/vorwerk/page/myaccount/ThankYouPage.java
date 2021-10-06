package com.tektonlabs.qa.vorwerk.page.myaccount;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class ThankYouPage extends Page {

    @FindBy(linkText = "Go to my account")
    private WebElement goToMyAccountButton;

    public ThankYouPage(WebDriver driver) {
        super(driver);
        assertThat(goToMyAccountButton.isDisplayed())
                .as("button should be displayed")
                .isTrue();
    }

    public MyAccountPage clickGoToMyAccountButton(){
        goToMyAccountButton.click();
        return new MyAccountPage(driver);
    }
}
