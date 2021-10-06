package com.tektonlabs.qa.vorwerk.tests.shop;

import com.tektonlabs.qa.vorwerk.page.PrivacyPolicyPage;
import com.tektonlabs.qa.vorwerk.page.ReturnPolicyPage;
import com.tektonlabs.qa.vorwerk.page.ShopPage;
import com.tektonlabs.qa.vorwerk.page.TermsOfUsePage;
import com.tektonlabs.qa.vorwerk.page.WarrantyPage;
import com.tektonlabs.qa.vorwerk.tests.BaseIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HomepageIT extends BaseIT {

    @Test
    @DisplayName("Should be able to load return policy")
    void shouldBeAbleToLoadReturnPolicy() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final ReturnPolicyPage returnPolicyPage = shopPage.returnPolicy();
        assertThat(returnPolicyPage.getCurrentUrl()).endsWith("return-policy/");
    }

    @Test
    @DisplayName("Should be able to load privacy policy")
    void shouldBeAbleToLoadPrivacyPolicy() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final PrivacyPolicyPage privacyPolicyPage = shopPage.privacyPolicy();
        assertThat(privacyPolicyPage.getCurrentUrl()).endsWith("privacy-policy/");
    }

    @Test
    @DisplayName("Should be able to load terms of use")
    void shouldBeAbleToLoadTermsOfUse() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final TermsOfUsePage termsOfUsePage = shopPage.termsOfUse();
        assertThat(termsOfUsePage.getCurrentUrl()).endsWith("terms-of-use/");
    }

    @Test
    @DisplayName("Should be able to load warranty")
    void shouldBeAbleToLoadWarranty() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final WarrantyPage warrantyPage = shopPage.warranty();
        assertThat(warrantyPage.getCurrentUrl()).endsWith("warranty/");
    }
}
