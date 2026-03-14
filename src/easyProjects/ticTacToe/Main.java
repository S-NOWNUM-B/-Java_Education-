package easyProjects.ticTacToe;

import java.util.Scanner;

// Класс игрока
class Player {
    private String name;
    private char symbol;
    private int wins;

    // Конструктор
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getWins() {
        return wins;
    }

    // Метод увеличения счетчика побед
    public void incrementWins() {
        wins++;
    }

    // Метод отображения информации о игроке
    public void displayInfo() {
        System.out.println(name + " (" + symbol + ") - Wins: " + wins);
    }
}

// Класс игрового поля
class Board {
    private char[][] grid;
    private final int SIZE = 3;
    private int movesCount;

    // Конструктор
    public Board() {
        this.grid = new char[SIZE][SIZE];
        this.movesCount = 0;
        initializeBoard();
    }

    // Метод инициализации доски
    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = ' ';
            }
        }
    }

    // Метод сброса доски
    public void reset() {
        initializeBoard();
        movesCount = 0;
    }

    // Метод отображения доски
    public void display() {
        System.out.println("\n     1   2   3");
        System.out.println("   +---+---+---+");

        for (int row = 0; row < SIZE; row++) {
            System.out.print(" " + (row + 1) + " ");
            for (int col = 0; col < SIZE; col++) {
                System.out.print("| " + grid[row][col] + " ");
            }
            System.out.println("|");
            System.out.println("   +---+---+---+");
        }
        System.out.println();
    }

    // Метод отображения доски с инструкцией
    public void displayWithGuide() {
        System.out.println("\n=== Game Board ===");
        display();
        System.out.println("Enter row (1-3) and column (1-3) to make a move");
    }

    // Метод проверки, свободна ли клетка
    public boolean isCellEmpty(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }
        return grid[row][col] == ' ';
    }

    // Метод совершения хода
    public boolean makeMove(int row, int col, char symbol) {
        if (!isCellEmpty(row, col)) {
            return false;
        }

        grid[row][col] = symbol;
        movesCount++;
        return true;
    }

    // Метод проверки заполненности доски
    public boolean isFull() {
        return movesCount >= SIZE * SIZE;
    }

    // Метод проверки победы по горизонтали
    private boolean checkRows(char symbol) {
        for (int row = 0; row < SIZE; row++) {
            if (grid[row][0] == symbol &&
                    grid[row][1] == symbol &&
                    grid[row][2] == symbol) {
                return true;
            }
        }
        return false;
    }

    // Метод проверки победы по вертикали
    private boolean checkColumns(char symbol) {
        for (int col = 0; col < SIZE; col++) {
            if (grid[0][col] == symbol &&
                    grid[1][col] == symbol &&
                    grid[2][col] == symbol) {
                return true;
            }
        }
        return false;
    }

    // Метод проверки победы по диагоналям
    private boolean checkDiagonals(char symbol) {
        // Главная диагональ (слева-направо)
        if (grid[0][0] == symbol &&
                grid[1][1] == symbol &&
                grid[2][2] == symbol) {
            return true;
        }

        // Побочная диагональ (справа-налево)
        if (grid[0][2] == symbol &&
                grid[1][1] == symbol &&
                grid[2][0] == symbol) {
            return true;
        }

        return false;
    }

    // Метод проверки победителя
    public boolean checkWinner(char symbol) {
        return checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol);
    }

    // Метод получения количества сделанных ходов
    public int getMovesCount() {
        return movesCount;
    }

    // Метод получения размера доски
    public int getSize() {
        return SIZE;
    }
}

// Перечисление состояния игры
enum GameState {
    IN_PROGRESS,
    PLAYER1_WON,
    PLAYER2_WON,
    DRAW
}

