package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * // ===== Основы Java: Типы, Память и Пул Строк =====
 * 
 * В этом файле я разбираю базу: как компьютер хранит числа, 
 * почему 0.1 + 0.2 != 0.3 и почему String - это "магия".
 * 
 * Мы используем этот файл чтобы закрепить понимание того,
 * как JVM управляет данными под капотом.
 */
public class BasicsNotes {

    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: Основы Java ===");

        // --- 1. Целочисленные типы и переполнение ---
        // byte: от -128 до 127. 
        byte smallNumber = 127; 
        smallNumber++; 
        // Заметка: Java не выдаст ошибку, она просто "провернет" число по кругу (Overflow).
        System.out.println("Переполнение byte (127 + 1): " + smallNumber); // -128 

        // --- 2. Точность плавающей точки (IEEE 754) ---
        // Никогда не используй double для финансовых расчетов!
        double a = 0.1;
        double b = 0.2;
        System.out.println("0.1 + 0.2 = " + (a + b)); // 0.30000000000000004
        
        // --- 3. String Pool (Пул строк) ---
        // Строки - это объекты, но Java оптимизирует их хранение.
        String s1 = "Java"; // Ссылка в пуле строк
        String s2 = "Java"; // Та же ссылка из пула
        String s3 = new String("Java"); // Новый объект в куче, не в пуле

        System.out.println("s1 == s2: " + (s1 == s2)); // true (один и тот же адрес)
        System.out.println("s1 == s3: " + (s1 == s3)); // false (разные адреса)
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true (содержимое одинаковое)

        // --- 4. Integer Cache (Кэширование оберток) ---
        // Java кэширует Integer в диапазоне [-128, 127]
        Integer x = 127;
        Integer y = 127;
        Integer bigX = 128;
        Integer bigY = 128;

        System.out.println("127 == 127 (Integer): " + (x == y)); // true (из кэша)
        System.out.println("128 == 128 (Integer): " + (bigX == bigY)); // false (разные объекты!)

        // --- 5. Эффект final на ссылочных типах ---
        final List<String> colors = new ArrayList<>();
        colors.add("Red"); // Можно! Состояние объекта менять можно.
        colors.add("Green");
        // colors = new ArrayList<>(); // Ошибка! Нельзя привязать переменную к другому объекту.
        
        System.out.println("Коллекция colors: " + colors);
    }
}
