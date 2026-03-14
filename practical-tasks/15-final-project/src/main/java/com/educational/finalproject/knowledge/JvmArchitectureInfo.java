package com.educational.finalproject.knowledge;

/**
 * # JVM, JRE, JDK
 *
 * ### JVM (Java Virtual Machine)
 * *   Исполняет байт-код.
 * *   Обеспечивает платформонезависимость ("Write Once, Run Anywhere").
 * *   Управляет памятью (Garbage Collection).
 *
 * ### JRE (Java Runtime Environment)
 * *   JVM + стандартные библиотеки классов.
 * *   Минимальный набор для **запуска** Java-приложений.
 *
 * ### JDK (Java Development Kit)
 * *   JRE + инструменты разработчика (компилятор `javac`, отладчик, `javadoc` и др.).
 * *   Набор для **разработки** и запуска.
 *
 * ### Процесс
 * `.java` (Код) -> `javac` (Компилятор) -> `.class` (Байт-код) -> `JVM` (Исполнение)
 */
public class JvmArchitectureInfo {
    private final String component;
    private final String description;

    public JvmArchitectureInfo(String component, String description) {
        this.component = component;
        this.description = description;
    }

    public String getComponent() {
        return component;
    }

    public String getDescription() {
        return description;
    }

    public void displayInfo() {
        System.out.println("Компонент: " + component);
        System.out.println("Описание: " + description);
    }
}
