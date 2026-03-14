package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тестирование базовых концепций Java</h1>
 * 
 * <p>Тесты позволяют не только убедиться в корректности кода, но и
 * служат "живой документацией". В данном классе мы проверяем
 * особенности работы с памятью и типами.</p>
 */
public class BasicsNotesTest {

    /**
     * Проверка корректности работы метода сложения.
     * Классический Unit-тест.
     */
    @Test
    public void testAddition() {
        assertEquals(5, BasicsNotes.add(2, 3), "Сумма 2 + 3 должна быть равна 5");
    }

    /**
     * Тест на проверку кэширования Integer.
     * Доказывает, что значения в диапазоне [-128, 127] разделяют один объект.
     */
    @Test
    public void testIntegerCache() {
        Integer a = 127;
        Integer b = 127;
        assertSame(a, b, "Должны быть одним и тем же объектом в памяти");

        Integer c = 128;
        Integer d = 128;
        assertNotSame(c, d, "Для значений > 127 должны создаваться разные объекты");
    }

    /**
     * Тест на сравнение строк.
     * Проверяет разницу между литералом и созданием через new.
     */
    @Test
    public void testStringEquality() {
        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");

        assertSame(s1, s2, "Литералы должны указывать на одну строку в пуле");
        assertNotSame(s1, s3, "Строка через new не должна быть в пуле по умолчанию");
        assertEquals(s1, s3, "Содержимое строк должно быть идентичным");
    }

    /**
     * Тест на переполнение byte.
     * Показывает, что 127 + 1 превращается в -128.
     */
    @Test
    public void testByteOverflow() {
        byte b = 127;
        b++;
        assertEquals(-128, b, "Переполнение byte должно привести к минимальному значению");
    }
}
