package auth;
    /// Класс для хранения данных аутентификации

public class AuthData {
    private String login;
    private String password;
    private String jiraUrl;
    private String jiraFilter;

    // Конструктор класса AuthData
    public AuthData(String login, String password, String jiraUrl, String jiraFilter) {
        this.login = login;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.jiraFilter = jiraFilter;
    }

    /// Геттеры для доступа к данным
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getJiraUrl() {
        return jiraUrl;
    }

    public String getJiraFilter() {
        return jiraFilter;
    }
}
