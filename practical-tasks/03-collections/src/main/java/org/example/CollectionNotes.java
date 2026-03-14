package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * // ===== Коллекции и Stream API =====
 * 
 * Разбираем, чем HashSet отличается от TreeSet и как 
 * писать меньше кода с помощью Стримов.
 */
public class CollectionNotes {
    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: Коллекции ===");

        // --- 1. Set (Множества) ---
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("A"); // Дубликат не добавится
        System.out.println("HashSet (без дублей): " + set);

        // --- 2. Map (Словари) ---
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "Python");
        System.out.println("Элемент по ключу 1: " + map.get(1));

        // --- 3. Stream API ---
        List<String> names = Arrays.asList("Ivan", "Petr", "Anna", "Igor");

        List<String> result = names.stream()
            .filter(n -> n.startsWith("I")) // Только те, что на 'I'
            .map(String::toUpperCase)     // В верхний регистр
            .sorted()                      // Сортировка
            .collect(Collectors.toList()); // Собираем в список

        System.out.println("Результат Stream API: " + result);

        // --- 4. Optional ---
        Optional<String> first = names.stream().findFirst();
        first.ifPresent(n -> System.out.println("Первое имя: " + n));
    }
}
