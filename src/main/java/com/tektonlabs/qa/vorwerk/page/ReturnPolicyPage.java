package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class ReturnPolicyPage extends Page {

    @FindBy(className = "entry-title")
    private WebElement returnPolicyLink;

    public ReturnPolicyPage(WebDriver driver) {
        super(driver);
        assertThat(returnPolicyLink.getText()).isEqualTo("Vorwerk’s Return Policy");
    }
}
