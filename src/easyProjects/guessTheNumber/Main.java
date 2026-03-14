package easyProjects.guessTheNumber;

import java.util.Random;
import java.util.Scanner;

// Класс игры
class Game {
    private int secretNumber;
    private int attempts;
    private boolean isGameWon;
    private Random random;

    // Конструктор
    public Game() {
        this.random = new Random();
        this.attempts = 0;
        this.isGameWon = false;
        generateNumber();
    }

    // Метод генерации случайного числа
    public void generateNumber() {
        this.secretNumber = random.nextInt(100) + 1;
    }

    // Метод проверки попытки
    public String checkGuess(int userGuess) {
        attempts++;

        if (userGuess < 1 || userGuess > 100) {
            return "Number must be between 1 and 100";
        }

        if (userGuess < secretNumber) {
            return "Too low! Try again.";
        } else if (userGuess > secretNumber) {
            return "Too high! Try again.";
        } else {
            isGameWon = true;
            return "Congratulations! You guessed the number!";
        }
    }

    // Геттер для количества попыток
    public int getAttempts() {
        return attempts;
    }

    // Геттер для проверки победы
    public boolean isWon() {
        return isGameWon;
    }

    // Метод сброса игры
    public void reset() {
        this.attempts = 0;
        this.isGameWon = false;
        generateNumber();
    }
}

// Класс для работы с пользовательским интерфейсом
class GameUI {
    private Scanner input;
    private Game game;

    public GameUI() {
        this.input = new Scanner(System.in);
        this.game = new Game();
    }

    public void run() {
        System.out.println("=== Guess the Number Game ===");
        System.out.println("I have picked a number between 1 and 100.");
        System.out.println("Try to guess it!\n");

        while (!game.isWon()) {
            System.out.print("Enter your guess: ");

            try {
                int userGuess = input.nextInt();
                String result = game.checkGuess(userGuess);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("Please enter a valid number");
                input.next(); // Очистка некорректного ввода
            }
        }

        System.out.println("Number of attempts: " + game.getAttempts());

        playAgain();
    }

    private void playAgain() {
        System.out.print("\nDo you want to play again? (y/n): ");
        String answer = input.next().toLowerCase();

        if (answer.equals("y") || answer.equals("yes")) {
            game.reset();
            System.out.println("\n=== New Game ===");
            run();
        } else {
            System.out.println("Thanks for playing!");
            input.close();
        }
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        GameUI ui = new GameUI();
        ui.run();
    }
}
