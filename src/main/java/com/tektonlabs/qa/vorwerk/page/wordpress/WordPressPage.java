package com.tektonlabs.qa.vorwerk.page.wordpress;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class WordPressPage extends Page {

    @FindBy(xpath = "//div[@class='wrap']/h1")
    private WebElement dashboardText;

    @FindBy(xpath = "//div[@class='wp-menu-image dashicons-before dashicons-admin-users']")
    private WebElement userNavigation;

    @FindBy(linkText = "All Users")
    private WebElement allUserNav;


    public WordPressPage(WebDriver driver) {
        super(driver);
        assertThat(dashboardText.getText())
                .isEqualTo("Dashboard");
    }

    public UsersPage navigateToAllUsers(){
        jsScrollTo(userNavigation);
        new Actions(driver).moveToElement(userNavigation).click().perform();
        return new UsersPage(driver);
    }

}
