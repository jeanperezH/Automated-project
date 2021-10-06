package com.tektonlabs.qa.vorwerk.tests;

import com.tektonlabs.qa.vorwerk.config.ShopConfig;
import com.tektonlabs.qa.vorwerk.page.myaccount.LoginPage;
import com.tektonlabs.qa.vorwerk.page.myaccount.MyAccountPage;
import com.tektonlabs.qa.vorwerk.page.ShopPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseIT {

    private static final Logger LOG = LoggerFactory.getLogger(BaseIT.class);
    protected static final ShopConfig CONFIG = initConfig();

    private WebDriver driver;

    private static WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        final var driver = new ChromeDriver(configureChrome());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        LOG.info("Capabilities of [chrome]:");
        ((HasCapabilities) driver).getCapabilities().asMap().forEach((key, value) -> LOG.info(key + ":" + value));
        return driver;
    }

    private static ShopConfig initConfig() {
        final var runtimeOverrides = new HashMap<String, String>();
        runtimeOverrides.put("env", env("ENVIRONMENT").orElse("stage"));
        runtimeOverrides.put("country", env("COUNTRY").orElse("usa"));
        final ShopConfig shopConfig = ConfigFactory.create(ShopConfig.class, runtimeOverrides);
        LOG.info(String.format("Country: [%s], Environment: [%s]", shopConfig.country(), shopConfig.env()));
        return shopConfig;
    }

    private static ChromeOptions configureChrome() {
        final var options = new ChromeOptions();
        options.setAcceptInsecureCerts(false);
        options.setHeadless(false);
        options.addArguments("--user-agent=selenium-automation");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        return options;
    }

    private static Optional<String> env(String key) {
        if (key == null) {
            return Optional.empty();
        } else {
            var value = System.getenv(key);
            if (value == null) {
                value = System.getProperty(key);
            }
            return Optional.ofNullable(value);
        }
    }

    private static String buildBaseUrl() {
        var url = "";
        if (Objects.equals(CONFIG.env(), "stage"))
            url = String.format("https://%s:%s@%s", CONFIG.environmentUsername(), CONFIG.environmentPassword(), CONFIG.domain());
        else {
            url = String.format("https://%s", CONFIG.domain());
        }
        LOG.info("Base URL [{}]", url);
        return url;
    }

    @BeforeEach
    void init() {
        driver = initDriver();
        driver.get(buildBaseUrl());
        sleepUninterruptibly(3, TimeUnit.SECONDS);
        dismissHtaccessMessage();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected void clearCart(String email, String pass) {
        final ShopPage shopPage = new ShopPage(getDriver());
        final LoginPage loginPage = shopPage.clickSignIn();
        final MyAccountPage myAccountPage = loginPage.inputCredentials(email, pass);
        sleepUninterruptibly(5, TimeUnit.SECONDS);
        if (myAccountPage.getCartContentCount() > 0) {
            myAccountPage.clickCartIcon();
            myAccountPage.ClearCart();
            myAccountPage.closeCartModal();
        }
        myAccountPage.clickSignout();
    }

    private void dismissHtaccessMessage(){
        final ShopPage shopPage = new ShopPage(getDriver());
        if (shopPage.htaccessMessage.isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", shopPage.htaccessMessage);
        }
    }

}

