package org.example;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <h1>Java I/O и NIO.2: Работа с данными и файлами</h1>
 * 
 * <p>Данный модуль посвящен способам взаимодействия Java-приложений с внешней средой:
 * чтению и записи файлов, работе с потоками байтов и символов.</p>
 *
 * <h2>1. Старый I/O (InputStream / OutputStream)</h2>
 * <p>Основан на потоковой модели. Чтение происходит побайтово или блоками. 
 * Основные классы:
 * <ul>
 *     <li><code>FileInputStream / FileOutputStream</code>: работа с байтами (картинки, видео).</li>
 *     <li><code>FileReader / FileWriter</code>: работа с символами (текст, учитывает кодировку).</li>
 *     <li><code>BufferedReader / BufferedWriter</code>: обертки для ускорения работы через буфер.</li>
 * </ul>
 * </p>
 *
 * <h2>2. Новый I/O (NIO.2)</h2>
 * <p>Появился в Java 7. Оперирует путями (Paths) и файлами (Files). 
 * Более современный, производительный и удобный API. 
 * Позволяет копировать, перемещать и удалять файлы одной строчкой кода.</p>
 *
 * <h2>3. Serialization (Сериализация)</h2>
 * <p>Процесс сохранения состояния объекта в поток байтов. Чтобы объект можно было 
 * сериализовать, его класс должен реализовать интерфейс <code>Serializable</code>.</p>
 *
 * @author Student
 */
public class IONotes {

    /**
     * Пример использования классического I/O с буферизацией.
     */
    public static void writeClassic(String filename, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        }
    }

    /**
     * Пример использования NIO.2 для чтения всех строк файла.
     */
    public static List<String> readNio(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    /**
     * Точка входа для демонстрации работы с файлами.
     */
    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: I/O & NIO ===");

        Path testFile = Paths.get("practice_file.txt");

        try {
            // 1. Записываем файл через NIO.2
            String secret = "Секретный код: 12345\nВторая линия теории.";
            Files.write(testFile, secret.getBytes(StandardCharsets.UTF_8));
            System.out.println("Файл успешно создан: " + testFile.toAbsolutePath());

            // 2. Читаем файл
            List<String> lines = readNio(testFile);
            System.out.println("Содержимое файла:");
            lines.forEach(line -> System.out.println(" > " + line));

            // 3. Удаляем файл (приборка за собой)
            Files.deleteIfExists(testFile);
            System.out.println("Файл удален.");

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }

        System.out.println("\n[ЗАМЕТКА] Всегда используйте try-with-resources для закрытия потоков!");
    }
}
