package com.educational.finalproject.knowledge;

/**
 * # Полиморфизм (Polymorphism)
 *
 * ### Виды
 * 1.  **Статический (Static)**: Перегрузка методов (`Overloading`) — методы с одинаковыми именами, но разными параметрами в одном классе.
 * 2.  **Динамический (Dynamic)**: Переопределение методов (`Overriding`) — изменение поведения метода родителя в наследнике.
 *
 * ### Upcasting и Downcasting
 * *   `Upcasting`: Приведение к родителю (безопасно, автоматически).
 * *   `Downcasting`: Приведение к наследнику (требует явного указания и проверки `instanceof`).
 */
public class PolymorphismConcept {

    /**
     * Методы для демонстрации статического полиморфизма (Overloading).
     */
    public void process(int value) {
        System.out.println("Обработка числа: " + value);
    }

    public void process(String value) {
        System.out.println("Обработка строки: " + value);
    }

    public void process(double value, String unit) {
        System.out.println("Обработка значения " + value + " с единицей " + unit);
    }

    /**
     * Поведение для демонстрации динамического полиморфизма (Overriding).
     */
    public String getInformation() {
        return "Базовая информация о полиморфизме";
    }
}

/**
 * Класс-наследник для демонстрации динамического полиморфизма.
 */
class DynamicPolymorphismExample extends PolymorphismConcept {
    @Override
    public String getInformation() {
        return "Разширенная информация (переопределено наследником)";
    }
}
