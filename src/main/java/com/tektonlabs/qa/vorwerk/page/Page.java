package com.tektonlabs.qa.vorwerk.page;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public abstract class Page {

    public static final int WAIT_TIME_IN_SECONDS = 10;
    private static final Logger LOG = LoggerFactory.getLogger(Page.class);

    protected final WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        LOG.info("At [{} => {}]", driver.getTitle(), driver.getCurrentUrl());
    }

    protected void jsScrollTo(WebElement element) {
        LOG.debug("Scrolling to {}", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void jsClick(WebElement element) {
        LOG.debug("JS clicking on {}", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected String jsGetTextContent(WebElement element) {
        LOG.debug("JS getting text content {}", element);
        return (String) ((JavascriptExecutor) this.driver).executeScript("return arguments[0].textContent;", element);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected static void snap(WebDriver webdriver) {
        final var srcFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
        final var destFile = new File(String.format("./snaps/%s.png", UUID.randomUUID()));
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            LOG.error("Couldn't take screenshot", e);
        }
    }

    protected void debugElementState(WebElement element) {
        snap(this.driver);
        LOG.debug("Element: [{}], Text: [{}], Location: [{}]", element, element.getText(), element.getLocation());
        LOG.debug("Is displayed: [{}]", element.isDisplayed());
        LOG.debug("Is enabled: [{}]", element.isEnabled());
        LOG.debug("Is selected: [{}]", element.isSelected());
    }

    protected void click(WebElement element) {
        LOG.debug("Clicking {}", element.toString());
        try {
            element.click();
        } catch (WebDriverException e) {
            debugElementState(element);
            throw e;
        }
    }
}
