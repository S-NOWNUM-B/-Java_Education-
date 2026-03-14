package easyProjects.shop;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

// Перечисление категорий товаров
enum Category {
    ELECTRONICS,
    CLOTHING,
    FOOD,
    BOOKS,
    SPORTS
}

// Класс товара
class Product {
    private static int productCounter = 1;
    private int id;
    private String name;
    private double price;
    private int quantity;
    private Category category;
    private String description;

    // Конструктор
    public Product(String name, double price, int quantity, Category category, String description) {
        this.id = productCounter++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    // Сеттеры
    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Метод уменьшения количества товара
    public boolean decreaseQuantity(int amount) {
        if (amount > quantity) {
            return false;
        }
        quantity -= amount;
        return true;
    }

    // Метод увеличения количества товара
    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    // Метод проверки доступности
    public boolean isAvailable(int requestedAmount) {
        return quantity >= requestedAmount;
    }

    // Метод отображения информации о товаре
    public void display() {
        System.out.println("\n=== Product Details ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.printf("Price: $%.2f%n", price);
        System.out.println("Available: " + quantity + " units");
        System.out.println("Category: " + category);
        System.out.println("Description: " + description);
        System.out.println("---");
    }

    // Метод краткого отображения товара
    public void displayShort() {
        String availability = quantity > 0 ? "✓" : "✗";
        System.out.printf("[%s] ID: %d | %s | $%.2f | Stock: %d%n",
                availability, id, name, price, quantity);
    }
}

// Класс элемента корзины
class CartItem {
    private Product product;
    private int quantity;

    // Конструктор
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Геттеры
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Метод изменения количества
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Метод увеличения количества
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    // Метод уменьшения количества
    public boolean decreaseQuantity(int amount) {
        if (amount >= quantity) {
            return false;
        }
        quantity -= amount;
        return true;
    }

    // Метод расчета стоимости позиции
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    // Метод отображения элемента корзины
    public void display() {
        System.out.printf("  %s (x%d) - $%.2f each = $%.2f%n",
                product.getName(), quantity,
                product.getPrice(), getSubtotal());
    }
}

// Класс корзины покупок
class ShoppingCart {
    private ArrayList<CartItem> items;
    private static final double TAX_RATE = 0.10; // 10% налог

    // Конструктор
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // Метод добавления товара в корзину
    public boolean addToCart(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be positive");
            return false;
        }

        if (!product.isAvailable(quantity)) {
            System.out.println("Error: Not enough stock available");
            System.out.println("Available: " + product.getQuantity() + " units");
            return false;
        }

        // Проверка, есть ли уже этот товар в корзине
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                int newQuantity = item.getQuantity() + quantity;
                if (!product.isAvailable(newQuantity)) {
                    System.out.println("Error: Not enough stock for this amount");
                    return false;
                }
                item.increaseQuantity(quantity);
                System.out.println("\nUpdated quantity in cart!");
                System.out.println(product.getName() + " - New quantity: " + item.getQuantity());
                return true;
            }
        }

