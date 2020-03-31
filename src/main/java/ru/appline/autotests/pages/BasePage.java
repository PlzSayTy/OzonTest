package ru.appline.autotests.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.autotests.annotations.FieldName;
import ru.appline.autotests.steps.BaseSteps;

import java.lang.reflect.Field;
import java.util.*;

import static ru.appline.autotests.pages.ResultPage.map;

public abstract class BasePage {
    WebDriver driver;
    Actions builder;
    Wait<WebDriver> wait;
    JavascriptExecutor executor;
    Select select;
    Map map2;
    List<WebElement> list;
    List<WebElement> list2;

    public BasePage() {
        driver = BaseSteps.getDriver();
        PageFactory.initElements(BaseSteps.getDriver(), this);
        wait = new WebDriverWait(driver, 10, 1000);
        builder = new Actions(driver);
        executor = (JavascriptExecutor) driver;
    }

    public void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void fullfill(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        clickOnElement(element);
        element.clear();
        //element.sendKeys("");
        element.sendKeys(value + "\n");
    }

    public void fullfillWithJs(WebElement element, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        clickOnElement(element);
        element.clear();
        executor.executeScript("arguments[0].value = '" + value + "';", element);
    }

    public void fullfillWithActionBuilder(WebElement element, String value) {
        waitUntilVisible(element);
        waitUntilClickable(element);
        builder.moveToElement(element).click().build().perform();
        executor.executeScript("arguments[0].value='';", element);
        element.sendKeys(value);
        element.sendKeys(Keys.ENTER);
    }

    public void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public WebElement getField(String name, String className) throws Exception {
        Class aClass = Class.forName(className);
        List<Field> fields = Arrays.asList(aClass.getFields());
        for (Field field : fields) {
            if (field.getAnnotation(FieldName.class).name().equalsIgnoreCase(name)) {
                return driver.findElement(By.xpath(field.getAnnotation(FindBy.class).xpath()));
            }
        }
        Assert.fail("Нет такого элемента \"" + name + " \"");
        return null;
    }

    public void jsClick(WebElement element) {
        builder.moveToElement(element).perform();
        executor.executeScript("arguments[0].click();", element);
    }

    public void jsClick(String name) throws Exception {
        Thread.sleep(1000);
        WebElement element = getField(name);
        jsClick(element);
    }

    public abstract WebElement getField(String name) throws Exception;
}
