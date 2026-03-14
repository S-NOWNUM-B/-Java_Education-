package com.educational.finalproject.knowledge;

/**
 * # Классы и объекты (Classes & Objects)
 *
 * ### Класс
 * "Чертеж" или шаблон для создания объектов. Описывает состояние (поля) и поведение (методы).
 *
 * ### Объект
 * Экземпляр класса в памяти.
 *
 * ### Конструктор
 * Специальный метод для инициализации объекта. Вызывается при создании через `new`.
 * *   Имя совпадает с именем класса.
 * *   Нет возвращаемого типа.
 * *   Бывает "по умолчанию" (без аргументов), если не созданы другие.
 */
public class CoreClassesConcepts {
    private String className;
    private int instancesCount;

    /**
     * Конструктор по умолчанию.
     */
    public CoreClassesConcepts() {
        this.className = "Default";
        this.instancesCount = 0;
    }

    /**
     * Параметризованный конструктор.
     * @param className имя концепции
     * @param instancesCount количество примеров
     */
    public CoreClassesConcepts(String className, int instancesCount) {
        this.className = className;
        this.instancesCount = instancesCount;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getInstancesCount() {
        return instancesCount;
    }

    public void setInstancesCount(int instancesCount) {
        this.instancesCount = instancesCount;
    }

    public void demonstrate() {
        System.out.println("Концепция: " + className);
        System.out.println("Экземпляров: " + instancesCount);
    }
}
