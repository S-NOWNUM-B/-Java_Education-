package com.educational.finalproject.knowledge;

import java.util.List;

/**
 * # Интерфейсы (Interfaces)
 *
 * ### Описание
 * Контракт, который обязует класс реализовать определенное поведение.
 *
 * ### Возможности
 * *   Все методы по умолчанию `public abstract` (до Java 8).
 * *   **Java 8**: Появились `default` методы (с телом) и `static` методы.
 * *   **Java 9**: Появились `private` методы.
 * *   Переменные всегда `public static final`.
 * *   Поддерживают **множественное наследование**.
 *
 * ### Функциональный интерфейс
 * Содержит ровно один абстрактный метод (используется для Lambdas). Помечается аннотацией `@FunctionalInterface`.
 */
public interface InterfaceDefinition {

    /**
     * Константа в интерфейсе (public static final).
     */
    String TOPIC = "Java Interfaces";

    /**
     * Абстрактный метод (public abstract).
     */
    void study();

    /**
     * Метод по умолчанию (default).
     */
    default void useDefaultBehavior() {
        System.out.println("Используется стандартное поведение интерфейса: " + TOPIC);
    }

    /**
     * Статический метод интерфейса.
     */
    static void displayGeneralInfo() {
        System.out.println("Интерфейсы позволяют реализовать множественное поведение в Java.");
    }
}

/**
 * Реализация интерфейса.
 */
class KnowledgeTracker implements InterfaceDefinition {
    private List<String> learnedItems;

    public KnowledgeTracker(List<String> learnedItems) {
        this.learnedItems = learnedItems;
    }

    @Override
    public void study() {
        System.out.println("Изучение элементов: " + learnedItems);
    }

    public List<String> getLearnedItems() {
        return learnedItems;
    }

    public void setLearnedItems(List<String> learnedItems) {
        this.learnedItems = learnedItems;
    }
}
