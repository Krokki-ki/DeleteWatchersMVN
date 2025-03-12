package core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

public class Processing {
    public static final String firstPartFilter = "watcher = currentUser() AND project not in (";
    public static final String lastPartFilter = ") ORDER BY key ASC";
    public static final String jiraBaseURL = "https://jira.moscow.alfaintra.net/issues/?jql=";

    // Метод для обработки кастомизированного фильтра
    public String customizeFilter() {
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем у пользователя ввод фильтра
        System.out.println("Укажите код Вашего проекта (или проектов через запятую): \nВводимая строка не должна быть пустой.\nЛибо нажмите Enter для прекращения программы.");

        while (true) {
            String projectNames = scanner.nextLine();
            String customizeFilterURL = null;
            String customizeFilterEncodeURL = null;

            // Проверяем, пустая ли строка
            if (projectNames.trim().isEmpty()) {
                System.out.println("Некорректный ввод. Завершение программы.");
                System.exit(0);
            }

            // Проверяем, что строка не пустая
            if (!projectNames.trim().isEmpty()) {
                customizeFilterURL = firstPartFilter + projectNames + lastPartFilter;

                // Вызываем метод для кодирования URL
                customizeFilterEncodeURL = filterEncoded(customizeFilterURL);
                System.out.println("URL Jira: " + customizeFilterEncodeURL);
                return customizeFilterEncodeURL;
            }
        }
    }

    // Метод для обработки пользовательского фильтра
    public String selfFilter() {
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем у пользователя ввод фильтра
        System.out.println("Укажите Ваш собственный фильтр: \nВводимая строка не должна быть пустой. \nЛибо нажмите Enter для прекращения программы.");

        while (true) {
            String projectFilter = scanner.nextLine();
            String selfFilterEncodeURL = null;

            // Проверяем, пустая ли строка
            if (projectFilter.trim().isEmpty()) {
                System.out.println("Некорректный ввод. Завершение программы.");
                System.exit(0);
            }

            // Проверяем, что строка не пустая
            if (!projectFilter.trim().isEmpty()) {

                // Вызываем метод для кодирования URL
                selfFilterEncodeURL = filterEncoded(projectFilter);
                System.out.println("URL Jira: " + selfFilterEncodeURL);
                return selfFilterEncodeURL;
            }
        }
    }

    // Метод преобразования фильтра в URL
    public String filterEncoded(String filter) {
        try {
            // Кодируем строку фильтра для использования в URL
            String encodedFilter = URLEncoder.encode(filter, "UTF-8");

            // Собираем полный URL
            return jiraBaseURL + encodedFilter;
        } catch (UnsupportedEncodingException e) {
            System.out.println("Ошибка кодирования фильтра: " + e.getMessage() + ". \nВероятно, строка имён проектов были указаны некорректно.");
            System.exit(1); // Завершаем программу с кодом ошибки
            return null;        // Этот return никогда не выполнится, но нужен для компиляции
        }

    }
}
