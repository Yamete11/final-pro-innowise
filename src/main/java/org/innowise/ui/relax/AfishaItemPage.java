package org.innowise.ui.relax;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AfishaItemPage {
    private WebDriver driver;

    @FindBy(xpath = "//a[@class=\"b-afisha-layout-theater_movie-tag\"]")
    private WebElement itemSection;

    @FindBy(id = "review-form")
    private WebElement feedbackSection;


    public AfishaItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getItemSection() {
        return itemSection.getText().toLowerCase();
    }

    public WebElement getFeedbackSection() {
        return feedbackSection;
    }
}
