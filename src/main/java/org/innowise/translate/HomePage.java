package org.innowise.translate;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class=\"VtwTSb\"]//button[@jsname=\"b3VHJd\"]")
    private WebElement acceptAll;

    @FindBy(xpath = "//body[@id=\"yDmH0d\"]//div[@class=\"OlSOob\"]//button[@jsname=\"RCbdJd\"]")
    private WebElement expandLeftButton;

    @FindBy(xpath = "//body[@id=\"yDmH0d\"]//div[@class=\"OlSOob\"]//button[@jsname=\"zumM6d\"]")
    private WebElement expandRightLink;

    @FindBy(xpath = "//div[@class=\"n4sEPd\"]//textarea[@class=\"er8xn\"]")
    private WebElement textArea;

    @FindBy(xpath = "//span[@class=\"ryNqvb\"]")
    private WebElement textResult;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickAcceptAll() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptAll)).click();
    }

    public void clickExpandLeftButton() {
        wait.until(ExpectedConditions.elementToBeClickable(expandLeftButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(expandLeftButton).click().perform();
    }

    public void clickExpandRightButton() {
        wait.until(ExpectedConditions.elementToBeClickable(expandRightLink));
        Actions actions = new Actions(driver);
        actions.moveToElement(expandRightLink).click().perform();
    }

    public void switchLeftLanguage(String targetLanguage) {
        String xpath = String.format("//div[@id='ucj-7']/following-sibling::span[@class='OQo6Zd']//div[@class='Llmcnf' and text()='%s']", targetLanguage);
        WebElement languageElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        Actions actions = new Actions(driver);
        actions.moveToElement(languageElement).click().perform();
    }

    public void switchRightLanguage(String targetLanguage) {
        String xpath = String.format("//div[@id='ucj-10']/following-sibling::span[@class='OQo6Zd']//div[@class='Llmcnf' and text()='%s']", targetLanguage);
        WebElement languageElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        Actions actions = new Actions(driver);
        actions.moveToElement(languageElement).click().perform();
    }

    public void enterText(String text) {
        wait.until(ExpectedConditions.visibilityOf(textArea)).sendKeys(text);
    }

    public String getTextResult() {
        return wait.until(ExpectedConditions.visibilityOf(textResult)).getText();
    }
}

