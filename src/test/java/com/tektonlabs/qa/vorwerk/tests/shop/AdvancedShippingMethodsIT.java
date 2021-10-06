package com.tektonlabs.qa.vorwerk.tests.shop;

import com.tektonlabs.qa.vorwerk.page.*;
import com.tektonlabs.qa.vorwerk.page.myaccount.LoginPage;
import com.tektonlabs.qa.vorwerk.page.myaccount.MyAccountPage;
import com.tektonlabs.qa.vorwerk.page.myaccount.RegisterNowPage;
import com.tektonlabs.qa.vorwerk.page.myaccount.ThankYouPage;
import com.tektonlabs.qa.vorwerk.page.wordpress.EditPage;
import com.tektonlabs.qa.vorwerk.page.wordpress.UsersPage;
import com.tektonlabs.qa.vorwerk.page.wordpress.WordPressPage;
import com.tektonlabs.qa.vorwerk.tests.BaseIT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class AdvancedShippingMethodsIT extends BaseIT {

    private static final String NAME = createRandomName();
    private static final String EMAIL_ADDRESS = createRandomEmailAddress();
    private static final String PASSWORD = createRandomPassword();

    @Test
    @DisplayName("Should be able to verify TM6 flat rate")
    void shouldBeAbleToVerifyTm6FlatRate() {
        clearCart(CONFIG.emailAddress(), CONFIG.password());
        final ShopPage newShopPage = new ShopPage(getDriver());
        final LoginPage loginPage = newShopPage.clickSignIn();
        final MyAccountPage myAccountPage = loginPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final ShopPage shopPage = myAccountPage.clickShopButton();
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        final BillingDetailsPage billingDetailsPage = productPage.clickCheckoutButton();
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        assertThat(paymentPage.getTm6FlatRate()).isEqualTo("$49.00");
    }

    @Test
    @DisplayName("Should be able to verify Free Shipping - New Consultants")
    void shouldBeAbleToVerifyFreeShippingNewConsultants() {
        clearCart(CONFIG.newConsultantEmailAddress(), CONFIG.newConsultantPassword());
        final ShopPage newShopPage = new ShopPage(getDriver());
        final LoginPage loginPage = newShopPage.clickSignIn();
        final MyAccountPage myAccountPage = loginPage.inputCredentials(CONFIG.newConsultantEmailAddress(), CONFIG.newConsultantPassword());
        final PartsPage partsPage = myAccountPage.shopThermomixParts();
        partsPage.addFirstProductToCart();
        final BillingDetailsPage billingDetailsPage = partsPage.clickCheckoutButton();
        sleepUninterruptibly(5, TimeUnit.SECONDS);
        assertThat(billingDetailsPage.getFreeShippingText()).isEqualTo(billingDetailsPage.expectedFreeShippingText());
    }

    @Test
    @DisplayName("Should be able to verify Ship whith UPS")
    void shouldBeAbleToVerifyShipWithUps() {
        clearCart(CONFIG.emailAddress(), CONFIG.password());
        final ShopPage newShopPage = new ShopPage(getDriver());
        final LoginPage loginPage = newShopPage.clickSignIn();
        final MyAccountPage myAccountPage = loginPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PartsPage partsPage = myAccountPage.shopThermomixParts();
        partsPage.addFirstProductToCart();
        final BillingDetailsPage billingDetailsPage = partsPage.clickCheckoutButton();
        billingDetailsPage.inputAddressDetails(CONFIG.firstName(),CONFIG.lastName(),CONFIG.phone(),CONFIG.streetAddressWithUps(),CONFIG.cityWithUps(),CONFIG.billingStateWithUps(),CONFIG.zipWithUps());
        assertThat(billingDetailsPage.getUpsPrice()).isEqualTo(billingDetailsPage.expectedUpsText());
    }

    @Disabled
    @DisplayName("Should be able to create new account")
    void shouldBeAbleToCreateNewAccount() {
        final ShopPage newShopPage = new ShopPage(getDriver());
        final LoginPage loginPage = newShopPage.clickSignIn();
        final RegisterNowPage registerNowPage = loginPage.clickRegisterNow();
        registerNowPage.fillForm(NAME,NAME,EMAIL_ADDRESS,PASSWORD,PASSWORD);
        final ThankYouPage thankYouPage = registerNowPage.clickRegisterButton();
        final MyAccountPage myAccountPage = thankYouPage.clickGoToMyAccountButton();
        assertThat(myAccountPage.getCurrentUrl().endsWith("my-account/"));
    }

    @Disabled
    @DisplayName("Should be able to change user role to new consultant")
    void shouldBeAbleToChangeUserRole() {
        final ShopPage newShopPage = new ShopPage(getDriver());
        final LoginPage loginPage = newShopPage.clickSignIn();
        final MyAccountPage myAccountPage = loginPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final WordPressPage wordPressPage = myAccountPage.goToWordPressPage();
        final UsersPage usersPage = wordPressPage.navigateToAllUsers();
        usersPage.searchUser();
        final EditPage editPage = usersPage.clickEdit();
        editPage.changeUserRole();
        assertThat(editPage.getCurrentUrl().endsWith("wp-admin/users.php"));
    }

    private static String createRandomName() {
        return UUID.randomUUID().toString();
    }

    private static String createRandomEmailAddress() {
        return String.format("%s@tektonlabs.com", UUID.randomUUID());
    }

    private static String createRandomPassword() {
        return UUID.randomUUID().toString();
    }
}
