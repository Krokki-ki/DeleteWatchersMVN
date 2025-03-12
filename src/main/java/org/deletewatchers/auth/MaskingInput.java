package auth;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
/// Методо для маскирования пароля на этапе ввода значения в консоль программы

public class MaskingInput {
    public static String readMaskedInput(String prompt) {
        try {
            // Создаём терминал
            Terminal terminal = TerminalBuilder.terminal();

            // Создаём LineReader
            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();

            // Запрашиваем ввод с маскировкой
            return reader.readLine(prompt, '*');
        } catch (Exception e) {
            System.err.println("Ошибка при работе с консолью: " + e.getMessage());
            return null;
        }
    }
}
