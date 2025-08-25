package ru.yandex.praktikum.scooter.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // ===== Elements (форма шаг 1) =====
    private final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[normalize-space()='Далее']");

    // ===== Elements (форма шаг 2) =====
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalDropdown = By.cssSelector("div.Dropdown-control");
    private final By rentalOptionTemplate = By.xpath("//div[contains(@class,'Dropdown-option') and normalize-space()='%s']");
    private final By colorBlackCheckbox = By.id("black"); // чекбокс 'чёрный жемчуг'
    private final By colorGreyCheckbox = By.id("grey");   // чекбокс 'серая безысходность'
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButtonMiddle = By.xpath("//button[contains(@class,'Button_Middle') and normalize-space()='Заказать']");

    // ===== Модалка подтверждения и успех =====
    private final By yesButton = By.xpath("//button[normalize-space()='Да']");
    private final By successTitle = By.xpath("//*[contains(text(),'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillStepOne(String firstName, String lastName, String address, String metroPartial, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        // метро — автокомплит: вводим часть и выбираем первый вариант
        WebElement metro = driver.findElement(metroInput);
        metro.sendKeys(metroPartial);
        // Ждём дропдаун и нажимаем Enter для выбора первого совпадения
        metro.sendKeys(Keys.ARROW_DOWN);
        metro.sendKeys(Keys.ENTER);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    public void fillStepTwo(String date, String rentPeriodText, String color, String comment) {
        WebElement dateEl = wait.until(ExpectedConditions.elementToBeClickable(dateInput));
        dateEl.click();
        dateEl.sendKeys(date);
        dateEl.sendKeys(Keys.ENTER);

        driver.findElement(rentalDropdown).click();
        // Выбор опции аренды
        String xp = String.format("//div[contains(@class,'Dropdown-option') and normalize-space()='%s']", rentPeriodText);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xp))).click();

        if ("black".equalsIgnoreCase(color)) {
            driver.findElement(colorBlackCheckbox).click();
        } else if ("grey".equalsIgnoreCase(color) || "gray".equalsIgnoreCase(color)) {
            driver.findElement(colorGreyCheckbox).click();
        }
        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(orderButtonMiddle).click();
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }

    public boolean isOrderSuccessVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successTitle)).isDisplayed();
    }
}
