package ru.netology.tests;


import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataBaseHelper;
import ru.netology.data.DataGenerator;
import ru.netology.page.CreditPage;
import ru.netology.page.StartPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPageTest {
        StartPage startPage = open("http://localhost:8080", StartPage.class);

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void cleanupTablesDB() {
        DataBaseHelper.cleanupTablesDB();
    }

    @Test
    @DisplayName("Successful buy a tour on credit approved card")
    void shouldSuccessfullyBuyATourOnCreditApprovedCard() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getApprovedCard();
        creditPage.fillCardData(cardInfo);
        creditPage.waitNotificationSuccess();
        assertEquals("APPROVED", DataBaseHelper.findCreditRequestStatus());
    }

    @Test
    @DisplayName("Error buy a tour on credit declined card")
    void shouldErrorBuyATourOnCreditDeclinedCard() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getDeclinedCard();
        creditPage.fillCardData(cardInfo);
        creditPage.waitNotificationError();
        assertEquals("DECLINED", DataBaseHelper.findCreditRequestStatus());
    }




}
