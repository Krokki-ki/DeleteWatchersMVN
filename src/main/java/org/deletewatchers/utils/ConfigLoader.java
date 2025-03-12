package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/// Класс для загрузки конфигурации из файла application.properties
public class ConfigLoader {
    private static final String PROPERTIES_FILE = "application.properties";
    private static Properties properties;
    public static final String configErrorFirst = "Не удалось найти файл конфигурации ";
    public static final String configErrorLast = ". \nУбедитесь, что он находится в папке src/main/resources. \nИли обратитесь в техническую поддержку SFA";
    public static final String configLoadError = "Произошла ошибка при загрузке файла конфигурации. \nОбратитесь в теническую поддержку SFA.";
    public static final String propertyNotFoundFirst = "Свойство ";
    public static final String propertyNotFoundLast = " не найдено в файле конфигурации.\nОбратитесь в техническую поддержку SFA";

    static {
        properties = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException(configErrorFirst + PROPERTIES_FILE + configErrorLast);
            }
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(configLoadError, e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(propertyNotFoundFirst + key + propertyNotFoundLast);
        }
        return value;
    }
}
