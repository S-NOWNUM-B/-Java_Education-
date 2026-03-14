package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тестирование уровня данных (DAO/Repository)</h1>
 * 
 * <p>Аннотация <code>@DataJpaTest</code> настраивает тестовое окружение
 * с базой данных в памяти (H2) для проверки работы репозиториев.</p>
 */
@DataJpaTest
public class JpaNotesTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveAndRetrieveStudent() {
        // Создаем и сохраняем
        Student ivan = new Student("Ivan", 20);
        studentRepository.save(ivan);

        // Ищем в БД
        Student found = studentRepository.findByName("Ivan");
        
        assertNotNull(found, "Студент должен быть найден в БД");
        assertEquals(20, found.getAge());
    }

    @Test
    public void testDeleteStudent() {
        Student petr = new Student("Petr", 22);
        petr = studentRepository.save(petr);
        
        studentRepository.delete(petr);
        
        Student found = studentRepository.findByName("Petr");
        assertNull(found, "После удаления студент не должен находиться");
    }
}
