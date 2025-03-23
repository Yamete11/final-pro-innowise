package org.innowise.onliner.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class=\"cart-form__offers-flex\"]//a[@class=\"cart-form__link cart-form__link_primary cart-form__link_base-alter\"]")
    private WebElement productTitle;

    @FindBy(xpath = "//a[@class=\"button-style button-style_auxiliary button-style_small cart-form__button cart-form__button_remove\"]")
    private WebElement removeFromCart;

    @FindBy(xpath = "//a[@class=\"button-style button-style_auxiliary button-style_small cart-form__button cart-form__button_increment helpers_hide_tablet\"]")
    private WebElement checkButton;


    @FindBy(xpath = "//div[@class=\"cart-form__description cart-form__description_primary cart-form__description_base-alter cart-form__description_condensed-extra\"]")
    private WebElement removeFromCartConfirmation;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle;
    }


    public void removeFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(checkButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(checkButton).perform();

        removeFromCart.click();
    }


    public WebElement getRemoveFromCartConfirmation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(removeFromCartConfirmation));
        return removeFromCartConfirmation;
    }
}
