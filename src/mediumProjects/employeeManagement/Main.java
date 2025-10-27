package mediumProjects.employeeManagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

// Перечисление для департаментов
enum Department {
    IT,
    HR,
    FINANCE,
    MARKETING,
    OPERATIONS
}

// Перечисление для статуса сотрудника
enum EmployeeStatus {
    ACTIVE,
    ON_LEAVE,
    TERMINATED
}

// Абстрактный класс сотрудника
abstract class Employee {
    protected static int employeeCounter = 1000;
    protected int id;
    protected String name;
    protected double baseSalary;
    protected Department department;
    protected LocalDate hireDate;
    protected EmployeeStatus status;

    // Конструктор
    public Employee(String name, double baseSalary, Department department) {
        this.id = employeeCounter++;
        this.name = name;
        this.baseSalary = baseSalary;
        this.department = department;
        this.hireDate = LocalDate.now();
        this.status = EmployeeStatus.ACTIVE;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    // Сеттеры
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    // Абстрактный метод расчета зарплаты (полиморфизм)
    public abstract double calculateSalary();

    // Абстрактный метод получения должности
    public abstract String getPosition();

    // Метод отображения информации о сотруднике
    public void displayInfo() {
        System.out.println("\n=== Employee Information ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Position: " + getPosition());
        System.out.println("Department: " + department);
        System.out.printf("Base Salary: $%.2f%n", baseSalary);
        System.out.printf("Total Salary: $%.2f%n", calculateSalary());
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Status: " + status);
        System.out.println("---");
    }

    // Метод краткого отображения
    public void displayShort() {
        String statusSymbol = status == EmployeeStatus.ACTIVE ? "✓" : "✗";
        System.out.printf("[%s] ID: %d | %s | %s | $%.2f%n",
                statusSymbol, id, name, getPosition(), calculateSalary());
    }
}

// Класс менеджера
class Manager extends Employee {
    private double bonus;
    private int teamSize;

    // Конструктор
    public Manager(String name, double baseSalary, Department department, double bonus, int teamSize) {
        super(name, baseSalary, department);
        this.bonus = bonus;
        this.teamSize = teamSize;
    }

    // Геттеры
    public double getBonus() {
        return bonus;
    }

    public int getTeamSize() {
        return teamSize;
    }

    // Сеттеры
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    // Переопределение метода расчета зарплаты (полиморфизм)
    @Override
    public double calculateSalary() {
        // Базовая зарплата + бонус + премия за размер команды
        double teamBonus = teamSize * 100; // $100 за каждого члена команды
        return baseSalary + bonus + teamBonus;
    }

    // Переопределение метода получения должности
    @Override
    public String getPosition() {
        return "Manager";
    }

    // Переопределение метода отображения информации
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Bonus: $%.2f%n", bonus);
        System.out.println("Team Size: " + teamSize);
        System.out.printf("Team Bonus: $%.2f%n", teamSize * 100);
    }
}

// Класс разработчика
class Developer extends Employee {
    private String programmingLanguage;
    private int projectCount;
    private double projectBonus;

    // Конструктор
    public Developer(String name, double baseSalary, Department department,
                     String programmingLanguage, int projectCount) {
        super(name, baseSalary, department);
        this.programmingLanguage = programmingLanguage;
        this.projectCount = projectCount;
        this.projectBonus = 500.0; // $500 за проект
    }

    // Геттеры
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public int getProjectCount() {
        return projectCount;
    }

    // Сеттеры
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    // Переопределение метода расчета зарплаты (полиморфизм)
    @Override
    public double calculateSalary() {
        // Базовая зарплата + бонус за проекты
        return baseSalary + (projectCount * projectBonus);
    }

    // Переопределение метода получения должности
    @Override
    public String getPosition() {
        return "Developer (" + programmingLanguage + ")";
    }

    // Переопределение метода отображения информации
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("Completed Projects: " + projectCount);
        System.out.printf("Project Bonus: $%.2f%n", projectCount * projectBonus);
    }
}

// Класс дизайнера
class Designer extends Employee {
    private String specialization;
    private int designCount;
    private double designBonus;

    // Конструктор
    public Designer(String name, double baseSalary, Department department,
                    String specialization, int designCount) {
        super(name, baseSalary, department);
        this.specialization = specialization;
        this.designCount = designCount;
        this.designBonus = 300.0; // $300 за дизайн
    }

    // Геттеры
    public String getSpecialization() {
        return specialization;
    }

    public int getDesignCount() {
        return designCount;
    }

    // Сеттеры
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setDesignCount(int designCount) {
        this.designCount = designCount;
    }

    // Переопределение метода расчета зарплаты (полиморфизм)
    @Override
    public double calculateSalary() {
        // Базовая зарплата + бонус за дизайны + премия за креативность
        double creativityBonus = baseSalary * 0.15; // 15% от базовой зарплаты
        return baseSalary + (designCount * designBonus) + creativityBonus;
    }

