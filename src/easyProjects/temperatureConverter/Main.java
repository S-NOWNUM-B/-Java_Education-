package easyProjects.temperatureConverter;

import java.util.Scanner;

// Перечисление для единиц измерения температуры
enum TemperatureUnit {
    CELSIUS,
    FAHRENHEIT,
    KELVIN
}

// Класс для хранения температуры (data class)
class Temperature {
    private double value;
    private TemperatureUnit unit;

    // Конструктор
    public Temperature(double value, TemperatureUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // Геттеры
    public double getValue() {
        return value;
    }

    public TemperatureUnit getUnit() {
        return unit;
    }

    // Метод для отображения температуры
    public String display() {
        String unitSymbol = switch (unit) {
            case CELSIUS -> "°C";
            case FAHRENHEIT -> "°F";
            case KELVIN -> "K";
        };
        return String.format("%.2f%s", value, unitSymbol);
    }
}

// Класс конвертера температуры
class TemperatureConverter {

    // Конвертация из Цельсия в Фаренгейт
    public double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32;
    }

    // Конвертация из Цельсия в Кельвин
    public double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    // Конвертация из Фаренгейта в Цельсий
    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9.0;
    }

    // Конвертация из Фаренгейта в Кельвин
    public double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9.0 + 273.15;
    }

    // Конвертация из Кельвина в Цельсий
    public double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    // Конвертация из Кельвина в Фаренгейт
    public double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9.0 / 5.0 + 32;
    }

    // Универсальный метод конвертации
    public Temperature convert(Temperature temperature, TemperatureUnit targetUnit) {
        double value = temperature.getValue();
        TemperatureUnit sourceUnit = temperature.getUnit();

        // Если единицы совпадают, возвращаем исходную температуру
        if (sourceUnit == targetUnit) {
            return temperature;
        }

        double result = 0;

        // Конвертация в зависимости от исходной и целевой единицы
        switch (sourceUnit) {
            case CELSIUS:
                result = switch (targetUnit) {
                    case FAHRENHEIT -> celsiusToFahrenheit(value);
                    case KELVIN -> celsiusToKelvin(value);
                    default -> value;
                };
                break;
            case FAHRENHEIT:
                result = switch (targetUnit) {
                    case CELSIUS -> fahrenheitToCelsius(value);
                    case KELVIN -> fahrenheitToKelvin(value);
                    default -> value;
                };
                break;
            case KELVIN:
                result = switch (targetUnit) {
                    case CELSIUS -> kelvinToCelsius(value);
                    case FAHRENHEIT -> kelvinToFahrenheit(value);
                    default -> value;
                };
                break;
        }

        return new Temperature(result, targetUnit);
    }
}

// Класс для работы с пользовательским интерфейсом
class ConverterUI {
    private Scanner input;
    private TemperatureConverter converter;

    public ConverterUI() {
        this.input = new Scanner(System.in);
        this.converter = new TemperatureConverter();
    }

    public void run() {
        System.out.println("=== Temperature Converter ===\n");

        while (true) {
            try {
                // Ввод исходной температуры
                Temperature sourceTemp = getTemperatureInput();

                // Выбор целевой единицы
                TemperatureUnit targetUnit = getTargetUnit(sourceTemp.getUnit());

                // Конвертация
                Temperature result = converter.convert(sourceTemp, targetUnit);

                // Вывод результата
                System.out.println("\nResult: " + sourceTemp.display() + " = " + result.display());

                // Продолжить или выйти
                if (!askContinue()) {
                    break;
                }

                System.out.println();

            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please try again.\n");
                input.nextLine(); // Очистка буфера
            }
        }

        System.out.println("Thanks for using Temperature Converter!");
        input.close();
    }

    // Метод для ввода температуры
    private Temperature getTemperatureInput() {
        System.out.println("Select source unit:");
        System.out.println("1. Celsius (°C)");
        System.out.println("2. Fahrenheit (°F)");
        System.out.println("3. Kelvin (K)");
        System.out.print("Enter choice (1-3): ");

        int choice = input.nextInt();
        TemperatureUnit unit = getUnitFromChoice(choice);

        System.out.print("Enter temperature value: ");
        double value = input.nextDouble();

        // Валидация для Кельвина (не может быть меньше 0)
        if (unit == TemperatureUnit.KELVIN && value < 0) {
            throw new IllegalArgumentException("Kelvin cannot be negative");
        }

        return new Temperature(value, unit);
    }

    // Метод для выбора целевой единицы
    private TemperatureUnit getTargetUnit(TemperatureUnit sourceUnit) {
        System.out.println("\nSelect target unit:");

        int choice = 1;
        if (sourceUnit != TemperatureUnit.CELSIUS) {
            System.out.println(choice + ". Celsius (°C)");
            choice++;
        }
        if (sourceUnit != TemperatureUnit.FAHRENHEIT) {
            System.out.println(choice + ". Fahrenheit (°F)");
            choice++;
        }
        if (sourceUnit != TemperatureUnit.KELVIN) {
            System.out.println(choice + ". Kelvin (K)");
        }

        System.out.print("Enter choice: ");
        int targetChoice = input.nextInt();

        // Определяем целевую единицу в зависимости от исходной
        int actualChoice = 0;
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            if (unit != sourceUnit) {
                actualChoice++;
                if (actualChoice == targetChoice) {
                    return unit;
                }
            }
        }

        throw new IllegalArgumentException("Invalid choice");
    }

    // Преобразование номера выбора в единицу измерения
    private TemperatureUnit getUnitFromChoice(int choice) {
        return switch (choice) {
            case 1 -> TemperatureUnit.CELSIUS;
            case 2 -> TemperatureUnit.FAHRENHEIT;
            case 3 -> TemperatureUnit.KELVIN;
            default -> throw new IllegalArgumentException("Invalid choice");
        };
    }

    // Спросить пользователя, хочет ли он продолжить
    private boolean askContinue() {
        System.out.print("\nConvert another temperature? (y/n): ");
        String answer = input.next().toLowerCase();
        return answer.equals("y") || answer.equals("yes");
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        ConverterUI ui = new ConverterUI();
        ui.run();
    }
}
