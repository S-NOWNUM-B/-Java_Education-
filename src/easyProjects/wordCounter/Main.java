package easyProjects.wordCounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Класс для хранения статистики текста
class TextStatistics {
    private int wordCount;
    private int charCount;
    private int sentenceCount;
    private int paragraphCount;

    // Конструктор
    public TextStatistics(int wordCount, int charCount, int sentenceCount, int paragraphCount) {
        this.wordCount = wordCount;
        this.charCount = charCount;
        this.sentenceCount = sentenceCount;
        this.paragraphCount = paragraphCount;
    }

    // Геттеры
    public int getWordCount() {
        return wordCount;
    }

    public int getCharCount() {
        return charCount;
    }

    public int getSentenceCount() {
        return sentenceCount;
    }

    public int getParagraphCount() {
        return paragraphCount;
    }

    // Метод для отображения статистики
    public void display() {
        System.out.println("\n=== Text Statistics ===");
        System.out.println("Words: " + wordCount);
        System.out.println("Characters (with spaces): " + charCount);
        System.out.println("Characters (without spaces): " + (charCount - countSpaces()));
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Paragraphs: " + paragraphCount);

        if (wordCount > 0) {
            double avgWordLength = (double) (charCount - countSpaces()) / wordCount;
            System.out.printf("Average word length: %.2f characters%n", avgWordLength);
        }
    }

    // Вспомогательный метод для подсчета пробелов
    private int countSpaces() {
        return charCount - (charCount - wordCount + 1);
    }
}

// Класс анализатора текста
class TextAnalyzer {

    // Метод подсчета слов
    public int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        // Разделение по пробельным символам
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    // Метод подсчета символов
    public int countChars(String text) {
        if (text == null) {
            return 0;
        }
        return text.length();
    }

    // Метод подсчета предложений
    public int countSentences(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        // Подсчет предложений по знакам: . ! ?
        int count = 0;
        boolean lastWasPunctuation = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Проверка на конец предложения
            if (c == '.' || c == '!' || c == '?') {
                if (!lastWasPunctuation) {
                    count++;
                    lastWasPunctuation = true;
                }
            } else if (!Character.isWhitespace(c)) {
                lastWasPunctuation = false;
            }
        }

        return count;
    }

    // Метод подсчета параграфов
    public int countParagraphs(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        // Параграфы разделяются пустыми строками (двойной перевод строки)
        String[] paragraphs = text.split("\\n\\s*\\n");
        return paragraphs.length;
    }

    // Универсальный метод анализа текста
    public TextStatistics analyze(String text) {
        int words = countWords(text);
        int chars = countChars(text);
        int sentences = countSentences(text);
        int paragraphs = countParagraphs(text);

        return new TextStatistics(words, chars, sentences, paragraphs);
    }
}

// Класс для чтения текста из файла
class FileTextReader {

    // Метод чтения файла
    public String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        return content.toString();
    }
}

// Класс для работы с пользовательским интерфейсом
class AnalyzerUI {
    private Scanner input;
    private TextAnalyzer analyzer;
    private FileTextReader fileReader;

    public AnalyzerUI() {
        this.input = new Scanner(System.in);
        this.analyzer = new TextAnalyzer();
        this.fileReader = new FileTextReader();
    }

    public void run() {
        System.out.println("=== Text Analyzer ===\n");

        while (true) {
            try {
                // Выбор источника текста
                String text = getTextInput();

                if (text == null) {
                    break;
                }

                // Анализ текста
                TextStatistics stats = analyzer.analyze(text);

                // Вывод результата
                stats.display();

                // Продолжить или выйти
                if (!askContinue()) {
                    break;
                }

                System.out.println();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }

        System.out.println("Thanks for using Text Analyzer!");
        input.close();
    }

    // Метод для выбора способа ввода текста
    private String getTextInput() throws IOException {
        System.out.println("Select input method:");
        System.out.println("1. Enter text manually");
        System.out.println("2. Read from file");
        System.out.println("3. Exit");
        System.out.print("Enter choice (1-3): ");

        int choice = input.nextInt();
        input.nextLine(); // Очистка буфера

        switch (choice) {
            case 1:
                return getManualInput();
            case 2:
                return getFileInput();
            case 3:
                return null;
            default:
                throw new IllegalArgumentException("Invalid choice");
        }
    }

    // Метод для ввода текста вручную
    private String getManualInput() {
        System.out.println("\nEnter your text (type 'END' on a new line to finish):");
        StringBuilder text = new StringBuilder();

        while (true) {
            String line = input.nextLine();
            if (line.equals("END")) {
                break;
            }
            text.append(line).append("\n");
        }

        return text.toString();
    }

    // Метод для чтения текста из файла
    private String getFileInput() throws IOException {
        System.out.print("\nEnter file path: ");
        String filePath = input.nextLine();

        String text = fileReader.readFile(filePath);
        System.out.println("File loaded successfully!");

        return text;
    }

    // Спросить пользователя, хочет ли он продолжить
    private boolean askContinue() {
        System.out.print("\nAnalyze another text? (y/n): ");
        String answer = input.next().toLowerCase();
        input.nextLine(); // Очистка буфера
        return answer.equals("y") || answer.equals("yes");
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        AnalyzerUI ui = new AnalyzerUI();
        ui.run();
    }
}
