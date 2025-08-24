package ru.yandex.praktikum.scooter.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TrackPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By inputOrderNumber = By.xpath("//input[@placeholder='Введите номер заказа']");
    private final By goButton = By.xpath("//button[normalize-space()='Go!']");
    private final By notFound = By.xpath("//*[contains(text(),'Такого заказа нет')]");

    public TrackPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "track");
    }

    public void searchOrder(String orderNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputOrderNumber)).sendKeys(orderNumber);
        driver.findElement(goButton).click();
    }

    public boolean isNotFoundVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(notFound)).isDisplayed();
    }
}
