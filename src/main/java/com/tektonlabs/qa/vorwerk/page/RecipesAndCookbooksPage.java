package com.tektonlabs.qa.vorwerk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipesAndCookbooksPage extends Page {

    @FindBy(id = "closed")
    private WebElement body;

    @FindBy(xpath = "//a[@data-product_id='3043070']")
    private WebElement mixMatchProductSelectButton;

    public RecipesAndCookbooksPage(WebDriver driver) {
        super(driver);
        assertThat(body.getAttribute("class"))
                .as("Should be on Recipes & Cookbooks page")
                .contains("term-recipes-cookbooks");
    }

    public BundleProductPage clickMixMatchProduct() {
        click(mixMatchProductSelectButton);
        return new BundleProductPage(driver);
    }
}
