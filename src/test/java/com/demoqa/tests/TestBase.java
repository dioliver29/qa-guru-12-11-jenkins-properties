package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.helpers.Attach;
import com.demoqa.pages.RegistrationFormPage;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static java.lang.String.format;

public class TestBase {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();

    String gender = "Other", subject = "Maths", hobby = "Music", state = "NCR", city = "Delhi", dayOfBirth = "14", monthOfBirth= "January", yearOfBirth = "1993";
    String  firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            userNumber = faker.number().digits(10),
            currentAddress = faker.rickAndMorty().quote();

    String expectedFullName = format("%s %s", firstName, lastName);
    String expectedDateOfBirth = format("%s %s", dayOfBirth, monthOfBirth,"%s", yearOfBirth);
    String expectedStateAndCity = format("%s %s", state, city);


    @BeforeAll
    static void setUp() {
        // Configuration.holdBrowserOpen = true;
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1600x900";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
    }
}
