package core;


import auth.AuthData;
import auth.Authentificator;
import browser.BrowserManager;
import utils.MaskingOutput;
import workspace.WorkSpaceChecker;

public class Application {
    private WorkSpaceChecker workSpaceChecker;      // Объект для проверки рабочего пространства
    private Authentificator authenticator;          // Объект для аутентификации пользователя
    private BrowserManager browserManager;          // Объект для управления браузером

    public static final String authError = "Ошибка: аутентификационные данные не были получены.";

    // Конструктор класса
    public Application() {

        // Инициализация объектов при создании экземпляра Application
        this.workSpaceChecker = new WorkSpaceChecker();     // Создаем объект для проверки рабочего пространства
        this.authenticator = new Authentificator();         // Создаем объект для аутентификации
        this.browserManager = new BrowserManager();         // Создаем объект для управления браузером

    }


    // Основной метод, который запускает логику программы
    public void run() {
        // 1. Проверяем операционную систему пользователя
        WorkSpaceChecker.checkOperationSystem();

        // 2. Проверяем рабочее пространство (например, подключение к VPN)
        WorkSpaceChecker.checkWorkSpace();

        // 3. Аутентифицируем пользователя и получаем данные для входа
        AuthData authData = authenticator.authenticate();

        // 4. Проверяем, успешно ли прошла аутентификация
        if (authData == null) {
            System.out.println(authError);
            return;
        }

        // 5. Маскируем и выводим чувствительные данные (логин и пароль)
        MaskingOutput maskOutput = new MaskingOutput();
        System.out.println("Логин: " + maskOutput.maskLogin(authData.getLogin()));
        System.out.println("Пароль: " + maskOutput.maskPassword(authData.getPassword()));
        System.out.println("URL Jira: " + authData.getJiraUrl());
        System.out.println("Фильтр Jira: " + authData.getJiraFilter());

        // 6. Открываем браузер и выполняем действия на сайте Jira
        browserManager.performActions(authData);
    }

}



