# Иерархия исключений

### Throwable
Базовый класс для всего, что можно выбросить через `throw`.

### 1. Error
Серьезные проблемы в работе JVM. Не предназначены для обработки в коде приложения.
*   Примеры: `OutOfMemoryError`, `StackOverflowError`.

### 2. Exception (Checked)
Компилятор требует обработать их через `try-catch` или пробросить через `throws`. Описывают внешние условия (файл не найден, ошибка сети).
*   Пример: `IOException`, `SQLException`.

### 3. RuntimeException (Unchecked)
Ошибки логики программиста. Компилятор не заставляет их обрабатывать.
*   Примеры: `NullPointerException`, `IndexOutOfBoundsException`, `ArithmeticException`.
