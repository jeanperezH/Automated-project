package com.tektonlabs.qa.vorwerk.page;

import com.tektonlabs.qa.vorwerk.page.myaccount.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class ShopPage extends PageWithCart {

    @FindBy(linkText = "Shop Now")
    private WebElement shopNowButton;

    @FindBy(linkText = "Sign in")
    private WebElement signInButton;

    @FindBy(className = "copyright")
    private WebElement copyright;

    @FindBy(linkText = "Terms of Use")
    private WebElement termsOfUse;

    @FindBy(linkText = "Privacy")
    private WebElement privacyPolicy;

    @FindBy(linkText = "Return Policy")
    private WebElement returnPolicy;

    @FindBy(linkText = "Warranty")
    private WebElement warranty;

    @FindBy(id = "menu-item-3010915")
    private WebElement minorNavigationShop;

    @FindBy(id = "menu-item-3166036")
    private WebElement accessoriesLink;

    @FindBy(id = "menu-item-3166037")
    private WebElement recipesAndCookbooksLink;

    @FindBy(id = "menu-item-3166038")
    private WebElement kitchenEssentialsLink;

    @FindBy(xpath = "//a[@data-product_id='3479011']")
    private WebElement anyItem;

    @FindBy(xpath = "//button[@aria-label='Ayuda']")
    private WebElement helpButton;

    @FindBy(className = "woocommerce-store-notice__dismiss-link")
    public WebElement htaccessMessage;

    public ShopPage(WebDriver driver) {
        super(driver);
        sleepUninterruptibly(5, TimeUnit.SECONDS);
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(not(stalenessOf(shopNowButton)));
        new WebDriverWait(this.driver, WAIT_TIME_IN_SECONDS).until(elementToBeClickable(shopNowButton));
        assertThat(shopNowButton.isDisplayed())
                .as("Shop Now button should be displayed")
                .isTrue();
    }

    public WebElement getCopyright() {
        return copyright;
    }

    public ProductPage clickShopNow() {
        click(shopNowButton);
        return new ProductPage(driver);
    }

    public PartsPage navigateToParts() {
        new Actions(driver).moveToElement(minorNavigationShop).moveToElement(accessoriesLink).click().perform();
        return new PartsPage(driver);
    }

    public RecipesAndCookbooksPage navigateToRecipesAndCookbook() {
        new Actions(driver).moveToElement(minorNavigationShop).moveToElement(recipesAndCookbooksLink).click().perform();
        return new RecipesAndCookbooksPage(driver);
    }

    public KitchenEssentialsPage navigateToKitchenEssentials() {
        new Actions(driver).moveToElement(minorNavigationShop).moveToElement(kitchenEssentialsLink).click().perform();
        return new KitchenEssentialsPage(driver);
    }

    public void clickAnyItem() {
        click(anyItem);
    }

    public LoginPage clickSignIn() {
        click(signInButton);
        return new LoginPage(driver);
    }

    public TermsOfUsePage termsOfUse() {
        jsScrollTo(termsOfUse);
        click(termsOfUse);
        return new TermsOfUsePage(driver);
    }

    public PrivacyPolicyPage privacyPolicy() {
        jsScrollTo(privacyPolicy);
        click(privacyPolicy);
        return new PrivacyPolicyPage(driver);
    }

    public ReturnPolicyPage returnPolicy() {
        jsScrollTo(returnPolicy);
        click(returnPolicy);
        return new ReturnPolicyPage(driver);
    }

    public WarrantyPage warranty() {
        jsScrollTo(warranty);
        click(warranty);
        return new WarrantyPage(driver);
    }
}
