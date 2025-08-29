package ru.yandex.praktikum.scooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.scooter.pages.MainPage;
import ru.yandex.praktikum.scooter.pages.OrderPage;

import static org.junit.Assert.assertTrue;

public class OrderButtonTest {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkOrderUpButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonOrderUp();
        OrderPage orderPage = new OrderPage(driver);
        assertTrue(orderPage.checkOpenOrderPage());
    }

    @Test
    public void checkOrderDownButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonOrderDown();
        OrderPage orderPage = new OrderPage(driver);
        assertTrue(orderPage.checkOpenOrderPage());
    }

    @After
    public void browserClose() {
        driver.quit();
    }
}

