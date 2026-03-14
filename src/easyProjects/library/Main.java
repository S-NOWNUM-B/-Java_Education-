package easyProjects.library;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

// Класс книги
class Book {
    private static int bookCounter = 1;
    private int id;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private String borrower;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String genre;
    private int publicationYear;

    // Конструктор
    public Book(String title, String author, String isbn, String genre, int publicationYear) {
        this.id = bookCounter++;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.borrower = null;
        this.borrowDate = null;
        this.returnDate = null;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBorrower() {
        return borrower;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    // Метод выдачи книги
    public boolean borrowBook(String borrowerName) {
        if (!isAvailable) {
            System.out.println("Error: Book is already borrowed");
            return false;
        }

        this.isAvailable = false;
        this.borrower = borrowerName;
        this.borrowDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusWeeks(2); // 2 недели на чтение

        System.out.println("\nBook borrowed successfully!");
        System.out.println("Borrower: " + borrowerName);
        System.out.println("Return date: " + returnDate);

        return true;
    }

    // Метод возврата книги
    public boolean returnBook() {
        if (isAvailable) {
            System.out.println("Error: Book was not borrowed");
            return false;
        }

        this.isAvailable = true;
        String previousBorrower = this.borrower;
        this.borrower = null;
        this.borrowDate = null;
        this.returnDate = null;

        System.out.println("\nBook returned successfully!");
        System.out.println("Previous borrower: " + previousBorrower);

        return true;
    }

    // Метод отображения информации о книге
    public void display() {
        String status = isAvailable ? "[AVAILABLE]" : "[BORROWED]";

        System.out.println("\n" + status);
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Genre: " + genre);
        System.out.println("Publication Year: " + publicationYear);

        if (!isAvailable) {
            System.out.println("Borrowed by: " + borrower);
            System.out.println("Borrow date: " + borrowDate);
            System.out.println("Return date: " + returnDate);

            if (LocalDate.now().isAfter(returnDate)) {
                System.out.println("⚠️  OVERDUE!");
            }
        }
        System.out.println("---");
    }

    // Метод краткого отображения книги
    public void displayShort() {
        String status = isAvailable ? "[✓]" : "[✗]";
        System.out.printf("%s ID: %d | \"%s\" by %s | ISBN: %s%n",
                status, id, title, author, isbn);
    }
}

// Класс библиотеки
class Library {
    private ArrayList<Book> books;
    private String libraryName;

    // Конструктор
    public Library(String libraryName) {
        this.books = new ArrayList<>();
        this.libraryName = libraryName;
    }

    // Метод добавления книги
    public void addBook(Book book) {
        // Проверка на дубликат по ISBN
        for (Book existingBook : books) {
            if (existingBook.getIsbn().equals(book.getIsbn())) {
                System.out.println("\nError: Book with ISBN " + book.getIsbn() + " already exists");
                return;
            }
        }

        books.add(book);
        System.out.println("\nBook added successfully!");
        System.out.println("Book ID: " + book.getId());
        System.out.println("Title: " + book.getTitle());
    }

    // Метод удаления книги по ID
    public boolean removeBook(int bookId) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == bookId) {
                Book removedBook = books.remove(i);
                System.out.println("\nBook removed: \"" + removedBook.getTitle() + "\"");
                return true;
            }
        }
        System.out.println("\nError: Book with ID " + bookId + " not found");
        return false;
    }

    // Метод поиска книг по названию
    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> results = new ArrayList<>();
        String searchTerm = title.toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm)) {
                results.add(book);
            }
        }

        return results;
    }

    // Метод поиска книг по автору
    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> results = new ArrayList<>();
        String searchTerm = author.toLowerCase();

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(searchTerm)) {
                results.add(book);
            }
        }

        return results;
    }

    // Метод поиска книги по ISBN
    public Book searchByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // Метод поиска книг по жанру
    public ArrayList<Book> searchByGenre(String genre) {
        ArrayList<Book> results = new ArrayList<>();
        String searchTerm = genre.toLowerCase();

        for (Book book : books) {
            if (book.getGenre().toLowerCase().contains(searchTerm)) {
                results.add(book);
            }
        }

        return results;
    }

    // Метод поиска книги по ID
    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // Метод отображения всех книг
    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("\nNo books in the library");
            return;
        }

        System.out.println("\n=== " + libraryName + " - All Books ===");
        for (Book book : books) {
            book.displayShort();
        }
        System.out.println("\nTotal books: " + books.size());
    }

    // Метод отображения доступных книг
    public void showAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }

        if (availableBooks.isEmpty()) {
            System.out.println("\nNo available books");
            return;
        }

        System.out.println("\n=== Available Books ===");
        for (Book book : availableBooks) {
            book.displayShort();
        }
        System.out.println("\nTotal available: " + availableBooks.size());
    }

    // Метод отображения взятых книг
    public void showBorrowedBooks() {
        ArrayList<Book> borrowedBooks = new ArrayList<>();

        for (Book book : books) {
            if (!book.isAvailable()) {
                borrowedBooks.add(book);
            }
        }

        if (borrowedBooks.isEmpty()) {
            System.out.println("\nNo borrowed books");
            return;
        }

        System.out.println("\n=== Borrowed Books ===");
        for (Book book : borrowedBooks) {
            book.displayShort();
            System.out.println("    Borrower: " + book.getBorrower() +
                    " | Return: " + book.getReturnDate());
        }
        System.out.println("\nTotal borrowed: " + borrowedBooks.size());
    }

    // Метод отображения просроченных книг
    public void showOverdueBooks() {
        ArrayList<Book> overdueBooks = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Book book : books) {
            if (!book.isAvailable() && today.isAfter(book.getReturnDate())) {
                overdueBooks.add(book);
            }
        }

        if (overdueBooks.isEmpty()) {
            System.out.println("\nNo overdue books");
            return;
        }

        System.out.println("\n=== Overdue Books ===");
        for (Book book : overdueBooks) {
            book.displayShort();
            System.out.println("    Borrower: " + book.getBorrower() +
                    " | Due: " + book.getReturnDate());
        }
        System.out.println("\nTotal overdue: " + overdueBooks.size());
    }

    // Метод отображения статистики
    public void showStatistics() {
        int total = books.size();
        int available = 0;
        int borrowed = 0;

        for (Book book : books) {
            if (book.isAvailable()) {
                available++;
            } else {
                borrowed++;
            }
        }

        System.out.println("\n=== " + libraryName + " Statistics ===");
        System.out.println("Total books: " + total);
        System.out.println("Available: " + available);
        System.out.println("Borrowed: " + borrowed);

        if (total > 0) {
            double availabilityRate = (double) available / total * 100;
            System.out.printf("Availability rate: %.1f%%%n", availabilityRate);
        }
    }

    // Метод отображения результатов поиска
    private void displaySearchResults(List<Book> results, String searchType) {
        if (results.isEmpty()) {
            System.out.println("\nNo books found");
            return;
        }

        System.out.println("\n=== Search Results (" + searchType + ") ===");
        for (Book book : results) {
            book.displayShort();
        }
        System.out.println("\nFound: " + results.size() + " book(s)");
    }
}

