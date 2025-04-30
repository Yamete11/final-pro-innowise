package org.innowise.ui.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VerificationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class=\"a-row\"]//a")
    private WebElement tryDifferentImageButton;

    public VerificationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickOnTryDifferentImageButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tryDifferentImageButton)).click();
    }
}
