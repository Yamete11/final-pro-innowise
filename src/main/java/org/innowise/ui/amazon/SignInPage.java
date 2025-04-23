package org.innowise.ui.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {

    private WebDriver driver;

    @FindBy(id = "ap_email_login")
    private WebElement emailInput;

    @FindBy(id = "continue")
    private WebElement submitEmail;

    @FindBy(id = "ap_password")
    private WebElement passwordInput;

    @FindBy(id = "signInSubmit")
    private WebElement signInButton;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signIn(String email, String password) throws InterruptedException {
        emailInput.sendKeys(email);
        submitEmail.click();
        passwordInput.sendKeys(password);
        signInButton.click();
    }
}
