package org.innowise.ui.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SignInPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[contains(@id, 'ap_email')]")
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

    public void signIn(String email, String password) {
        emailInput.sendKeys(email);
        submitEmail.click();
        passwordInput.sendKeys(password);
        signInButton.click();
    }
}
