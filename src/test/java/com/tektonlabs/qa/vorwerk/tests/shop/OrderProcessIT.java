package com.tektonlabs.qa.vorwerk.tests.shop;

import com.tektonlabs.qa.vorwerk.page.BillingDetailsPage;
import com.tektonlabs.qa.vorwerk.page.BundleProductPage;
import com.tektonlabs.qa.vorwerk.page.EmailPage;
import com.tektonlabs.qa.vorwerk.page.KitchenEssentialsPage;
import com.tektonlabs.qa.vorwerk.page.PartsPage;
import com.tektonlabs.qa.vorwerk.page.PaymentPage;
import com.tektonlabs.qa.vorwerk.page.ProductPage;
import com.tektonlabs.qa.vorwerk.page.RecipesAndCookbooksPage;
import com.tektonlabs.qa.vorwerk.page.ShopPage;
import com.tektonlabs.qa.vorwerk.tests.BaseIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderProcessIT extends BaseIT {

    private static final String BPA_WARNING = "WARNING: This product can expose you to bisphenol A (BPA)\n" +
            ", which is known to the State of California to cause birth defects or other reproductive harm. For more information, go to www.P65Warnings.ca.gov.";
    private static final String USD_AMOUNT_PATTERN = "^\\$\\d.*$";

    @Test
    @DisplayName("Should be able to order a simple product Thermomix")
    void shouldBeAbleToOrderSimpleProductThermomix() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        final EmailPage emailPage = productPage.clickCheckout();
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        paymentPage.clickStripePaymentMethod();
        paymentPage.acceptTermsAndConditions();
        assertThat(paymentPage.getPriceAmount()).matches(USD_AMOUNT_PATTERN);
    }

    @Test
    @DisplayName("Should be able to order a simple product accessory")
    void shouldBeAbleToOrderSimpleProductAccessory() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final PartsPage partsPage = shopPage.navigateToParts();
        partsPage.addFirstProductToCart();
        final EmailPage emailPage = partsPage.clickCheckout();
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        paymentPage.clickStripePaymentMethod();
        paymentPage.acceptTermsAndConditions();
        assertThat(paymentPage.getPriceAmount()).matches(USD_AMOUNT_PATTERN);
    }

    @Test
    @DisplayName("Should be able to order a product bundle Thermomix")
    void shouldBeAbleToOrderSimpleProductBundleThermomix() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final KitchenEssentialsPage kitchenEssentialsPage = shopPage.navigateToKitchenEssentials();
        final BundleProductPage bundleProductPage = kitchenEssentialsPage.clickSousVideProduct();
        final EmailPage emailPage = bundleProductPage.addToCartAndCheckout();
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        paymentPage.clickStripePaymentMethod();
        paymentPage.acceptTermsAndConditions();
        assertThat(paymentPage.getPriceAmount()).matches(USD_AMOUNT_PATTERN);
    }

    @Test
    @DisplayName("Should be able to order a product bundle Mix & Match")
    void shouldBeAbleToOrderProductBundleMixAndMatch() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final RecipesAndCookbooksPage recipesAndCookbooksPage = shopPage.navigateToRecipesAndCookbook();
        final BundleProductPage bundleProductPage = recipesAndCookbooksPage.clickMixMatchProduct();
        final EmailPage emailPage = bundleProductPage.addToCartAndCheckout();
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        paymentPage.clickStripePaymentMethod();
        paymentPage.acceptTermsAndConditions();
        assertThat(paymentPage.getPriceAmount()).matches(USD_AMOUNT_PATTERN);
    }

    @Test
    @DisplayName("Should show a BPA warning when ordering a Thermomix in California")
    void shouldShowBpaWarningWhenOrderingThermomixInCalifornia() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        final EmailPage emailPage = productPage.clickCheckout();
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.cityWithBpaWarning(), CONFIG.billingStateWithBpaWarning(), CONFIG.zipWithBpaWarning());
        paymentPage.clickStripePaymentMethod();
        paymentPage.acceptTermsAndConditions();
        assertThat(paymentPage.getBpaWarningMessage()).isEqualTo(BPA_WARNING);
        assertThat(paymentPage.getPriceAmount()).matches(USD_AMOUNT_PATTERN);
    }

    @Test
    @DisplayName("Should be able to order any item shopping cart badge")
    void shouldBeAbleToOrderAnyItemShoppingCartBadge() {
        final ShopPage shopPage = new ShopPage(getDriver());
        shopPage.clickAnyItem();
        shopPage.closeCartModal();
        shopPage.clickCartIcon();
        final EmailPage emailPage = shopPage.clickCheckout();
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        paymentPage.clickStripePaymentMethod();
        paymentPage.acceptTermsAndConditions();
        assertThat(paymentPage.getPriceAmount()).matches(USD_AMOUNT_PATTERN);
    }

    @Test
    @DisplayName("Should be able to order any item and update the quantity")
    void shouldBeAbleToOrderAnyItemAndUpdateTheQuantity() {
        clearCart(CONFIG.emailAddress(), CONFIG.password());
        final ShopPage shopPage = new ShopPage(getDriver());
        final ProductPage productPage = shopPage.clickShopNow();
        productPage.clickAddToCart();
        productPage.clickPlusIcon();
        final EmailPage emailPage = productPage.clickCheckout();
        assertThat(emailPage.getCartContentCount()).isEqualTo(2);
        final BillingDetailsPage billingDetailsPage = emailPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        final PaymentPage paymentPage = billingDetailsPage.inputAddressDetails(CONFIG.firstName(), CONFIG.lastName(), CONFIG.phone(), CONFIG.streetAddress(), CONFIG.city(), CONFIG.billingState(), CONFIG.zip());
        paymentPage.clickStripePaymentMethod();
        paymentPage.acceptTermsAndConditions();
        assertThat(paymentPage.getPriceAmount()).matches(USD_AMOUNT_PATTERN);
    }
}
