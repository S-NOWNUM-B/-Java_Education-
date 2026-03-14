package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * // ===== Spring Boot: Мой первый веб-сервис =====
 * 
 * В этом файле я разбираюсь, как за 5 минут запустить сервер
 * и организовать REST API.
 */

@SpringBootApplication // Магия: включает автоконфигурацию, поиск бинов и запуск Tomcat
public class SpringAppNotes {
    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: Spring Boot ===");
        SpringApplication.run(SpringAppNotes.class, args);
    }
}

@RestController // Говорит Spring, что этот класс умеет обрабатывать HTTP-запросы
@RequestMapping("/api") // Базовый путь для всех методов этого класса
class HelloController {

    // Эту заметку можно увидеть, открыв http://localhost:8080/api/hello
    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name", defaultValue = "Student") String name) {
        return "Привет, " + name + "! Это твое первое Spring Boot приложение.";
    }

    // Пример работы с JSON (Spring сам превратит объект в JSON)
    @GetMapping("/status")
    public AppStatus getStatus() {
        return new AppStatus("Running", 200);
    }
}

// Простая "заметка" (POJO) для демонстрации JSON
class AppStatus {
    private String status;
    private int code;

    public AppStatus() {
    }

    public AppStatus(String status, int code) {
        this.status = status;
        this.code = code;
    }

    // Геттеры обязательны для работы JSON-сериализатора!
    public String getStatus() { return status; }
    public int getCode() { return code; }

    public void setStatus(String status) { this.status = status; }
    public void setCode(int code) { this.code = code; }
}
