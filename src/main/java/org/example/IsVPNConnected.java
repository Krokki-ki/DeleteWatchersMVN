package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IsVPNConnected {
    public static boolean isVPNConnected() {
        try {
            // Команда для получения списка сетевых интерфейсов
            Process process = Runtime.getRuntime().exec("ipconfig");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Проверяем наличие VPN-интерфейса
                if (line.contains("fe80::2366:34b4:f71d:9ecf%20")) { //считывание IPv6
                    System.out.println("Процесс " + line + " - успешно запущен, VPN подключён.");
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