        // Добавляем новый товар в корзину
        items.add(new CartItem(product, quantity));
        System.out.println("\nProduct added to cart!");
        System.out.println(product.getName() + " (x" + quantity + ")");
        return true;
    }

    // Метод удаления товара из корзины
    public boolean removeFromCart(int productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId() == productId) {
                CartItem removed = items.remove(i);
                System.out.println("\nRemoved from cart: " + removed.getProduct().getName());
                return true;
            }
        }
        System.out.println("\nError: Product not found in cart");
        return false;
    }

    // Метод изменения количества товара в корзине
    public boolean updateQuantity(int productId, int newQuantity) {
        if (newQuantity <= 0) {
            return removeFromCart(productId);
        }

        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                if (!item.getProduct().isAvailable(newQuantity)) {
                    System.out.println("Error: Not enough stock available");
                    return false;
                }
                item.setQuantity(newQuantity);
                System.out.println("\nQuantity updated!");
                return true;
            }
        }

        System.out.println("Error: Product not found in cart");
        return false;
    }

    // Метод очистки корзины
    public void clear() {
        items.clear();
        System.out.println("\nCart cleared!");
    }

    // Метод проверки пустоты корзины
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Метод получения количества товаров
    public int getTotalItems() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getQuantity();
        }
        return total;
    }

    // Метод расчета промежуточной суммы
    public double calculateSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    // Метод расчета налога
    public double calculateTax() {
        return calculateSubtotal() * TAX_RATE;
    }

    // Метод расчета итоговой суммы
    public double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }

    // Метод отображения содержимого корзины
    public void display() {
        if (items.isEmpty()) {
            System.out.println("\nYour cart is empty");
            return;
        }

        System.out.println("\n=== Shopping Cart ===");
        for (CartItem item : items) {
            item.display();
        }

        System.out.println("\n--- Summary ---");
        System.out.printf("Subtotal: $%.2f%n", calculateSubtotal());
        System.out.printf("Tax (%.0f%%): $%.2f%n", TAX_RATE * 100, calculateTax());
        System.out.printf("Total: $%.2f%n", calculateTotal());
        System.out.println("\nTotal items: " + getTotalItems());
    }

    // Метод получения списка товаров
    public ArrayList<CartItem> getItems() {
        return items;
    }
}

// Класс магазина
class Shop {
    private String shopName;
    private ArrayList<Product> inventory;
    private ArrayList<String> orderHistory;

    // Конструктор
    public Shop(String shopName) {
        this.shopName = shopName;
        this.inventory = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        initializeInventory();
    }

    // Метод инициализации начального товарного запаса
    private void initializeInventory() {
        // Электроника
        inventory.add(new Product("Laptop", 999.99, 10, Category.ELECTRONICS,
                "High-performance laptop with 16GB RAM"));
        inventory.add(new Product("Smartphone", 599.99, 15, Category.ELECTRONICS,
                "Latest model with 128GB storage"));
        inventory.add(new Product("Headphones", 79.99, 25, Category.ELECTRONICS,
                "Wireless noise-cancelling headphones"));

        // Одежда
        inventory.add(new Product("T-Shirt", 19.99, 50, Category.CLOTHING,
                "Cotton casual t-shirt"));
        inventory.add(new Product("Jeans", 49.99, 30, Category.CLOTHING,
                "Classic blue denim jeans"));

        // Еда
        inventory.add(new Product("Coffee Beans", 12.99, 100, Category.FOOD,
                "Premium arabica coffee beans 1kg"));
        inventory.add(new Product("Chocolate", 3.99, 200, Category.FOOD,
                "Swiss dark chocolate bar"));

        // Книги
        inventory.add(new Product("Java Programming", 45.99, 20, Category.BOOKS,
                "Complete guide to Java programming"));
        inventory.add(new Product("Design Patterns", 39.99, 15, Category.BOOKS,
                "Software design patterns book"));

        // Спорт
        inventory.add(new Product("Yoga Mat", 29.99, 40, Category.SPORTS,
                "Non-slip exercise yoga mat"));
    }

    // Метод добавления товара в магазин
    public void addProduct(Product product) {
        inventory.add(product);
        System.out.println("\nProduct added to shop inventory!");
        System.out.println("Product ID: " + product.getId());
    }

    // Метод поиска товара по ID
    public Product findProductById(int productId) {
        for (Product product : inventory) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    // Метод поиска товаров по названию
    public ArrayList<Product> searchByName(String name) {
        ArrayList<Product> results = new ArrayList<>();
        String searchTerm = name.toLowerCase();

        for (Product product : inventory) {
            if (product.getName().toLowerCase().contains(searchTerm)) {
                results.add(product);
            }
        }

        return results;
    }

    // Метод поиска товаров по категории
    public ArrayList<Product> searchByCategory(Category category) {
        ArrayList<Product> results = new ArrayList<>();

        for (Product product : inventory) {
            if (product.getCategory() == category) {
                results.add(product);
            }
        }

        return results;
    }

    // Метод поиска товаров в ценовом диапазоне
    public ArrayList<Product> searchByPriceRange(double minPrice, double maxPrice) {
        ArrayList<Product> results = new ArrayList<>();

        for (Product product : inventory) {
            double price = product.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                results.add(product);
            }
        }

        return results;
    }

