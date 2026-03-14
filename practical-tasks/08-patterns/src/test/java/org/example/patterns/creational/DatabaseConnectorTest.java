package org.example.patterns.creational;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тест паттерна Singleton</h1>
 */
public class DatabaseConnectorTest {

    @Test
    public void testSingletonInstance() {
        DatabaseConnector instance1 = DatabaseConnector.getInstance();
        DatabaseConnector instance2 = DatabaseConnector.getInstance();

        assertNotNull(instance1);
        assertSame(instance1, instance2, "Singleton должен возвращать один и тот же объект");
    }
}
