package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class TermsOfUsePage extends Page {

    @FindBy(linkText = "Terms of Use")
    private WebElement termsOfUseLink;

    public TermsOfUsePage(WebDriver driver) {
        super(driver);
        assertThat(termsOfUseLink.getAttribute("aria-current")).isEqualTo("page");
    }
}
