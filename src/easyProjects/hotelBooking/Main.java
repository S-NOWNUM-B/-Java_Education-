package mediumProjects.hotelBooking;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

// Перечисление типов номеров
enum RoomType {
    SINGLE("Single Room", 1, 100.0),
    DOUBLE("Double Room", 2, 150.0),
    SUITE("Suite", 4, 300.0),
    DELUXE("Deluxe Suite", 6, 500.0);

    private final String displayName;
    private final int capacity;
    private final double basePrice;

    RoomType(String displayName, int capacity, double basePrice) {
        this.displayName = displayName;
        this.capacity = capacity;
        this.basePrice = basePrice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getBasePrice() {
        return basePrice;
    }
}

// Перечисление статусов бронирования
enum BookingStatus {
    CONFIRMED,
    CHECKED_IN,
    CHECKED_OUT,
    CANCELLED
}

// Класс гостя
class Guest {
    private static int guestCounter = 1;
    private int guestId;
    private String name;
    private String email;
    private String phoneNumber;
    private String idDocument;

    // Конструктор
    public Guest(String name, String email, String phoneNumber, String idDocument) {
        this.guestId = guestCounter++;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idDocument = idDocument;
    }

    // Геттеры
    public int getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIdDocument() {
        return idDocument;
    }

    // Метод отображения информации о госте
    public void displayInfo() {
        System.out.println("\n=== Guest Information ===");
        System.out.println("Guest ID: " + guestId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("ID Document: " + idDocument);
        System.out.println("---");
    }
}

// Класс номера
class Room {
    private int roomNumber;
    private RoomType type;
    private double pricePerNight;
    private boolean isAvailable;
    private int floor;
    private boolean hasBalcony;
    private boolean hasSeaView;

    // Конструктор
    public Room(int roomNumber, RoomType type, int floor, boolean hasBalcony, boolean hasSeaView) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = type.getBasePrice();
        this.isAvailable = true;
        this.floor = floor;
        this.hasBalcony = hasBalcony;
        this.hasSeaView = hasSeaView;

        // Надбавка за балкон
        if (hasBalcony) {
            this.pricePerNight += 20.0;
        }

        // Надбавка за вид на море
        if (hasSeaView) {
            this.pricePerNight += 50.0;
        }
    }

    // Геттеры
    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getFloor() {
        return floor;
    }

    public boolean hasBalcony() {
        return hasBalcony;
    }

    public boolean hasSeaView() {
        return hasSeaView;
    }

    // Сеттеры
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // Метод отображения информации о номере
    public void displayInfo() {
        System.out.println("\n=== Room Information ===");
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Type: " + type.getDisplayName());
        System.out.println("Capacity: " + type.getCapacity() + " guests");
        System.out.printf("Price per night: $%.2f%n", pricePerNight);
        System.out.println("Floor: " + floor);
        System.out.println("Balcony: " + (hasBalcony ? "Yes" : "No"));
        System.out.println("Sea View: " + (hasSeaView ? "Yes" : "No"));
        System.out.println("Status: " + (isAvailable ? "Available" : "Occupied"));
        System.out.println("---");
    }

    // Метод краткого отображения номера
    public void displayShort() {
        String status = isAvailable ? "[✓]" : "[✗]";
        String features = "";
        if (hasBalcony) features += "🏖️ ";
        if (hasSeaView) features += "🌊 ";

        System.out.printf("%s Room %d | %s | Floor %d | $%.2f/night %s%n",
                status, roomNumber, type.getDisplayName(),
                floor, pricePerNight, features);
    }
}

// Класс бронирования
class Booking {
    private static int bookingCounter = 1000;
    private int bookingId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate bookingDate;
    private BookingStatus status;
    private double totalCost;

    // Конструктор
    public Booking(Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = bookingCounter++;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingDate = LocalDate.now();
        this.status = BookingStatus.CONFIRMED;
        this.totalCost = calculateTotalCost();
    }

