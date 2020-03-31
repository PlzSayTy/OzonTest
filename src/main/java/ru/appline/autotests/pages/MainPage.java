package ru.appline.autotests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class MainPage extends BasePage {
    @FindBy(xpath = "//input[contains(@name, 'search')]")
    WebElement inputField;

    public void fullFillInput(String value) {
        fullfill(inputField, value);
    }

    @Override
    public WebElement getField(String name) throws Exception {
        return null;
    }
}
