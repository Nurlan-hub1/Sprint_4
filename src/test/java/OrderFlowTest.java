package ru.yandex.praktikum.scooter.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.scooter.pageobjects.MainPage;
import ru.yandex.praktikum.scooter.pageobjects.OrderPage;
import ru.yandex.praktikum.scooter.utils.BaseTest;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderFlowTest extends BaseTest {

    @Parameterized.Parameters(name = "entry={0}, name={1} {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // entry: top/bottom; user data; rent period; color
                {"top", "Иван", "Иванов", "Москва, Тверская, 1", "Тверская", "+79995553322", "25.09.2025", "двое суток", "black", "Позвоните за 30 мин"},
                {"bottom", "Мария", "Петрова", "Санкт-Петербург, Невский пр., 10", "Невский", "+79995550011", "26.09.2025", "сутки", "grey", "Оставьте у консьержа"}
        });
    }

    @Parameterized.Parameter(0) public String entryPoint;
    @Parameterized.Parameter(1) public String firstName;
    @Parameterized.Parameter(2) public String lastName;
    @Parameterized.Parameter(3) public String address;
    @Parameterized.Parameter(4) public String metro;
    @Parameterized.Parameter(5) public String phone;
    @Parameterized.Parameter(6) public String date;
    @Parameterized.Parameter(7) public String rentPeriod;
    @Parameterized.Parameter(8) public String color;
    @Parameterized.Parameter(9) public String comment;

    @Test
    public void positiveOrderFlow() {
        MainPage main = new MainPage(driver).open(baseUrl);
        if ("top".equalsIgnoreCase(entryPoint)) {
            main.clickTopOrderButton();
        } else {
            main.clickBottomOrderButton();
        }

        OrderPage order = new OrderPage(driver);
        order.fillStepOne(firstName, lastName, address, metro, phone);
        order.fillStepTwo(date, rentPeriod, color, comment);
        order.confirmOrder();

        // Известный баг: в Chrome подтверждение может не появиться.
        // Если нужен стабильный прогон — запускайте в Firefox: -Dbrowser=firefox
        org.junit.Assert.assertTrue("Должно появиться сообщение об успешном создании заказа", order.isOrderSuccessVisible());
    }
}
