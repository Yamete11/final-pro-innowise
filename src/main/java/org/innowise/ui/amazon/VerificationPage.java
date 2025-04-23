package org.innowise.ui.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerificationPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class=\"a-row\"]//a")
    private WebElement tryDifferentImageButton;

    public VerificationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnTryDifferentImageButton() {
        tryDifferentImageButton.click();
    }
}
