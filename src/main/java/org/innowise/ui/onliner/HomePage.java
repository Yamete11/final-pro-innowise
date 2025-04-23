package org.innowise.ui.onliner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//input[contains(@class, 'fast-search__input')]")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='result__item result__item_product']")
    private WebElement productItem;

    private final By iframeLocator = By.xpath("//div[@id='fast-search-modal']//iframe[@class='modal-iframe']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String product) {
        searchInput.sendKeys(product);
    }


    public void switchToSearchFrame() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(iframeLocator));
        driver.switchTo().frame(iframe);
    }


    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void clickOnProductItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(productItem)).click();
    }

}