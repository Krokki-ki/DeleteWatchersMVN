package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/// Для запуска .jar из CMD надо использовать
/// cd /D D:\Java Projects\DeleteWatchersMVN\out\artifacts\DeleteWatchersMVN_jar
/// java -jar DeleteWatchersMVN.jar

public class Main {

    public static void main(String[] args) {
        // Проверка режима работы
        CheckWorkSpace checking = new CheckWorkSpace();
        checking.checkWorkSpace();

        // Укажите путь к ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");

        // Выбор способа авторизации
        ChoozeAuthentification choose = new ChoozeAuthentification();
        String[] authData = choose.choozeAuthMethod();

        // Объявляем переменные вне блока if
        String myLogin, myPassword, jiraURL, jiraFilter = null;

        // Проверяем, что authData не null
        if (authData != null && authData.length == 4) {
            myLogin = authData[0];
            myPassword = authData[1];
            jiraURL = authData[2];
            jiraFilter = authData[3];
        } else {
            System.out.println("Ошибка: данные авторизации не были получены.");
            return; // Завершаем программу, если данные не были получены
        }

        MaskingOutput maskOutput = new MaskingOutput();
        System.out.println("Логин: " + maskOutput.maskLogin(myLogin));
        System.out.println("Пароль: " + maskOutput.maskPassword(myPassword));
        System.out.println("URL Jira: " + authData[2]);
        System.out.println("Фильтр Jira: " + authData[3]);


        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();        // задаём параметры открывающегося окна браузера (full screen)
        Waiting.waitUntil(2000);

        driver.get(jiraURL);
        Waiting.waitUntil(1000);

        WebElement skipAddSettings = driver.findElement(By.xpath("//*[@id=\"details-button\"]"));
        skipAddSettings.click();
        Waiting.waitUntil(1000);
        WebElement goToSite = driver.findElement(By.xpath("//*[@id=\"proceed-link\"]"));
        goToSite.click();
        Waiting.waitUntil(2000);

        //  передаём значения ранее считанных строк в поля для логина и пароля
        driver.findElement(By.xpath("//*[@id=\"login-form-username\"]")).sendKeys(myLogin);
        driver.findElement(By.xpath("//*[@id=\"login-form-password\"]")).sendKeys(myPassword);
        WebElement logInButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        logInButton.click();
        Waiting.waitUntil(2000);
        driver.get(jiraFilter);
        Waiting.waitUntil(2000);
        System.out.println("Конец программы на этапе открытия фильтра Jira");


        WebElement dropDownButton = driver.findElement(By.xpath("//*[@id=\"layout-switcher-button\"]"));
        dropDownButton.click();
        WebElement detailViewButton = driver.findElement(By.xpath("//*[@id=\"layout-switcher-options\"]/div/ul/li[1]/a"));
        detailViewButton.click();
        Waiting.waitUntil(2000);
        System.out.println("Конец программы на этапе смены представления");

        //driver.quit();
    }
}