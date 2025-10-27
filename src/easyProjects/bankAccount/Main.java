package easyProjects.bankAccount;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Класс банковского счета
class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;
    private List<String> transactionHistory;

    // Конструктор
    public Account(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance >= 0 ? initialBalance : 0;
        this.transactionHistory = new ArrayList<>();

        if (initialBalance > 0) {
            transactionHistory.add("Initial deposit: $" + String.format("%.2f", initialBalance));
        }
    }

    // Геттер для номера счета
    public String getAccountNumber() {
        return accountNumber;
    }

    // Геттер для имени владельца
    public String getOwnerName() {
        return ownerName;
    }

    // Геттер для баланса
    public double getBalance() {
        return balance;
    }

    // Метод пополнения счета
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be positive");
            return false;
        }

        balance += amount;
        String transaction = "Deposit: +$" + String.format("%.2f", amount) +
                " | New balance: $" + String.format("%.2f", balance);
        transactionHistory.add(transaction);

        System.out.println("Successfully deposited: $" + String.format("%.2f", amount));
        return true;
    }

    // Метод снятия со счета
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive");
            return false;
        }

        if (amount > balance) {
            System.out.println("Error: Insufficient funds");
            System.out.println("Current balance: $" + String.format("%.2f", balance));
            System.out.println("Requested amount: $" + String.format("%.2f", amount));
            return false;
        }

        balance -= amount;
        String transaction = "Withdrawal: -$" + String.format("%.2f", amount) +
                " | New balance: $" + String.format("%.2f", balance);
        transactionHistory.add(transaction);

        System.out.println("Successfully withdrawn: $" + String.format("%.2f", amount));
        return true;
    }

    // Метод перевода на другой счет
    public boolean transfer(Account targetAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Error: Transfer amount must be positive");
            return false;
        }

        if (amount > balance) {
            System.out.println("Error: Insufficient funds for transfer");
            return false;
        }

        balance -= amount;
        targetAccount.balance += amount;

        String senderTransaction = "Transfer to " + targetAccount.getAccountNumber() +
                ": -$" + String.format("%.2f", amount) +
                " | New balance: $" + String.format("%.2f", balance);
        transactionHistory.add(senderTransaction);

        String receiverTransaction = "Transfer from " + this.accountNumber +
                ": +$" + String.format("%.2f", amount) +
                " | New balance: $" + String.format("%.2f", targetAccount.balance);
        targetAccount.transactionHistory.add(receiverTransaction);

        System.out.println("Successfully transferred: $" + String.format("%.2f", amount));
        return true;
    }

    // Метод отображения информации о счете
    public void displayAccountInfo() {
        System.out.println("\n=== Account Information ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
    }

    // Метод отображения истории транзакций
    public void displayTransactionHistory() {
        System.out.println("\n=== Transaction History ===");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet");
        } else {
            for (int i = 0; i < transactionHistory.size(); i++) {
                System.out.println((i + 1) + ". " + transactionHistory.get(i));
            }
        }
    }
}

// Класс банковской системы
class BankSystem {
    private List<Account> accounts;
    private int nextAccountNumber;

    public BankSystem() {
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1000;
    }

    // Метод создания нового счета
    public Account createAccount(String ownerName, double initialBalance) {
        String accountNumber = "ACC" + nextAccountNumber++;
        Account newAccount = new Account(accountNumber, ownerName, initialBalance);
        accounts.add(newAccount);

        System.out.println("\nAccount created successfully!");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Owner: " + ownerName);
        System.out.println("Initial Balance: $" + String.format("%.2f", initialBalance));

        return newAccount;
    }

    // Метод поиска счета по номеру
    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    // Метод отображения всех счетов
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts in the system");
            return;
        }

        System.out.println("\n=== All Accounts ===");
        for (Account account : accounts) {
            System.out.println("Account: " + account.getAccountNumber() +
                    " | Owner: " + account.getOwnerName() +
                    " | Balance: $" + String.format("%.2f", account.getBalance()));
        }
    }
}

// Класс для работы с пользовательским интерфейсом
class BankUI {
    private Scanner input;
    private BankSystem bankSystem;

