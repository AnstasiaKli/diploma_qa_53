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
    @DisplayName("Error buy a tour declined card")
    void shouldErrorBuyATourDeclinedCard() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getDeclinedCard();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.waitNotificationError();
        assertEquals("DECLINED", DataBaseHelper.findPaymentStatus());
    }

    @Test
    @DisplayName("Error empty card number")
    void shouldErrorEmptyCardNumber() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getEmptyCardNumber();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.cardNumberFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error nonexistent card number")
    void shouldErrorNonexistentCardNumber() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getNonexistentCardNumber();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.waitNotificationError();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error 15 Symbols card number")
    void shouldError15SymbolsCardNumber() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.get15SymbolsCardNumber();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.cardNumberFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty month")
    void shouldErrorEmptyMonth() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getEmptyMonth();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongFormatMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error invalid Format Month")
    void shouldErrorInvalidFormatMonth() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getInvalidFormatMonth();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongFormatMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error nonexistent month")
    void shouldErrorNonexistentMonth() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getNonexistentMonth();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error previous month of this year")
    void shouldErrorPreviousMonthOfThisYear() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getPreviousMonthOfThisYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error month 00 of this year")
    void shouldErrorMonth00OfThisYear() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getMonth00OfThisYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error month 00 of the next year")
    void shouldErrorMonth00OfTheNextYear() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getMonth00OfTheNextYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty year")
    void shouldErrorEmptyYear() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getEmptyYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongFormatMonthErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error year 00")
    void shouldErrorYear00() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getYear00();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.previousYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error previous year")
    void shouldErrorPreviousYear() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getPreviousYearToDate();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.previousYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error year plus 6 years to date")
    void shouldErrorYearPlus6YearsToDate() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getYearPlus6YearsToDate();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongExpirationYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error invalid format year")
    void shouldErrorInvalidFormatYear() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getInvalidFormatYear();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.wrongFormatYearErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty owner")
    void shouldErrorEmptyOwner() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getEmptyOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.emptyOwnerErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error numbers in the field owner")
    void shouldErrorNumbersInTheFieldOwner() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getNumbersInTheFieldOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error special symbols in the field owner")
    void shouldErrorSpecialSymbolsInTheFieldOwner() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getSpecialSymbolsInTheFieldOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error over 60 symbols in the field owner")
    void shouldErrorOver60SymbolsInTheFieldOwner() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getOver60SymbolsInTheFieldOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error 1 symbol in the field owner")
    void shouldError1SymbolInTheFieldOwner() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.get1SymbolInTheFieldOwner();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.ownerFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error empty CVC")
    void shouldErrorEmptyCVC() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getEmptyCVC();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.cvcEmptyErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }

    @Test
    @DisplayName("Error CVC 2 symbols")
    void shouldErrorCVC2Symbols() {
        startPage.toCardPaymentPage();
        var cardPaymentPage = new CardPaymentPage();
        var cardInfo = DataGenerator.getCVC2Symbols();
        cardPaymentPage.fillCardData(cardInfo);
        cardPaymentPage.cvcFormatErrorVisible();
        assertEquals("0", DataBaseHelper.orderCount());
    }


}
