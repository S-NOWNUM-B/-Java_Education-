package com.educational.finalproject.knowledge;

/**
 * # Наследование (Inheritance)
 *
 * ### Механизм
 * *   Ключевое слово: `extends`.
 * *   Позволяет переиспользовать код и создавать иерархии типов.
 * *   В Java — только **одиночное** наследование классов.
 *
 * ### Ключевое слово super
 * *   Вызов конструктора родителя: `super()`.
 * *   Обращение к методам родителя: `super.method()`.
 *
 * ### Ограничения
 * *   Конструкторы не наследуются.
 * *   `private` поля не доступны в дочерних классах напрямую (только через геттеры).
 * *   Классы, помеченные `final`, нельзя наследовать.
 */
public class InheritanceConcept {
    protected String baseType;
    private String privateDetail;

    public InheritanceConcept(String baseType) {
        this.baseType = baseType;
    }

    public void commonBehavior() {
        System.out.println("Базовое поведение для: " + baseType);
    }

    public String getPrivateDetail() {
        return privateDetail;
    }

    public void setPrivateDetail(String privateDetail) {
        this.privateDetail = privateDetail;
    }
}

/**
 * Пример дочернего класса для демонстрации наследования.
 */
class SpecializedKnowledge extends InheritanceConcept {
    private String specialization;

    public SpecializedKnowledge(String baseType, String specialization) {
        super(baseType);
        this.specialization = specialization;
    }

    @Override
    public void commonBehavior() {
        super.commonBehavior();
        System.out.println("Специализация: " + specialization);
    }

    public void uniqueAction() {
        System.out.println("Уникальное действие наследника " + specialization);
    }
}