    public BankUI() {
        this.input = new Scanner(System.in);
        this.bankSystem = new BankSystem();
    }

    public void run() {
        System.out.println("=== Bank Account Management System ===\n");

        while (true) {
            try {
                displayMainMenu();
                int choice = input.nextInt();
                input.nextLine(); // Очистка буфера

                if (choice == 9) {
                    System.out.println("\nThank you for using Bank System!");
                    break;
                }

                handleMenuChoice(choice);

            } catch (Exception e) {
                System.out.println("\nError: Invalid input. Please try again.");
                input.nextLine(); // Очистка буфера
            }
        }

        input.close();
    }

    // Отображение главного меню
    private void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create new account");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Transfer money");
        System.out.println("5. Check balance");
        System.out.println("6. View account information");
        System.out.println("7. View transaction history");
        System.out.println("8. View all accounts");
        System.out.println("9. Exit");
        System.out.print("Enter choice (1-9): ");
    }

    // Обработка выбора меню
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                createNewAccount();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                withdrawMoney();
                break;
            case 4:
                transferMoney();
                break;
            case 5:
                checkBalance();
                break;
            case 6:
                viewAccountInfo();
                break;
            case 7:
                viewTransactionHistory();
                break;
            case 8:
                bankSystem.displayAllAccounts();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Создание нового счета
    private void createNewAccount() {
        System.out.print("\nEnter owner name: ");
        String ownerName = input.nextLine();

        System.out.print("Enter initial deposit amount: $");
        double initialBalance = input.nextDouble();
        input.nextLine(); // Очистка буфера

        if (initialBalance < 0) {
            System.out.println("Error: Initial balance cannot be negative");
            return;
        }

        bankSystem.createAccount(ownerName, initialBalance);
    }

    // Пополнение счета
    private void depositMoney() {
        System.out.print("\nEnter account number: ");
        String accountNumber = input.nextLine();

        Account account = bankSystem.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found");
            return;
        }

        System.out.print("Enter deposit amount: $");
        double amount = input.nextDouble();
        input.nextLine(); // Очистка буфера

        account.deposit(amount);
    }

    // Снятие со счета
    private void withdrawMoney() {
        System.out.print("\nEnter account number: ");
        String accountNumber = input.nextLine();

        Account account = bankSystem.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found");
            return;
        }

        System.out.print("Enter withdrawal amount: $");
        double amount = input.nextDouble();
        input.nextLine(); // Очистка буфера

        account.withdraw(amount);
    }

    // Перевод между счетами
    private void transferMoney() {
        System.out.print("\nEnter sender account number: ");
        String senderAccountNumber = input.nextLine();

        Account senderAccount = bankSystem.findAccount(senderAccountNumber);
        if (senderAccount == null) {
            System.out.println("Error: Sender account not found");
            return;
        }

        System.out.print("Enter receiver account number: ");
        String receiverAccountNumber = input.nextLine();

        Account receiverAccount = bankSystem.findAccount(receiverAccountNumber);
        if (receiverAccount == null) {
            System.out.println("Error: Receiver account not found");
            return;
        }

        System.out.print("Enter transfer amount: $");
        double amount = input.nextDouble();
        input.nextLine(); // Очистка буфера

        senderAccount.transfer(receiverAccount, amount);
    }

    // Проверка баланса
    private void checkBalance() {
        System.out.print("\nEnter account number: ");
        String accountNumber = input.nextLine();

        Account account = bankSystem.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found");
            return;
        }

        System.out.println("\nCurrent balance: $" + String.format("%.2f", account.getBalance()));
    }

    // Просмотр информации о счете
    private void viewAccountInfo() {
        System.out.print("\nEnter account number: ");
        String accountNumber = input.nextLine();

        Account account = bankSystem.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found");
            return;
        }

        account.displayAccountInfo();
    }

    // Просмотр истории транзакций
    private void viewTransactionHistory() {
        System.out.print("\nEnter account number: ");
        String accountNumber = input.nextLine();

        Account account = bankSystem.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found");
            return;
        }

        account.displayTransactionHistory();
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        BankUI ui = new BankUI();
        ui.run();
    }
}
