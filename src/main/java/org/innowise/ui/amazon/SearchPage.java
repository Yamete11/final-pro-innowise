package org.innowise.ui.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    private WebDriver driver;

    @FindBy(xpath = "(//div[@class=\"a-section\"]//h2//span)[1]")
    private WebElement searchResult;

    @FindBy(xpath = "(//div[@class=\"puisg-col-inner\"]//a)[1]")
    private WebElement firstProductLink;


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkResult(String text) {
        return searchResult.getText().contains(text);
    }

    public void clickFirstProductLink() {
        firstProductLink.click();
    }
}