    // Метод отображения всех товаров
    public void displayAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("\nNo products available");
            return;
        }

        System.out.println("\n=== " + shopName + " - All Products ===");
        for (Product product : inventory) {
            product.displayShort();
        }
        System.out.println("\nTotal products: " + inventory.size());
    }

    // Метод отображения товаров по категориям
    public void displayByCategory() {
        HashMap<Category, ArrayList<Product>> categoryMap = new HashMap<>();

        for (Product product : inventory) {
            Category cat = product.getCategory();
            if (!categoryMap.containsKey(cat)) {
                categoryMap.put(cat, new ArrayList<>());
            }
            categoryMap.get(cat).add(product);
        }

        System.out.println("\n=== Products by Category ===");
        for (Category category : Category.values()) {
            ArrayList<Product> products = categoryMap.get(category);
            if (products != null && !products.isEmpty()) {
                System.out.println("\n--- " + category + " ---");
                for (Product product : products) {
                    product.displayShort();
                }
            }
        }
    }

    // Метод оформления заказа
    public boolean checkout(ShoppingCart cart) {
        if (cart.isEmpty()) {
            System.out.println("\nError: Cart is empty");
            return false;
        }

        // Проверка доступности всех товаров
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (!product.isAvailable(item.getQuantity())) {
                System.out.println("\nError: " + product.getName() + " is out of stock");
                return false;
            }
        }

        // Уменьшаем количество товаров на складе
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.decreaseQuantity(item.getQuantity());
        }

        // Сохраняем заказ в историю
        String order = "Order - Total: $" + String.format("%.2f", cart.calculateTotal()) +
                " - Items: " + cart.getTotalItems();
        orderHistory.add(order);

        // Отображаем чек
        displayReceipt(cart);

        return true;
    }

    // Метод отображения чека
    private void displayReceipt(ShoppingCart cart) {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║      " + shopName + " Receipt      ║");
        System.out.println("╚════════════════════════════════╝");

        cart.display();

        System.out.println("\n✓ Order completed successfully!");
        System.out.println("Thank you for your purchase!");
    }

    // Метод отображения истории заказов
    public void displayOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("\nNo orders yet");
            return;
        }

        System.out.println("\n=== Order History ===");
        for (int i = 0; i < orderHistory.size(); i++) {
            System.out.println((i + 1) + ". " + orderHistory.get(i));
        }
        System.out.println("\nTotal orders: " + orderHistory.size());
    }

    // Метод получения статистики магазина
    public void displayStatistics() {
        int totalProducts = inventory.size();
        int availableProducts = 0;
        int totalStock = 0;
        double totalValue = 0;

        for (Product product : inventory) {
            if (product.getQuantity() > 0) {
                availableProducts++;
            }
            totalStock += product.getQuantity();
            totalValue += product.getPrice() * product.getQuantity();
        }

        System.out.println("\n=== " + shopName + " Statistics ===");
        System.out.println("Total products: " + totalProducts);
        System.out.println("Available products: " + availableProducts);
        System.out.println("Total stock units: " + totalStock);
        System.out.printf("Total inventory value: $%.2f%n", totalValue);
        System.out.println("Total orders: " + orderHistory.size());
    }
}

// Класс для работы с пользовательским интерфейсом
class ShopUI {
    private Scanner input;
    private Shop shop;
    private ShoppingCart cart;

    public ShopUI(String shopName) {
        this.input = new Scanner(System.in);
        this.shop = new Shop(shopName);
        this.cart = new ShoppingCart();
    }

