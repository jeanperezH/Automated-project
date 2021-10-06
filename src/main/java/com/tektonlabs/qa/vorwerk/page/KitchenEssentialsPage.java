package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class KitchenEssentialsPage extends Page {

    @FindBy(id = "closed")
    private WebElement body;

    @FindBy(xpath = "//a[@data-product_id='3221853']")
    private WebElement sousVideProduct;

    public KitchenEssentialsPage(WebDriver driver) {
        super(driver);
        assertThat(body.getAttribute("class"))
                .as("Should be on Kitchen Essentials page")
                .contains("term-kitchen-essentials");
    }

    public BundleProductPage clickSousVideProduct() {
        click(sousVideProduct);
        return new BundleProductPage(driver);
    }
}
