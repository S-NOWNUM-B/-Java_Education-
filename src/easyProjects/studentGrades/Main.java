package easyProjects.studentGrades;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Перечисление для статуса успеваемости
enum AcademicStatus {
    EXCELLENT,      // Отлично (90-100)
    GOOD,           // Хорошо (75-89)
    SATISFACTORY,   // Удовлетворительно (60-74)
    UNSATISFACTORY  // Неудовлетворительно (0-59)
}

// Класс для хранения информации о предмете и оценке
class Grade {
    private String subject;
    private double score;

    public Grade(String subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

// Класс студента
class Student {
    private static int studentCounter = 1000;
    private int id;
    private String name;
    private String major;
    private int year;
    private ArrayList<Grade> grades;

    // Конструктор
    public Student(String name, String major, int year) {
        this.id = studentCounter++;
        this.name = name;
        this.major = major;
        this.year = year;
        this.grades = new ArrayList<>();
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Метод добавления оценки
    public void addGrade(String subject, double score) {
        if (score < 0 || score > 100) {
            System.out.println("Error: Grade must be between 0 and 100");
            return;
        }

        // Проверка на существующий предмет
        for (Grade grade : grades) {
            if (grade.getSubject().equalsIgnoreCase(subject)) {
                System.out.println("Warning: Grade for " + subject + " already exists");
                System.out.print("Do you want to update it? (y/n): ");
                return;
            }
        }

        grades.add(new Grade(subject, score));
        System.out.println("\nGrade added successfully!");
        System.out.println("Subject: " + subject + " | Score: " + score);
    }

    // Метод обновления оценки
    public boolean updateGrade(String subject, double newScore) {
        if (newScore < 0 || newScore > 100) {
            System.out.println("Error: Grade must be between 0 and 100");
            return false;
        }

        for (Grade grade : grades) {
            if (grade.getSubject().equalsIgnoreCase(subject)) {
                double oldScore = grade.getScore();
                grade.setScore(newScore);
                System.out.println("\nGrade updated successfully!");
                System.out.println("Subject: " + subject);
                System.out.println("Old score: " + oldScore + " → New score: " + newScore);
                return true;
            }
        }

        System.out.println("Error: Subject not found");
        return false;
    }

    // Метод удаления оценки
    public boolean removeGrade(String subject) {
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).getSubject().equalsIgnoreCase(subject)) {
                Grade removed = grades.remove(i);
                System.out.println("\nGrade removed: " + removed.getSubject() +
                        " (" + removed.getScore() + ")");
                return true;
            }
        }
        System.out.println("Error: Subject not found");
        return false;
    }

    // Метод вычисления среднего балла
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (Grade grade : grades) {
            sum += grade.getScore();
        }

        return sum / grades.size();
    }

    // Метод получения статуса успеваемости
    public AcademicStatus getStatus() {
        double average = calculateAverage();

        if (average >= 90) {
            return AcademicStatus.EXCELLENT;
        } else if (average >= 75) {
            return AcademicStatus.GOOD;
        } else if (average >= 60) {
            return AcademicStatus.SATISFACTORY;
        } else {
            return AcademicStatus.UNSATISFACTORY;
        }
    }

    // Метод получения текстового статуса
    public String getStatusText() {
        return switch (getStatus()) {
            case EXCELLENT -> "Отлично (Excellent)";
            case GOOD -> "Хорошо (Good)";
            case SATISFACTORY -> "Удовлетворительно (Satisfactory)";
            case UNSATISFACTORY -> "Неудовлетворительно (Unsatisfactory)";
        };
    }

    // Метод получения наивысшей оценки
    public Grade getHighestGrade() {
        if (grades.isEmpty()) {
            return null;
        }

        Grade highest = grades.get(0);
        for (Grade grade : grades) {
            if (grade.getScore() > highest.getScore()) {
                highest = grade;
            }
        }
        return highest;
    }

    // Метод получения низшей оценки
    public Grade getLowestGrade() {
        if (grades.isEmpty()) {
            return null;
        }

        Grade lowest = grades.get(0);
        for (Grade grade : grades) {
            if (grade.getScore() < lowest.getScore()) {
                lowest = grade;
            }
        }
        return lowest;
    }

    // Метод отображения информации о студенте
    public void display() {
        System.out.println("\n=== Student Information ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Major: " + major);
        System.out.println("Year: " + year);
        System.out.println("Total subjects: " + grades.size());

        if (!grades.isEmpty()) {
            System.out.println("\nGrades:");
            for (Grade grade : grades) {
                System.out.printf("  • %s: %.2f%n", grade.getSubject(), grade.getScore());
            }

            System.out.printf("\nAverage: %.2f%n", calculateAverage());
            System.out.println("Status: " + getStatusText());

            Grade highest = getHighestGrade();
            Grade lowest = getLowestGrade();

            if (highest != null) {
                System.out.printf("Highest: %s (%.2f)%n", highest.getSubject(), highest.getScore());
            }
            if (lowest != null) {
                System.out.printf("Lowest: %s (%.2f)%n", lowest.getSubject(), lowest.getScore());
            }
        } else {
            System.out.println("\nNo grades recorded yet");
        }
        System.out.println("---");
    }

    // Метод краткого отображения студента
    public void displayShort() {
        String statusSymbol = switch (getStatus()) {
            case EXCELLENT -> "★★★";
            case GOOD -> "★★";
            case SATISFACTORY -> "★";
            case UNSATISFACTORY -> "✗";
        };

        System.out.printf("ID: %d | %s | Year %d | Avg: %.2f %s%n",
                id, name, year, calculateAverage(), statusSymbol);
    }
}

