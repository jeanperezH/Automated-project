package com.tektonlabs.qa.vorwerk.page.wordpress;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersPage extends Page {

    @FindBy(xpath = "//div[@class='wrap']/h1")
    private WebElement usersText;

    @FindBy(linkText = "Clear cache")
    private WebElement clearCacheButton;

    @FindBy(id = "user-search-input")
    private WebElement userSearchInput;

    @FindBy(id = "search-submit")
    private WebElement searchButton;

    @FindBy(id = "the-list")
    private WebElement elementSearched;

    @FindBy(name = "users[]")
    private WebElement checkboxOfElement;

    @FindBy(linkText = "Edit")
    private WebElement editLink;

    public UsersPage(WebDriver driver) {
        super(driver);
        assertThat(usersText.getText())
                .isEqualTo("Users");
    }

    public UsersPage searchUser(){
        userSearchInput.sendKeys("j34ny10@gmail.com");
        click(searchButton);
        return new UsersPage(driver);
    }

    public EditPage clickEdit(){
        jsScrollTo(clearCacheButton);
        //driver.switchTo()
        new Actions(driver).moveToElement(elementSearched);
        click(checkboxOfElement);
        click(editLink);
        return new EditPage(driver);
    }

}
