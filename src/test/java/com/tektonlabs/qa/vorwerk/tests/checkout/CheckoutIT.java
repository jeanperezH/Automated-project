package com.tektonlabs.qa.vorwerk.tests.checkout;

import com.tektonlabs.qa.vorwerk.page.BillingDetailsPage;
import com.tektonlabs.qa.vorwerk.page.EmailPage;
import com.tektonlabs.qa.vorwerk.page.LostPasswordPage;
import com.tektonlabs.qa.vorwerk.page.ProductPage;
import com.tektonlabs.qa.vorwerk.page.ShopPage;
import com.tektonlabs.qa.vorwerk.tests.BaseIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutIT extends BaseIT {

    @Test
    @DisplayName("Should be able to login via checkout")
    void shouldBeAbleToLoginViaCheckout() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        final EmailPage emailPage = productPage.clickCheckout();
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        assertThat(billingDetailsPage.getCurrentUrl()).endsWith("checkout/");
    }

    @Test
    @DisplayName("Should be able to reset password via checkout")
    void shouldBeAbleToResetPasswordViaCheckout() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        final EmailPage emailPage = productPage.clickCheckout();
        emailPage.inputWhatsYourEmailAddress(CONFIG.emailAddress());
        emailPage.clickContinue();
        final LostPasswordPage lostPasswordPage = emailPage.clickHere();
        lostPasswordPage.inputCredentials(CONFIG.emailAddress());
        assertThat(lostPasswordPage.getMessage()).isEqualTo("Password reset email has been sent.");
    }

}
