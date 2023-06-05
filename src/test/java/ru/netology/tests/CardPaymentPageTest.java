package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataBaseHelper;
import ru.netology.data.DataGenerator;
import ru.netology.page.CardPaymentPage;
import ru.netology.page.CreditPage;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardPaymentPageTest {
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
    @DisplayName("Successful buy a tour approved card")
    void shouldSuccessfullyBuyATourApprovedCard() {
        startPage.toCardPaymentPage();
        var cardInfo = DataGenerator.getApprovedCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.waitNotificationSuccess();
        assertEquals("APPROVED", DataBaseHelper.findPaymentStatus());
    }

    @Test
    @DisplayName("Error buy a tour on credit declined card")
    void shouldErrorBuyATourOnCreditDeclinedCard() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getDeclinedCard();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.waitNotificationError();
        assertEquals("DECLINED", DataBaseHelper.findPaymentStatus());
    }

    @Test
    @DisplayName("Error empty card number on credit")
    void shouldErrorEmptyCardNumberOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getEmptyCardNumber();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.cardNumberFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error nonexistent card number on credit")
    void shouldErrorNonexistentCardNumberOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getNonexistentCardNumber();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.waitNotificationError();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error 15 Symbols card number on credit")
    void shouldError15SymbolsCardNumberOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.get15SymbolsCardNumber();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.cardNumberFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty month on credit")
    void shouldErrorEmptyMonthOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getEmptyMonth();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongFormatMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error invalid Format Month on credit")
    void shouldErrorInvalidFormatMonthOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getInvalidFormatMonth();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongFormatMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error nonexistent month on credit")
    void shouldErrorNonexistentMonthOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getNonexistentMonth();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error previous month of this year on credit")
    void shouldErrorPreviousMonthOfThisYearOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getPreviousMonthOfThisYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error month 00 of this year on credit")
    void shouldErrorMonth00OfThisYearOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getMonth00OfThisYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error month 00 of the next year on credit")
    void shouldErrorMonth00OfTheNextYearOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getMonth00OfTheNextYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty year on credit")
    void shouldErrorEmptyYearOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getEmptyYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongFormatMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error year 00 on credit")
    void shouldErrorYear00OnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getYear00();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.previousYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error previous year on credit")
    void shouldErrorPreviousYearOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getPreviousYearToDate();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.previousYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error year plus 6 years to date on credit")
    void shouldErrorYearPlus6YearsToDateOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getYearPlus6YearsToDate();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error invalid format year on credit")
    void shouldErrorInvalidFormatYearOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getInvalidFormatYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongFormatYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty owner on credit")
    void shouldErrorEmptyOwnerOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getEmptyOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.emptyOwnerErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error numbers in the field owner on credit")
    void shouldErrorNumbersInTheFieldOwnerOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getNumbersInTheFieldOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error special symbols in the field owner on credit")
    void shouldErrorSpecialSymbolsInTheFieldOwnerOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getSpecialSymbolsInTheFieldOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error over 60 symbols in the field owner on credit")
    void shouldErrorOver60SymbolsInTheFieldOwnerOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo= DataGenerator.getOver60SymbolsInTheFieldOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error 1 symbol in the field owner on credit")
    void shouldError1SymbolInTheFieldOwnerOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo= DataGenerator.get1SymbolInTheFieldOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty CVC on credit")
    void shouldErrorEmptyCVCOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo= DataGenerator.getEmptyCVC();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.cvcEmptyErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error CVC 2 symbols on credit")
    void shouldErrorCVC2SymbolsOnCredit() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo= DataGenerator.getCVC2Symbols();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.cvcFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }


}
