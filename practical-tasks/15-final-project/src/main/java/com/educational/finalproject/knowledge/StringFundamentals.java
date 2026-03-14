package com.educational.finalproject.knowledge;

/**
 * # Класс String
 *
 * ### Особенности
 * *   **Immutable**: Объект нельзя изменить после создания. Любая модификация создает новую строку.
 * *   **Final**: Класс нельзя наследовать.
 * *   **Хранение**: В Java 9+ внутри используется `byte[]` вместо `char[]` для экономии места.
 *
 * ### String Pool
 * Выделенная область в куче для хранения уникальных строк.
 * *   `String s1 = "Java";` — проверит пул, если строка есть — вернет ссылку, если нет — создаст.
 * *   `String s2 = new String("Java");` — принудительно создаст новый объект в куче.
 *
 * ### StringBuilder и StringBuffer
 * Используются для частой модификации строк (конкатенации в циклах).
 * *   `StringBuilder`: Быстрый, но не потокобезопасный.
 * *   `StringBuffer`: Медленнее, так как методы синхронизированы (Thread-safe).
 */
public class StringFundamentals {
    private String content;

    public StringFundamentals(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Демонстрация пула строк.
     */
    public boolean compareInPool(String other) {
        String interned = other.intern();
        return content.equals(interned);
    }

    /**
     * Демонстрация StringBuilder для эффективной конкатенации.
     */
    public String buildLargeString(int repetitions) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repetitions; i++) {
            sb.append(content).append(" ");
        }
        return sb.toString();
    }
}