    // Переопределение метода получения должности
    @Override
    public String getPosition() {
        return "Designer (" + specialization + ")";
    }

    // Переопределение метода отображения информации
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
        System.out.println("Completed Designs: " + designCount);
        System.out.printf("Design Bonus: $%.2f%n", designCount * designBonus);
        System.out.printf("Creativity Bonus: $%.2f%n", baseSalary * 0.15);
    }
}

// Класс компании
class Company {
    private String companyName;
    private ArrayList<Employee> employees;

    // Конструктор
    public Company(String companyName) {
        this.companyName = companyName;
        this.employees = new ArrayList<>();
    }

    // Метод добавления сотрудника
    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("\nEmployee added successfully!");
        System.out.println("Employee ID: " + employee.getId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Position: " + employee.getPosition());
    }

    // Метод удаления сотрудника
    public boolean removeEmployee(int employeeId) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == employeeId) {
                Employee removed = employees.remove(i);
                System.out.println("\nEmployee removed: " + removed.getName());
                return true;
            }
        }
        System.out.println("\nError: Employee with ID " + employeeId + " not found");
        return false;
    }

    // Метод поиска сотрудника по ID
    public Employee findEmployeeById(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    // Метод поиска сотрудников по имени
    public ArrayList<Employee> searchByName(String name) {
        ArrayList<Employee> results = new ArrayList<>();
        String searchTerm = name.toLowerCase();

        for (Employee employee : employees) {
            if (employee.getName().toLowerCase().contains(searchTerm)) {
                results.add(employee);
            }
        }

        return results;
    }

    // Метод поиска сотрудников по департаменту
    public ArrayList<Employee> getEmployeesByDepartment(Department department) {
        ArrayList<Employee> results = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                results.add(employee);
            }
        }

        return results;
    }

    // Метод получения сотрудников по должности (демонстрация полиморфизма)
    public ArrayList<Employee> getEmployeesByType(Class<?> type) {
        ArrayList<Employee> results = new ArrayList<>();

        for (Employee employee : employees) {
            if (type.isInstance(employee)) {
                results.add(employee);
            }
        }

        return results;
    }

    // Метод расчета общего фонда заработной платы (полиморфизм)
    public double getTotalPayroll() {
        double total = 0;
        for (Employee employee : employees) {
            if (employee.getStatus() == EmployeeStatus.ACTIVE) {
                total += employee.calculateSalary(); // Полиморфный вызов
            }
        }
        return total;
    }

    // Метод расчета средней зарплаты
    public double getAverageSalary() {
        if (employees.isEmpty()) {
            return 0;
        }
        return getTotalPayroll() / getActiveEmployeeCount();
    }

    // Метод получения количества активных сотрудников
    public int getActiveEmployeeCount() {
        int count = 0;
        for (Employee employee : employees) {
            if (employee.getStatus() == EmployeeStatus.ACTIVE) {
                count++;
            }
        }
        return count;
    }

    // Метод отображения всех сотрудников
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("\nNo employees in the company");
            return;
        }

        System.out.println("\n=== " + companyName + " - All Employees ===");
        for (Employee employee : employees) {
            employee.displayShort();
        }
        System.out.println("\nTotal employees: " + employees.size());
    }

    // Метод отображения сотрудников по типу (демонстрация полиморфизма)
    public void displayEmployeesByType() {
        System.out.println("\n=== Employees by Type ===");

        ArrayList<Employee> managers = getEmployeesByType(Manager.class);
        if (!managers.isEmpty()) {
            System.out.println("\n--- Managers ---");
            for (Employee emp : managers) {
                emp.displayShort();
            }
        }

        ArrayList<Employee> developers = getEmployeesByType(Developer.class);
        if (!developers.isEmpty()) {
            System.out.println("\n--- Developers ---");
            for (Employee emp : developers) {
                emp.displayShort();
            }
        }

        ArrayList<Employee> designers = getEmployeesByType(Designer.class);
        if (!designers.isEmpty()) {
            System.out.println("\n--- Designers ---");
            for (Employee emp : designers) {
                emp.displayShort();
            }
        }
    }

    // Метод отображения сотрудников по департаменту
    public void displayByDepartment() {
        System.out.println("\n=== Employees by Department ===");

        for (Department dept : Department.values()) {
            ArrayList<Employee> deptEmployees = getEmployeesByDepartment(dept);
            if (!deptEmployees.isEmpty()) {
                System.out.println("\n--- " + dept + " ---");
                for (Employee emp : deptEmployees) {
                    emp.displayShort();
                }
            }
        }
    }

    // Метод отображения статистики компании
    public void displayStatistics() {
        int totalEmployees = employees.size();
        int activeEmployees = getActiveEmployeeCount();
        int managers = getEmployeesByType(Manager.class).size();
        int developers = getEmployeesByType(Developer.class).size();
        int designers = getEmployeesByType(Designer.class).size();

        System.out.println("\n=== " + companyName + " Statistics ===");
        System.out.println("Total Employees: " + totalEmployees);
        System.out.println("Active Employees: " + activeEmployees);
        System.out.println("\nEmployee Distribution:");
        System.out.println("  Managers: " + managers);
        System.out.println("  Developers: " + developers);
        System.out.println("  Designers: " + designers);

        System.out.printf("\nTotal Payroll: $%.2f%n", getTotalPayroll());
        System.out.printf("Average Salary: $%.2f%n", getAverageSalary());

        // Статистика по департаментам
        System.out.println("\nDepartment Distribution:");
        for (Department dept : Department.values()) {
            int count = getEmployeesByDepartment(dept).size();
            if (count > 0) {
                System.out.println("  " + dept + ": " + count);
            }
        }
    }

    // Метод получения топ сотрудников по зарплате
    public void displayTopEarners(int count) {
        if (employees.isEmpty()) {
            System.out.println("\nNo employees in the company");
            return;
        }

        // Сортировка по зарплате (полиморфный вызов calculateSalary)
        ArrayList<Employee> sortedEmployees = new ArrayList<>(employees);
        sortedEmployees.sort((e1, e2) ->
                Double.compare(e2.calculateSalary(), e1.calculateSalary()));

        int displayCount = Math.min(count, sortedEmployees.size());

        System.out.println("\n=== Top " + displayCount + " Earners ===");
        for (int i = 0; i < displayCount; i++) {
            Employee emp = sortedEmployees.get(i);
            System.out.printf("%d. %s (%s) - $%.2f%n",
                    i + 1, emp.getName(), emp.getPosition(),
                    emp.calculateSalary());
        }
    }
}