    // Геттеры
    public int getBookingId() {
        return bookingId;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // Сеттер для статуса
    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    // Метод расчета количества ночей
    public long getNumberOfNights() {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    // Метод расчета общей стоимости
    public double calculateTotalCost() {
        long nights = getNumberOfNights();
        double baseCost = room.getPricePerNight() * nights;

        // Скидка при длительном проживании (более 7 ночей - 10% скидка)
        if (nights >= 7) {
            baseCost *= 0.90;
        }

        // Налог 10%
        double tax = baseCost * 0.10;

        return baseCost + tax;
    }

    // Метод проверки пересечения дат с другим бронированием
    public boolean overlapsWith(LocalDate newCheckIn, LocalDate newCheckOut) {
        // Проверка, пересекаются ли даты
        return !(newCheckOut.isBefore(checkInDate) ||
                newCheckOut.isEqual(checkInDate) ||
                newCheckIn.isAfter(checkOutDate) ||
                newCheckIn.isEqual(checkOutDate));
    }

    // Метод проверки, активно ли бронирование
    public boolean isActive() {
        return status == BookingStatus.CONFIRMED || status == BookingStatus.CHECKED_IN;
    }

    // Метод отображения информации о бронировании
    public void displayInfo() {
        System.out.println("\n=== Booking Information ===");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Guest: " + guest.getName());
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Room Type: " + room.getType().getDisplayName());
        System.out.println("Check-in: " + checkInDate);
        System.out.println("Check-out: " + checkOutDate);
        System.out.println("Number of nights: " + getNumberOfNights());
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Status: " + status);
        System.out.printf("Total Cost: $%.2f%n", totalCost);
        System.out.println("---");
    }

    // Метод краткого отображения бронирования
    public void displayShort() {
        String statusSymbol = switch (status) {
            case CONFIRMED -> "✓";
            case CHECKED_IN -> "🔑";
            case CHECKED_OUT -> "✔";
            case CANCELLED -> "✗";
        };

        System.out.printf("[%s] Booking #%d | Guest: %s | Room %d | %s to %s | $%.2f%n",
                statusSymbol, bookingId, guest.getName(),
                room.getRoomNumber(), checkInDate, checkOutDate, totalCost);
    }
}

// Класс отеля
class Hotel {
    private String hotelName;
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;
    private ArrayList<Guest> guests;

    // Конструктор
    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.guests = new ArrayList<>();
        initializeRooms();
    }

    // Инициализация номеров отеля
    private void initializeRooms() {
        // Первый этаж - одноместные и двухместные
        rooms.add(new Room(101, RoomType.SINGLE, 1, false, false));
        rooms.add(new Room(102, RoomType.SINGLE, 1, false, false));
        rooms.add(new Room(103, RoomType.DOUBLE, 1, false, false));
        rooms.add(new Room(104, RoomType.DOUBLE, 1, false, false));

        // Второй этаж - двухместные с балконами
        rooms.add(new Room(201, RoomType.DOUBLE, 2, true, false));
        rooms.add(new Room(202, RoomType.DOUBLE, 2, true, false));
        rooms.add(new Room(203, RoomType.SUITE, 2, true, false));

        // Третий этаж - люксы с видом на море
        rooms.add(new Room(301, RoomType.SUITE, 3, true, true));
        rooms.add(new Room(302, RoomType.SUITE, 3, true, true));
        rooms.add(new Room(303, RoomType.DELUXE, 3, true, true));
    }

    // Метод добавления гостя
    public Guest addGuest(String name, String email, String phone, String idDocument) {
        Guest guest = new Guest(name, email, phone, idDocument);
        guests.add(guest);
        return guest;
    }

    // Метод поиска гостя по ID
    public Guest findGuestById(int guestId) {
        for (Guest guest : guests) {
            if (guest.getGuestId() == guestId) {
                return guest;
            }
        }
        return null;
    }

    // Метод поиска номера по номеру комнаты
    public Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    // Метод проверки доступности номера
    public boolean checkAvailability(Room room, LocalDate checkIn, LocalDate checkOut) {
        // Проверка базовой доступности номера
        if (!room.isAvailable()) {
            return false;
        }

        // Проверка пересечения с существующими бронированиями
        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == room.getRoomNumber() &&
                    booking.isActive() &&
                    booking.overlapsWith(checkIn, checkOut)) {
                return false;
            }
        }

        return true;
    }

    // Метод получения доступных номеров
    public ArrayList<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        ArrayList<Room> availableRooms = new ArrayList<>();

