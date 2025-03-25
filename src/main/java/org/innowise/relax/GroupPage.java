package org.innowise.relax;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class GroupPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='MenuItem__text' and text()='Фильтры']")
    private WebElement filterButton;

    @FindBy(xpath = "//button[@class=\"Button Button--huge Button--primary Button--flat\"]")
    private WebElement showButton;

    @FindBy(xpath = "//div[@class=\"CatalogNav__title\"]//span")
    private WebElement filterResultQuantity;

    public GroupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFilterButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(filterButton));
        filterButton.click();
    }


    public void openTypeOfFilter(String type) {
        String xpath = String.format("//div[@class='FilterSidebar__itemTitle' and text()='%s']", type);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement filterTypeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        Actions actions = new Actions(driver);
        actions.moveToElement(filterTypeElement).click().perform();
    }


    public void openTypeOfFilterOption(String option) {
        String xpath = String.format("//span[@class='Radio__text' and text()='%s']", option);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        element.click();
    }

    public void chooseRadioBox(String option) {
        String xpath = String.format("//span[@class='ToggleSwitch__text' and text()='%s']", option);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        element.click();
    }

    public void chooseFilter(String group, String option) {
        String xpath = String.format("//div[@class='FilterSidebar__itemTitle' and text()='%s']/following-sibling::div[@class=\"CompositeButtons__wrapper Wrapper \"]//div[@class='CompositeButtons__toggle']", group);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        element.click();

        xpath = String.format("//span[@class=\"CheckButton__span\" and text()='%s']", option);
        WebElement secondElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        secondElement.click();
    }

    public void selectOptionFromMenu(String menuTitle, String option) {
        String xpath = String.format("//div[@class='FilterSidebar__itemTitle' and text()='%s']/following-sibling::div[contains(@class, 'CompositeButtons__wrapper')]//span[text()='%s']", menuTitle, option);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        Actions actions = new Actions(driver);
        actions.moveToElement(optionElement).click().perform();
    }

    public void clickShowButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(showButton));
        showButton.click();
    }

    public boolean checkResultQuantity() {
        return Integer.parseInt(filterResultQuantity.getText()) > 0;
    }


}
