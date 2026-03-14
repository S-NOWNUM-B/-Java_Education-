package org.example;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h1>Spring Data JPA и Hibernate: Мир ORM</h1>
 * 
 * <p>ORM (Object-Relational Mapping) позволяет работать с Базой Данных 
 * через объекты Java, не написав ни строчки SQL вручную.</p>
 *
 * <h2>1. Сущность (Entity)</h2>
 * <p>Класс, помеченный аннотацией <code>@Entity</code>, мапится (связывается) 
 * с таблицей в БД. Поля класса становятся колонками таблицы.</p>
 *
 * <h2>2. Репозиторий (Repository)</h2>
 * <p>Интерфейс Spring Data, который предоставляет готовые методы для работы с данными: 
 * <code>save()</code>, <code>findAll()</code>, <code>delete()</code> и др. 
 * Spring сам генерирует реализацию этого интерфейса на лету.</p>
 *
 * <h2>3. Hibernate</h2>
 * <p>Это самая популярная реализация спецификации JPA. Именно Hibernate "под капотом" 
 * превращает ваши вызовы методов в реальные SQL-запросы.</p>
 *
 * @author Student
 */

@SpringBootApplication
public class JpaNotes {
    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: Spring Data JPA ===");
        SpringApplication.run(JpaNotes.class, args);
    }
}

/**
 * Пример сущности "Студент".
 */
@Entity
@Table(name = "students")
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int age;

    // Hibernate требует пустой конструктор
    public Student() {}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

/**
 * Репозиторий для управления студентами.
 */
@Repository
interface StudentRepository extends JpaRepository<Student, Long> {
    // Spring сам поймет, что нужно искать по полю name
    Student findByName(String name);
}
