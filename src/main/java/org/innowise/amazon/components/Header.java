package org.innowise.amazon.components;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {

    private WebDriver driver;

    @FindBy(id = "nav-cart")
    private WebElement cart;

    @FindBy(xpath = "//div[@id='nav-flyout-ya-signin']//span[@class=\"nav-action-inner\"]")
    private WebElement signInButton;

    @FindBy(id = "nav-link-accountList")
    private WebElement accountAndListButton;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchInput;

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement helloLabel;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCart() {
        cart.click();
    }

    public void hoverOverAccountAndListButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(accountAndListButton).perform();
        signInButton.click();
    }

    public void searchProducts(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
    }

    public boolean getHelloLabel(String text) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(helloLabel));
        return helloLabel.getText().contains(text);
    }
}
