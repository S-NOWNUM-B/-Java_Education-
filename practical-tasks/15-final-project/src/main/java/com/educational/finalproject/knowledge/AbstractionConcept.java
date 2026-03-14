package com.educational.finalproject.knowledge;

/**
 * # Абстракция (Abstraction)
 *
 * ### Абстрактный класс
 * *   Ключевое слово: `abstract`.
 * *   Нельзя создать объект этого класса.
 * *   Может содержать как обычные методы, так и абстрактные (без тела).
 * *   Может содержать конструкторы (вызываются наследниками).
 *
 * ### Абстрактный метод
 * *   Описывает сигнатуру, но не реализацию.
 * *   Должен быть переопределен в первом неабстрактном наследнике.
 */
public abstract class AbstractionConcept {
    private String name;

    public AbstractionConcept(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Абстрактный метод для реализации в наследниках.
     */
    public abstract void performAction();

    /**
     * Обычный метод с реализацией.
     */
    public void displayBaseInfo() {
        System.out.println("Абстрактная концепция: " + name);
    }
}

/**
 * Конкретная реализация абстракции.
 */
class ConcreteConcept extends AbstractionConcept {
    private String details;

    public ConcreteConcept(String name, String details) {
        super(name);
        this.details = details;
    }

    @Override
    public void performAction() {
        System.out.println("Действие конкретной реализации: " + details);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
