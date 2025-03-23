package org.innowise.relax;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlacePage {
    private WebDriver driver;

    @FindBy(xpath = "(//span[@class=\"Button__iconWrapper\"])[1]")
    private WebElement placeData;

    @FindBy(xpath = "//div[@itemprop=\"address\"]")
    private WebElement address;

    @FindBy(xpath = "//span[@class=\"TimeMarker__text\"]")
    private WebElement workingHours;

    @FindBy(xpath = "//span[@class=\"PhoneLink__number\"]")
    private WebElement phoneNumber;

    @FindBy(xpath = "//div[@class=\"h17f477bf\"]")
    private WebElement adCloseButton;

    public PlacePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void closeAd(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(adCloseButton));
        adCloseButton.click();
    }

    public void clickPlaceData() {
        placeData.click();
    }

    public WebElement getAddress() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(address));
        return address;
    }

    public WebElement getWorkingHours() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(workingHours));
        return workingHours;
    }

    public WebElement getPhoneNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(phoneNumber));
        return phoneNumber;
    }
}
