package org.example;

import org.junit.jupiter.api.Test;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тестирование JDBC соединения</h1>
 * 
 * <p>Проверка базовых операций с БД (CRUD) через JDBC драйвер H2.</p>
 */
public class JDBCNotesTest {

    private static final String URL = "jdbc:h2:mem:test_jdbc;DB_CLOSE_DELAY=-1";

    @Test
    public void testConnection() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, "sa", "")) {
            assertNotNull(conn);
            assertFalse(conn.isClosed());
        }
    }

    @Test
    public void testTableCreation() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, "sa", "");
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE test_table (id INT PRIMARY KEY)");
            ResultSet rs = conn.getMetaData().getTables(null, null, "TEST_TABLE", null);
            
            assertTrue(rs.next(), "Таблица должна быть успешно создана");
        }
    }
}
