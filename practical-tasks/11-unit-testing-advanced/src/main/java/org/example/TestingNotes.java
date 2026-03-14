package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

/**
 * <h1>Продвинутое тестирование: Mockito и изоляция</h1>
 * 
 * <p>В современном Java-программировании юнит-тестирование неразрывно 
 * связано с понятием "Мокирования" (Mocking).</p>
 *
 * <h2>1. Зачем нужны моки?</h2>
 * <p>Когда мы тестируем сервис, нам не нужна реальная БД или внешний API.
 * Мы создаем "пустышку" (Mock) зависимости, которая ведет себя так, как мы прикажем.
 * Это позволяет тестировать логику <b>в изоляции.</b></p>
 *
 * <h2>2. Mockito</h2>
 * <p>Самая популярная библиотека для создания моков. Она позволяет:
 * <ul>
 *     <li><code>when(...).thenReturn(...)</code>: имитировать вовзрат значения.</li>
 *     <li><code>verify(...)</code>: проверять, вызывался ли метод.</li>
 *     <li><code>any()</code>: использовать гибкие сопоставители аргументов.</li>
 * </ul>
 * </p>
 *
 * @author Student
 */

@SpringBootApplication
public class TestingNotes {
    public static void main(String[] args) {
        System.out.println("=== Запуск практического модуля: Advanced Testing ===");
        SpringApplication.run(TestingNotes.class, args);
    }
}

/**
 * Зависимость, которую мы будем подменять.
 */
interface NotificationService {
    boolean send(String message);
}

/**
 * Сервис, логику которого мы будем тестировать.
 */
@Service
class UserNotificationService {
    private final NotificationService notificationService;

    public UserNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Пример бизнес-логики: отправка уведомления только если сообщение не пустое.
     */
    public String notifyUser(String msg) {
        if (msg == null || msg.trim().isEmpty()) {
            return "FAILURE";
        }
        
        boolean sent = notificationService.send(msg);
        return sent ? "SUCCESS" : "RETRY";
    }
}
