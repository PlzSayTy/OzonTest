package ru.appline.autotests.steps;

import cucumber.api.java.en.When;
import ru.appline.autotests.pages.MainPage;

public class MainPageStep {
    MainPage mainPage = new MainPage();

    @When("Выполняется поиск по товару \"(.+)\"$")
    public void fullFillInputField(String value) {
        mainPage.fullFillInput(value);
    }
}
