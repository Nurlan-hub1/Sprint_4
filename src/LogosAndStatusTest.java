package ru.yandex.praktikum.scooter.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WindowType;
import ru.yandex.praktikum.scooter.pageobjects.MainPage;
import ru.yandex.praktikum.scooter.pageobjects.TrackPage;
import ru.yandex.praktikum.scooter.utils.BaseTest;

import java.util.Set;

public class LogosAndStatusTest extends BaseTest {

    @Test
    public void scooterLogoReturnsToHome() {
        MainPage main = new MainPage(driver).open(baseUrl);
        // Уходим в раздел FAQ, затем кликаем логотип и проверяем, что URL снова главная
        main.clickFaqQuestionByIndex(0);
        main.clickScooterLogo();
        Assert.assertTrue("Должны быть на главной странице", driver.getCurrentUrl().equals(baseUrl) || driver.getCurrentUrl().startsWith(baseUrl));
    }

    @Test
    public void yandexLogoOpensNewTab() {
        MainPage main = new MainPage(driver).open(baseUrl);
        String current = driver.getWindowHandle();
        Set<String> before = driver.getWindowHandles();
        main.clickYandexLogo();
        // ждём новую вкладку
        for (int i = 0; i < 20; i++) {
            if (driver.getWindowHandles().size() > before.size()) break;
            try { Thread.sleep(250); } catch (InterruptedException ignored) {}
        }
        Set<String> after = driver.getWindowHandles();
        after.removeAll(before);
        Assert.assertTrue("Должна открыться новая вкладка", after.size() == 1);
        String newTab = after.iterator().next();
        driver.switchTo().window(newTab);
        String url = driver.getCurrentUrl();
        Assert.assertTrue("URL должен содержать yandex", url.contains("yandex"));
        // закрываем доп. вкладку
        driver.close();
        driver.switchTo().window(current);
    }

    @Test
    public void wrongOrderNumberShowsNotFound() {
        TrackPage track = new TrackPage(driver);
        track.open(baseUrl);
        track.searchOrder("1234567890");
        Assert.assertTrue("Должно быть сообщение, что заказа нет", track.isNotFoundVisible());
    }
}
