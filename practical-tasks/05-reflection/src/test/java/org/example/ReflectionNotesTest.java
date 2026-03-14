package org.example;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тестирование Reflection API</h1>
 * 
 * <p>Проверка возможности доступа к скрытым элементам классов
 * через механизмы рефлексии.</p>
 */
public class ReflectionNotesTest {

    @Test
    public void testPrivateMethodAccess() throws Exception {
        SecretCrate crate = new SecretCrate();
        Method method = SecretCrate.class.getDeclaredMethod("stayHidden");
        method.setAccessible(true);
        
        // Вызов метода не должен вызвать исключений
        assertDoesNotThrow(() -> method.invoke(crate));
    }

    @Test
    public void testAnnotationPresence() {
        Method[] methods = SecretCrate.class.getDeclaredMethods();
        boolean found = false;
        for (Method m : methods) {
            if (m.isAnnotationPresent(MyTest.class)) {
                found = true;
                MyTest annotation = m.getAnnotation(MyTest.class);
                assertNotNull(annotation.info());
            }
        }
        assertTrue(found, "Аннотация @MyTest должна присутствовать хотя бы на одном методе");
    }
}
