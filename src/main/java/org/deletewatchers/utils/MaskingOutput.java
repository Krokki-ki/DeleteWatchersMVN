package utils;
/// Персональный класс с методами, которые маскируют такие чувствительные данные, как логин и пароль

public class MaskingOutput {

    // Метод маскировки логина
    public static String maskLogin(String login) {
        // Если логин пустой, возвращаем пустую строку
        if (login == null || login.isEmpty()) {
            return "Вы не ввели логин";
        }

        // Если логин из одного символа - возвращаем как есть
        if (login.length() == 1) {
            return login;
        }

        // Если логин из двух символов - скрываем один символ
        else if (login.length() == 2) {
            return login.substring(0, 2) + "*".repeat(login.length() - 1);
        }

        // Если логин из трёх символов - скрываем два символа
        else if (login.length() == 3) {
            return login.substring(0, 3) + "*".repeat(login.length() - 2);
        }
        // Иначе - маскируем три символа
        return login.substring(0, 4) + "*".repeat(login.length() - 3);
    }

    // Метод маскировки пароля
    public static String maskPassword(String password) {
        // Если пароль пустой, возвращаем пустую строку
        if (password == null || password.isEmpty()) {
            return "Вы не ввели пароль";
        }

        // Маскируем весь пароль
        return "*".repeat(password.length());
    }


}
