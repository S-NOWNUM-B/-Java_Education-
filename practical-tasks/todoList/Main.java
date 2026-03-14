package easyProjects.todoList;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Перечисление для приоритета задачи
enum Priority {
    LOW,
    MEDIUM,
    HIGH
}

// Класс задачи
class Task {
    private static int taskCounter = 1;
    private int id;
    private String title;
    private String description;
    private boolean isCompleted;
    private Priority priority;
    private LocalDate deadline;
    private LocalDate createdAt;

    // Конструктор
    public Task(String title, String description, Priority priority, LocalDate deadline) {
        this.id = taskCounter++;
        this.title = title;
        this.description = description;
        this.isCompleted = false;
        this.priority = priority;
        this.deadline = deadline;
        this.createdAt = LocalDate.now();
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    // Сеттеры
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    // Метод отметки задачи как выполненной
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    // Метод отметки задачи как невыполненной
    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    // Метод отображения задачи
    public void display() {
        String status = isCompleted ? "[✓] COMPLETED" : "[ ] PENDING";
        String priorityStr = switch (priority) {
            case LOW -> "LOW";
            case MEDIUM -> "MEDIUM";
            case HIGH -> "HIGH";
        };

        System.out.println("ID: " + id + " | " + status);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Priority: " + priorityStr);
        System.out.println("Deadline: " + (deadline != null ? deadline : "No deadline"));
        System.out.println("Created: " + createdAt);
        System.out.println("---");
    }

    // Метод краткого отображения задачи
    public void displayShort() {
        String status = isCompleted ? "[✓]" : "[ ]";
        String prioritySymbol = switch (priority) {
            case LOW -> "●";
            case MEDIUM -> "●●";
            case HIGH -> "●●●";
        };

        System.out.printf("%s ID: %d | %s | Priority: %s | %s%n",
                status, id, title, prioritySymbol,
                deadline != null ? "Due: " + deadline : "No deadline");
    }
}

// Класс списка задач
class TodoList {
    private ArrayList<Task> tasks;

    // Конструктор
    public TodoList() {
        this.tasks = new ArrayList<>();
    }

    // Метод добавления задачи
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("\nTask added successfully!");
        System.out.println("Task ID: " + task.getId());
    }

    // Метод удаления задачи по ID
    public boolean removeTask(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                Task removedTask = tasks.remove(i);
                System.out.println("\nTask removed: " + removedTask.getTitle());
                return true;
            }
        }
        System.out.println("\nError: Task with ID " + taskId + " not found");
        return false;
    }

    // Метод отметки задачи как выполненной
    public boolean markAsCompleted(int taskId) {
        Task task = findTaskById(taskId);
        if (task != null) {
            task.markAsCompleted();
            System.out.println("\nTask marked as completed: " + task.getTitle());
            return true;
        }
        System.out.println("\nError: Task with ID " + taskId + " not found");
        return false;
    }

    // Метод отметки задачи как невыполненной
    public boolean markAsIncomplete(int taskId) {
        Task task = findTaskById(taskId);
        if (task != null) {
            task.markAsIncomplete();
            System.out.println("\nTask marked as incomplete: " + task.getTitle());
            return true;
        }
        System.out.println("\nError: Task with ID " + taskId + " not found");
        return false;
    }

    // Метод редактирования задачи
    public boolean editTask(int taskId, String newTitle, String newDescription, Priority newPriority, LocalDate newDeadline) {
        Task task = findTaskById(taskId);
        if (task != null) {
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            task.setPriority(newPriority);
            task.setDeadline(newDeadline);
            System.out.println("\nTask updated successfully!");
            return true;
        }
        System.out.println("\nError: Task with ID " + taskId + " not found");
        return false;
    }

    // Метод отображения всех задач
    public void showAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks in the list");
            return;
        }

        System.out.println("\n=== All Tasks ===");
        for (Task task : tasks) {
            task.displayShort();
        }
        System.out.println("\nTotal tasks: " + tasks.size());
    }

    // Метод отображения задач по статусу
    public void showTasksByStatus(boolean completed) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                filteredTasks.add(task);
            }
        }

        if (filteredTasks.isEmpty()) {
            System.out.println("\nNo " + (completed ? "completed" : "pending") + " tasks");
            return;
        }

        System.out.println("\n=== " + (completed ? "Completed" : "Pending") + " Tasks ===");
        for (Task task : filteredTasks) {
            task.displayShort();
        }
        System.out.println("\nTotal: " + filteredTasks.size());
    }

    // Метод отображения задач по приоритету
    public void showTasksByPriority(Priority priority) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                filteredTasks.add(task);
            }
        }

        if (filteredTasks.isEmpty()) {
            System.out.println("\nNo tasks with " + priority + " priority");
            return;
        }

        System.out.println("\n=== " + priority + " Priority Tasks ===");
        for (Task task : filteredTasks) {
            task.displayShort();
        }
        System.out.println("\nTotal: " + filteredTasks.size());
    }

    // Метод отображения детальной информации о задаче
    public void showTaskDetails(int taskId) {
        Task task = findTaskById(taskId);
        if (task != null) {
            System.out.println("\n=== Task Details ===");
            task.display();
        } else {
            System.out.println("\nError: Task with ID " + taskId + " not found");
        }
    }

    // Метод поиска задачи по ID
    private Task findTaskById(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }

    // Метод получения статистики
    public void showStatistics() {
        int total = tasks.size();
        int completed = 0;
        int pending = 0;
        int highPriority = 0;

        for (Task task : tasks) {
            if (task.isCompleted()) {
                completed++;
            } else {
                pending++;
            }

            if (task.getPriority() == Priority.HIGH) {
                highPriority++;
            }
        }

        System.out.println("\n=== Statistics ===");
        System.out.println("Total tasks: " + total);
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + pending);
        System.out.println("High priority tasks: " + highPriority);

        if (total > 0) {
            double completionRate = (double) completed / total * 100;
            System.out.printf("Completion rate: %.1f%%%n", completionRate);
        }
    }
}

