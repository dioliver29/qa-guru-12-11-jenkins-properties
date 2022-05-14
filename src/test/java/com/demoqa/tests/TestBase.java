package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.config.CredentialsConfig;
import com.demoqa.helpers.Attach;
import com.demoqa.pages.RegistrationFormPage;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

@Tag("systemProperties")
public class TestBase {

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    static String login = config.login();
    static String password = config.password();

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

    //static String remoteSelenideUrl = System.getProperty("remoteSelenideUrl");
    //static String baseUrl = System.getProperty("baseUrl");


    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = System.getProperty("baseUrl");
        //Configuration.baseUrl = "https://demoqa.com";
       /*
        Configuration.remote = "https://user1:1234@" + remoteSelenideUrl + "/wd/hub";
        Configuration.baseUrl = "https://demoqa.com";
       */
        Configuration.remote = String.format("https://%s:%s"+"@"+ System.getProperty("remoteSelenideUrl") +"/wd/hub", login, password);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
