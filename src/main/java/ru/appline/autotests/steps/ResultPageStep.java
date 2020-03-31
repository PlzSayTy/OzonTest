package ru.appline.autotests.steps;

import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import ru.appline.autotests.pages.ResultPage;

import java.util.Map;

public class ResultPageStep {
    ResultPage resultPage = new ResultPage();

    @When("^заполняются поля ввода$")
    public void fullFill(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        dataMap.forEach((chosenInput, value) -> {
            resultPage.fullfillChosenInput(chosenInput, value);
        });
    }

    @When("отмечается чекбокс \"(.+)\"$")
    public void pressOnCheckBox(String checkBoxName) {
        resultPage.pressCheckBox(checkBoxName);
    }

    @When("^добавить в корзину$")
    public void addIntoBucket(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        dataMap.forEach((chosenInput, value) -> {
            resultPage.addIntoBucket(chosenInput, value);
        });
    }

    @When("перейти в \"(.+)\"$")
    public void goToPage(String name) {
        resultPage.goTo(name);
    }

    @When("отметить бренды \"(.+)\", \"(.+)\"$")
    public void selectBrands(String name1, String name2) {
        resultPage.selectBrands(name1);
        resultPage.selectBrands(name2);
    }

//    @When("отмечается чекбокс \"(.+)\"$")
//    public void
}
