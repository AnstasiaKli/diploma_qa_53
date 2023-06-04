package ru.netology.data;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import com.github.javafaker.Faker;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    private static void sendRequest(CardInfo card) {
        Gson gson = new Gson();
        String jsonCard = gson.toJson(card);
        given()
                .spec(requestSpec)
                .body(jsonCard)
                .when()
                .post("/api/system/cards")
                .then()
                .statusCode(200);
    }
    public static CardInfo getApprovedCard() {
        return new CardInfo("4444444444444441", "12", "23", "Anna Ivanova", "452");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444444444444442", "12", "23", "Anna Ivanova", "678");
    }

    public static CardInfo getEmptyCardNumber() {
        return new CardInfo("", "12", "23", "Anna Ivanova", "678");
    }

    public static String generateMonth() {
       String month = LocalDate.now().plusMonths((int)Math.random() * 10).format(DateTimeFormatter.ofPattern("MM"));
       return month;
    }

    public static String generateYear(int shiftYear) {
        String year = LocalDate.now().plusYears(shiftYear).format(DateTimeFormatter.ofPattern("YY"));
        return year;
    }

    public static CardInfo getNonexistentCardNumber() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = generateYear(1);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("5452456626262622", month, year, owner, cvc);
    }

    public static CardInfo get15SymbolsCardNumber() {
        Faker faker = new Faker();
        String number = faker.number().digits(15);
        String month = generateMonth();
        String year = generateYear(1);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo(number, month, year, owner, cvc);
    }

    public static CardInfo getEmptyMonth() {
        Faker faker = new Faker();
        String year = generateYear(1);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", "", year, owner, cvc);
    }

    public static CardInfo getInvalidFormatMonth() {
        Faker faker = new Faker();
        String month = faker.number().digit();
        String year = generateYear(1);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getNonexistentMonth() {
        Faker faker = new Faker();
        String year = generateYear(1);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", "99", year, owner, cvc);
    }

    public static CardInfo getPreviousMonthOfThisYear() {
        Faker faker = new Faker();
        String year = generateYear(0);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", "04", year, owner, cvc);
    }

    public static CardInfo getMonth00() {
        Faker faker = new Faker();
        String year = generateYear(1);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", "00", year, owner, cvc);
    }

    public static CardInfo getEmptyYear() {
        Faker faker = new Faker();
        String month = generateMonth();
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, "", owner, cvc);
    }

    public static CardInfo getYear00() {
        Faker faker = new Faker();
        String month = generateMonth();
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, "00", owner, cvc);
    }

    public static CardInfo getPreviousYearToDate() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = generateYear(-1);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getYearPlus6YearsToDate() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = generateYear(6);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getEmptyOwner() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, "", cvc);
    }

    public static CardInfo getNumbersInTheFieldOwner() {
        Faker faker = new Faker();
        String owner =faker.number().digit();
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getSpecialSymbolsInTheFieldOwner() {
        Faker faker = new Faker();
        String owner = faker.name().firstName() + " %$ * &";
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getOver60SymbolsInTheFieldOwner() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, "okpfkor*;g,h;'fpp;f;ff;ee;ff;kkgkgddnbvbvbvbvbvbbvbvbvvbvbvbvbvbvbvbvbvb", cvc);
    }

    public static CardInfo get1SymbolInTheFieldOwner() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, "a", cvc);
    }

    public static CardInfo getEmptyCVC() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = generateYear(1);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        return new CardInfo("4444444444444441", month, year, owner, "");
    }

    public static CardInfo getCVC2Symbols() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = generateYear(1);
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String cvc = faker.number().digits(2);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
}

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }
}