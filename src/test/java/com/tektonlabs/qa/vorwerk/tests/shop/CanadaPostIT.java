package com.tektonlabs.qa.vorwerk.tests.shop;

import com.tektonlabs.qa.vorwerk.page.BillingDetailsPage;
import com.tektonlabs.qa.vorwerk.page.PartsPage;
import com.tektonlabs.qa.vorwerk.page.ShopPage;
import com.tektonlabs.qa.vorwerk.page.myaccount.LoginPage;
import com.tektonlabs.qa.vorwerk.page.myaccount.MyAccountPage;
import com.tektonlabs.qa.vorwerk.tests.BaseCanadaIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class CanadaPostIT extends BaseCanadaIT {

    @Test
    @DisplayName("Should be able to verify Ship whith Canada Post")
    void shouldBeAbleToVerifyShipWithCanadaPost() {
        clearCart(CONFIG.emailAddress(), CONFIG.password());
        final ShopPage newShopPage = new ShopPage(getDriver());
        final LoginPage loginPage = newShopPage.clickSignIn();
        final MyAccountPage myAccountPage = loginPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PartsPage partsPage = myAccountPage.navigateToThermomixParts();
        partsPage.addFirstProductToCart();
        final BillingDetailsPage billingDetailsPage = partsPage.clickCheckoutButton();
        billingDetailsPage.inputAddressDetails(CONFIG.firstName(),CONFIG.lastName(),CONFIG.phone(),CONFIG.streetAddressWithCanPost(),CONFIG.cityWithCanPost(),CONFIG.billingStateWithCanPost(),CONFIG.zipWithCanPost());
        assertThat(billingDetailsPage.getCanPostPrice()).isEqualTo(billingDetailsPage.expectedCanPostText());
    }
}
