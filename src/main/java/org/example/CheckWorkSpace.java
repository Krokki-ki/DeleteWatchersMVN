package org.example;

import java.util.Scanner;

import static org.example.IsVPNConnected.isVPNConnected;

public class CheckWorkSpace {
    public static final String workSpaceNavigate = "Укажите типа Вашего рабочего места: \n1 - Локальное рабочее место \n2 - Удалённое рабочее место \nИли нажмите любую другую клавишу для прекращения программы \n";
    public static final String errorNoVPN = "Произошла ошибка. Вероятно, у вас не подключен VPN. \nПожалуйста, подключите VPN и повторите попытку. \n";
    public static final String invalidCodeBreak = "Неверный ввод. Завершение программы.\n";
    public static final String typeLocal = "Выбран тип \"Локальное рабочее место\". Проверка подключения VPN.\n";
    public static final String typeRemote = "Выбран тип \"Удалённое рабочее место\". Проверка статуса VPN не требуется.\n";

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

