package com.demoqa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.RegistrationFormPage;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class FormTests extends TestBase{

    @Test
    @Owner("stikheeva")
    @DisplayName("Successful fill registration test")
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
        step("Проверка текста в заголовке формы" , () ->
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form")));
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
