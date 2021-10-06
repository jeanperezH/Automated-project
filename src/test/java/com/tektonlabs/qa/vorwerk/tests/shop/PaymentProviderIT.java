package com.tektonlabs.qa.vorwerk.tests.shop;

import com.tektonlabs.qa.vorwerk.page.BillingDetailsPage;
import com.tektonlabs.qa.vorwerk.page.EmailPage;
import com.tektonlabs.qa.vorwerk.page.GetCookingPage;
import com.tektonlabs.qa.vorwerk.page.PaymentPage;
import com.tektonlabs.qa.vorwerk.page.ProductPage;
import com.tektonlabs.qa.vorwerk.page.RegisterPage;
import com.tektonlabs.qa.vorwerk.page.ShopPage;
import com.tektonlabs.qa.vorwerk.tests.BaseIT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentProviderIT extends BaseIT {

    private static final String EMAIL_ADDRESS = createRandomEmailAddress();
    private static final String PASSWORD = createRandomPassword();

    @Test
    @DisplayName("Should be able to submit order with login and Bread as payment provider")
    void shouldBeAbleSubmitOrderWithLoginAndBread() {
        //clearCart();
        final ShopPage shopPage = new ShopPage(getDriver());
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        final EmailPage emailPage = productPage.clickCheckout();
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.cityWithBpaWarning(), CONFIG.billingStateWithBpaWarning(), CONFIG.zipWithBpaWarning());
        paymentPage.clickBreadPaymentMethod();
        paymentPage.acceptTermsAndConditions();
        paymentPage.clickSubmitOrderButton();
        paymentPage.clickFirstLoginButtonInBread();
        paymentPage.enterPhoneNumber(CONFIG.phone());
        paymentPage.enterCode(CONFIG.breadCode());
        paymentPage.choosePlan();
        final GetCookingPage getCookingPage = paymentPage.acceptBreadTerms();
        assertThat(getCookingPage.getCurrentUrl()).contains(getCookingPage.getOrderNumber());
    }

    @Disabled
    @DisplayName("Should be able to submit order with registration and Bread as payment provider")
    void shouldBeAbleSubmitOrderWithRegistrationAndBread() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        final EmailPage emailPage = productPage.clickCheckout();
        emailPage.inputWhatsYourEmailAddress(EMAIL_ADDRESS);
        emailPage.clickContinue();
        final RegisterPage registerPage = new RegisterPage(getDriver());
        final BillingDetailsPage billingDetailsPage = registerPage.inputCredentials(EMAIL_ADDRESS, PASSWORD);
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        paymentPage.clickBreadPaymentMethod();
        paymentPage.acceptTermsAndConditions();
        /*final GetCookingPage getCookingPage = paymentPage.clickSubmitOrder();
        assertThat(getCookingPage.getCurrentUrl()).endsWith(getCookingPage.generateExpectedUrlPath());*/
    }

    private static String createRandomEmailAddress() {
        return String.format("%s@tektonlabs.com", UUID.randomUUID());
    }

    private static String createRandomPassword() {
        return UUID.randomUUID().toString();
    }
}
