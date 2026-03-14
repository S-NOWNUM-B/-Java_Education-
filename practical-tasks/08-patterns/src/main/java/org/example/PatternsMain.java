package org.example;

import org.example.patterns.creational.DatabaseConnector;
import org.example.patterns.behavioral.ShoppingCart;

/**
 * <h1>Паттерны проектирования на практике</h1>
 * 
 * <p>Этот модуль демонстрирует мощь и гибкость объектно-ориентированных шаблонов.
 * Мы используем их, чтобы код был расширяемым и легким в поддержке.</p>
 */
public class PatternsMain {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация паттернов проектирования ===");

        // 1. Singleton
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.connect();

        // 2. Strategy
        // В реальном приложении мы бы подставляли стратегии динамически
        ShoppingCart cart = new ShoppingCart();
        
        System.out.println("--- Оформляем заказ 1 ---");
        // cart.setPaymentStrategy(new CreditCardStrategy()); // Ошибка видимости? Исправим в тестах или через Factory
        
        System.out.println("\n[ЗАМЕТКА] Паттерны — это не догма, а набор проверенных решений.");
    }
}
