package com.tektonlabs.qa.vorwerk.page.myaccount;

import com.tektonlabs.qa.vorwerk.page.PageWithCart;
import com.tektonlabs.qa.vorwerk.page.PartsPage;
import com.tektonlabs.qa.vorwerk.page.ShopPage;
import com.tektonlabs.qa.vorwerk.page.wordpress.WordPressPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class MyAccountPage extends PageWithCart {

    @FindBy(className = "switch-sign-out")
    private WebElement signoutButton;

    @FindBy(className = "woocommerce-MyAccount-navigation")
    private WebElement myAccountMajorNavigation;

    @FindBy(xpath = "//a[@href='https://shop-stage.thermomix.com/']")
    private WebElement shopButton;

    @FindBy(id = "menu-item-3010915")
    private WebElement minorNavigationShop;

    @FindBy(id = "menu-item-1002931")
    private WebElement minorNavigationShopForCan;

    @FindBy(id = "menu-item-3166036")
    private WebElement thermomixPartsButton;

    @FindBy(id = "menu-item-6000895")
    private WebElement thermomixPartsButtonForCan;

    @FindBy(linkText = "Thermomix USA")
    private WebElement thermomixAdminButton;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        sleepUninterruptibly(10, TimeUnit.SECONDS);
        assertThat(myAccountMajorNavigation.isDisplayed())
                .as("MyAccount major navigation should be displayed")
                .isTrue();
    }

    public ShopPage clickShopButton() {
        click(shopButton);
        return new ShopPage(driver);
    }

    public WordPressPage goToWordPressPage() {
        click(thermomixAdminButton);
        return new WordPressPage(driver);
    }

    public PartsPage shopThermomixParts(){
        new Actions(driver).moveToElement(minorNavigationShop).moveToElement(thermomixPartsButton).click().perform();
        return new PartsPage(driver);
    }

    public PartsPage navigateToThermomixParts(){
        new Actions(driver).moveToElement(minorNavigationShopForCan).moveToElement(thermomixPartsButtonForCan).click().perform();
        return new PartsPage(driver);
    }

    public void clickSignout() {
        click(signoutButton);
        sleepUninterruptibly(5, TimeUnit.SECONDS);
    }
}
