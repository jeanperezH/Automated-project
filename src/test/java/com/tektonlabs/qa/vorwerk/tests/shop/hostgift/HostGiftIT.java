package com.tektonlabs.qa.vorwerk.tests.shop.hostgift;

import com.tektonlabs.qa.vorwerk.page.*;
import com.tektonlabs.qa.vorwerk.page.myaccount.LoginPage;
import com.tektonlabs.qa.vorwerk.page.myaccount.MyAccountPage;
import com.tektonlabs.qa.vorwerk.tests.BaseIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.assertj.core.api.Assertions.assertThat;

public class HostGiftIT extends BaseIT {
    @Test
    @DisplayName("Should be able to verify discount for host")
    void shouldBeAbleToVerifyDiscountForHost() {
        clearCart(CONFIG.emailAddress(), CONFIG.password());
        final ShopPage newShopPage = new ShopPage(getDriver());
        final LoginPage loginPage = newShopPage.clickSignIn();
        final MyAccountPage myAccountPage = loginPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final ShopPage shopPage = myAccountPage.clickShopButton();
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        productPage.enterCouponCode(CONFIG.couponCode());
        final BillingDetailsPage billingDetailsPage = productPage.clickCheckoutButton();
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        assertThat(paymentPage.discountWithCouponCode.isDisplayed()).isTrue();
    }

    @Test
    @DisplayName("Should be able to verify discount for guest")
    void shouldBeAbleToVerifyDiscountForGuest() {
        clearCart(CONFIG.guestEmailAddress(), CONFIG.guestPassword());
        final ShopPage newShopPage = new ShopPage(getDriver());
        final LoginPage loginPage = newShopPage.clickSignIn();
        final MyAccountPage myAccountPage = loginPage.inputCredentials(CONFIG.guestEmailAddress(), CONFIG.guestPassword());
        final ShopPage shopPage = myAccountPage.clickShopButton();
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        productPage.enterCouponCode(CONFIG.guestCouponCode());
        final BillingDetailsPage billingDetailsPage = productPage.clickCheckoutButton();
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.guestFirstName(), CONFIG.guestLastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        assertThat(paymentPage.discountWithCouponCode.isDisplayed()).isTrue();
    }

    @Test
    @DisplayName("Should be able to order a TM6 with the guest's coupon code")
    void shouldBeAbleToOrderTm6WithDiscountOfGuest() {
        clearCart(CONFIG.guestEmailAddress(), CONFIG.guestPassword());
        final ShopPage shopPage = new ShopPage(getDriver());
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        productPage.enterCouponCode(CONFIG.guestCouponCode());
        final EmailPage emailPage = productPage.clickCheckout();
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.guestEmailAddress(), CONFIG.guestPassword());
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.guestFirstName(), CONFIG.guestLastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        paymentPage.clickPaypalMethod();
        assertThat(paymentPage.discountWithCouponCode.isDisplayed()).isTrue();
        paymentPage.acceptTermsAndConditions();
        final PaypalPage paypalPage = paymentPage.clickProceedToPaypalButton();
        paypalPage.clickAcceptAllCookiesButton();
        final PaypalCheckoutPage paypalCheckoutPage = paypalPage.clickLogin(CONFIG.paypalEmailAddress(), CONFIG.paypalPassword());
        paypalCheckoutPage.clickAcceptAllCookiesButton();
        final DetailsPaypalPage detailsPaypalPage = paypalCheckoutPage.clickPayNowButton();
        final GetCookingPage getCookingPage = detailsPaypalPage.clickReturnButton();
        getCookingPage.scrollToDetails();
        assertThat(getCookingPage.titleText.isDisplayed()).isTrue();
    }
}
