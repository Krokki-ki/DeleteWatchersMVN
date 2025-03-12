package browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ConfigLoader;
import utils.Waiting;
import auth.AuthData;


/// Класс для управления браузером с использованием Selenium WebDriver.
/// Отвечает за инициализацию браузера, навигацию по страницам и взаимодействие с элементами.

public class BrowserManager {
    public static final String infoOpenedJiraFilter = "Конец программы на этапе открытия фильтра Jira\n";
    public static final String infoChangeView = "Конец программы на этапе смены представления\n";
    private WebDriver driver;

    // Конструктор класса BrowserManager - Инициализирует WebDriver и настраивает браузер
    public BrowserManager() {
        // Загружаем путь к ChromeDriver из файла конфигурации application.properties
        String chromeDriverPath = ConfigLoader.getProperty("chromeDriverPath");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Инициализируем ChromeDriver
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();       // задаём параметры открывающегося окна браузера (full screen)
        Waiting.waitUntil(2000);
    }

    // Открываем URL Jira в браузере
    public void openUrl(String url) {
        driver.get(url);
        Waiting.waitUntil(1000);
    }


    // Выполняем вход на главную страницу Jira с последующей аутентификацией
    public void loginToJira(String login, String password) {
        // Пропускаем предупреждение безопасности (если есть)
        WebElement skipWarning = driver.findElement(By.xpath("//*[@id=\"details-button\"]"));
        skipWarning.click();
        Waiting.waitUntil(1000);

        WebElement goToSite = driver.findElement(By.xpath("//*[@id=\"proceed-link\"]"));
        goToSite.click();
        Waiting.waitUntil(2000);

        // Вводим логин и пароль
        driver.findElement(By.xpath("//*[@id=\"login-form-username\"]")).sendKeys(login);
        driver.findElement(By.xpath("//*[@id=\"login-form-password\"]")).sendKeys(password);

        // Нажимаем кнопку входа
        WebElement logInButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        logInButton.click();
        Waiting.waitUntil(2000);
    }

    // Переходим к указанному фильтру Jira
    public void openJiraFilter(String filterUrl) {
        driver.get(filterUrl);
        Waiting.waitUntil(2000);
        System.out.println(infoOpenedJiraFilter);
    }

    // Меняем представление на DetailView
    public void switchToDetailView() {
        // Поиск и нажатие кнопки раскрывающегося списка для смены представления
        WebElement dropDownButton = driver.findElement(By.xpath("//*[@id=\"layout-switcher-button\"]"));
        dropDownButton.click();
        Waiting.waitUntil(1000);

        // Поиск и нажатие кнопки DetailView для смены представления
        WebElement detailViewButton = driver.findElement(By.xpath("//*[@id=\"layout-switcher-options\"]/div/ul/li[1]/a"));
        detailViewButton.click();
        Waiting.waitUntil(2000);
        System.out.println();
    }

    /// тут будут другие методы

    // Метод для выполнения всех действий с браузером на основе данных аутентификации
    public void performActions(AuthData authData) {
        // Открываем URL Jira
        openUrl(authData.getJiraUrl());

        // Выполняем аутентификацию в Jira
        loginToJira(authData.getLogin(), authData.getPassword());

        // Открываем фильтр Jira
        openJiraFilter(authData.getJiraFilter());

        // Меняем представление на DetailView
        switchToDetailView();
    }



    // Закрытие браузера
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
