package com.educational.finalproject.knowledge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * # Коллекции: List
 *
 * ### Интерфейс List
 * Упорядоченная коллекция, элементы которой доступны по индексу. Допускает дубликаты.
 *
 * ### Реализации
 *
 * **1. ArrayList**
 * *   Основан на динамическом массиве.
 * *   Быстрый доступ по индексу: `O(1)`.
 * *   Медленное добавление/удаление в середину: `O(n)`.
 * *   Лучший выбор для частого чтения.
 *
 * **2. LinkedList**
 * *   Основан на двусвязном списке.
 * *   Доступ по индексу: `O(n)`.
 * *   Быстрое добавление/удаление в начало/конец: `O(1)`.
 * *   Потребляет больше памяти (хранит ссылки).
 *
 * **3. Vector**
 * *   Аналог ArrayList, но методы синхронизированы (Thread-safe).
 * *   Устаревший, работает медленно.
 */
public class CollectionsListKnowledge {
    private List<String> arrayListExample;
    private List<String> linkedListExample;

    public CollectionsListKnowledge() {
        this.arrayListExample = new ArrayList<>();
        this.linkedListExample = new LinkedList<>();
    }

    public void addToArray(String item) {
        arrayListExample.add(item);
    }

    public void addToLinked(String item) {
        linkedListExample.add(item);
    }

    public List<String> getArrayListExample() {
        return arrayListExample;
    }

    public void setArrayListExample(List<String> arrayListExample) {
        this.arrayListExample = arrayListExample;
    }

    public List<String> getLinkedListExample() {
        return linkedListExample;
    }

    public void setLinkedListExample(List<String> linkedListExample) {
        this.linkedListExample = linkedListExample;
    }

    public void comparePerformance() {
        System.out.println("ArrayList подходит для поиска, LinkedList — для частых вставок в начало.");
    }
}