// Класс реестра студентов
class StudentRegistry {
    private ArrayList<Student> students;
    private String universityName;

    // Конструктор
    public StudentRegistry(String universityName) {
        this.students = new ArrayList<>();
        this.universityName = universityName;
    }

    // Метод добавления студента
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("\nStudent registered successfully!");
        System.out.println("Student ID: " + student.getId());
        System.out.println("Name: " + student.getName());
    }

    // Метод удаления студента
    public boolean removeStudent(int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId) {
                Student removed = students.remove(i);
                System.out.println("\nStudent removed: " + removed.getName());
                return true;
            }
        }
        System.out.println("\nError: Student with ID " + studentId + " not found");
        return false;
    }

    // Метод поиска студента по ID
    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    // Метод поиска студентов по имени
    public ArrayList<Student> searchByName(String name) {
        ArrayList<Student> results = new ArrayList<>();
        String searchTerm = name.toLowerCase();

        for (Student student : students) {
            if (student.getName().toLowerCase().contains(searchTerm)) {
                results.add(student);
            }
        }

        return results;
    }

    // Метод поиска студентов по специальности
    public ArrayList<Student> searchByMajor(String major) {
        ArrayList<Student> results = new ArrayList<>();
        String searchTerm = major.toLowerCase();

        for (Student student : students) {
            if (student.getMajor().toLowerCase().contains(searchTerm)) {
                results.add(student);
            }
        }

        return results;
    }

    // Метод получения студентов по статусу
    public ArrayList<Student> getStudentsByStatus(AcademicStatus status) {
        ArrayList<Student> results = new ArrayList<>();

        for (Student student : students) {
            if (student.getStatus() == status) {
                results.add(student);
            }
        }

        return results;
    }

    // Метод отображения всех студентов
    public void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students registered");
            return;
        }

        System.out.println("\n=== " + universityName + " - All Students ===");
        for (Student student : students) {
            student.displayShort();
        }
        System.out.println("\nTotal students: " + students.size());
    }

    // Метод отображения топ студентов
    public void showTopStudents(int count) {
        if (students.isEmpty()) {
            System.out.println("\nNo students registered");
            return;
        }

        // Сортировка студентов по среднему баллу
        ArrayList<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort((s1, s2) -> Double.compare(s2.calculateAverage(), s1.calculateAverage()));

        int displayCount = Math.min(count, sortedStudents.size());

        System.out.println("\n=== Top " + displayCount + " Students ===");
        for (int i = 0; i < displayCount; i++) {
            Student student = sortedStudents.get(i);
            System.out.printf("%d. %s | Avg: %.2f | %s%n",
                    i + 1, student.getName(),
                    student.calculateAverage(),
                    student.getStatusText());
        }
    }

    // Метод расчета общей статистики
    public void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("\nNo students to analyze");
            return;
        }

        int total = students.size();
        int excellent = 0;
        int good = 0;
        int satisfactory = 0;
        int unsatisfactory = 0;
        double totalAverage = 0;
        int totalGrades = 0;

        for (Student student : students) {
            switch (student.getStatus()) {
                case EXCELLENT -> excellent++;
                case GOOD -> good++;
                case SATISFACTORY -> satisfactory++;
                case UNSATISFACTORY -> unsatisfactory++;
            }

            totalAverage += student.calculateAverage();
            totalGrades += student.getGrades().size();
        }

        double overallAverage = totalAverage / total;

        System.out.println("\n=== " + universityName + " Statistics ===");
        System.out.println("Total students: " + total);
        System.out.println("Total grades recorded: " + totalGrades);
        System.out.printf("Overall average: %.2f%n", overallAverage);
        System.out.println("\nPerformance Distribution:");
        System.out.println("  Excellent: " + excellent + " (" + (excellent * 100.0 / total) + "%)");
        System.out.println("  Good: " + good + " (" + (good * 100.0 / total) + "%)");
        System.out.println("  Satisfactory: " + satisfactory + " (" + (satisfactory * 100.0 / total) + "%)");
        System.out.println("  Unsatisfactory: " + unsatisfactory + " (" + (unsatisfactory * 100.0 / total) + "%)");
    }

    // Метод анализа по предметам
    public void showSubjectAnalysis() {
        if (students.isEmpty()) {
            System.out.println("\nNo students to analyze");
            return;
        }

        // HashMap для хранения всех оценок по предметам
        HashMap<String, ArrayList<Double>> subjectScores = new HashMap<>();

        for (Student student : students) {
            for (Grade grade : student.getGrades()) {
                String subject = grade.getSubject();
                double score = grade.getScore();

                if (!subjectScores.containsKey(subject)) {
                    subjectScores.put(subject, new ArrayList<>());
                }
                subjectScores.get(subject).add(score);
            }
        }

        if (subjectScores.isEmpty()) {
            System.out.println("\nNo grades recorded yet");
            return;
        }

        System.out.println("\n=== Subject Analysis ===");
        for (Map.Entry<String, ArrayList<Double>> entry : subjectScores.entrySet()) {
            String subject = entry.getKey();
            ArrayList<Double> scores = entry.getValue();

            double sum = 0;
            for (double score : scores) {
                sum += score;
            }
            double average = sum / scores.size();

            System.out.printf("%s: %.2f average (%d students)%n",
                    subject, average, scores.size());
        }
    }
}

