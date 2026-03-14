package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- Программа для расчета площади прямоугольника ---");
        
        try {
            System.out.print("Введите длину: ");
            double length = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Введите ширину: ");
            double width = Double.parseDouble(scanner.nextLine());
            
            if (length <= 0 || width <= 0) {
                System.out.println("Ошибка: стороны должны быть положительными числами!");
            } else {
                double area = length * width;
                System.out.printf("Площадь прямоугольника: %.2f\n", area);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено не число!");
        } finally {
            scanner.close();
            System.out.println("Программа завершена.");
        }
    }
}
