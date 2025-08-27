package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static java.time.Duration.ofSeconds;

public class MainPage {

    private WebDriver driver;

    //Логотип страницы
    //Кнопка Заказать вверху страницы
    private By orderUp = By.xpath("//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //Кнопка Статус заказа
    private By orderSatus = By.xpath("//button[text()='Статус заказа']");
    //Заголовок страницы
    //Текст под заголовком
    //Самокат(картинка)
    //Технические характеристики самоката
    //Как это работает
    //Список элементов "Как это работает"
    //Кнопка Заказать внизу страницы
    private By orderDown = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    //Вопрсы о важном (заголовок)
    //Список элементов "Вопросы о важном"
    //Кнопка cookie
    private By cookieButton = By.xpath("//button[text()='да все привыкли']");
    private final String questionLocator = "//div[@id='accordion__heading-%s']";
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')]/p[text()='%s']";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    //Метод кликает по верхней кнопке Заказать
    public void clickButtonOrderUp() {
        driver.findElement(orderUp).click();
    }

    //Метод кликает по нижней кнопке Заказать
    public void clickButtonOrderDown() {
        WebElement element = driver.findElement(orderDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void openQuestion(int index) {
        WebElement element = driver.findElement(By.xpath(String.format(questionLocator, index)));
        new WebDriverWait(driver, ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public boolean answerIsDisplayed(String answer) {
        WebElement element = driver.findElement(By.xpath(String.format(answerLocator, answer)));
        return element.isDisplayed();
    }
    // Открыть вопрос по индексу (1..N)
    public void clickFaqQuestionByIndex(int index) {
        WebElement element = driver.findElement(By.xpath(String.format(questionLocator, index)));
        new WebDriverWait(driver, ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    // Получить текст ответа по индексу вопроса
    public String getFaqAnswerTextByIndex(int index) {
        String answerByIndex = String.format("//div[@id='accordion__panel-%d']//p", index);
        WebElement answerEl = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answerByIndex)));
        return answerEl.getText();
    }
    
}
