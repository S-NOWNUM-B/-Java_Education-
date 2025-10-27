package easyProjects.calculator;

import java.util.Scanner;

// Интерфейс для операций
interface Operation {
    double execute(double num1, double num2);
}

// Класс для сложения
class Addition implements Operation {
    @Override
    public double execute(double num1, double num2) {
        return num1 + num2;
    }
}

// Класс для вычитания
class Subtraction implements Operation {
    @Override
    public double execute(double num1, double num2) {
        return num1 - num2;
    }
}

// Класс для умножения
class Multiplication implements Operation {
    @Override
    public double execute(double num1, double num2) {
        return num1 * num2;
    }
}

// Класс для деления
class Division implements Operation {
    @Override
    public double execute(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return num1 / num2;
    }
}

// Класс для остатка от деления
class Modulo implements Operation {
    @Override
    public double execute(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return num1 % num2;
    }
}

// Основной класс калькулятора
class Calculator {
    private double num1;
    private double num2;
    private Operation operation;

    // Конструктор
    public Calculator(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    // Установка операции
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    // Выполнение вычисления
    public double calculate() {
        if (operation == null) {
            throw new IllegalStateException("Operation is not set");
        }
        return operation.execute(num1, num2);
    }
}

// Класс для работы с пользовательским вводом
class CalculatorUI {
    private Scanner input;

    public CalculatorUI() {
        this.input = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Calculator ===");

        System.out.print("Please enter the first number: ");
        double num1 = input.nextDouble();

        System.out.print("Please enter the second number: ");
        double num2 = input.nextDouble();

        System.out.print("Please enter the operator (+, -, *, /, %): ");
        char operator = input.next().charAt(0);

        Calculator calculator = new Calculator(num1, num2);

        try {
            Operation operation = getOperation(operator);
            calculator.setOperation(operation);
            double result = calculator.calculate();
            System.out.println("The answer is: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid operator");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    private Operation getOperation(char operator) {
        switch (operator) {
            case '+':
                return new Addition();
            case '-':
                return new Subtraction();
            case '*':
                return new Multiplication();
            case '/':
                return new Division();
            case '%':
                return new Modulo();
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        CalculatorUI ui = new CalculatorUI();
        ui.run();
    }
}
