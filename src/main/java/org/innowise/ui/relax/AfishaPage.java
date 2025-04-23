package org.innowise.ui.relax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AfishaPage {

    private final WebDriver driver;

    public AfishaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void chooseSection(String section) {
        String xpath = String.format("//a[@class=\"b-where-to_heading_lnk\" and text()=\"%s \"]//..//..//..//div[@class=\"b-afisha-layout_maldives_strap b-afisha-layout_maldives_strap_afisha\"]//li[1]", section);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement filterTypeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        Actions actions = new Actions(driver);
        actions.moveToElement(filterTypeElement).click().perform();
    }
}