        for (Room room : rooms) {
            if (checkAvailability(room, checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    // Метод получения доступных номеров по типу
    public ArrayList<Room> getAvailableRoomsByType(RoomType type, LocalDate checkIn, LocalDate checkOut) {
        ArrayList<Room> availableRooms = new ArrayList<>();

        for (Room room : rooms) {
            if (room.getType() == type && checkAvailability(room, checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    // Метод бронирования номера
    public Booking bookRoom(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        // Валидация дат
        if (checkIn.isBefore(LocalDate.now())) {
            System.out.println("Error: Check-in date cannot be in the past");
            return null;
        }

        if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
            System.out.println("Error: Check-out date must be after check-in date");
            return null;
        }

        // Проверка доступности
        if (!checkAvailability(room, checkIn, checkOut)) {
            System.out.println("Error: Room is not available for the selected dates");
            return null;
        }

        // Создание бронирования
        Booking booking = new Booking(guest, room, checkIn, checkOut);
        bookings.add(booking);

        System.out.println("\n✓ Booking created successfully!");
        System.out.println("Booking ID: " + booking.getBookingId());
        System.out.printf("Total Cost: $%.2f%n", booking.getTotalCost());

        return booking;
    }

    // Метод отмены бронирования
    public boolean cancelBooking(int bookingId) {
        Booking booking = findBookingById(bookingId);

        if (booking == null) {
            System.out.println("Error: Booking not found");
            return false;
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            System.out.println("Error: Booking is already cancelled");
            return false;
        }

        if (booking.getStatus() == BookingStatus.CHECKED_OUT) {
            System.out.println("Error: Cannot cancel completed booking");
            return false;
        }

        booking.setStatus(BookingStatus.CANCELLED);
        booking.getRoom().setAvailable(true);

        System.out.println("\n✓ Booking cancelled successfully!");
        System.out.println("Booking ID: " + bookingId);

        // Расчет возврата средств
        long daysUntilCheckIn = ChronoUnit.DAYS.between(LocalDate.now(), booking.getCheckInDate());
        double refund = 0;

        if (daysUntilCheckIn >= 7) {
            refund = booking.getTotalCost(); // Полный возврат
            System.out.println("Full refund: $" + String.format("%.2f", refund));
        } else if (daysUntilCheckIn >= 3) {
            refund = booking.getTotalCost() * 0.50; // 50% возврат
            System.out.println("50% refund: $" + String.format("%.2f", refund));
        } else {
            System.out.println("No refund (less than 3 days before check-in)");
        }

        return true;
    }

    // Метод поиска бронирования по ID
    public Booking findBookingById(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

    // Метод получения бронирований гостя
    public ArrayList<Booking> getGuestBookings(Guest guest) {
        ArrayList<Booking> guestBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getGuest().getGuestId() == guest.getGuestId()) {
                guestBookings.add(booking);
            }
        }

        return guestBookings;
    }

    // Метод заселения
    public boolean checkIn(int bookingId) {
        Booking booking = findBookingById(bookingId);

        if (booking == null) {
            System.out.println("Error: Booking not found");
            return false;
        }

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            System.out.println("Error: Booking status must be CONFIRMED");
            return false;
        }

        if (LocalDate.now().isBefore(booking.getCheckInDate())) {
            System.out.println("Error: Check-in date has not arrived yet");
            return false;
        }

        booking.setStatus(BookingStatus.CHECKED_IN);
        booking.getRoom().setAvailable(false);

        System.out.println("\n✓ Check-in successful!");
        System.out.println("Welcome, " + booking.getGuest().getName() + "!");
        System.out.println("Room: " + booking.getRoom().getRoomNumber());

        return true;
    }

    // Метод выселения
    public boolean checkOut(int bookingId) {
        Booking booking = findBookingById(bookingId);

        if (booking == null) {
            System.out.println("Error: Booking not found");
            return false;
        }

        if (booking.getStatus() != BookingStatus.CHECKED_IN) {
            System.out.println("Error: Guest is not checked in");
            return false;
        }

        booking.setStatus(BookingStatus.CHECKED_OUT);
        booking.getRoom().setAvailable(true);

        System.out.println("\n✓ Check-out successful!");
        System.out.println("Thank you for staying with us, " + booking.getGuest().getName() + "!");
        System.out.printf("Total paid: $%.2f%n", booking.getTotalCost());

        return true;
    }

    // Метод отображения всех номеров
    public void displayAllRooms() {
        System.out.println("\n=== " + hotelName + " - All Rooms ===");
        for (Room room : rooms) {
            room.displayShort();
        }
        System.out.println("\nTotal rooms: " + rooms.size());
    }

    // Метод отображения всех бронирований
    public void displayAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("\nNo bookings found");
            return;
        }

        System.out.println("\n=== All Bookings ===");
        for (Booking booking : bookings) {
            booking.displayShort();
        }
        System.out.println("\nTotal bookings: " + bookings.size());
    }

    // Метод отображения активных бронирований
    public void displayActiveBookings() {
        ArrayList<Booking> activeBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.isActive()) {
                activeBookings.add(booking);
            }
        }

        if (activeBookings.isEmpty()) {
            System.out.println("\nNo active bookings");
            return;
        }

        System.out.println("\n=== Active Bookings ===");
        for (Booking booking : activeBookings) {
            booking.displayShort();
        }
        System.out.println("\nTotal active bookings: " + activeBookings.size());
    }

