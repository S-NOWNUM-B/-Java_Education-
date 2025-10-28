package mediumProjects.parkingLot;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

// Перечисление типов парковочных мест
enum SpotType {
    COMPACT("Compact", 1),
    REGULAR("Regular", 1),
    LARGE("Large", 2),
    HANDICAPPED("Handicapped", 1);

    private final String displayName;
    private final int multiplier;

    SpotType(String displayName, int multiplier) {
        this.displayName = displayName;
        this.multiplier = multiplier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMultiplier() {
        return multiplier;
    }
}

// Перечисление статусов парковочного талона
enum TicketStatus {
    ACTIVE,
    PAID,
    CANCELLED
}

// Абстрактный класс транспортного средства
abstract class Vehicle {
    protected String licensePlate;
    protected String color;
    protected String ownerName;

    // Конструктор
    public Vehicle(String licensePlate, String color, String ownerName) {
        this.licensePlate = licensePlate;
        this.color = color;
        this.ownerName = ownerName;
    }

    // Геттеры
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getColor() {
        return color;
    }

    public String getOwnerName() {
        return ownerName;
    }

    // Абстрактный метод получения типа транспорта
    public abstract String getVehicleType();

    // Абстрактный метод получения подходящих типов мест
    public abstract ArrayList<SpotType> getSuitableSpotTypes();

    // Абстрактный метод расчета базового тарифа (за час)
    public abstract double getHourlyRate();

    // Метод отображения информации
    public void displayInfo() {
        System.out.println("Type: " + getVehicleType());
        System.out.println("License Plate: " + licensePlate);
        System.out.println("Color: " + color);
        System.out.println("Owner: " + ownerName);
        System.out.printf("Hourly Rate: $%.2f%n", getHourlyRate());
    }
}

// Класс мотоцикла
class Motorcycle extends Vehicle {
    private int engineCapacity;

    // Конструктор
    public Motorcycle(String licensePlate, String color, String ownerName, int engineCapacity) {
        super(licensePlate, color, ownerName);
        this.engineCapacity = engineCapacity;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public ArrayList<SpotType> getSuitableSpotTypes() {
        ArrayList<SpotType> types = new ArrayList<>();
        types.add(SpotType.COMPACT);
        types.add(SpotType.REGULAR);
        return types;
    }

    @Override
    public double getHourlyRate() {
        return 2.0; // $2 в час для мотоциклов
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Engine Capacity: " + engineCapacity + "cc");
    }
}

// Класс легкового автомобиля
class Car extends Vehicle {
    private String model;
    private boolean isElectric;

    // Конструктор
    public Car(String licensePlate, String color, String ownerName, String model, boolean isElectric) {
        super(licensePlate, color, ownerName);
        this.model = model;
        this.isElectric = isElectric;
    }

    public String getModel() {
        return model;
    }

    public boolean isElectric() {
        return isElectric;
    }

    @Override
    public String getVehicleType() {
        return isElectric ? "Electric Car" : "Car";
    }

    @Override
    public ArrayList<SpotType> getSuitableSpotTypes() {
        ArrayList<SpotType> types = new ArrayList<>();
        types.add(SpotType.REGULAR);
        types.add(SpotType.HANDICAPPED);
        return types;
    }

    @Override
    public double getHourlyRate() {
        return isElectric ? 3.0 : 4.0; // Скидка для электромобилей
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Model: " + model);
        System.out.println("Electric: " + (isElectric ? "Yes" : "No"));
    }
}

// Класс грузовика
class Truck extends Vehicle {
    private double weightCapacity;
    private int numberOfAxles;

    // Конструктор
    public Truck(String licensePlate, String color, String ownerName,
                 double weightCapacity, int numberOfAxles) {
        super(licensePlate, color, ownerName);
        this.weightCapacity = weightCapacity;
        this.numberOfAxles = numberOfAxles;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }

    public int getNumberOfAxles() {
        return numberOfAxles;
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }

    @Override
    public ArrayList<SpotType> getSuitableSpotTypes() {
        ArrayList<SpotType> types = new ArrayList<>();
        types.add(SpotType.LARGE);
        return types;
    }

    @Override
    public double getHourlyRate() {
        // Тариф зависит от количества осей
        return 6.0 + (numberOfAxles - 2) * 2.0;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Weight Capacity: %.1f tons%n", weightCapacity);
        System.out.println("Number of Axles: " + numberOfAxles);
    }
}

// Класс парковочного места
class ParkingSpot {
    private int spotNumber;
    private SpotType type;
    private boolean isOccupied;
    private Vehicle vehicle;
    private int floor;

