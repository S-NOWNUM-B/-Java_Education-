package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тестирование операций ввода-вывода</h1>
 * 
 * <p>Проверка корректности записи и чтения файлов с использованием
 * временных директорий JUnit 5 (@TempDir).</p>
 */
public class IONotesTest {

    @TempDir
    Path tempDir;

    /**
     * Тест записи классическим способом и чтения через NIO.
     */
    @Test
    public void testFileOperations() throws IOException {
        Path filePath = tempDir.resolve("test.txt");
        String content = "Hello, Java IO!";

        // Записываем
        IONotes.writeClassic(filePath.toString(), content);

        // Проверяем существование
        assertTrue(Files.exists(filePath), "Файл должен существовать в временной папке");

        // Читаем через NIO и проверяем содержимое
        List<String> lines = IONotes.readNio(filePath);
        assertEquals(1, lines.size());
        assertEquals(content, lines.get(0));
    }

    /**
     * Тест на удаление файла.
     */
    @Test
    public void testDelete() throws IOException {
        Path filePath = tempDir.resolve("delete_me.txt");
        Files.createFile(filePath);
        
        boolean deleted = Files.deleteIfExists(filePath);
        assertTrue(deleted, "Файл должен быть успешно удален");
        assertFalse(Files.exists(filePath), "Файла больше не должно быть");
    }
}