// Класс для работы с пользовательским интерфейсом
class LibraryUI {
    private Scanner input;
    private Library library;

    public LibraryUI(String libraryName) {
        this.input = new Scanner(System.in);
        this.library = new Library(libraryName);
    }

    public void run() {
        System.out.println("=== Library Management System ===");
        System.out.println("Welcome to " + library.getClass().getName() + "!\n");

        while (true) {
            try {
                displayMainMenu();
                int choice = input.nextInt();
                input.nextLine(); // Очистка буфера

                if (choice == 13) {
                    System.out.println("\nThank you for using Library Management System!");
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
        System.out.println("1. Add new book");
        System.out.println("2. Remove book");
        System.out.println("3. Borrow book");
        System.out.println("4. Return book");
        System.out.println("5. Search by title");
        System.out.println("6. Search by author");
        System.out.println("7. Search by ISBN");
        System.out.println("8. Search by genre");
        System.out.println("9. View all books");
        System.out.println("10. View available books");
        System.out.println("11. View borrowed books");
        System.out.println("12. View statistics");
        System.out.println("13. Exit");
        System.out.print("Enter choice (1-13): ");
    }

    // Обработка выбора меню
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addNewBook();
                break;
            case 2:
                removeBook();
                break;
            case 3:
                borrowBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                searchByTitle();
                break;
            case 6:
                searchByAuthor();
                break;
            case 7:
                searchByIsbn();
                break;
            case 8:
                searchByGenre();
                break;
            case 9:
                library.showAllBooks();
                break;
            case 10:
                library.showAvailableBooks();
                break;
            case 11:
                library.showBorrowedBooks();
                break;
            case 12:
                library.showStatistics();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Добавление новой книги
    private void addNewBook() {
        System.out.print("\nEnter book title: ");
        String title = input.nextLine();

        System.out.print("Enter author name: ");
        String author = input.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = input.nextLine();

        System.out.print("Enter genre: ");
        String genre = input.nextLine();

        System.out.print("Enter publication year: ");
        int year = input.nextInt();
        input.nextLine(); // Очистка буфера

        Book book = new Book(title, author, isbn, genre, year);
        library.addBook(book);
    }

    // Удаление книги
    private void removeBook() {
        System.out.print("\nEnter book ID to remove: ");
        int bookId = input.nextInt();
        input.nextLine(); // Очистка буфера

        library.removeBook(bookId);
    }

    // Выдача книги
    private void borrowBook() {
        System.out.print("\nEnter book ID to borrow: ");
        int bookId = input.nextInt();
        input.nextLine(); // Очистка буфера

        Book book = library.findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found");
            return;
        }

        System.out.print("Enter borrower name: ");
        String borrowerName = input.nextLine();

        book.borrowBook(borrowerName);
    }

    // Возврат книги
    private void returnBook() {
        System.out.print("\nEnter book ID to return: ");
        int bookId = input.nextInt();
        input.nextLine(); // Очистка буфера

        Book book = library.findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found");
            return;
        }

        book.returnBook();
    }

    // Поиск по названию
    private void searchByTitle() {
        System.out.print("\nEnter title to search: ");
        String title = input.nextLine();

        ArrayList<Book> results = library.searchByTitle(title);
        displaySearchResults(results, "Title");
    }

    // Поиск по автору
    private void searchByAuthor() {
        System.out.print("\nEnter author name to search: ");
        String author = input.nextLine();

        ArrayList<Book> results = library.searchByAuthor(author);
        displaySearchResults(results, "Author");
    }

    // Поиск по ISBN
    private void searchByIsbn() {
        System.out.print("\nEnter ISBN to search: ");
        String isbn = input.nextLine();

        Book book = library.searchByIsbn(isbn);
        if (book != null) {
            System.out.println("\n=== Book Found ===");
            book.display();
        } else {
            System.out.println("\nBook not found");
        }
    }

    // Поиск по жанру
    private void searchByGenre() {
        System.out.print("\nEnter genre to search: ");
        String genre = input.nextLine();

        ArrayList<Book> results = library.searchByGenre(genre);
        displaySearchResults(results, "Genre");
    }

    // Отображение результатов поиска
    private void displaySearchResults(ArrayList<Book> results, String searchType) {
        if (results.isEmpty()) {
            System.out.println("\nNo books found");
            return;
        }

        System.out.println("\n=== Search Results (" + searchType + ") ===");
        for (Book book : results) {
            book.displayShort();
        }
        System.out.println("\nFound: " + results.size() + " book(s)");
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        LibraryUI ui = new LibraryUI("Central Library");
        ui.run();
    }
}