// Класс игры
class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameState state;
    private int gamesPlayed;
    private int draws;

    // Конструктор
    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.state = GameState.IN_PROGRESS;
        this.gamesPlayed = 0;
        this.draws = 0;
    }

    // Метод начала новой игры
    public void startNewGame() {
        board.reset();
        currentPlayer = player1;
        state = GameState.IN_PROGRESS;
        gamesPlayed++;
    }

    // Метод совершения хода
    public boolean makeMove(int row, int col) {
        // Конвертируем из 1-based в 0-based индексацию
        row--;
        col--;

        // Проверяем валидность хода
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            System.out.println("Error: Position outside board boundaries!");
            return false;
        }

        if (!board.isCellEmpty(row, col)) {
            System.out.println("Error: Cell already occupied!");
            return false;
        }

        // Совершаем ход
        board.makeMove(row, col, currentPlayer.getSymbol());

        return true;
    }

    // Метод проверки состояния игры
    public void checkGameState() {
        // Проверка на победу текущего игрока
        if (board.checkWinner(currentPlayer.getSymbol())) {
            if (currentPlayer == player1) {
                state = GameState.PLAYER1_WON;
                player1.incrementWins();
            } else {
                state = GameState.PLAYER2_WON;
                player2.incrementWins();
            }
            return;
        }

        // Проверка на ничью
        if (board.isFull()) {
            state = GameState.DRAW;
            draws++;
            return;
        }

        state = GameState.IN_PROGRESS;
    }

    // Метод смены игрока
    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    // Метод получения текущего игрока
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // Метод получения доски
    public Board getBoard() {
        return board;
    }

    // Метод получения состояния игры
    public GameState getState() {
        return state;
    }

    // Метод проверки, закончена ли игра
    public boolean isGameOver() {
        return state != GameState.IN_PROGRESS;
    }

    // Метод отображения результата игры
    public void displayResult() {
        board.display();

        switch (state) {
            case PLAYER1_WON:
                System.out.println("🎉 " + player1.getName() + " (" + player1.getSymbol() + ") WINS!");
                break;
            case PLAYER2_WON:
                System.out.println("🎉 " + player2.getName() + " (" + player2.getSymbol() + ") WINS!");
                break;
            case DRAW:
                System.out.println("🤝 It's a DRAW!");
                break;
            default:
                break;
        }
    }

    // Метод отображения статистики
    public void displayStatistics() {
        System.out.println("\n=== Game Statistics ===");
        System.out.println("Total games played: " + gamesPlayed);
        player1.displayInfo();
        player2.displayInfo();
        System.out.println("Draws: " + draws);

        if (gamesPlayed > 0) {
            System.out.printf("\n%s win rate: %.1f%%%n",
                    player1.getName(),
                    (double) player1.getWins() / gamesPlayed * 100);
            System.out.printf("%s win rate: %.1f%%%n",
                    player2.getName(),
                    (double) player2.getWins() / gamesPlayed * 100);
        }
    }
}

// Класс для работы с пользовательским интерфейсом
class TicTacToeUI {
    private Scanner input;
    private Game game;

    public TicTacToeUI() {
        this.input = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Tic-Tac-Toe Game ===\n");

        // Создание игроков
        Player player1 = createPlayer(1, 'X');
        Player player2 = createPlayer(2, 'O');

        game = new Game(player1, player2);

        // Главное меню
        while (true) {
            displayMainMenu();

            try {
                int choice = input.nextInt();
                input.nextLine(); // Очистка буфера

                switch (choice) {
                    case 1:
                        playGame();
                        break;
                    case 2:
                        game.displayStatistics();
                        break;
                    case 3:
                        System.out.println("\nThanks for playing!");
                        input.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("\nError: Invalid input. Please try again.");
                input.nextLine(); // Очистка буфера
            }
        }
    }

    // Метод создания игрока
    private Player createPlayer(int playerNumber, char symbol) {
        System.out.print("Enter name for Player " + playerNumber + " (" + symbol + "): ");
        String name = input.nextLine();
        return new Player(name, symbol);
    }

    // Метод отображения главного меню
    private void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Play Game");
        System.out.println("2. View Statistics");
        System.out.println("3. Exit");
        System.out.print("Enter choice (1-3): ");
    }

    // Метод игрового процесса
    private void playGame() {
        game.startNewGame();

        System.out.println("\n=== New Game Started ===");
        game.getBoard().displayWithGuide();

        // Игровой цикл
        while (!game.isGameOver()) {
            Player current = game.getCurrentPlayer();

            System.out.println("\n" + current.getName() + "'s turn (" + current.getSymbol() + ")");

            try {
                System.out.print("Enter row (1-3): ");
                int row = input.nextInt();

                System.out.print("Enter column (1-3): ");
                int col = input.nextInt();
                input.nextLine(); // Очистка буфера

                // Попытка совершить ход
                if (game.makeMove(row, col)) {
                    game.getBoard().display();
                    game.checkGameState();

                    if (!game.isGameOver()) {
                        game.switchPlayer();
                    }
                } else {
                    System.out.println("Try again!");
                }

            } catch (Exception e) {
                System.out.println("\nError: Invalid input. Please enter numbers 1-3.");
                input.nextLine(); // Очистка буфера
            }
        }

        // Отображение результата
        game.displayResult();

        // Спросить, хотят ли игроки сыграть снова
        System.out.print("\nPlay again? (y/n): ");
        String answer = input.nextLine().toLowerCase();

        if (answer.equals("y") || answer.equals("yes")) {
            playGame();
        }
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        TicTacToeUI ui = new TicTacToeUI();
        ui.run();
    }
}