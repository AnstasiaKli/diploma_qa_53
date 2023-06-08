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


    public static String generateMonth() {
        String month = LocalDate.now().plusMonths((int) Math.random() * 10).format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public static String generateYear(int shiftYear) {
        String year = LocalDate.now().plusYears(shiftYear).format(DateTimeFormatter.ofPattern("YY"));
        return year;
    }

    public static String generateName() {
        Faker faker = new Faker();
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateCVC() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }

    public static CardInfo getApprovedCard() {
        String month = generateMonth();
        String year = generateYear(1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getDeclinedCard() {
        String month = generateMonth();
        String year = generateYear(1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444442", month, year, owner, cvc);
    }

    public static CardInfo getEmptyCardNumber() {
        String month = generateMonth();
        String year = generateYear(1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("", month, year, owner, cvc);
    }

    public static CardInfo getNonexistentCardNumber() {
        String month = generateMonth();
        String year = generateYear(1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("5452456626262622", month, year, owner, cvc);
    }

    public static CardInfo get15SymbolsCardNumber() {
        Faker faker = new Faker();
        String number = faker.number().digits(15);
        String month = generateMonth();
        String year = generateYear(1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo(number, month, year, owner, cvc);
    }

    public static CardInfo getEmptyMonth() {
        String year = generateYear(1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", "", year, owner, cvc);
    }

    public static CardInfo getInvalidFormatMonth() {
        Faker faker = new Faker();
        String month = faker.number().digit();
        String year = generateYear(1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getNonexistentMonth() {
        Faker faker = new Faker();
        String month = String.valueOf(faker.number().numberBetween(13, 99));
        String year = generateYear(1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getPreviousMonthOfThisYear() {
        String month = LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern("MM"));
        String year = generateYear(0);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getMonth00OfThisYear() {
        String year = generateYear(0);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", "00", year, owner, cvc);
    }

    public static CardInfo getMonth00OfTheNextYear() {
        String year = generateYear(1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", "00", year, owner, cvc);
    }

    public static CardInfo getEmptyYear() {
        String month = generateMonth();
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, "", owner, cvc);
    }

    public static CardInfo getYear00() {
        String month = generateMonth();
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, "00", owner, cvc);
    }

    public static CardInfo getPreviousYearToDate() {
        String month = generateMonth();
        String year = generateYear(-1);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getYearPlus6YearsToDate() {
        String month = generateMonth();
        String year = generateYear(6);
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getInvalidFormatYear() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = faker.number().digit();
        String owner = generateName();
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getEmptyOwner() {
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, "", cvc);
    }

    public static CardInfo getNumbersInTheFieldOwner() {
        Faker faker = new Faker();
        String owner = faker.name().firstName() + " " + faker.number().digits(5);
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getSpecialSymbolsInTheFieldOwner() {
        Faker faker = new Faker();
        String owner = faker.name().firstName() + " %$ * &";
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getOver60SymbolsInTheFieldOwner() {
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, "okpfkor*;g,h;'fpp;f;ff;ee;ff;kkgkgddnbvbvbvbvbvbbvbvbvvbvbvbvbvbvbvbvbvb", cvc);
    }

    public static CardInfo get1SymbolInTheFieldOwner() {
        String month = generateMonth();
        String year = generateYear(1);
        String cvc = generateCVC();
        return new CardInfo("4444444444444441", month, year, "a", cvc);
    }

    public static CardInfo getEmptyCVC() {
        String month = generateMonth();
        String year = generateYear(1);
        String owner = generateName();
        return new CardInfo("4444444444444441", month, year, owner, "");
    }

    public static CardInfo getCVC2Symbols() {
        Faker faker = new Faker();
        String month = generateMonth();
        String year = generateYear(1);
        String owner = generateName();
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