    // Метод отображения статистики отеля
    public void displayStatistics() {
        int totalRooms = rooms.size();
        int occupiedRooms = 0;
        int totalBookings = bookings.size();
        int activeBookings = 0;
        int cancelledBookings = 0;
        double totalRevenue = 0;

        for (Room room : rooms) {
            if (!room.isAvailable()) {
                occupiedRooms++;
            }
        }

        for (Booking booking : bookings) {
            if (booking.isActive()) {
                activeBookings++;
            }
            if (booking.getStatus() == BookingStatus.CANCELLED) {
                cancelledBookings++;
            }
            if (booking.getStatus() == BookingStatus.CHECKED_OUT) {
                totalRevenue += booking.getTotalCost();
            }
        }

        double occupancyRate = (double) occupiedRooms / totalRooms * 100;

        System.out.println("\n=== " + hotelName + " Statistics ===");
        System.out.println("Total Rooms: " + totalRooms);
        System.out.println("Occupied Rooms: " + occupiedRooms);
        System.out.printf("Occupancy Rate: %.1f%%%n", occupancyRate);
        System.out.println("\nBookings:");
        System.out.println("  Total: " + totalBookings);
        System.out.println("  Active: " + activeBookings);
        System.out.println("  Cancelled: " + cancelledBookings);
        System.out.printf("\nTotal Revenue: $%.2f%n", totalRevenue);
        System.out.println("Total Guests: " + guests.size());
    }
}

// Класс для работы с пользовательским интерфейсом
class HotelUI {
    private Scanner input;
    private Hotel hotel;
    private DateTimeFormatter dateFormatter;

