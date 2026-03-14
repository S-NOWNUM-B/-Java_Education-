package org.example;

import java.sql.*;

/**
 * // ===== JDBC: Работа с базами данных (через H2) =====
 * 
 * В этом файле я учусь подключаться к БД, создавать таблицы 
 * и выполнять SQL-запросы прямо из Java-кода.
 */
public class JDBCNotes {

    // URL для H2 в памяти: данные пропадут после закрытия программы, зато быстро и удобно.
    private static final String DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: JDBC ===");

        // try-with-resources: Connection закроется сам (!)
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            
            System.out.println("Подключено к БД!");

            // 1. Создание таблицы
            String sqlCreate = "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255))";
            stmt.execute(sqlCreate);
            System.out.println("Таблица создана.");

            // 2. Вставка данных (через PreparedStatement - защита от SQL Injection!)
            String sqlInsert = "INSERT INTO users (id, name) VALUES (?, ?)";
            try (PreparedStatement pStmt = conn.prepareStatement(sqlInsert)) {
                pStmt.setInt(1, 1);
                pStmt.setString(2, "Ivan");
                pStmt.executeUpdate();
                
                pStmt.setInt(1, 2);
                pStmt.setString(2, "Petr");
                pStmt.executeUpdate();
            }
            System.out.println("Данные вставлены.");

            // 3. Чтение данных
            String sqlSelect = "SELECT * FROM users";
            try (ResultSet rs = stmt.executeQuery(sqlSelect)) {
                while (rs.next()) {
                    System.out.println("User: ID=" + rs.getInt("id") + ", Name=" + rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n[ЗАМЕТКА] Напрямую JDBC сейчас используют редко (много кода). ");
        System.out.println("Обычно берут Spring Data JPA или Hibernate, но основы знать надо!");
    }
}
