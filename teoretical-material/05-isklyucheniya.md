# Исключения (Exceptions) в Java

Механизм для обработки ошибок во время выполнения программы.

### 1. Иерархия
*   **Throwable**: Родитель всех ошибок.
    *   **Error**: Серьезные проблемы (напр. `OutOfMemoryError`). Программа обычно не может их обработать.
    *   **Exception**: Ошибки, которые можно перехватить.
        *   **RuntimeException** (**Unchecked**): Ошибки логики (напр. `NullPointerException`). Компилятор не заставляет их обрабатывать.
        *   **Checked Exceptions**: Ошибки, которые *обязательно* нужно обработать или пробросить выше (напр. `IOException`).

### 2. Конструкция try-catch-finally
```java
try {
    // Код, который может вызвать ошибку
} catch (IOException e) {
    // Обработка конкретной ошибки
} catch (Exception e) {
    // Общая обработка (должна быть в конце)
} finally {
    // Выполняется ВСЕГДА (закрытие ресурсов)
}
```

### 3. Try-with-resources
Автоматически закрывает ресурсы (классы, реализующие `AutoCloseable`).
```java
try (BufferedReader br = new BufferedReader(new FileReader(path))) {
    return br.readLine();
} catch (IOException e) {
    // ...
}
```

### 4. Ключевые слова
*   `throw`: Выбросить исключение вручную (`throw new Exception()`).
*   `throws`: Указывается в сигнатуре метода, предупреждая, что метод может выбросить исключение.
