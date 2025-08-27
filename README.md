# Проект автотестов: Яндекс.Самокат (Sprint 4)

Набор UI-автотестов для учебного сервиса «Яндекс.Самокат». Стек: **Java 11**, **JUnit 4.13.2**, **Selenium 4.17.0**, **Maven**.

## Технологии

| Компонент | Версия |
|---|---|
| Java | 11 |
| JUnit | 4.13.2 |
| Selenium | 4.17.0 |
| Maven | 3.9.x |
| WebDriverManager | 5.6.3 |

## Структура
- `src/main/java/ru/yandex/praktikum/scooter/pages` — Page Object-ы (MainPage, OrderPage)  
- `src/test/java/ru/yandex/praktikum/scooter` — тесты (`QuestionsOpen`, `Order`)  
- `src/main/java/ru/yandex/praktikum/scooter/WebDriverFactory.java` — фабрика браузеров с неявным ожиданием

## Запуск

В корне проекта:

```
mvn clean test
```

Запуск конкретного теста:

```
mvn -Dtest=QuestionsOpen test
```