// Класс для работы с пользовательским интерфейсом
class RegistryUI {
    private Scanner input;
    private StudentRegistry registry;

    public RegistryUI(String universityName) {
        this.input = new Scanner(System.in);
        this.registry = new StudentRegistry(universityName);
    }

    public void run() {
        System.out.println("=== Student Grade Management System ===\n");

        while (true) {
            try {
                displayMainMenu();
                int choice = input.nextInt();
                input.nextLine(); // Очистка буфера

                if (choice == 14) {
                    System.out.println("\nThank you for using Student Grade Management System!");
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
        System.out.println("1. Register new student");
        System.out.println("2. Remove student");
        System.out.println("3. Add grade");
        System.out.println("4. Update grade");
        System.out.println("5. Remove grade");
        System.out.println("6. View student information");
        System.out.println("7. Search students by name");
        System.out.println("8. Search students by major");
        System.out.println("9. View all students");
        System.out.println("10. View top students");
        System.out.println("11. View students by status");
        System.out.println("12. View statistics");
        System.out.println("13. View subject analysis");
        System.out.println("14. Exit");
        System.out.print("Enter choice (1-14): ");
    }

    // Обработка выбора меню
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                registerStudent();
                break;
            case 2:
                removeStudent();
                break;
            case 3:
                addGrade();
                break;
            case 4:
                updateGrade();
                break;
            case 5:
                removeGrade();
                break;
            case 6:
                viewStudentInfo();
                break;
            case 7:
                searchByName();
                break;
            case 8:
                searchByMajor();
                break;
            case 9:
                registry.showAllStudents();
                break;
            case 10:
                viewTopStudents();
                break;
            case 11:
                viewStudentsByStatus();
                break;
            case 12:
                registry.showStatistics();
                break;
            case 13:
                registry.showSubjectAnalysis();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Регистрация нового студента
    private void registerStudent() {
        System.out.print("\nEnter student name: ");
        String name = input.nextLine();

        System.out.print("Enter major: ");
        String major = input.nextLine();

        System.out.print("Enter year (1-4): ");
        int year = input.nextInt();
        input.nextLine(); // Очистка буфера

        Student student = new Student(name, major, year);
        registry.addStudent(student);
    }

    // Удаление студента
    private void removeStudent() {
        System.out.print("\nEnter student ID to remove: ");
        int studentId = input.nextInt();
        input.nextLine(); // Очистка буфера

        registry.removeStudent(studentId);
    }

    // Добавление оценки
    private void addGrade() {
        System.out.print("\nEnter student ID: ");
        int studentId = input.nextInt();
        input.nextLine(); // Очистка буфера

        Student student = registry.findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found");
            return;
        }

        System.out.print("Enter subject name: ");
        String subject = input.nextLine();

        System.out.print("Enter grade (0-100): ");
        double score = input.nextDouble();
        input.nextLine(); // Очистка буфера

        student.addGrade(subject, score);
    }

    // Обновление оценки
    private void updateGrade() {
        System.out.print("\nEnter student ID: ");
        int studentId = input.nextInt();
        input.nextLine(); // Очистка буфера

        Student student = registry.findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found");
            return;
        }

        System.out.print("Enter subject name: ");
        String subject = input.nextLine();

        System.out.print("Enter new grade (0-100): ");
        double score = input.nextDouble();
        input.nextLine(); // Очистка буфера

        student.updateGrade(subject, score);
    }

    // Удаление оценки
    private void removeGrade() {
        System.out.print("\nEnter student ID: ");
        int studentId = input.nextInt();
        input.nextLine(); // Очистка буфера

        Student student = registry.findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found");
            return;
        }

        System.out.print("Enter subject name: ");
        String subject = input.nextLine();

        student.removeGrade(subject);
    }

