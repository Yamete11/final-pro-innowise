package org.innowise.relax;

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

    @FindBy(xpath = "//span[contains(text(), 'Принять')]")
    private WebElement acceptCookiesButton;

    @FindBy(id = "search_open")
    private WebElement searchBox;

    @FindBy(xpath = "//div[contains(text(), 'Ресторан, бар, клуб')]")
    private WebElement searchResult;

    @FindBy(xpath = "(//span[@class=\"SearchResults__button\"])[1]")
    private WebElement openMore;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }

    public void inputSearch(String search) {
        searchBox.sendKeys(search);
    }

    public void openSearchResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(openMore));
        openMore.click();
        wait.until(ExpectedConditions.elementToBeClickable(searchResult));
        searchResult.click();
    }

    public void openType(String type) {
        String xpath = String.format("//div[contains(@class, 'CategoriesMainMenu__item') and @title='%s']", type);
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }

    public void openGroup(String group) {
        String xpath = String.format("//div[@class='List__item']/a[text()='%s']", group);
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }

}
