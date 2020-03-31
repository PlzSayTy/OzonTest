package ru.appline.autotests.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.autotests.annotations.FieldName;

import java.util.List;
import java.util.Map;

import static ru.appline.autotests.pages.ResultPage.map;

public class BasketPage extends BasePage {
    @FieldName(name = "currentProductName")
    @FindBy(xpath = "//span[contains(text(), 'iPhone')]")
    List<WebElement> currentProductsName;
    @FindBy(xpath = "//div[text() = 'Удалить']")
    @FieldName(name = "удалить выбранные")
    public WebElement removeButton;
    @FindBy(xpath = "//div[contains(@class, 'a9i5')]")
    List<WebElement> currentProductPrice;
    Map<String, String> map2;

    public void assertProducts() throws Exception {
        Thread.sleep(3000);
        waitUntilVisible(driver.findElement(By.xpath("//span[contains(text(), 'iPhone')]")));
        System.out.println(currentProductsName.get(0).getText());
        for (int i = 0; i <= map.size(); i++) {
            System.out.println(currentProductsName.get(i).getText());
            Assert.assertTrue(map.containsKey(currentProductsName.get(i).getText()));
        }
    }

    public void removeItems(String name) throws Exception {
        clickToRemove(name);
        clickToRemove(removeButton);
    }

    @Override
    public WebElement getField(String name1) throws Exception {
        return getField(name1, "ru.appline.autotests.pages.BasketPage");
    }
}
