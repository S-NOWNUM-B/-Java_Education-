package com.educational.finalproject.knowledge;

import java.io.IOException;

/**
 * # Обработка исключений
 *
 * ### try-catch-finally
 * Классический способ обработки. Блок `finally` выполняется всегда, даже если в `try` был `return`.
 *
 * ### try-with-resources (Java 7+)
 * Автоматически закрывает ресурсы, реализующие интерфейс `AutoCloseable`.
 * ```java
 * try (FileWriter writer = new FileWriter("file.txt")) {
 *     // работа с ресурсом
 * } catch (IOException e) { ... }
 * ```
 *
 * ### Multi-catch (Java 7+)
 * Обработка нескольких типов исключений в одном блоке `catch`.
 * ```java
 * catch (IOException | SQLException e) { ... }
 * ```
 *
 * ### Ключевые слова
 * *   `throw`: Ручной выброс исключения.
 * *   `throws`: Объявление в сигнатуре метода, что он может выбросить исключение.
 */
public class ExceptionHandlingTechniques {
    private String lastError;

    /**
     * Демонстрация try-with-resources.
     */
    public void demonstrateResourceHandling() {
        // Пример симуляции ресурса
        try (DummyResource resource = new DummyResource()) {
            resource.doWork();
        } catch (Exception e) {
            this.lastError = e.getMessage();
            System.err.println("Ошибка при работе с ресурсом: " + lastError);
        }
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    /**
     * Внутренний класс для демонстрации AutoCloseable.
     */
    static class DummyResource implements AutoCloseable {
        public void doWork() throws IOException {
            System.out.println("Выполнение работы с ресурсом...");
        }

        @Override
        public void close() throws Exception {
            System.out.println("Ресурс автоматически закрыт.");
        }
    }
}
