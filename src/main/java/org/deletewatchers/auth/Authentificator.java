package auth;

import processing.Processing;

import java.io.*;
import java.util.Scanner;

///Метод chooseAuthMethod:
///     Предлагает пользователю выбрать способ авторизации.
///     В зависимости от выбора вызывает либо consoleAuth, либо fileAuth.
///
/// Метод consoleAuth:
///     Запрашивает у пользователя логин, пароль, URL Jira и фильтр через консоль.
///     Возвращает массив строк с данными.
///
/// Метод fileAuth:
///     Считывает данные из файла (логин, пароль, URL Jira и фильтр).
///     Возвращает массив строк с данными.
///     Если файл не найден или произошла ошибка чтения, программа завершается.

public class ChoozeAuthentification {
    public static final String authNavigate = "Выберите способ авторизации: \n1 - Ввод данных через консоль \n2 - Считывание данных из файла (файл должен быть подготовлен) \nИли нажмите любую клавишу для прекращения программы\n";
    public static final String invalidCodeBreak = "Неверный ввод. Завершение программы.\n";

    public static final String enterLogin = "Введите Ваш логин: ";
    public static final String enterPassword = "Введите Ваш пароль: ";
    public static final String enterJiraURL = "Введите URL ресурса Jira: ";
    public static final String enterJiraFilter = "\nПожалуйста, укажите способ ввода фильтра Jira: \n1 - Использовать фильтр \"watcher = currentUser() AND project not in (проект1, проект2, ..., проектN) ORDER BY key ASC\"; \nПримечание: будут обработаны все Issues, которые НЕ попадают в перечисленные Вами проеткы! \n\n2 - Указать свой Jira-фильтр. \nПримечание: за валидность указанного Вами фильтра программа ответственности не несёт <смайлик>\n\nИли укажите любое другое значение, чтобы выйти из программы.\n";
//    public static final String enterJiraFilter = "Укажите Ваш фильтр Jira в формате \"watcher = currentUser() AND project not in (<Ваш проект или проекты, указанные через запятую>) ORDER BY key ASC\" :";

    public static final String selectFilePath = "Укажите путь к Вашему файлу в формате \"Disk:\\YourPath\\FileName.txt\": ";
    public static final String fileNotFound = "Файл не найден. Проверьте путь к файлу.\n";
    public static final String fileReadError = "Ошибка чтения файла.\n";
    public static final String loadDataSuccess = "Данные успешно загружены из файла.\n";

    // Метод для выбора способа авторизации
    public String[] choozeAuthMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(authNavigate);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return consoleAuth();
            case 2:
                return fileAuth();
            default:
                System.out.println(invalidCodeBreak);
                System.exit(0);
                return null; // Этот return никогда не выполнится, но нужен для компиляции
        }
    }

    // Метод для авторизации через консоль
    public String[] consoleAuth() {
        Scanner scanner = new Scanner(System.in);
        String[] authData = new String[4];

        // Запрашиваем логин, пароль и URL Jira
        System.out.print(enterLogin);
        authData[0] = scanner.nextLine();

        System.out.print(enterPassword);
        authData[1] = MaskingInput.readMaskedInput(""); // Пустой prompt, так как текст уже выведен

        System.out.print(enterJiraURL);
        authData[2] = scanner.nextLine();

        // Запрашиваем способ ввода фильтра
        System.out.print(enterJiraFilter);
        String filterChoice = scanner.nextLine();

        // Обрабатываем выбор фильтра
        Processing enterJiraFilterWay = new Processing();
        switch (filterChoice) {
            case "1":
                System.out.println("Вы выбрали использование кастомизированного фильтра.\n");
                authData[3] = enterJiraFilterWay.customizeFilter();     // Сохраняем фильтр в authData[3]
                break;
            case "2":
                System.out.println("Вы выбрали использование своего фильтра.\n");
                authData[3] = enterJiraFilterWay.selfFilter();          // Сохраняем фильтр в authData[3]
                break;
            default:
                System.out.println(invalidCodeBreak);
                System.exit(0);
        }

        return authData;        // Возвращаем массив с логином, паролем, URL Jira и фильтром
    }

    // Метод для авторизации через файл
    private String[] fileAuth() {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        String[] authData = new String[4];
        System.out.print(selectFilePath);
        filePath = scanner.nextLine();      // записали путь к файлу в переменную filePath
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            // считываем строки из файла поочерёдно
            authData[0] = reader.readLine();    // Логин
            authData[1] = reader.readLine();    // Пароль
            authData[2] = reader.readLine();    // URL Jira
            authData[3] = reader.readLine();    // URL фильтра Jira

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(fileNotFound);
            e.printStackTrace();
            System.exit(1); // Завершаем программу, если файл не найден
        } catch (IOException e) {
            System.out.println(fileReadError);
            e.printStackTrace();
            System.exit(1); // Завершаем программу, если произошла ошибка ввода-вывода
        }

        System.out.println(loadDataSuccess);
        return authData;
    }
}