// Класс для работы с пользовательским интерфейсом
class TodoUI {
    private Scanner input;
    private TodoList todoList;
    private DateTimeFormatter dateFormatter;

    public TodoUI() {
        this.input = new Scanner(System.in);
        this.todoList = new TodoList();
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void run() {
        System.out.println("=== Todo List Manager ===\n");

        while (true) {
            try {
                displayMainMenu();
                int choice = input.nextInt();
                input.nextLine(); // Очистка буфера

                if (choice == 11) {
                    System.out.println("\nGoodbye! Keep being productive!");
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
        System.out.println("1. Add new task");
        System.out.println("2. Remove task");
        System.out.println("3. Mark task as completed");
        System.out.println("4. Mark task as incomplete");
        System.out.println("5. Edit task");
        System.out.println("6. View all tasks");
        System.out.println("7. View completed tasks");
        System.out.println("8. View pending tasks");
        System.out.println("9. View task details");
        System.out.println("10. View statistics");
        System.out.println("11. Exit");
        System.out.print("Enter choice (1-11): ");
    }

    // Обработка выбора меню
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addNewTask();
                break;
            case 2:
                removeTask();
                break;
            case 3:
                markTaskAsCompleted();
                break;
            case 4:
                markTaskAsIncomplete();
                break;
            case 5:
                editTask();
                break;
            case 6:
                todoList.showAllTasks();
                break;
            case 7:
                todoList.showTasksByStatus(true);
                break;
            case 8:
                todoList.showTasksByStatus(false);
                break;
            case 9:
                viewTaskDetails();
                break;
            case 10:
                todoList.showStatistics();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Добавление новой задачи
    private void addNewTask() {
        System.out.print("\nEnter task title: ");
        String title = input.nextLine();

        System.out.print("Enter task description: ");
        String description = input.nextLine();

        System.out.println("Select priority:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");
        System.out.print("Enter choice (1-3): ");
        int priorityChoice = input.nextInt();
        input.nextLine(); // Очистка буфера

        Priority priority = switch (priorityChoice) {
            case 1 -> Priority.LOW;
            case 2 -> Priority.MEDIUM;
            case 3 -> Priority.HIGH;
            default -> Priority.MEDIUM;
        };

        System.out.print("Enter deadline (yyyy-MM-dd) or press Enter to skip: ");
        String deadlineStr = input.nextLine();

        LocalDate deadline = null;
        if (!deadlineStr.trim().isEmpty()) {
            try {
                deadline = LocalDate.parse(deadlineStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. No deadline set.");
            }
        }

        Task task = new Task(title, description, priority, deadline);
        todoList.addTask(task);
    }

    // Удаление задачи
    private void removeTask() {
        System.out.print("\nEnter task ID to remove: ");
        int taskId = input.nextInt();
        input.nextLine(); // Очистка буфера

        todoList.removeTask(taskId);
    }

    // Отметка задачи как выполненной
    private void markTaskAsCompleted() {
        System.out.print("\nEnter task ID to mark as completed: ");
        int taskId = input.nextInt();
        input.nextLine(); // Очистка буфера

        todoList.markAsCompleted(taskId);
    }

    // Отметка задачи как невыполненной
    private void markTaskAsIncomplete() {
        System.out.print("\nEnter task ID to mark as incomplete: ");
        int taskId = input.nextInt();
        input.nextLine(); // Очистка буфера

        todoList.markAsIncomplete(taskId);
    }

    // Редактирование задачи
    private void editTask() {
        System.out.print("\nEnter task ID to edit: ");
        int taskId = input.nextInt();
        input.nextLine(); // Очистка буфера

        System.out.print("Enter new title: ");
        String title = input.nextLine();

        System.out.print("Enter new description: ");
        String description = input.nextLine();

        System.out.println("Select new priority:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");
        System.out.print("Enter choice (1-3): ");
        int priorityChoice = input.nextInt();
        input.nextLine(); // Очистка буфера

        Priority priority = switch (priorityChoice) {
            case 1 -> Priority.LOW;
            case 2 -> Priority.MEDIUM;
            case 3 -> Priority.HIGH;
            default -> Priority.MEDIUM;
        };

        System.out.print("Enter new deadline (yyyy-MM-dd) or press Enter to skip: ");
        String deadlineStr = input.nextLine();

        LocalDate deadline = null;
        if (!deadlineStr.trim().isEmpty()) {
            try {
                deadline = LocalDate.parse(deadlineStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. No deadline set.");
            }
        }

        todoList.editTask(taskId, title, description, priority, deadline);
    }

    // Просмотр детальной информации о задаче
    private void viewTaskDetails() {
        System.out.print("\nEnter task ID to view details: ");
        int taskId = input.nextInt();
        input.nextLine(); // Очистка буфера

        todoList.showTaskDetails(taskId);
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        TodoUI ui = new TodoUI();
        ui.run();
    }
}