package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    private By headOrder = By.xpath("//div[text()='Для кого самокат']");

    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    private final String metroStationMenu = "//div[text()='%s']";
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");

    private By dateField = By.xpath("//div[@class='react-datepicker-wrapper']/div/input");
    private By timeField = By.className("Dropdown-placeholder");
    private final String timeDropDown = "//div[text()='%s']";
    private By blackCheckBox = By.id("black");
    private By greyCheckBox = By.id("grey");
    private By commentField = By.xpath("//div[@class='Input_InputContainer__3NykH']/input[contains(@class,'Input_Responsible')]");
    private By orderDown = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");

    private By yesButton = By.xpath("//button[text()='Да']");
    private By orderPass = By.xpath("//div[text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setMetroStation(String metroStation) {
        driver.findElement(metroStationField).click();
        WebElement stationMetroElement = driver.findElement(By.xpath(String.format(metroStationMenu, metroStation)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", stationMetroElement);
        stationMetroElement.click();
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void setData(String data) {
        driver.findElement(dateField).sendKeys(data, Keys.ENTER);
    }

    public void setTime(String time) {
        driver.findElement(timeField).click();
        driver.findElement(By.xpath(String.format(timeDropDown, time))).click();
    }

    public void setColor(String color) {
        if (color.equals("чёрный жемчуг")) {
            driver.findElement(blackCheckBox).click();
        } else if (color.equals("серая безысходность")) {
            driver.findElement(greyCheckBox).click();
        }
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderDown() {
        driver.findElement(orderDown).click();
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public void fillingFormForWhom(String name, String surname, String address, String metroStation, String phone) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStation(metroStation);
        setPhone(phone);
        clickNextButton();
    }

    public void fillingFormAboutRent(String data, String time, String color, String comment) {
        setData(data);
        setTime(time);
        setColor(color);
        setComment(comment);
        clickOrderDown();
        clickYesButton();
    }

    // Проверка наличия элемента "Заказ оформлен" на всплывающем окне
    public boolean findElementOrderPass() {
        WebElement element = driver.findElement(orderPass);
        if (element != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkOpenOrderPage() {
        WebElement element = driver.findElement(headOrder);
        if (element != null) {
            return true;
        } else {
            return false;
        }
    }
    // Явное ожидание видимости элемента
    private WebElement waitVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void setFirstName(String firstName) {
        waitVisible(nameField).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        waitVisible(surnameField).sendKeys(lastName);
    }

    

    public void selectMetro(String metroText) {
        WebElement metro = waitVisible(metroStationField);
        metro.click();
        By option = By.xpath(String.format(metroStationMenu, metroText));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(option))
                .click();
    }

    

    // Объединённый шаг заполнения первого шага формы
    public void fillStepOne(String firstName, String lastName, String address, String metro, String phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        selectMetro(metro);
        setPhone(phone);
        clickNextButton();
    }

}
