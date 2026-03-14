package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Основы Java: Типы данных, Память и Пул Строк</h1>
 * 
 * <p>Данный модуль посвящен фундаментальным концепциям Java, понимание которых необходимо
 * для написания эффективного и безопасного кода. Мы рассматриваем как JVM управляет
 * данными на низком уровне.</p>
 *
 * <h2>1. Система типов</h2>
 * Java является строго типизированным языком. Все типы делятся на:
 * <ul>
 *     <li><b>Примитивные:</b> хранят значения непосредственно в стеке (byte, short, int, long, float, double, char, boolean).</li>
 *     <li><b>Ссылочные:</b> хранят адрес объекта, находящегося в куче (String, массивы, классы).</li>
 * </ul>
 *
 * <h2>2. Управление памятью</h2>
 * <ul>
 *     <li><b>Stack (Стек):</b> Быстрая память для локальных переменных и примитивов. Работает по принципу LIFO.</li>
 *     <li><b>Heap (Куча):</b> Общая область памяти для всех объектов. Здесь работает Garbage Collector.</li>
 * </ul>
 *
 * <h2>3. Пул Строк (String Pool)</h2>
 * Специальная область в куче для оптимизации хранения строк. Если строка создается литералом,
 * JVM сначала ищет её в пуле. Если через <code>new String()</code> — создается новый объект в Heap.
 *
 * @author Student
 * @version 1.1
 */
public class BasicsNotes {

    /**
     * Точка входа в программу.
     * Демонстрирует нюансы работы с типами и памятью.
     */
    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: Основы Java ===");

        // --- 1. Целочисленные типы и переполнение ---
        // byte: 8 бит, диапазон от -128 до 127.
        byte smallNumber = 127; 
        smallNumber++; 
        // В Java нет исключения при переполнении целых чисел, происходит "зацикливание".
        System.out.println("Переполнение byte (127 + 1): " + smallNumber); // -128 

        // --- 2. Точность плавающей точки ---
        // Использование double для финансов - грубая ошибка из-за специфики IEEE 754.
        double a = 0.1;
        double b = 0.2;
        System.out.println("Результат 0.1 + 0.2: " + (a + b)); 
        
        // --- 3. String Pool и сравнение ---
        String s1 = "Java"; 
        String s2 = "Java"; 
        String s3 = new String("Java"); 

        // Сравнение ссылок (адресов в памяти)
        System.out.println("s1 == s2 (оба из пула): " + (s1 == s2)); // true
        System.out.println("s1 == s3 (пул vs куча): " + (s1 == s3)); // false
        // Правильное сравнение содержимого
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true

        // --- 4. Integer Cache ---
        // Обертки над примитивами кэшируются в диапазоне [-128; 127] для экономии памяти.
        Integer x = 127;
        Integer y = 127;
        Integer bigX = 128;
        Integer bigY = 128;

        System.out.println("127 == 127 (Integer Cache): " + (x == y)); // true
        System.out.println("128 == 128 (No cache): " + (bigX == bigY)); // false

        // --- 5. Модификатор final ---
        // Для ссылочных типов final запрещает менять саму ссылку, но не состояние объекта.
        final List<String> list = new ArrayList<>();
        list.add("Java"); // Допустимо
        list.add("Spring");
        System.out.println("Элементы списка: " + list);
    }

    /**
     * Помогательный метод для тестов.
     * @param a первое число
     * @param b второе число
     * @return сумма чисел
     */
    public static int add(int a, int b) {
        return a + b;
    }
}
