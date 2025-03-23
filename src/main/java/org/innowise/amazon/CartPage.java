package org.innowise.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;

    @FindBy(id = "NATC_SMART_WAGON_CONF_MSG_SUCCESS")
    private WebElement alert;

    @FindBy(xpath = "//span[@data-feature-id=\"sc-update-quantity-select\"]//span[@class=\"a-dropdown-prompt\"]")
    private WebElement numberOfProduct;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getAlert() {
        return alert;
    }

    public boolean checkTheNumberOfProduct() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(numberOfProduct));
        return Integer.parseInt(numberOfProduct.getText()) > 0;
    }
}
