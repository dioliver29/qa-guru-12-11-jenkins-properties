package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.RegistrationFormPage;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

public class FormTests {
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
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1600x900";

    }


    @Test
    void fillFormTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        RegistrationFormPage steps = new RegistrationFormPage();


        steps.openPage();
        steps.setFirstName(firstName);
        steps.setLastName(lastName);
        steps.setUserEmail(userEmail);
        steps.setGender(gender);
        steps.setUserNumber(userNumber);
        steps.setBirthDate(dayOfBirth, monthOfBirth, yearOfBirth);
        steps.setSubject(subject);
        steps.setHobby(hobby);
        steps.setPicture();
        steps.setAddress(currentAddress);
        steps.setState(state);
        steps.setCity(city);


        step("Проверка клика на заполненную форму", () -> $("#submit").click());


        //Asserts
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        this.registrationFormPage
                .checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", expectedDateOfBirth)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", "file.png")
                .checkResult("Address", currentAddress)
                .checkResult("State and City", expectedStateAndCity);




    }

}
