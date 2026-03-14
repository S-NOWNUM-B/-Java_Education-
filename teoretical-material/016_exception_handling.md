# Обработка исключений

### try-catch-finally
*   `try`: Блок с кодом, который может упасть.
*   `catch`: Перехват и обработка ошибки. Можно использовать несколько блоков (от частного к общему).
*   `finally`: Блок, который выполнится **всегда** (даже если был `return` или ошибка). Идеально для закрытия ресурсов.

### try-with-resources (Java 7+)
Автоматическое закрытие ресурсов, реализующих `AutoCloseable`.
```java
try (FileWriter writer = new FileWriter("test.txt")) {
    writer.write("Hello");
} // writer закроется автоматически
```

### Multicatch (Java 7+)
Позволяет перехватывать несколько исключений в одном блоке:
`catch (IOException | SQLException e) { ... }`
