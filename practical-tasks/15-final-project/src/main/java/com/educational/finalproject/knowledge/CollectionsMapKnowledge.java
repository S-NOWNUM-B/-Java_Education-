package com.educational.finalproject.knowledge;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * # Коллекции: Map
 *
 * ### Интерфейс Map
 * Хранит данные в виде пар "ключ-значение". Ключи уникальны.
 *
 * ### Реализации
 *
 * **1. HashMap**
 * *   Быстрый доступ: `O(1)`.
 * *   Не гарантирует порядок.
 * *   Позволяет один `null` ключ и множество `null` значений.
 *
 * **2. LinkedHashMap**
 * *   Сохраняет порядок добавления.
 *
 * **3. TreeMap**
 * *   Сортирует ключи (Natural order или `Comparator`).
 * *   Основана на красно-черном дереве.
 * *   Доступ: `O(log n)`.
 * *   Не позволяет `null` ключи.
 *
 * **4. Hashtable**
 * *   Устаревший аналог `HashMap`, методы синхронизированы.
 * *   Не позволяет `null`.
 */
public class CollectionsMapKnowledge {
    private Map<String, String> hashMap;
    private Map<String, String> linkedMap;
    private Map<String, String> treeMap;

    public CollectionsMapKnowledge() {
        this.hashMap = new HashMap<>();
        this.linkedMap = new LinkedHashMap<>();
        this.treeMap = new TreeMap<>();
    }

    public void putToAll(String key, String value) {
        hashMap.put(key, value);
        linkedMap.put(key, value);
        treeMap.put(key, value);
    }

    public Map<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    public Map<String, String> getLinkedMap() {
        return linkedMap;
    }

    public void setLinkedMap(Map<String, String> linkedMap) {
        this.linkedMap = linkedMap;
    }

    public Map<String, String> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(Map<String, String> treeMap) {
        this.treeMap = treeMap;
    }
}
