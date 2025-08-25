package ru.yandex.praktikum.scooter.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // ===== Elements list (for reviewers) =====
    // Заголовок страницы
    // Кнопка «Заказать» (верхняя)
    // Кнопка «Заказать» (нижняя)
    // Секция FAQ «Вопросы о важном» аккордеон (заголовки и ответы)
    // Логотип «Самокат»
    // Логотип «Яндекс»
    // Кнопка «Статус заказа»

    private final By headerOrderButton = By.xpath("(//button[normalize-space()='Заказать'])[1]");
    private final By bottomOrderButton = By.xpath("(//button[normalize-space()='Заказать'])[last()]");
    private final By scooterLogo = By.cssSelector("a[href='/']");
    private final By yandexLogo = By.cssSelector("a[aria-label='Yandex']");
    private final By orderStatusHeaderButton = By.xpath("//a[contains(@class,'Header_Link') and normalize-space()='Статус заказа']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public MainPage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    public void clickTopOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(headerOrderButton)).click();
    }

    public void clickBottomOrderButton() {
        // Скроллим к нижней кнопке и кликаем
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(bottomOrderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
    }

    public void clickFaqQuestionByIndex(int index) {
        // В учебном стенде FAQ имеет id вида accordion__heading-0..7 / accordion__panel-0..7
        By question = By.id("accordion__heading-" + index);
        WebElement q = wait.until(ExpectedConditions.elementToBeClickable(question));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", q);
        q.click();
    }

    public String getFaqAnswerTextByIndex(int index) {
        By answer = By.id("accordion__panel-" + index);
        WebElement a = wait.until(ExpectedConditions.visibilityOfElementLocated(answer));
        return a.getText();
    }

    public void clickScooterLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(scooterLogo)).click();
    }

    public void clickYandexLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(yandexLogo)).click();
    }

    public void goToOrderStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(orderStatusHeaderButton)).click();
    }
}
