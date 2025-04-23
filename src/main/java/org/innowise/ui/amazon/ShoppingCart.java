package org.innowise.ui.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCart {

    private WebDriver driver;

    @FindBy(xpath = "//span[@class=\"a-truncate-cut\"]")
    private WebElement productTitle;

    public ShoppingCart(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductTitle() {
        return productTitle;
    }
}
