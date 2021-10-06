package com.tektonlabs.qa.vorwerk.page.consultant_host_form;

import com.tektonlabs.qa.vorwerk.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsultantLoungePage extends Page {

    @FindBy(id = "welcome-to-the-consultant-lounge")
    private WebElement welcomeText;

    @FindBy(id = "user_login")
    private WebElement emailInput;

    @FindBy(id = "user_pass")
    private WebElement userPassInput;

    @FindBy(id = "wp-submit")
    private WebElement loginButton;

    public ConsultantLoungePage(WebDriver driver) {
        super(driver);
        assertThat(welcomeText.getText()).isEqualTo("Welcome to the Consultant Lounge!");
    }

    public MainPage clickLogin(String email, String pass){
        fillEmailAndPassword(email,pass);
        click(loginButton);
        return new MainPage(driver);
    }

    private void fillEmailAndPassword(String email, String pass) {
        emailInput.clear();
        emailInput.sendKeys(email);
        userPassInput.clear();
        userPassInput.sendKeys(pass);
    }
}
