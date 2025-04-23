package org.innowise.ui.onliner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;

    @FindBy(xpath = "//h1[@class='catalog-masthead__title']")
    private WebElement productTitle;

    @FindBy(xpath = "//div[@class=\"product-specs\"]")
    private WebElement productSpecs;

    @FindBy(xpath = "(//a[@class=\"button-style product-aside__button product-aside__button_narrow-alter product-aside__button_cart button-style_big-alter button-style_expletive\"])[1]")
    private WebElement firstShopButton;

    @FindBy(xpath = "//a[@class=\"button-style button-style_another button-style_base-alter product-recommended__button\"]")
    private WebElement goToCart;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductTitle(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.getText().trim();
    }

    public WebElement getProductSpecs() {
        return productSpecs;
    }


    public void clickFirstShopButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(firstShopButton));
        firstShopButton.click();
    }

    public void clickGoToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(goToCart));
        goToCart.click();
    }
}