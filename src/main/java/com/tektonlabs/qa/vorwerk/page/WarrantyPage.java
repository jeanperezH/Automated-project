package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class WarrantyPage extends Page {

    @FindBy(linkText = "Warranty")
    private WebElement warrantyLink;

    public WarrantyPage(WebDriver driver) {
        super(driver);
        assertThat(warrantyLink.getAttribute("aria-current")).isEqualTo("page");
    }
}
