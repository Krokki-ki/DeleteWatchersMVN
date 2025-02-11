package org.example;

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
    public static final String authNavigate = "Выберите способ авторизации: \n1 - Ввод данных через консоль \n2 - Считывание данных из файла (файл должен быть подготовлен) \nИли нажмите любую клавишу для прекращения программы";

    // Метод для выбора способа авторизации
    public String [] choozeAuthMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(authNavigate);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return consoleAuth();
            case 2:
                return fileAuth();
            default:
                System.out.println("Неверный ввод. Завершение программы.");
                System.exit(0);
                return null; // Этот return никогда не выполнится, но нужен для компиляции
        }
    }

    // Метод для авторизации через консоль
    private String[] consoleAuth() {
        Scanner scanner = new Scanner(System.in);
        String[] authData = new String[4];
        String password = null;

        System.out.print("Введите Ваш логин: ");
        authData[0] = scanner.nextLine();

        System.out.print("Введите Ваш пароль: ");
        authData[1] = MaskingInput.readMaskedInput(""); // Пустой prompt, так как текст уже выведен

        System.out.print("Введите URL ресурса Jira: ");
        authData[2] = scanner.nextLine();

        System.out.print("Введите URL фильтра Jira: ");
        authData[3] = scanner.nextLine();

        return authData;
    }

    // Метод для авторизации через файл
    private String[] fileAuth() {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        String[] authData = new String[4];
        System.out.print("Укажите путь к Вашему файлу в формате \"Disk:\\YourPath\\FileName.txt\": ");
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
            System.out.println("Файл не найден. Проверьте путь к файлу.");
            e.printStackTrace();
            System.exit(1); // Завершаем программу, если файл не найден
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
            e.printStackTrace();
            System.exit(1); // Завершаем программу, если произошла ошибка ввода-вывода
        }

        System.out.println("Данные успешно загружены из файла.");
        return authData;
    }
}
