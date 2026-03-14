package com.educational.finalproject.knowledge;

/**
 * # Инкапсуляция (Encapsulation)
 *
 * ### Модификаторы доступа
 * *   `public`: доступ отовсюду.
 * *   `protected`: внутри пакета + наследники.
 * *   `default` (без слова): только внутри пакета.
 * *   `private`: только внутри класса.
 *
 * ### Принцип
 * Скрыть внутреннее состояние объекта и выставить наружу только необходимые методы для взаимодействия.
 */
public class EncapsulationPrinciple {
    private String secretValue;
    private int restrictionLevel;

    public EncapsulationPrinciple(String secretValue, int restrictionLevel) {
        this.secretValue = secretValue;
        this.restrictionLevel = restrictionLevel;
    }

    /**
     * Пример инкапсуляции: доступ к полю через геттер.
     */
    public String getSecretValue() {
        if (restrictionLevel > 5) {
            return "ACCESS DENIED";
        }
        return secretValue;
    }

    /**
     * Пример инкапсуляции: изменение поля через сеттер с валидацией.
     */
    public void setSecretValue(String secretValue) {
        if (secretValue != null && !secretValue.isEmpty()) {
            this.secretValue = secretValue;
        }
    }

    public int getRestrictionLevel() {
        return restrictionLevel;
    }

    public void setRestrictionLevel(int restrictionLevel) {
        if (restrictionLevel >= 0 && restrictionLevel <= 10) {
            this.restrictionLevel = restrictionLevel;
        }
    }
}