    public void run() {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║  Welcome to Online Shop!       ║");
        System.out.println("╚════════════════════════════════╝\n");

        while (true) {
            try {
                displayMainMenu();
                int choice = input.nextInt();
                input.nextLine(); // Очистка буфера

                if (choice == 11) {
                    System.out.println("\nThank you for visiting!");
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
        System.out.println("1. View all products");
        System.out.println("2. View products by category");
        System.out.println("3. Search product by name");
        System.out.println("4. Search by price range");
        System.out.println("5. View product details");
        System.out.println("6. Add to cart");
        System.out.println("7. View cart");
        System.out.println("8. Update cart");
        System.out.println("9. Remove from cart");
        System.out.println("10. Checkout");
        System.out.println("11. Exit");
        System.out.print("Enter choice (1-11): ");
    }

    // Обработка выбора меню
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                shop.displayAllProducts();
                break;
            case 2:
                viewByCategory();
                break;
            case 3:
                searchByName();
                break;
            case 4:
                searchByPriceRange();
                break;
            case 5:
                viewProductDetails();
                break;
            case 6:
                addToCart();
                break;
            case 7:
                cart.display();
                break;
            case 8:
                updateCart();
                break;
            case 9:
                removeFromCart();
                break;
            case 10:
                checkout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Просмотр по категориям
    private void viewByCategory() {
        shop.displayByCategory();
    }

    // Поиск по названию
    private void searchByName() {
        System.out.print("\nEnter product name to search: ");
        String name = input.nextLine();

        ArrayList<Product> results = shop.searchByName(name);
        displaySearchResults(results);
    }

    // Поиск по ценовому диапазону
    private void searchByPriceRange() {
        System.out.print("\nEnter minimum price: $");
        double minPrice = input.nextDouble();

        System.out.print("Enter maximum price: $");
        double maxPrice = input.nextDouble();
        input.nextLine(); // Очистка буфера

        ArrayList<Product> results = shop.searchByPriceRange(minPrice, maxPrice);
        displaySearchResults(results);
    }

    // Отображение результатов поиска
    private void displaySearchResults(ArrayList<Product> results) {
        if (results.isEmpty()) {
            System.out.println("\nNo products found");
            return;
        }

        System.out.println("\n=== Search Results ===");
        for (Product product : results) {
            product.displayShort();
        }
        System.out.println("\nFound: " + results.size() + " product(s)");
    }

    // Просмотр деталей товара
    private void viewProductDetails() {
        System.out.print("\nEnter product ID: ");
        int productId = input.nextInt();
        input.nextLine(); // Очистка буфера

        Product product = shop.findProductById(productId);
        if (product != null) {
            product.display();
        } else {
            System.out.println("Error: Product not found");
        }
    }

    // Добавление в корзину
    private void addToCart() {
        System.out.print("\nEnter product ID: ");
        int productId = input.nextInt();

        System.out.print("Enter quantity: ");
        int quantity = input.nextInt();
        input.nextLine(); // Очистка буфера

        Product product = shop.findProductById(productId);
        if (product != null) {
            cart.addToCart(product, quantity);
        } else {
            System.out.println("Error: Product not found");
        }
    }

    // Обновление корзины
    private void updateCart() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty");
            return;
        }

        cart.display();

        System.out.print("\nEnter product ID to update: ");
        int productId = input.nextInt();

        System.out.print("Enter new quantity (0 to remove): ");
        int quantity = input.nextInt();
        input.nextLine(); // Очистка буфера

        cart.updateQuantity(productId, quantity);
    }

    // Удаление из корзины
    private void removeFromCart() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty");
            return;
        }

        cart.display();

        System.out.print("\nEnter product ID to remove: ");
        int productId = input.nextInt();
        input.nextLine(); // Очистка буфера

        cart.removeFromCart(productId);
    }

    // Оформление заказа
    private void checkout() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty");
            return;
        }

        cart.display();

        System.out.print("\nProceed with checkout? (y/n): ");
        String answer = input.nextLine().toLowerCase();

        if (answer.equals("y") || answer.equals("yes")) {
            if (shop.checkout(cart)) {
                cart.clear();
            }
        } else {
            System.out.println("\nCheckout cancelled");
        }
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        ShopUI ui = new ShopUI("TechShop");
        ui.run();
    }
}