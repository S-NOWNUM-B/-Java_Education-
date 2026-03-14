package org.example.patterns.creational;

/**
 * <h1>Паттерн Singleton (Одиночка)</h1>
 * 
 * <p>Порождающий паттерн проектирования, который гарантирует, что у класса
 * есть только один экземпляр, и предоставляет к нему глобальную точку доступа.</p>
 * 
 * <h2>Зачем это нужно?</h2>
 * <ul>
 *     <li>Экономия ресурсов (например, один пул соединений с БД).</li>
 *     <li>Глобальное состояние (логирование, настройки конфигурации).</li>
 * </ul>
 */
public class DatabaseConnector {
    private static volatile DatabaseConnector instance;
    private String connectionString;

    // Приватный конструктор - никто снаружи не сможет сделать new DatabaseConnector()
    private DatabaseConnector() {
        this.connectionString = "jdbc:h2:mem:patterns_db";
    }

    /**
     * Потокобезопасная реализация Singleton (Double-Checked Locking).
     */
    public static DatabaseConnector getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnector.class) {
                if (instance == null) {
                    instance = new DatabaseConnector();
                }
            }
        }
        return instance;
    }

    public void connect() {
        System.out.println("Подключение по адресу: " + connectionString);
    }
}