    public HotelUI(String hotelName) {
        this.input = new Scanner(System.in);
        this.hotel = new Hotel(hotelName);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void run() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Hotel Booking System                  ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        while (true) {
            try {
                displayMainMenu();
                int choice = input.nextInt();
                input.nextLine(); // Очистка буфера

                if (choice == 13) {
                    System.out.println("\nThank you for using Hotel Booking System!");
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
        System.out.println("1. View all rooms");
        System.out.println("2. Check room availability");
        System.out.println("3. Make a booking");
        System.out.println("4. Cancel booking");
        System.out.println("5. View booking details");
        System.out.println("6. View all bookings");
        System.out.println("7. View active bookings");
        System.out.println("8. Check-in");
        System.out.println("9. Check-out");
        System.out.println("10. Register new guest");
        System.out.println("11. View guest bookings");
        System.out.println("12. View hotel statistics");
        System.out.println("13. Exit");
        System.out.print("Enter choice (1-13): ");
    }

    // Обработка выбора меню
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                hotel.displayAllRooms();
                break;
            case 2:
                checkAvailability();
                break;
            case 3:
                makeBooking();
                break;
            case 4:
                cancelBooking();
                break;
            case 5:
                viewBookingDetails();
                break;
            case 6:
                hotel.displayAllBookings();
                break;
            case 7:
                hotel.displayActiveBookings();
                break;
            case 8:
                checkIn();
                break;
            case 9:
                checkOut();
                break;
            case 10:
                registerGuest();
                break;
            case 11:
                viewGuestBookings();
                break;
            case 12:
                hotel.displayStatistics();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Проверка доступности
    private void checkAvailability() {
        System.out.print("\nEnter check-in date (yyyy-MM-dd): ");
        String checkInStr = input.nextLine();

        System.out.print("Enter check-out date (yyyy-MM-dd): ");
        String checkOutStr = input.nextLine();

        try {
            LocalDate checkIn = LocalDate.parse(checkInStr, dateFormatter);
            LocalDate checkOut = LocalDate.parse(checkOutStr, dateFormatter);

            ArrayList<Room> availableRooms = hotel.getAvailableRooms(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                System.out.println("\nNo rooms available for these dates");
                return;
            }

            System.out.println("\n=== Available Rooms ===");
            for (Room room : availableRooms) {
                room.displayShort();
            }
            System.out.println("\nAvailable rooms: " + availableRooms.size());

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Use yyyy-MM-dd");
        }
    }

    // Создание бронирования
    private void makeBooking() {
        System.out.print("\nEnter guest ID: ");
        int guestId = input.nextInt();
        input.nextLine();

        Guest guest = hotel.findGuestById(guestId);
        if (guest == null) {
            System.out.println("Error: Guest not found. Please register the guest first.");
            return;
        }

        System.out.print("Enter room number: ");
        int roomNumber = input.nextInt();
        input.nextLine();

        Room room = hotel.findRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Error: Room not found");
            return;
        }

        System.out.print("Enter check-in date (yyyy-MM-dd): ");
        String checkInStr = input.nextLine();

        System.out.print("Enter check-out date (yyyy-MM-dd): ");
        String checkOutStr = input.nextLine();

        try {
            LocalDate checkIn = LocalDate.parse(checkInStr, dateFormatter);
            LocalDate checkOut = LocalDate.parse(checkOutStr, dateFormatter);

            Booking booking = hotel.bookRoom(guest, room, checkIn, checkOut);

            if (booking != null) {
                booking.displayInfo();
            }

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Use yyyy-MM-dd");
        }
    }

    // Отмена бронирования
    private void cancelBooking() {
        System.out.print("\nEnter booking ID to cancel: ");
        int bookingId = input.nextInt();
        input.nextLine();

        hotel.cancelBooking(bookingId);
    }

    // Просмотр деталей бронирования
    private void viewBookingDetails() {
        System.out.print("\nEnter booking ID: ");
        int bookingId = input.nextInt();
        input.nextLine();

        Booking booking = hotel.findBookingById(bookingId);
        if (booking != null) {
            booking.displayInfo();
        } else {
            System.out.println("Error: Booking not found");
        }
    }

    // Заселение
    private void checkIn() {
        System.out.print("\nEnter booking ID for check-in: ");
        int bookingId = input.nextInt();
        input.nextLine();

        hotel.checkIn(bookingId);
    }

    // Выселение
    private void checkOut() {
        System.out.print("\nEnter booking ID for check-out: ");
        int bookingId = input.nextInt();
        input.nextLine();

        hotel.checkOut(bookingId);
    }

    // Регистрация гостя
    private void registerGuest() {
        System.out.print("\nEnter guest name: ");
        String name = input.nextLine();

        System.out.print("Enter email: ");
        String email = input.nextLine();

        System.out.print("Enter phone number: ");
        String phone = input.nextLine();

        System.out.print("Enter ID document: ");
        String idDocument = input.nextLine();

        Guest guest = hotel.addGuest(name, email, phone, idDocument);
        System.out.println("\n✓ Guest registered successfully!");
        System.out.println("Guest ID: " + guest.getGuestId());
    }

    // Просмотр бронирований гостя
    private void viewGuestBookings() {
        System.out.print("\nEnter guest ID: ");
        int guestId = input.nextInt();
        input.nextLine();

        Guest guest = hotel.findGuestById(guestId);
        if (guest == null) {
            System.out.println("Error: Guest not found");
            return;
        }

        ArrayList<Booking> guestBookings = hotel.getGuestBookings(guest);

        if (guestBookings.isEmpty()) {
            System.out.println("\nNo bookings found for this guest");
            return;
        }

        System.out.println("\n=== Bookings for " + guest.getName() + " ===");
        for (Booking booking : guestBookings) {
            booking.displayShort();
        }
        System.out.println("\nTotal bookings: " + guestBookings.size());
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        HotelUI ui = new HotelUI("Grand Hotel");
        ui.run();
    }
}