    // Конструктор
    public ParkingSpot(int spotNumber, SpotType type, int floor) {
        this.spotNumber = spotNumber;
        this.type = type;
        this.isOccupied = false;
        this.vehicle = null;
        this.floor = floor;
    }

    // Геттеры
    public int getSpotNumber() {
        return spotNumber;
    }

    public SpotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getFloor() {
        return floor;
    }

    // Метод занятия места
    public boolean occupy(Vehicle vehicle) {
        if (isOccupied) {
            return false;
        }

        // Проверка совместимости типа места с транспортом
        if (!vehicle.getSuitableSpotTypes().contains(type)) {
            return false;
        }

        this.vehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    // Метод освобождения места
    public void vacate() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    // Метод отображения информации о месте
    public void displayInfo() {
        System.out.println("\n=== Parking Spot Information ===");
        System.out.println("Spot Number: " + spotNumber);
        System.out.println("Type: " + type.getDisplayName());
        System.out.println("Floor: " + floor);
        System.out.println("Status: " + (isOccupied ? "Occupied" : "Available"));

        if (isOccupied && vehicle != null) {
            System.out.println("\nParked Vehicle:");
            vehicle.displayInfo();
        }
        System.out.println("---");
    }

    // Метод краткого отображения места
    public void displayShort() {
        String status = isOccupied ? "[X]" : "[✓]";
        String vehicleInfo = isOccupied && vehicle != null ?
                " - " + vehicle.getVehicleType() + " (" + vehicle.getLicensePlate() + ")" : "";

        System.out.printf("%s Spot #%d | Floor %d | %s%s%n",
                status, spotNumber, floor, type.getDisplayName(), vehicleInfo);
    }
}

// Класс парковочного талона
class ParkingTicket {
    private static int ticketCounter = 1000;
    private int ticketId;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private TicketStatus status;
    private double fee;

    // Конструктор
    public ParkingTicket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = ticketCounter++;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
        this.exitTime = null;
        this.status = TicketStatus.ACTIVE;
        this.fee = 0.0;
    }

    // Геттеры
    public int getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public double getFee() {
        return fee;
    }

    // Метод расчета продолжительности парковки
    public Duration getParkingDuration() {
        LocalDateTime end = exitTime != null ? exitTime : LocalDateTime.now();
        return Duration.between(entryTime, end);
    }

    // Метод расчета стоимости парковки
    public double calculateFee() {
        Duration duration = getParkingDuration();
        long minutes = duration.toMinutes();

        // Минимум 1 час
        double hours = Math.max(1.0, Math.ceil(minutes / 60.0));

        // Базовый тариф транспорта * множитель типа места
        double baseRate = vehicle.getHourlyRate();
        int spotMultiplier = spot.getType().getMultiplier();

        double baseFee = hours * baseRate * spotMultiplier;

        // Скидка за длительную парковку (более 24 часов - 20% скидка)
        if (hours > 24) {
            baseFee *= 0.80;
        }

        // Налог 10%
        double tax = baseFee * 0.10;

        return baseFee + tax;
    }

    // Метод завершения парковки
    public void complete() {
        this.exitTime = LocalDateTime.now();
        this.fee = calculateFee();
        this.status = TicketStatus.PAID;
    }

    // Метод отмены талона
    public void cancel() {
        this.status = TicketStatus.CANCELLED;
    }

    // Метод отображения информации о талоне
    public void displayInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("\n=== Parking Ticket ===");
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Status: " + status);
        System.out.println("\nVehicle:");
        vehicle.displayInfo();
        System.out.println("\nParking Spot: #" + spot.getSpotNumber() +
                " (" + spot.getType().getDisplayName() + ")");
        System.out.println("Floor: " + spot.getFloor());
        System.out.println("\nEntry Time: " + entryTime.format(formatter));

        if (exitTime != null) {
            System.out.println("Exit Time: " + exitTime.format(formatter));
        }

        Duration duration = getParkingDuration();
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        System.out.printf("Duration: %d hours %d minutes%n", hours, minutes);

        System.out.printf("Fee: $%.2f%n", status == TicketStatus.PAID ? fee : calculateFee());
        System.out.println("---");
    }
}

// Класс парковки
class ParkingLot {
    private String name;
    private ArrayList<ParkingSpot> spots;
    private ArrayList<ParkingTicket> tickets;
    private int totalFloors;

    // Конструктор
    public ParkingLot(String name, int totalFloors) {
        this.name = name;
        this.spots = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.totalFloors = totalFloors;
        initializeParkingSpots();
    }

