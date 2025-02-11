package org.example;

import java.util.Scanner;

import static org.example.IsVPNConnected.isVPNConnected;

public class CheckWorkSpace {
    public static final String workSpaceNavigate = "Укажите типа Вашего рабочего места: \n1 - Локальное рабочее место \n2 - Удалённое рабочее место \nИли нажмите любую клавишу для прекращения программы";

    public static void checkWorkSpace() {
        Scanner readingString = new Scanner(System.in);
        System.out.println(workSpaceNavigate);
        int launchType = readingString.nextInt();

        if (launchType == 1) {
            System.out.println("Выбран тип \"Локальное рабочее место\". Проверка подключения VPN.");
            System.out.println(); // для разделения строки вывода
            while (!isVPNConnected()) {

                System.out.println("Произошла ошибка. Вероятно, у вас не подключен VPN.");
                System.out.println("Пожалуйста, подключите VPN и повторите попытку.");
                System.out.println(); // для разделения строки вывода
                System.out.println(workSpaceNavigate);
                readingString.nextInt();
            }
        } else if (launchType == 2) {
                System.out.println("Выбран тип \"Удалённое рабочее место\". Проверка статуса VPN не требуется.");
                System.out.println(); // для разделения строки вывода
        } else {
                System.out.println("Неверный ввод. Завершение программы.");
                System.out.println(); // для разделения строки вывода
                System.exit(0);     //прекращение программы по инициативе пользователя
        }
    }
}