    // Просмотр информации о студенте
    private void viewStudentInfo() {
        System.out.print("\nEnter student ID: ");
        int studentId = input.nextInt();
        input.nextLine(); // Очистка буфера

        Student student = registry.findStudentById(studentId);
        if (student != null) {
            student.display();
        } else {
            System.out.println("Error: Student not found");
        }
    }

    // Поиск по имени
    private void searchByName() {
        System.out.print("\nEnter name to search: ");
        String name = input.nextLine();

        ArrayList<Student> results = registry.searchByName(name);
        displaySearchResults(results, "Name");
    }

    // Поиск по специальности
    private void searchByMajor() {
        System.out.print("\nEnter major to search: ");
        String major = input.nextLine();

        ArrayList<Student> results = registry.searchByMajor(major);
        displaySearchResults(results, "Major");
    }

    // Просмотр топ студентов
    private void viewTopStudents() {
        System.out.print("\nEnter number of top students to display: ");
        int count = input.nextInt();
        input.nextLine(); // Очистка буфера

        registry.showTopStudents(count);
    }

    // Просмотр студентов по статусу
    private void viewStudentsByStatus() {
        System.out.println("\nSelect status:");
        System.out.println("1. Excellent");
        System.out.println("2. Good");
        System.out.println("3. Satisfactory");
        System.out.println("4. Unsatisfactory");
        System.out.print("Enter choice (1-4): ");

        int choice = input.nextInt();
        input.nextLine(); // Очистка буфера

        AcademicStatus status = switch (choice) {
            case 1 -> AcademicStatus.EXCELLENT;
            case 2 -> AcademicStatus.GOOD;
            case 3 -> AcademicStatus.SATISFACTORY;
            case 4 -> AcademicStatus.UNSATISFACTORY;
            default -> null;
        };

        if (status != null) {
            ArrayList<Student> results = registry.getStudentsByStatus(status);
            displaySearchResults(results, status.toString());
        } else {
            System.out.println("Invalid choice");
        }
    }

    // Отображение результатов поиска
    private void displaySearchResults(ArrayList<Student> results, String searchType) {
        if (results.isEmpty()) {
            System.out.println("\nNo students found");
            return;
        }

        System.out.println("\n=== Search Results (" + searchType + ") ===");
        for (Student student : results) {
            student.displayShort();
        }
        System.out.println("\nFound: " + results.size() + " student(s)");
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        RegistryUI ui = new RegistryUI("University of Technology");
        ui.run();
    }
}