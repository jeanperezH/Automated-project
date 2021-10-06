package com.tektonlabs.qa.vorwerk.page.consultant_host_form;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPage extends Page {

    @FindBy(id = "logo")
    private WebElement logo;

    @FindBy(id = "menu-item-1041969")
    private WebElement resourcesItem;

    @FindBy(id = "menu-item-1042614")
    private WebElement formsItem;

    @FindBy(xpath = "//img[@src='https://consultant-stage.thermomix.com/wp-content/uploads/2020/12/Host-Form-Thumbnail@2x.png']")
    private WebElement formIcon;

    public MainPage(WebDriver driver) {
        super(driver);
        assertThat(logo.isDisplayed()).isTrue();
    }

    public MainPage navigateToForms(){
        new Actions(driver).moveToElement(resourcesItem).moveToElement(formsItem).click().perform();
        return new MainPage(driver);
    }

    public HostFormPage clickFormIcon(){
        click(formIcon);
        return new HostFormPage(driver);
    }
}
