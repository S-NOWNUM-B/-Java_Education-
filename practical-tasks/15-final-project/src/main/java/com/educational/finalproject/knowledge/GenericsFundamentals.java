package com.educational.finalproject.knowledge;

import java.util.ArrayList;
import java.util.List;

/**
 * # Generics (Обобщения)
 *
 * ### Описание
 * Позволяют создавать классы, интерфейсы и методы, где тип данных указан как параметр. Обеспечивают Type Safety (безопасность типов) на этапе компиляции.
 *
 * ### Type Erasure (Стирание типов)
 * Информация о типах доступна только во время компиляции. В рантайме `List<String>` и `List<Integer>` становятся просто `List<Object>`.
 *
 * ### Wildcards
 * *   `<?>`: Любой тип (Unbounded).
 * *   `<? extends T>`: Любой тип, являющийся наследником T (Upper Bound). Позволяет чтение.
 * *   `<? super T>`: Любой тип, являющийся предком T (Lower Bound). Позволяет запись.
 *
 * ### PECS (Producer Extends, Consumer Super)
 * Правило использования вайлдкардов для правильного чтения и записи.
 */
public class GenericsFundamentals<T> {
    private T data;
    private List<T> history;

    public GenericsFundamentals(T initialData) {
        this.data = initialData;
        this.history = new ArrayList<>();
        this.history.add(initialData);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        this.history.add(data);
    }

    public List<T> getHistory() {
        return history;
    }

    /**
     * Пример метода с вайлдкардом.
     */
    public static void printList(List<? extends Number> list) {
        for (Number n : list) {
            System.out.println("Число: " + n);
        }
    }

    /**
     * Обобщенный метод.
     */
    public <E> void displayGenericType(E element) {
        System.out.println("Тип переданного элемента: " + element.getClass().getSimpleName());
    }
}
