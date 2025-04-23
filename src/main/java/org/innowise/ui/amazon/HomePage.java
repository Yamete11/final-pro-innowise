package org.innowise.ui.amazon;

import org.innowise.ui.amazon.components.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    private Header header;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.header = new Header(driver);
    }

    public Header getHeader() {
        return header;
    }
}
