package com.educational.finalproject.knowledge;

/**
 * # Иерархия исключений
 *
 * ### Throwable
 * Базовый класс для всего, что можно выбросить через `throw`.
 *
 * ### 1. Error
 * Серьезные проблемы в работе JVM. Не предназначены для обработки в коде приложения.
 * *   Примеры: `OutOfMemoryError`, `StackOverflowError`.
 *
 * ### 2. Exception (Checked)
 * Компилятор требует обработать их через `try-catch` или пробросить через `throws`. Описывают внешние условия (файл не найден, ошибка сети).
 * *   Пример: `IOException`, `SQLException`.
 *
 * ### 3. RuntimeException (Unchecked)
 * Ошибки логики программиста. Компилятор не заставляет их обрабатывать.
 * *   Примеры: `NullPointerException`, `IndexOutOfBoundsException`, `ArithmeticException`.
 */
public class ExceptionsHierarchyKnowledge {
    private String exceptionType;
    private boolean isChecked;

    public ExceptionsHierarchyKnowledge(String exceptionType, boolean isChecked) {
        this.exceptionType = exceptionType;
        this.isChecked = isChecked;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    /**
     * Пример обработки исключения.
     */
    public void demonstrateHandling() {
        try {
            if (exceptionType.contains("Runtime")) {
                throw new RuntimeException("Демонстрация Unchecked исключения");
            } else {
                System.out.println("Обработка Checked исключения требует try-catch или throws.");
            }
        } catch (Exception e) {
            System.err.println("Исключение обработато: " + e.getMessage());
        } finally {
            System.out.println("Блок finally выполняется всегда.");
        }
    }
}
