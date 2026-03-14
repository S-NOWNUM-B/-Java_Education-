package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>Java Collections Framework и Stream API</h1>
 * 
 * <p>Коллекции — это структуры данных, предназначенные для хранения групп объектов.
 * В Java они объединяются в единую иерархию под интерфейсом <code>Collection</code>.</p>
 *
 * <h2>1. List (Списки)</h2>
 * Упорядоченные коллекции, поддерживают доступ по индексу и дубликаты.
 * Основные реализации: <code>ArrayList</code> (на базе массива) и <code>LinkedList</code> (связный список).
 *
 * <h2>2. Set (Множества)</h2>
 * Коллекции, не содержащие дубликатов. <code>HashSet</code> использует хеш-таблицу (быстро),
 * <code>TreeSet</code> хранит элементы в отсортированном порядке.
 *
 * <h2>3. Map (Словари/Отображения)</h2>
 * Хранят пары "ключ-значение". Ключи уникальны. Не наследуются от Collection.
 *
 * <h2>4. Stream API</h2>
 * Появился в Java 8. Позволяет обрабатывать данные в декларативном стиле
 * (фильтрация, маппинг, сортировка) без явных циклов.
 *
 * @author Student
 */
public class CollectionNotes {
    /**
     * Демонстрация работы с коллекциями и стримами.
     */
    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: Коллекции ===");

        // --- 1. Set (Множества) ---
        // HashSet - не гарантирует порядок, очень быстрая проверка на наличие.
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("A"); // Второе "A" будет проигнорировано
        System.out.println("HashSet (уникальные элементы): " + set);

        // --- 2. Map (Ключ-Значение) ---
        // HashMap - стандарт de-facto для хранения пар данных.
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "Python");
        System.out.println("Значение по ключу 1: " + map.get(1));

        // --- 3. Stream API ---
        List<String> names = Arrays.asList("Ivan", "Petr", "Anna", "Igor");

        // Декларативная цепочка вызовов
        List<String> result = names.stream()
            .filter(n -> n.startsWith("I")) // Оставляем только на букву 'I'
            .map(String::toUpperCase)     // В верхний регистр
            .sorted()                      // Алфавитный порядок
            .collect(Collectors.toList()); // Собираем обратно в список

        System.out.println("Результат Stream API: " + result);

        // --- 4. Optional ---
        // Способ избежать NullPointerException при работе с объектами, которых может не быть.
        Optional<String> first = names.stream().findFirst();
        first.ifPresent(n -> System.out.println("Первый найденный элемент: " + n));
    }

    /**
     * Метод для демонстрации стримов в тестах.
     * @param input входной список строк
     * @return отфильтрованный список
     */
    public static List<String> filterByLetter(List<String> input, String letter) {
        return input.stream()
                .filter(s -> s.startsWith(letter))
                .collect(Collectors.toList());
    }
}