// Класс для работы с пользовательским интерфейсом
class CompanyUI {
    private Scanner input;
    private Company company;

    public CompanyUI(String companyName) {
        this.input = new Scanner(System.in);
        this.company = new Company(companyName);
        initializeSampleData();
    }

    // Инициализация примерных данных
    private void initializeSampleData() {
        company.addEmployee(new Manager("John Smith", 80000, Department.IT, 10000, 5));
        company.addEmployee(new Developer("Alice Johnson", 70000, Department.IT, "Java", 8));
        company.addEmployee(new Developer("Bob Wilson", 75000, Department.IT, "Python", 10));
        company.addEmployee(new Designer("Emma Davis", 60000, Department.MARKETING, "UI/UX", 12));
        company.addEmployee(new Manager("Sarah Brown", 85000, Department.HR, 8000, 3));
    }

    public void run() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Employee Management System            ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        while (true) {
            try {
                displayMainMenu();
                int choice = input.nextInt();
                input.nextLine(); // Очистка буфера

                if (choice == 13) {
                    System.out.println("\nGoodbye!");
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
        System.out.println("1. Add new employee");
        System.out.println("2. Remove employee");
        System.out.println("3. View employee details");
        System.out.println("4. Search employee by name");
        System.out.println("5. View all employees");
        System.out.println("6. View employees by type");
        System.out.println("7. View employees by department");
        System.out.println("8. Update employee salary");
        System.out.println("9. Update employee status");
        System.out.println("10. View total payroll");
        System.out.println("11. View top earners");
        System.out.println("12. View company statistics");
        System.out.println("13. Exit");
        System.out.print("Enter choice (1-13): ");
    }

    // Обработка выбора меню
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addNewEmployee();
                break;
            case 2:
                removeEmployee();
                break;
            case 3:
                viewEmployeeDetails();
                break;
            case 4:
                searchByName();
                break;
            case 5:
                company.displayAllEmployees();
                break;
            case 6:
                company.displayEmployeesByType();
                break;
            case 7:
                company.displayByDepartment();
                break;
            case 8:
                updateSalary();
                break;
            case 9:
                updateStatus();
                break;
            case 10:
                viewTotalPayroll();
                break;
            case 11:
                viewTopEarners();
                break;
            case 12:
                company.displayStatistics();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Добавление нового сотрудника
    private void addNewEmployee() {
        System.out.println("\nSelect employee type:");
        System.out.println("1. Manager");
        System.out.println("2. Developer");
        System.out.println("3. Designer");
        System.out.print("Enter choice (1-3): ");

        int type = input.nextInt();
        input.nextLine(); // Очистка буфера

        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter base salary: $");
        double salary = input.nextDouble();
        input.nextLine(); // Очистка буфера

        System.out.println("Select department:");
        for (int i = 0; i < Department.values().length; i++) {
            System.out.println((i + 1) + ". " + Department.values()[i]);
        }
        System.out.print("Enter choice: ");
        int deptChoice = input.nextInt();
        input.nextLine(); // Очистка буфера

        Department dept = Department.values()[deptChoice - 1];

        Employee employee = null;

        switch (type) {
            case 1: // Manager
                System.out.print("Enter bonus amount: $");
                double bonus = input.nextDouble();
                System.out.print("Enter team size: ");
                int teamSize = input.nextInt();
                input.nextLine(); // Очистка буфера
                employee = new Manager(name, salary, dept, bonus, teamSize);
                break;

            case 2: // Developer
                System.out.print("Enter programming language: ");
                String language = input.nextLine();
                System.out.print("Enter number of completed projects: ");
                int projects = input.nextInt();
                input.nextLine(); // Очистка буфера
                employee = new Developer(name, salary, dept, language, projects);
                break;

            case 3: // Designer
                System.out.print("Enter specialization: ");
                String specialization = input.nextLine();
                System.out.print("Enter number of completed designs: ");
                int designs = input.nextInt();
                input.nextLine(); // Очистка буфера
                employee = new Designer(name, salary, dept, specialization, designs);
                break;
        }

        if (employee != null) {
            company.addEmployee(employee);
        }
    }

    // Удаление сотрудника
    private void removeEmployee() {
        System.out.print("\nEnter employee ID to remove: ");
        int id = input.nextInt();
        input.nextLine(); // Очистка буфера

        company.removeEmployee(id);
    }

    // Просмотр деталей сотрудника
    private void viewEmployeeDetails() {
        System.out.print("\nEnter employee ID: ");
        int id = input.nextInt();
        input.nextLine(); // Очистка буфера

        Employee employee = company.findEmployeeById(id);
        if (employee != null) {
            employee.displayInfo(); // Полиморфный вызов
        } else {
            System.out.println("Error: Employee not found");
        }
    }

    // Поиск по имени
    private void searchByName() {
        System.out.print("\nEnter name to search: ");
        String name = input.nextLine();

        ArrayList<Employee> results = company.searchByName(name);

        if (results.isEmpty()) {
            System.out.println("\nNo employees found");
            return;
        }

        System.out.println("\n=== Search Results ===");
        for (Employee emp : results) {
            emp.displayShort();
        }
        System.out.println("\nFound: " + results.size() + " employee(s)");
    }

    // Обновление зарплаты
    private void updateSalary() {
        System.out.print("\nEnter employee ID: ");
        int id = input.nextInt();
        input.nextLine(); // Очистка буфера

        Employee employee = company.findEmployeeById(id);
        if (employee == null) {
            System.out.println("Error: Employee not found");
            return;
        }

        System.out.printf("Current base salary: $%.2f%n", employee.getBaseSalary());
        System.out.print("Enter new base salary: $");
        double newSalary = input.nextDouble();
        input.nextLine(); // Очистка буфера

        employee.setBaseSalary(newSalary);
        System.out.println("\nSalary updated successfully!");
        System.out.printf("New total salary: $%.2f%n", employee.calculateSalary());
    }

    // Обновление статуса
    private void updateStatus() {
        System.out.print("\nEnter employee ID: ");
        int id = input.nextInt();
        input.nextLine(); // Очистка буфера

        Employee employee = company.findEmployeeById(id);
        if (employee == null) {
            System.out.println("Error: Employee not found");
            return;
        }

        System.out.println("\nSelect new status:");
        System.out.println("1. ACTIVE");
        System.out.println("2. ON_LEAVE");
        System.out.println("3. TERMINATED");
        System.out.print("Enter choice (1-3): ");

        int statusChoice = input.nextInt();
        input.nextLine(); // Очистка буфера

        EmployeeStatus newStatus = switch (statusChoice) {
            case 1 -> EmployeeStatus.ACTIVE;
            case 2 -> EmployeeStatus.ON_LEAVE;
            case 3 -> EmployeeStatus.TERMINATED;
            default -> null;
        };

        if (newStatus != null) {
            employee.setStatus(newStatus);
            System.out.println("\nStatus updated successfully!");
        } else {
            System.out.println("Invalid choice");
        }
    }

    // Просмотр общего фонда заработной платы
    private void viewTotalPayroll() {
        double total = company.getTotalPayroll();
        System.out.printf("\nTotal Payroll: $%.2f%n", total);
        System.out.printf("Average Salary: $%.2f%n", company.getAverageSalary());
        System.out.println("Active Employees: " + company.getActiveEmployeeCount());
    }

    // Просмотр топ сотрудников
    private void viewTopEarners() {
        System.out.print("\nEnter number of top earners to display: ");
        int count = input.nextInt();
        input.nextLine(); // Очистка буфера

        company.displayTopEarners(count);
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        CompanyUI ui = new CompanyUI("TechCorp International");
        ui.run();
    }
}