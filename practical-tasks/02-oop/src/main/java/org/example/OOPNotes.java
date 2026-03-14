package org.example;

/**
 * <h1>Объектно-Ориентированное Программирование (ООП) в Java</h1>
 * 
 * <p>Данный модуль демонстрирует применение четырех основных принципов ООП:
 * Инкапсуляция, Наследование, Полиморфизм и Абстракция.</p>
 *
 * <h2>1. Инкапсуляция</h2>
 * Механизм сокрытия внутренней реализации объекта и защиты его данных
 * от неконтролируемого доступа. Реализуется с помощью модификаторов доступа
 * (private, protected, public) и методов доступа (геттеры/сеттеры).
 *
 * <h2>2. Наследование</h2>
 * Позволяет описывать новый класс (потомок) на основе существующего (родитель).
 * Потомок наследует поля и методы родителя, что способствует повторному 
 * использованию кода.
 *
 * <h2>3. Полиморфизм</h2>
 * Способность объекта принимать множество форм. Самый частый вид — использование
 * ссылки на родительский класс для работы с объектом дочернего класса.
 *
 * <h2>4. Абстракция</h2>
 * Выделение наиболее важных характеристик объекта и отсекание второстепенных.
 * Реализуется с помощью абстрактных классов и интерфейсов.
 *
 * @author Student
 */

/**
 * Контракт для всех способов оплаты.
 */
interface PaymentMethod {
    /**
     * Выполнить оплату.
     * @param amount сумма для списания
     */
    void pay(double amount);
}

/**
 * Базовый абстрактный класс для банковских карт.
 * Содержит общую логику для всех типов карт (номер карты).
 */
abstract class BankCard implements PaymentMethod {
    private String cardNumber;

    public BankCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Логирование операций.
     * @param msg сообщение для вывода
     */
    protected void log(String msg) {
        System.out.println("[BankLog] " + cardNumber + ": " + msg);
    }

    public String getCardNumber() {
        return cardNumber;
    }
}

/**
 * Реализация дебетовой карты.
 */
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
            log("Списание " + amount + ". Текущий остаток: " + balance);
        } else {
            System.out.println("Ошибка: Недостаточно средств на карте " + getCardNumber());
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class OOPNotes {
    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: ООП ===");

        // Демонстрация полиморфизма: использование типа интерфейса
        PaymentMethod myPay = new DebitCard("4444-5555", 1500.0);
        myPay.pay(500.0);

        // Анонимный класс - полезен, когда реализация нужна только один раз
        PaymentMethod cash = new PaymentMethod() {
            @Override
            public void pay(double amount) {
                System.out.println("[Cash] Оплата наличными: " + amount);
            }
        };
        cash.pay(200.0);
    }
}
