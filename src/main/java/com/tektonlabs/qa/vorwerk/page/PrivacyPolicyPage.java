package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrivacyPolicyPage extends Page {

    @FindBy(linkText = "Privacy")
    private WebElement PrivacyPolicyLink;

    public PrivacyPolicyPage(WebDriver driver) {
        super(driver);
        assertThat(PrivacyPolicyLink.getAttribute("aria-current")).isEqualTo("page");
    }
}
