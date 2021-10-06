package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class DetailsPaypalPage extends Page{

    private static final Logger LOG = LoggerFactory.getLogger(DetailsPaypalPage.class);

    @FindBy(xpath = "//div[@class='done']")
    private WebElement checkIcon;

    @FindBy(id = "merchantReturnBtn")
    private WebElement returnToMerchantButton;


    public DetailsPaypalPage(WebDriver driver) {
        super(driver);
        sleepUninterruptibly(10, TimeUnit.SECONDS);
        assertThat(checkIcon.isDisplayed()).isTrue();
    }

    public GetCookingPage clickReturnButton(){
        returnToMerchantButton.click();
        return new GetCookingPage(driver);
    }
}
