package org.example;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тестирование коллекций и Stream API</h1>
 * 
 * <p>Проверка корректности работы структур данных и логики
 * обработки через стримы.</p>
 */
public class CollectionNotesTest {

    /**
     * Проверка уникальности в HashSet.
     */
    @Test
    public void testHashSetUniqueness() {
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Java");
        assertEquals(1, set.size(), "HashSet не должен содержать дубликатов");
    }

    /**
     * Проверка работы фильтрации через Stream API.
     */
    @Test
    public void testStreamFilter() {
        List<String> names = Arrays.asList("Ivan", "Petr", "Igor");
        List<String> result = CollectionNotes.filterByLetter(names, "I");
        
        assertEquals(2, result.size(), "Должно быть два имени на букву 'I'");
        assertTrue(result.contains("Ivan"));
        assertTrue(result.contains("Igor"));
    }

    /**
     * Тест маппинга в HashMap.
     */
    @Test
    public void testHashMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(100, "Excellent");
        assertEquals("Excellent", map.get(100));
        assertNull(map.get(99));
    }
}
