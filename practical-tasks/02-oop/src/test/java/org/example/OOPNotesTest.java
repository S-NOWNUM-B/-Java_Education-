package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тестирование объектно-ориентированной модели</h1>
 * 
 * <p>Эти тесты проверяют корректность реализации принципов ООП,
 * таких как инкапсуляция баланса и полиморфное поведение платежных методов.</p>
 */
public class OOPNotesTest {

    /**
     * Тест инкапсуляции и успешной оплаты.
     */
    @Test
    public void testSuccessfulPayment() {
        DebitCard card = new DebitCard("1111-2222", 1000.0);
        card.pay(400.0);
        assertEquals(600.0, card.getBalance(), "Баланс должен уменьшиться на сумму оплаты");
    }

    /**
     * Тест защиты от отрицательного баланса.
     */
    @Test
    public void testInsufficientFunds() {
        DebitCard card = new DebitCard("1111-2222", 100.0);
        card.pay(150.0);
        assertEquals(100.0, card.getBalance(), "Баланс не должен меняться при нехватке средств");
    }

    /**
     * Тест полиморфизма.
     */
    @Test
    public void testPolymorphism() {
        PaymentMethod method = new DebitCard("3333-4444", 500.0);
        assertTrue(method instanceof BankCard, "Дебетовая карта должна быть экземпляром BankCard");
        assertTrue(method instanceof PaymentMethod, "Дебетовая карта должна быть экземпляром PaymentMethod");
    }

    /**
     * Тест анонимного класса.
     */
    @Test
    public void testAnonymousClass() {
        final double[] paidAmount = {0};
        PaymentMethod testPay = new PaymentMethod() {
            @Override
            public void pay(double amount) {
                paidAmount[0] = amount;
            }
        };
        testPay.pay(77.0);
        assertEquals(77.0, paidAmount[0]);
    }
}
