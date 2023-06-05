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
        public static String url = System.getProperty("sut.url");

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
        var cardInfo= DataGenerator.getApprovedCard();
        var creditPage = new CreditPage();
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

    @Test
    @DisplayName("Error empty card number on credit")
    void shouldErrorEmptyCardNumberOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getEmptyCardNumber();
        creditPage.fillCardData(cardInfo);
        creditPage.cardNumberFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error nonexistent card number on credit")
    void shouldErrorNonexistentCardNumberOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getNonexistentCardNumber();
        creditPage.fillCardData(cardInfo);
        creditPage.waitNotificationError();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error 15 Symbols card number on credit")
    void shouldError15SymbolsCardNumberOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.get15SymbolsCardNumber();
        creditPage.fillCardData(cardInfo);
        creditPage.cardNumberFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty month on credit")
    void shouldErrorEmptyMonthOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getEmptyMonth();
        creditPage.fillCardData(cardInfo);
        creditPage.wrongFormatMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error invalid Format Month on credit")
    void shouldErrorInvalidFormatMonthOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getInvalidFormatMonth();
        creditPage.fillCardData(cardInfo);
        creditPage.wrongFormatMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error nonexistent month on credit")
    void shouldErrorNonexistentMonthOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getNonexistentMonth();
        creditPage.fillCardData(cardInfo);
        creditPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error previous month of this year on credit")
    void shouldErrorPreviousMonthOfThisYearOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getPreviousMonthOfThisYear();
        creditPage.fillCardData(cardInfo);
        creditPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error month 00 of this year on credit")
    void shouldErrorMonth00OfThisYearOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getMonth00OfThisYear();
        creditPage.fillCardData(cardInfo);
        creditPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error month 00 of the next year on credit")
    void shouldErrorMonth00OfTheNextYearOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getMonth00OfTheNextYear();
        creditPage.fillCardData(cardInfo);
        creditPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty year on credit")
    void shouldErrorEmptyYearOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getEmptyYear();
        creditPage.fillCardData(cardInfo);
        creditPage.wrongFormatMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error year 00 on credit")
    void shouldErrorYear00OnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getYear00();
        creditPage.fillCardData(cardInfo);
        creditPage.previousYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error previous year on credit")
    void shouldErrorPreviousYearOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getPreviousYearToDate();
        creditPage.fillCardData(cardInfo);
        creditPage.previousYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error year plus 6 years to date on credit")
    void shouldErrorYearPlus6YearsToDateOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getYearPlus6YearsToDate();
        creditPage.fillCardData(cardInfo);
        creditPage.wrongExpirationYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error invalid format year on credit")
    void shouldErrorInvalidFormatYearOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getInvalidFormatYear();
        creditPage.fillCardData(cardInfo);
        creditPage.wrongFormatYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty owner on credit")
    void shouldErrorEmptyOwnerOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getEmptyOwner();
        creditPage.fillCardData(cardInfo);
        creditPage.emptyOwnerErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error numbers in the field owner on credit")
    void shouldErrorNumbersInTheFieldOwnerOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getNumbersInTheFieldOwner();
        creditPage.fillCardData(cardInfo);
        creditPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error special symbols in the field owner on credit")
    void shouldErrorSpecialSymbolsInTheFieldOwnerOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getSpecialSymbolsInTheFieldOwner();
        creditPage.fillCardData(cardInfo);
        creditPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error over 60 symbols in the field owner on credit")
    void shouldErrorOver60SymbolsInTheFieldOwnerOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getOver60SymbolsInTheFieldOwner();
        creditPage.fillCardData(cardInfo);
        creditPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error 1 symbol in the field owner on credit")
    void shouldError1SymbolInTheFieldOwnerOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.get1SymbolInTheFieldOwner();
        creditPage.fillCardData(cardInfo);
        creditPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty CVC on credit")
    void shouldErrorEmptyCVCOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getEmptyCVC();
        creditPage.fillCardData(cardInfo);
        creditPage.cvcEmptyErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error CVC 2 symbols on credit")
    void shouldErrorCVC2SymbolsOnCredit() {
        startPage.toCreditPage();
        var creditPage = new CreditPage();
        var cardInfo= DataGenerator.getCVC2Symbols();
        creditPage.fillCardData(cardInfo);
        creditPage.cvcFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }


}
