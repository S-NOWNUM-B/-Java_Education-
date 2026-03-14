package com.educational.finalproject.knowledge;

/**
 * # Модификаторы Static и Final (Static & Final)
 *
 * ### Static
 * Принадлежит классу, а не объекту. Общий для всех экземпляров.
 * *   **Static поля**: Хранят состояние, единое для всех объектов класса.
 * *   **Static методы**: Могут быть вызваны без создания объекта. Не имеют доступа к `this`.
 * *   **Static блоки**: Выполняются один раз при загрузке класса.
 *
 * ### Final
 * Запрещает изменения.
 * *   **Final переменная**: Константа, инициализируется один раз.
 * *   **Final метод**: Нельзя переопределить в наследниках.
 * *   **Final класс**: Нельзя наследовать.
 */
public final class ModifiersKnowledge {
    public static final String GLOBAL_CONCEPT = "Immutability and Shared State";
    private static int accessCounter = 0;
    
    private final long creationTimestamp;
    private final String metadata;

    static {
        System.out.println("Загрузка знаний о модификаторах...");
    }

    public ModifiersKnowledge(String metadata) {
        this.creationTimestamp = System.currentTimeMillis();
        this.metadata = metadata;
        incrementCounter();
    }

    public static synchronized void incrementCounter() {
        accessCounter++;
    }

    public static int getAccessCounter() {
        return accessCounter;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public String getMetadata() {
        return metadata;
    }

    public final void displayFixedInfo() {
        System.out.println("Метаданные (не могут быть изменены наследниками): " + metadata);
    }
}
