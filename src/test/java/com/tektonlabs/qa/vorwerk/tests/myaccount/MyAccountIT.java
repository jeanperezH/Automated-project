package com.tektonlabs.qa.vorwerk.tests.myaccount;

import com.tektonlabs.qa.vorwerk.page.LostPasswordPage;
import com.tektonlabs.qa.vorwerk.page.ShopPage;
import com.tektonlabs.qa.vorwerk.page.myaccount.LoginPage;
import com.tektonlabs.qa.vorwerk.page.myaccount.MyAccountPage;
import com.tektonlabs.qa.vorwerk.tests.BaseIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MyAccountIT extends BaseIT {

    private static final String WRONG_PASSWORD = CONFIG.password().concat("a");

    @Test
    @DisplayName("Should be able to login via My Account")
    void shouldBeAbleToLoginViaMyAccount() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final LoginPage loginPage = shopPage.clickSignIn();
        final MyAccountPage myAccountPage = loginPage.inputCredentials(CONFIG.emailAddress(), CONFIG.password());
        assertThat(myAccountPage.getCurrentUrl()).endsWith("my-account/");
    }

    @Test
    @DisplayName("Should be able to reset password")
    void shouldBeAbleToResetPassword() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final LoginPage loginPage = shopPage.clickSignIn();
        final LostPasswordPage lostPasswordPage = loginPage.clickLostPassword();
        lostPasswordPage.inputCredentials(CONFIG.emailAddress());
        assertThat(lostPasswordPage.getMessage()).isEqualTo("Password reset email has been sent.");
    }

    @Test
    @DisplayName("Should be able to enter wrong password more than three times")
    void shouldBeAbleToEnterWrongPasswordMoreThanThreeTimes() {
        final ShopPage shopPage = new ShopPage(getDriver());
        final LoginPage loginPage = shopPage.clickSignIn();
        IntStream.range(0, 3).forEach(i -> enterWrongCredentials(loginPage));
        final LostPasswordPage lostPasswordPage = loginPage.clickLostPassword();
        lostPasswordPage.inputCredentials(CONFIG.emailAddress());
        assertThat(lostPasswordPage.getMessage()).isEqualTo("Password reset email has been sent.");
    }

    private void enterWrongCredentials(LoginPage loginPage) {
        loginPage.inputEmailAddress(CONFIG.emailAddress());
        loginPage.inputPassword(WRONG_PASSWORD);
        loginPage.clickLogin();
        assertThat(loginPage.getErrorMessage()).contains("Incorrect username or password.");
    }
}
