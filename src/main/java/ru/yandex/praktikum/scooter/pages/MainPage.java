package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ===== Локаторы =====
    private final By orderUp = By.xpath("//button[@class='Button_Button__ra12g' and text()='Заказать']");
    private final By orderStatus = By.xpath("//button[text()='Статус заказа']");
    private final By orderDown = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private final By cookieButton = By.xpath("//button[text()='да все привыкли']");

    // Шаблонные локаторы (динамические)
    private static final String questionLocatorPattern = "//div[@id='accordion__heading-%d']";
    private static final String answerLocatorPattern = "//div[@id='accordion__panel-%d']//p";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== Методы для получения By по индексу =====
    private By getQuestionLocator(int index) {
        return By.xpath(String.format(questionLocatorPattern, index));
    }

    private By getAnswerLocator(int index) {
        return By.xpath(String.format(answerLocatorPattern, index));
    }

    // ===== Действия на странице =====
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void clickButtonOrderUp() {
        driver.findElement(orderUp).click();
    }

    public void clickButtonOrderDown() {
        WebElement element = driver.findElement(orderDown);
        scrollToElement(element);
        element.click();
    }

    public void clickFaqQuestionByIndex(int index) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(getQuestionLocator(index)));
        scrollToElement(element);
        element.click();
    }

    public String getFaqAnswerTextByIndex(int index) {
        WebElement answerEl = wait.until(ExpectedConditions.visibilityOfElementLocated(getAnswerLocator(index)));
        return answerEl.getText();
    }

    public boolean faqAnswerIsDisplayed(int index) {
        return driver.findElement(getAnswerLocator(index)).isDisplayed();
    }

    // ===== Вспомогательный метод =====
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}

