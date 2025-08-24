package ru.yandex.praktikum.scooter.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.scooter.pageobjects.MainPage;
import ru.yandex.praktikum.scooter.utils.BaseTest;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {

    @Parameterized.Parameters(name = "FAQ item index = {0}")
    public static Collection<Object[]> data() {
        // На стенде обычно 8 вопросов (0..7)
        return Arrays.asList(new Object[][]{
                {0},{1},{2},{3},{4},{5},{6},{7}
        });
    }

    @Parameterized.Parameter
    public int index;

    @Test
    public void faqItemOpensAndHasText() {
        MainPage main = new MainPage(driver).open(baseUrl);
        main.clickFaqQuestionByIndex(index);
        String text = main.getFaqAnswerTextByIndex(index);
        org.junit.Assert.assertNotNull("Текст ответа должен отображаться", text);
        org.junit.Assert.assertTrue("Текст ответа должен быть непустым", text.trim().length() > 0);
    }
}
