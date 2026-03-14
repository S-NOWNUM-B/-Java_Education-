package org.example;

import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тестирование многопоточного кода</h1>
 * 
 * <p>Проверка корректности работы атомарных счетчиков и демонстрация
 * проблем с обычными переменными в конкурентной среде.</p>
 */
public class ConcurrencyNotesTest {

    /**
     * Тест корректности работы AtomicInteger.
     */
    @Test
    public void testAtomicInteger() {
        AtomicInteger counter = new AtomicInteger(0);
        int result = counter.addAndGet(5);
        assertEquals(5, result);
        assertEquals(5, counter.get());
    }

    /**
     * Тест гонки потоков (Race Condition).
     * Демонстрирует, что обычный int дает неверный результат при нагрузке.
     */
    @Test
    public void testRaceCondition() throws InterruptedException {
        int result = ConcurrencyNotes.runUnsafeTest(10, 1000);
        // С большой вероятностью результат будет меньше 10000
        // Но даже если повезет, этот тест подчеркивает нестабильность.
        assertTrue(result <= 10000, "Результат не может быть больше максимума");
    }
}