    // Инициализация парковочных мест
    private void initializeParkingSpots() {
        int spotNumber = 1;

        for (int floor = 1; floor <= totalFloors; floor++) {
            // На каждом этаже:
            // 5 компактных мест для мотоциклов
            for (int i = 0; i < 5; i++) {
                spots.add(new ParkingSpot(spotNumber++, SpotType.COMPACT, floor));
            }

            // 10 обычных мест для легковых
            for (int i = 0; i < 10; i++) {
                spots.add(new ParkingSpot(spotNumber++, SpotType.REGULAR, floor));
            }

            // 3 больших места для грузовиков
            for (int i = 0; i < 3; i++) {
                spots.add(new ParkingSpot(spotNumber++, SpotType.LARGE, floor));
            }

            // 2 места для инвалидов
            for (int i = 0; i < 2; i++) {
                spots.add(new ParkingSpot(spotNumber++, SpotType.HANDICAPPED, floor));
            }
        }
    }

    // Метод получения доступных мест
    public ArrayList<ParkingSpot> getAvailableSpots() {
        ArrayList<ParkingSpot> available = new ArrayList<>();

        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                available.add(spot);
            }
        }

        return available;
    }

    // Метод получения доступных мест по типу
    public ArrayList<ParkingSpot> getAvailableSpotsByType(SpotType type) {
        ArrayList<ParkingSpot> available = new ArrayList<>();

        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.getType() == type) {
                available.add(spot);
            }
        }

        return available;
    }

    // Метод получения доступных мест для транспорта
    public ArrayList<ParkingSpot> getAvailableSpotsForVehicle(Vehicle vehicle) {
        ArrayList<ParkingSpot> available = new ArrayList<>();
        ArrayList<SpotType> suitableTypes = vehicle.getSuitableSpotTypes();

        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && suitableTypes.contains(spot.getType())) {
                available.add(spot);
            }
        }

        return available;
    }

    // Метод парковки транспорта
    public ParkingTicket parkVehicle(Vehicle vehicle, int spotNumber) {
        // Поиск места
        ParkingSpot spot = findSpotByNumber(spotNumber);

        if (spot == null) {
            System.out.println("Error: Parking spot not found");
            return null;
        }

        if (spot.isOccupied()) {
            System.out.println("Error: Parking spot is already occupied");
            return null;
        }

        // Попытка занять место
        if (!spot.occupy(vehicle)) {
            System.out.println("Error: Vehicle type not compatible with spot type");
            return null;
        }

        // Создание талона
        ParkingTicket ticket = new ParkingTicket(vehicle, spot);
        tickets.add(ticket);

        System.out.println("\n✓ Vehicle parked successfully!");
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Spot: #" + spot.getSpotNumber() + " (Floor " + spot.getFloor() + ")");
        System.out.printf("Hourly Rate: $%.2f%n", vehicle.getHourlyRate());

        return ticket;
    }

    // Метод выезда транспорта
    public boolean removeVehicle(int ticketId) {
        ParkingTicket ticket = findTicketById(ticketId);

        if (ticket == null) {
            System.out.println("Error: Ticket not found");
            return false;
        }

        if (ticket.getStatus() != TicketStatus.ACTIVE) {
            System.out.println("Error: Ticket is not active");
            return false;
        }

        // Завершение парковки
        ticket.complete();
        ticket.getSpot().vacate();

        System.out.println("\n✓ Vehicle removed successfully!");
        ticket.displayInfo();

        return true;
    }

    // Метод поиска места по номеру
    public ParkingSpot findSpotByNumber(int spotNumber) {
        for (ParkingSpot spot : spots) {
            if (spot.getSpotNumber() == spotNumber) {
                return spot;
            }
        }
        return null;
    }

    // Метод поиска талона по ID
    public ParkingTicket findTicketById(int ticketId) {
        for (ParkingTicket ticket : tickets) {
            if (ticket.getTicketId() == ticketId) {
                return ticket;
            }
        }
        return null;
    }

    // Метод поиска талона по номеру места
    public ParkingTicket findActiveTicketBySpot(int spotNumber) {
        for (ParkingTicket ticket : tickets) {
            if (ticket.getSpot().getSpotNumber() == spotNumber &&
                    ticket.getStatus() == TicketStatus.ACTIVE) {
                return ticket;
            }
        }
        return null;
    }

    // Метод отображения всех мест
    public void displayAllSpots() {
        System.out.println("\n=== " + name + " - All Parking Spots ===");

        for (int floor = 1; floor <= totalFloors; floor++) {
            System.out.println("\n--- Floor " + floor + " ---");
            for (ParkingSpot spot : spots) {
                if (spot.getFloor() == floor) {
                    spot.displayShort();
                }
            }
        }

        System.out.println("\nTotal spots: " + spots.size());
    }

    // Метод отображения доступных мест
    public void displayAvailableSpots() {
        ArrayList<ParkingSpot> available = getAvailableSpots();

        if (available.isEmpty()) {
            System.out.println("\nNo available spots");
            return;
        }

        System.out.println("\n=== Available Parking Spots ===");
        for (ParkingSpot spot : available) {
            spot.displayShort();
        }
        System.out.println("\nAvailable spots: " + available.size());
    }

    // Метод отображения активных талонов
    public void displayActiveTickets() {
        ArrayList<ParkingTicket> activeTickets = new ArrayList<>();

        for (ParkingTicket ticket : tickets) {
            if (ticket.getStatus() == TicketStatus.ACTIVE) {
                activeTickets.add(ticket);
            }
        }

        if (activeTickets.isEmpty()) {
            System.out.println("\nNo active tickets");
            return;
        }

        System.out.println("\n=== Active Parking Tickets ===");
        for (ParkingTicket ticket : activeTickets) {
            System.out.printf("Ticket #%d | Spot #%d | %s | %s%n",
                    ticket.getTicketId(),
                    ticket.getSpot().getSpotNumber(),
                    ticket.getVehicle().getVehicleType(),
                    ticket.getVehicle().getLicensePlate());
        }
        System.out.println("\nActive tickets: " + activeTickets.size());
    }

    // Метод отображения статистики
    public void displayStatistics() {
        int totalSpots = spots.size();
        int occupiedSpots = 0;
        int totalTickets = tickets.size();
        int activeTickets = 0;
        double totalRevenue = 0;

        // Подсчет по типам транспорта
        int motorcycleCount = 0;
        int carCount = 0;
        int truckCount = 0;

        for (ParkingSpot spot : spots) {
            if (spot.isOccupied()) {
                occupiedSpots++;

                Vehicle v = spot.getVehicle();
                if (v instanceof Motorcycle) motorcycleCount++;
                else if (v instanceof Car) carCount++;
                else if (v instanceof Truck) truckCount++;
            }
        }

        for (ParkingTicket ticket : tickets) {
            if (ticket.getStatus() == TicketStatus.ACTIVE) {
                activeTickets++;
            } else if (ticket.getStatus() == TicketStatus.PAID) {
                totalRevenue += ticket.getFee();
            }
        }

        double occupancyRate = (double) occupiedSpots / totalSpots * 100;

        System.out.println("\n=== " + name + " Statistics ===");
        System.out.println("Total Parking Spots: " + totalSpots);
        System.out.println("Occupied Spots: " + occupiedSpots);
        System.out.println("Available Spots: " + (totalSpots - occupiedSpots));
        System.out.printf("Occupancy Rate: %.1f%%%n", occupancyRate);

        System.out.println("\nVehicle Distribution:");
        System.out.println("  Motorcycles: " + motorcycleCount);
        System.out.println("  Cars: " + carCount);
        System.out.println("  Trucks: " + truckCount);

        System.out.println("\nTickets:");
        System.out.println("  Total Issued: " + totalTickets);
        System.out.println("  Active: " + activeTickets);
        System.out.println("  Completed: " + (totalTickets - activeTickets));

        System.out.printf("\nTotal Revenue: $%.2f%n", totalRevenue);

        if (totalTickets - activeTickets > 0) {
            double avgRevenue = totalRevenue / (totalTickets - activeTickets);
            System.out.printf("Average Revenue per Ticket: $%.2f%n", avgRevenue);
        }
    }
}

