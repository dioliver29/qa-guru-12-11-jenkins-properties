package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FormTests {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();

    String subject = "Maths", hobbie = "Music", state = "NCR", city = "Delhi";

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            userNumber = faker.number().digits(10),
            currentAddress = faker.rickAndMorty().quote();

   // String expectedFullName = format("%s %s", firstName, lastName);


    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1600x900";

    }


    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        registrationFormPage
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .setGender("Other")
                    .setUserNumber(userNumber)
                    .setBirthDate("14", "January", "1993")
                    .setSubject(subject)
                    .setHobbie(hobbie)
                    .setPicture()
                    .setAddress(currentAddress)
                    .setState(state)
                    .setCity(city);


        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $("#submit").click();

        //Asserts
        $(".table-responsive").shouldHave(text("Ivan Ivan Ivanov"), text("Ivan@ya.ru"), text("Other"), text("10 April,1990"), text("Music"), text("file.png"),text("smth"), text("NCR Delhi"));





    }

}
