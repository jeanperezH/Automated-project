package com.tektonlabs.qa.vorwerk.page.wordpress;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class EditPage extends Page {

    @FindBy(linkText = "Add New")
    private WebElement addNewButton;

    @FindBy(linkText = "More information")
    private WebElement moreInformationLinkText;

    @FindBy(id = "role")
    private WebElement selectRole;

    @FindBy(id = "submit")
    private WebElement updateUserButton;

    @FindBy(xpath = "//select[@id='role']/option[@value='new_consultant']")
    private WebElement newConsultantOption;

    @FindBy(xpath = "//div[@class='wp-menu-image svg']")
    private WebElement wooCommerceNavigation;

    @FindBy(linkText = "Shop as Customer")
    private WebElement shopAsCustomerOption;

    @FindBy(id = "select2-cxsac-select2-user-search-container")
    private WebElement findACustomer;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    private WebElement searchCustomerInput;

    @FindBy(xpath = "//li[@class='select2-results__option select2-results__option--highlighted']")
    private WebElement result;

    public EditPage(WebDriver driver) {
        super(driver);
        assertThat(addNewButton.isDisplayed())
                .as("Add New button should be displayed")
                .isTrue();
    }

    public EditPage changeUserRole(){
        jsScrollTo(moreInformationLinkText);
        Select roles = new Select (driver.findElement(By.id("role")));
        roles.selectByVisibleText("New Consultant");
        sleepUninterruptibly(5, TimeUnit.SECONDS);
        jsScrollTo(updateUserButton);
        click(updateUserButton);
        return new EditPage(driver);
    }

    public EditPage NavigateToShopAsCustomer(){
        jsScrollTo(wooCommerceNavigation);
        new Actions(driver).moveToElement(wooCommerceNavigation).moveToElement(shopAsCustomerOption).click().perform();
        driver.switchTo().activeElement();

        return new EditPage(driver);
    }
}
