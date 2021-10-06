package com.tektonlabs.qa.vorwerk.tests.shop.hostgift;

import com.tektonlabs.qa.vorwerk.page.consultant_host_form.*;
import com.tektonlabs.qa.vorwerk.tests.BaseForConsultantIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HostFormIT extends BaseForConsultantIT {

    @Test
    @DisplayName("Should be able to fill the host and guest forms")
    void shouldBeAbleToFillTheHostAndGuestForms() {
        final ConsultantLoungePage consultantLoungePage = new ConsultantLoungePage(getDriver());
        final MainPage mainPage = consultantLoungePage.clickLogin(CONFIG.emailAddress(),CONFIG.password());
        mainPage.navigateToForms();
        final HostFormPage hostFormPage = mainPage.clickFormIcon();
        final ConfirmPage confirmPage = hostFormPage.clickSubmit(CONFIG.streetAddress(),CONFIG.city(),CONFIG.billingState(),CONFIG.zip(),CONFIG.phone(),CONFIG.emailAddress());
        final ThankYouPage thankYouPage = confirmPage.clickConfirm();
        assertThat(thankYouPage.textContent()).isEqualTo(thankYouPage.expectedText());
    }

}
