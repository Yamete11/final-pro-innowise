package org.innowise.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "(//a[@class='catalog-offers__link catalog-offers__link_primary'])[1]")
    private WebElement firstProduct;

    @FindBy(xpath = "//input[contains(@class, 'fast-search__input')]")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='result__item result__item_product']")
    private WebElement productItem;

    @FindBy(id = "submit-button")
    private WebElement cookieSubmitButton;

    private final By iframeLocator = By.xpath("//div[@id='fast-search-modal']//iframe[@class='modal-iframe']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String product) {
        searchInput.sendKeys(product);
    }

    public void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cookieSubmitButton)).click();
    }

    public void switchToSearchFrame() {
        WebElement iframe = driver.findElement(iframeLocator);
        driver.switchTo().frame(iframe);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void clickOnProductItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(productItem)).click();
    }

    public String openFirstProduct() throws InterruptedException {
        Thread.sleep(2000);
        String text = firstProduct.getText().trim();
        firstProduct.click();
        return text;
    }

    public String getFirstProduct() {
        return firstProduct.getText().trim();
    }
}