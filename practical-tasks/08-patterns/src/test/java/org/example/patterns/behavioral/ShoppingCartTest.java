package org.example.patterns.behavioral;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тесты паттерна Strategy</h1>
 */
public class ShoppingCartTest {

    @Test
    public void testCreditCardPayment() {
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new CreditCardStrategy());

        // Перехватываем вывод в консоль для проверки
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cart.checkout(100.0);

        assertTrue(outContent.toString().contains("100.0 через Credit Card"));
        
        System.setOut(System.out); // Возвращаем стандартный вывод
    }

    @Test
    public void testCryptoPayment() {
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new CryptoStrategy());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cart.checkout(50.0);

        assertTrue(outContent.toString().contains("50.0 через Bitcoin"));
        
        System.setOut(System.out);
    }
}
