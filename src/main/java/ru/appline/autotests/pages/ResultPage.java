package ru.appline.autotests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultPage extends BasePage {
    @FindBy(xpath = "//input[contains(@class, 'ui-av9 ui-av4')]")
    List<WebElement> inputsList;
    //    @FindBy(xpath = "//input[contains(@class, 'ui-aj')]")
//    List<WebElement> checkBoxList;
    @FindBy(xpath = "//div[contains(text(), 'В корзину')]")
    List<WebElement> listOfProducts;
    @FindBy(xpath = "//a[contains(text(), 'iPhone')]")
    List<WebElement> productNames;
    @FindBy(xpath = "//span[contains(text(), '\u20BD')]")
    List<WebElement> productPrice;
    @FindBy(xpath = "//div[contains(@class, 'b9h6 b1m5')]")
    WebElement toClick;
    @FindBy(xpath = "//span[contains(text(), 'Корзина')]")
    WebElement basketButton;
    public static Map<String, Integer> map;

    public void fullfillChosenInput(String input, String value) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@aria-label, 'Закрыть сообщение')]"))).click();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@aria-label, 'Закрыть сообщение')]"))).click();
            } catch (Exception e1) {

            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (input) {
            case "ДО":
                WebElement element = inputsList.stream()
                        .skip(1)
                        .findFirst().orElseThrow(() -> new RuntimeException("нет такой позиции"));
                fullfillWithActionBuilder(element, value + "\n");
        }
    }

    public void pressCheckBox(String checkBoxName) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (checkBoxName) {
            case "Высокий рейтинг":
//                WebElement element1 = checkBoxList.stream()
//                        .skip(47)
//                        .findFirst().orElseThrow(()-> new RuntimeException("нет такой позиции"));
                builder.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'" + checkBoxName + "')]"))).perform();
                executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(),'" + checkBoxName + "')]")));
                wait.until(driver1 -> ((JavascriptExecutor) driver1)).executeScript("return document.readyState").equals("complete");
            case "NFC":
//                WebElement element2 = checkBoxList.stream()
//                        .skip(50)
//                        .findFirst().orElseThrow(()-> new RuntimeException("нет такой позиции"));
                builder.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'" + checkBoxName + "')]"))).perform();
                executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(),'" + checkBoxName + "')]")));
                wait.until(driver1 -> ((JavascriptExecutor) driver1)).executeScript("return document.readyState").equals("complete");
        }
    }

    public void addIntoBucket(String cuantity, String position) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'В корзину')]")));
        String s;
        map = new HashMap<>();
        switch (position) {
            case "четных":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'В корзину')]")));
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (cuantity) {
                    case "все":
                        for (int i = 1; i < listOfProducts.size(); i += 2) {
                            executor.executeScript("arguments[0].scrollIntoView(true);", listOfProducts.get(i));
                            clickOnElement(listOfProducts.get((i)));
                            s = productPrice.get(i).getText().replace("\u20BD", "").replace(" ", "");
                            map.put(productNames.get(i).getText(), Integer.parseInt(s));
                        }
                    default:
                        for (int i = 1; i < 100; i += 2) {
                            executor.executeScript("arguments[0].scrollIntoView(true);", listOfProducts.get(i));
                            clickOnElement(listOfProducts.get((i)));
                            s = productPrice.get(i).getText().replace("\u20BD", "").replace(" ", "");
                            map.put(productNames.get(i).getText(), Integer.parseInt(s));
                            if (map.size() == Integer.parseInt(cuantity)) {
                                break;
                            }
                        }
                }
                System.out.println(map);
        }

    }

    public void goTo(String way) {
        switch (way) {
            case "Basket page":
                builder.moveToElement(basketButton).click().perform();
        }
    }

    public void selectBrands(String name) {
        try {
            builder.moveToElement((driver.findElement(By.xpath("//span[contains(text(), 'Посмотреть все')]")))).perform();
            clickToRemove(driver.findElement(By.xpath("//span[contains(text(), 'Посмотреть все')]")));
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        builder.moveToElement(driver.findElement(By.xpath("//span[contains(text(), '" + name + "')]"))).click().perform();
    }

    @Override
    public WebElement getField(String name) throws Exception {
        return null;
    }
}
