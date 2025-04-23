package org.innowise.ui.amazon;

import org.innowise.ui.amazon.components.Header;
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

    private Header header;

    @FindBy(id = "NATC_SMART_WAGON_CONF_MSG_SUCCESS")
    private WebElement alert;

    @FindBy(xpath = "//span[@class=\"sc-quantity-stepper\"]//span[@data-a-selector=\"value\"]")
    private WebElement quantityOfProduct;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.header = new Header(driver);
    }

    public WebElement getAlert() {
        return alert;
    }

    public boolean checkTheNumberOfProduct() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(quantityOfProduct));
        return Integer.parseInt(quantityOfProduct.getText()) > 0;
    }

    public Header getHeader() {
        return header;
    }
}
