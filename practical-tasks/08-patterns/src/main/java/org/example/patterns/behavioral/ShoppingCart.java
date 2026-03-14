package org.example.patterns.behavioral;

/**
 * <h1>Паттерн Strategy (Стратегия)</h1>
 * 
 * <p>Поведенческий паттерн, который определяет семейство схожих алгоритмов,
 * инкапсулирует каждый из них и делает их взаимозаменяемыми.</p>
 * 
 * <p>Позволяет менять алгоритм независимо от клиентов, которые его используют.</p>
 */

/**
 * Общий интерфейс для стратегий оплаты.
 */
interface PaymentStrategy {
    void processPayment(double amount);
}

/**
 * Реализация оплаты кредитной картой.
 */
class CreditCardStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата " + amount + " через Credit Card.");
    }
}

/**
 * Реализация оплаты через криптовалюту.
 */
class CryptoStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата " + amount + " через Bitcoin.");
    }
}

/**
 * Контекст, использующий стратегию.
 */
public class ShoppingCart {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(double amount) {
        if (strategy == null) {
            System.out.println("Выберите способ оплаты!");
            return;
        }
        strategy.processPayment(amount);
    }
}
