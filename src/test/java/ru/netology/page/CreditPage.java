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
    private SelenideElement emptyCardNumberError = $(byText("Неверный формат"));
    private SelenideElement wrongMonthError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement emptyMonthError = $(byText("Неверный формат"));
    private SelenideElement previousYearError = $(byText("Истёк срок действия карты"));
    private SelenideElement emptyYearError = $(byText("Неверный формат"));
    private SelenideElement wrongYearError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement emptyOwnerError = $(byText("Поле обязательно для заполнения"));
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

    public boolean cardNumberFormatErrorVisible() {
        return cardNumberFormatError.isDisplayed();
    }

    public boolean emptyCardNumberErrorVisible() {
        return emptyCardNumberError.isDisplayed();
    }

    public boolean wrongMonthErrorVisible() {
        return wrongMonthError.isDisplayed();
    }

    public boolean emptyMonthErrorVisible() {
        return emptyMonthError.isDisplayed();
    }

    public boolean previousYearErrorVisible() {
        return previousYearError.isDisplayed();
    }

    public boolean emptyYearErrorVisible() {
        return emptyYearError.isDisplayed();
    }

    public boolean wrongYearErrorVisible() {
        return wrongYearError.isDisplayed();
    }

    public boolean emptyOwnerErrorVisible() {
        return emptyOwnerError.isDisplayed();
    }

    public boolean cvcFormatErrorVisible() {
        return cvcFormatError.isDisplayed();
    }

    public boolean cvcEmptyErrorVisible() {
        return cvcEmptyError.isDisplayed();
    }

}
