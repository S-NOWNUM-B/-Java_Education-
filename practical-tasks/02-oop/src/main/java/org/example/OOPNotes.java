package org.example;

/**
 * // ===== ООП: Наследование, Полиморфизм и Абстракция =====
 * 
 * В этом примере я строю систему оплаты. 
 * Тут показано, как "ссылка на родителя может держать потомка" 
 * и как интерфейсы отделяют "что делать" от "как делать".
 * 
 * Это практическое закрепление принципов ООП.
 */

// --- Интерфейс ---
interface PaymentMethod {
    void pay(double amount);
}

// --- Абстрактный класс ---
abstract class BankCard implements PaymentMethod {
    protected String cardNumber;

    public BankCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    protected void log(String msg) {
        System.out.println("[BankLog] " + cardNumber + ": " + msg);
    }
}

// --- Реализация 1 ---
class DebitCard extends BankCard {
    private double balance;

    public DebitCard(String cardNumber, double balance) {
        super(cardNumber);
        this.balance = balance;
    }

    @Override
    public void pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            log("Списание " + amount + ". Остаток: " + balance);
        } else {
            System.out.println("Ошибка: Денег нет!");
        }
    }
}

public class OOPNotes {
    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: ООП ===");

        // Полиморфизм: переменная типа интерфейса ссылается на конкретный объект
        PaymentMethod myPay = new DebitCard("4444-5555", 1000.0);
        myPay.pay(500.0);

        // Анонимный класс - когда лень создавать новый файл ради одного метода
        PaymentMethod cash = new PaymentMethod() {
            @Override
            public void pay(double amount) {
                System.out.println("Оплата наличными через кассу: " + amount);
            }
        };
        cash.pay(200.0);
    }
}
