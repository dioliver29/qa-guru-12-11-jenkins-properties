package com.demoqa.pages;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.demoqa.pages.components.CalendarComponent;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class RegistrationFormPage {
    CalendarComponent calendar = new CalendarComponent();


    // locators
    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderChoice =  $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectChoice = $("#subjectsInput"),
            hobbiesSelect = $("#hobbiesWrapper"),
            selectPicture = $("#uploadPicture"),
            userAddressInput = $("#currentAddress"),
            listOfStates = $(byText("Select State")),
            listOfCities = $(byText("Select City"));

    // actions

    @Step("Открываем страницу регистрации")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }
    @Step("Вводим имя")
    public RegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }
    @Step("Вводим фамилию")
    public RegistrationFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Вводим e-mail")
    public RegistrationFormPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    @Step("Выбираем гендер")
    public RegistrationFormPage setGender(String userGender) {
        genderChoice.$(byText(userGender)).click();
        return this;
    }

    @Step("Вводим номер телефона")
    public RegistrationFormPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    @Step("Вводим дату рождения")
    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Выбираем предмет")
    public RegistrationFormPage setSubject(String subject) {
        subjectChoice.click();
        subjectChoice.setValue("m");
        $(byText(subject)).click();
        return this;
    }

    @Step("Выбираем хобби")
    public RegistrationFormPage setHobby(String hobby) {
        hobbiesSelect.$(byText(hobby)).click();
        return this;
    }

    @Step("Загружаем фото")
    public RegistrationFormPage setPicture() {
        selectPicture.uploadFromClasspath("file.png");
        return this;
    }

    @Step("Вводим адрес")
    public RegistrationFormPage setAddress(String userAddress) {
        userAddressInput.setValue(userAddress);
        return this;
    }

    @Step("Выбираем штат")
    public RegistrationFormPage setState(String state) {
        listOfStates.click();
        $(byText(state)).click();
        return this;
    }

    @Step("Выбираем город")
    public RegistrationFormPage setCity(String city) {
        listOfCities.click();
        $(byText(city)).click();
      //  attachScreenshot();
        return this;
    }

    @Step("{registrationFormText} = {registrationFormValue}")
    public RegistrationFormPage checkResult(String registrationFormText, String registrationFormValue) {
        $(".table-responsive").$(byText(registrationFormText))
                .parent().shouldHave(text(registrationFormValue));
        return this;
    }

    /*@Attachment(value = "Скриншот теста", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver())
                .getScreenshotAs(OutputType.BYTES);
    }*/

}