// Класс для работы с пользовательским интерфейсом
class ParkingLotUI {
    private Scanner input;
    private ParkingLot parkingLot;

    public ParkingLotUI(String parkingLotName, int floors) {
        this.input = new Scanner(System.in);
        this.parkingLot = new ParkingLot(parkingLotName, floors);
    }

    public void run() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Parking Lot Management System         ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        while (true) {
            try {
                displayMainMenu();
                int choice = input.nextInt();
                input.nextLine(); // Очистка буфера

                if (choice == 10) {
                    System.out.println("\nThank you for using Parking Lot System!");
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
        System.out.println("1. View all parking spots");
        System.out.println("2. View available spots");
        System.out.println("3. Park vehicle");
        System.out.println("4. Remove vehicle");
        System.out.println("5. View ticket details");
        System.out.println("6. View active tickets");
        System.out.println("7. Calculate parking fee");
        System.out.println("8. View spot details");
        System.out.println("9. View statistics");
        System.out.println("10. Exit");
        System.out.print("Enter choice (1-10): ");
    }

    // Обработка выбора меню
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                parkingLot.displayAllSpots();
                break;
            case 2:
                parkingLot.displayAvailableSpots();
                break;
            case 3:
                parkVehicle();
                break;
            case 4:
                removeVehicle();
                break;
            case 5:
                viewTicketDetails();
                break;
            case 6:
                parkingLot.displayActiveTickets();
                break;
            case 7:
                calculateFee();
                break;
            case 8:
                viewSpotDetails();
                break;
            case 9:
                parkingLot.displayStatistics();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Парковка транспорта
    private void parkVehicle() {
        System.out.println("\nSelect vehicle type:");
        System.out.println("1. Motorcycle");
        System.out.println("2. Car");
        System.out.println("3. Truck");
        System.out.print("Enter choice (1-3): ");

        int type = input.nextInt();
        input.nextLine(); // Очистка буфера

        System.out.print("Enter license plate: ");
        String plate = input.nextLine();

        System.out.print("Enter color: ");
        String color = input.nextLine();

        System.out.print("Enter owner name: ");
        String owner = input.nextLine();

        Vehicle vehicle = null;

        switch (type) {
            case 1: // Motorcycle
                System.out.print("Enter engine capacity (cc): ");
                int capacity = input.nextInt();
                input.nextLine();
                vehicle = new Motorcycle(plate, color, owner, capacity);
                break;

            case 2: // Car
                System.out.print("Enter model: ");
                String model = input.nextLine();
                System.out.print("Is it electric? (y/n): ");
                boolean isElectric = input.nextLine().toLowerCase().startsWith("y");
                vehicle = new Car(plate, color, owner, model, isElectric);
                break;

            case 3: // Truck
                System.out.print("Enter weight capacity (tons): ");
                double weight = input.nextDouble();
                System.out.print("Enter number of axles: ");
                int axles = input.nextInt();
                input.nextLine();
                vehicle = new Truck(plate, color, owner, weight, axles);
                break;

            default:
                System.out.println("Invalid vehicle type");
                return;
        }

        // Показать доступные места для этого транспорта
        ArrayList<ParkingSpot> availableSpots = parkingLot.getAvailableSpotsForVehicle(vehicle);

        if (availableSpots.isEmpty()) {
            System.out.println("\nNo suitable parking spots available");
            return;
        }

        System.out.println("\n=== Available Spots for " + vehicle.getVehicleType() + " ===");
        for (ParkingSpot spot : availableSpots) {
            spot.displayShort();
        }

        System.out.print("\nEnter spot number to park: ");
        int spotNumber = input.nextInt();
        input.nextLine();

        parkingLot.parkVehicle(vehicle, spotNumber);
    }

    // Выезд транспорта
    private void removeVehicle() {
        System.out.print("\nEnter ticket ID: ");
        int ticketId = input.nextInt();
        input.nextLine();

        parkingLot.removeVehicle(ticketId);
    }

    // Просмотр деталей талона
    private void viewTicketDetails() {
        System.out.print("\nEnter ticket ID: ");
        int ticketId = input.nextInt();
        input.nextLine();

        ParkingTicket ticket = parkingLot.findTicketById(ticketId);

        if (ticket != null) {
            ticket.displayInfo();
        } else {
            System.out.println("Error: Ticket not found");
        }
    }

    // Расчет стоимости парковки
    private void calculateFee() {
        System.out.print("\nEnter ticket ID: ");
        int ticketId = input.nextInt();
        input.nextLine();

        ParkingTicket ticket = parkingLot.findTicketById(ticketId);

        if (ticket == null) {
            System.out.println("Error: Ticket not found");
            return;
        }

        System.out.println("\n=== Parking Fee Calculation ===");
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Vehicle: " + ticket.getVehicle().getVehicleType() +
                " (" + ticket.getVehicle().getLicensePlate() + ")");

        Duration duration = ticket.getParkingDuration();
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        System.out.printf("Duration: %d hours %d minutes%n", hours, minutes);

        System.out.printf("Estimated Fee: $%.2f%n", ticket.calculateFee());
    }

    // Просмотр деталей места
    private void viewSpotDetails() {
        System.out.print("\nEnter spot number: ");
        int spotNumber = input.nextInt();
        input.nextLine();

        ParkingSpot spot = parkingLot.findSpotByNumber(spotNumber);

        if (spot != null) {
            spot.displayInfo();
        } else {
            System.out.println("Error: Spot not found");
        }
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        ParkingLotUI ui = new ParkingLotUI("Central Parking", 3);
        ui.run();
    }
}
