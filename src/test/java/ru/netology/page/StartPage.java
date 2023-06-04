package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    private SelenideElement heading = $$("h2").find(text("Путешествие дня"));
    private SelenideElement buyButton = $$("button").find(exactText("Купить"));
    private SelenideElement takeCreditButton = $$("button").find(exactText("Купить в кредит"));

    public StartPage() {
        heading.shouldBe(visible);
    }

    public CardPaymentPage toCardPaymentPage() {
        buyButton.click();
        return new CardPaymentPage();
    }

    public CreditPage toCreditPage() {
        takeCreditButton.click();
        return new CreditPage();
    }
}
