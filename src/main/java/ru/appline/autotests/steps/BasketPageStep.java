package ru.appline.autotests.steps;

import cucumber.api.java.en.When;
import cucumber.api.java.ru.Когда;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebElement;
import ru.appline.autotests.pages.BasePage;
import ru.appline.autotests.pages.BasketPage;

import java.io.IOException;
import java.util.Map;

import static ru.appline.autotests.pages.ResultPage.map;
import static ru.appline.autotests.steps.BaseSteps.pageObject;

public class BasketPageStep {
    BasketPage basketPage = new BasketPage();

    @When("страница \"(.*)\" загружена")
    public void pageLoaded(String name) throws Exception {
        Class clazz = Class.forName("ru.appline.autotests.pages." + name);
        pageObject = (BasePage) clazz.newInstance();
    }

    @When("Проверяю добавленные товары")
    public void assertBasket() throws Exception {
        basketPage.assertProducts();
    }

    //
    //Тут использую рефлексию
    @When("^выполнено нажатие на кнопку \"(.+)\"$")
    public void удалениеТоваров(String name) throws Exception {
        basketPage.removeItems(name);
    }
    //Тут пытаюсь приатачить мапу с покупками
    @When("^Показать купленные$")
    public void showBought() {
        show();
    }

    @Attachment
    public Map show() {
        return map;
    }
}
