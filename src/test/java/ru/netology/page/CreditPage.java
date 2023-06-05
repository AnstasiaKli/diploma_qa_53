package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement heading = $$("h3").find(text("Кредит по данным карты"));
    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement notificationSuccess = $(".notification_status_ok ");
    private SelenideElement notificationError = $(".notification_status_error ");
    private SelenideElement cardNumberFormatError = $(byText("Неверный формат"));
    private SelenideElement wrongExpirationMonthError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement wrongFormatMonthError = $(byText("Неверный формат"));
    private SelenideElement previousYearError = $(byText("Истёк срок действия карты"));
    private SelenideElement wrongFormatYearError = $(byText("Неверный формат"));
    private SelenideElement wrongExpirationYearError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement emptyOwnerError = $(byText("Поле обязательно для заполнения"));
    private SelenideElement ownerFormatError = $(byText("Неверный формат"));
    private SelenideElement cvcFormatError = $(byText("Неверный формат"));
    private SelenideElement cvcEmptyError = $(byText("Неверный формат"));

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public void fillCardData(DataGenerator.CardInfo card) {
        cardNumber.setValue(card.getCardNumber());
        month.setValue(card.getMonth());
        year.setValue(card.getYear());
        owner.setValue(card.getOwner());
        cvc.setValue(card.getCvc());
        continueButton.click();
    }

    public void waitNotificationSuccess() {
        notificationSuccess.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationError() {
        notificationError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void cardNumberFormatErrorVisible() {
        cardNumberFormatError.shouldBe(visible, Duration.ofSeconds(15));
    }


    public void wrongFormatMonthErrorVisible() {
        wrongFormatMonthError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void wrongExpirationMonthErrorVisible() {
        wrongExpirationMonthError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void previousYearErrorVisible() {
        previousYearError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void wrongFormatYearErrorVisible() {
        wrongFormatYearError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void wrongExpirationYearErrorVisible() {
        wrongExpirationYearError.shouldBe(visible, Duration.ofSeconds(15));
    }


    public void emptyOwnerErrorVisible() {
        emptyOwnerError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void ownerFormatErrorVisible() {
        ownerFormatError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void cvcFormatErrorVisible() {
        cvcFormatError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void cvcEmptyErrorVisible() {
        cvcEmptyError.shouldBe(visible, Duration.ofSeconds(15));
    }

}
