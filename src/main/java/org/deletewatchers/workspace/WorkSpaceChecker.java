package workspace;

import java.util.Scanner;

import static workspace.IsVPNConnected.isVPNConnected;

public class WorkSpaceChecker {
    // Константы для выбора операционной системы
    public static final String osNavigate = "Укажите вашу операционную систему: " +
            "\n1 - Windows \n2 - MacOS \n3 - Linux " +
            "\nИли нажмите любую другую клавишу для прекращения программы. \n";
    public static final String osWindows = "Выбрана ОС Windows...\n";
    public static final String osMacOS = "Выбрана ОС MacOS...\n" +
            "Это программа ещё пока не умеет работать с MacOS :(\n";
    public static final String osLinux = "Выбрана ОС Linux...\n" +
            "Это программа ещё пока не умеет работать с Linux :(\n";
    public static final String invalidCodeBreak = "Неверный ввод. Завершение программы.\n";

    // Константы для выбора типа рабочего места
    public static final String workSpaceNavigate = "Укажите типа Вашего рабочего места: " +
            "\n1 - Локальное рабочее место \n2 - Удалённое рабочее место " +
            "\nИли нажмите любую другую клавишу для прекращения программы \n";
    public static final String errorNoVPN = "Произошла ошибка. Вероятно, у вас не подключен VPN. " +
            "\nПожалуйста, подключите VPN и повторите попытку. \n";
    public static final String typeLocal = "Выбран тип \"Локальное рабочее место\". Проверка подключения VPN.\n";
    public static final String typeRemote = "Выбран тип \"Удалённое рабочее место\". Проверка статуса VPN не требуется.\n";


    /// Метод для проверки операционной системы пользователя.
    /// Если выбрана Windows, программа продолжает работу.
    /// Если выбрана MacOS или Linux, программа завершается с соответствующим сообщением.
    public static void checkOperationSystem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(osNavigate);

        while (true) {
            String osChoise = scanner.nextLine();

            switch (osChoise) {
                case "1":           // OS Windows
                    System.out.println(osWindows);
                    return;         // Выход из метода и продолжение программы
                case "2":           // OS MacOS
                    System.out.println(osMacOS);
                    System.exit(0);     // Завершение программы
                case "3":           // OS Linux
                    System.out.println(osLinux);
                    System.exit(0);
                default:            // Неверный ввод
                    System.out.println(invalidCodeBreak);
                    System.exit(0);
            }
        }
    }

    public static void checkWorkSpace() {
        Scanner readingString = new Scanner(System.in);
        System.out.println(workSpaceNavigate);

        while (true) {
            String launchType = readingString.nextLine();

            if (launchType.equals("1")) {
                System.out.println(typeLocal);

                while (!isVPNConnected()) {
                    System.out.println(errorNoVPN);
                    System.out.println(workSpaceNavigate);
                    launchType = readingString.nextLine();          // Читаем ввод как строку

                    if (!launchType.equals("1")) {
                        System.out.println(invalidCodeBreak);
                        System.exit(0);                     // Прекращение программы по инициативе пользователя
                    }
                }
                break;                  // Выход из цикла, если VPN подключён

            } else if (launchType.equals("2")) {
                System.out.println(typeRemote);
                break;
            } else {
                System.out.println(invalidCodeBreak);
                System.exit(0);
            }
        }
    }
}

