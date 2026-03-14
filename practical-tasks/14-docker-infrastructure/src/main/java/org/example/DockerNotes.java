package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>Инфраструктура: Docker и Контейнеризация</h1>
 * 
 * <p>Docker позволяет упаковать приложение и все его зависимости в единый образ (Image),
 * который гарантированно одинаково запустится на любой машине.</p>
 *
 * <h2>1. Dockerfile</h2>
 * <p>Текстовый файл с инструкциями по сборке образа. Обычно включает:
 * <ul>
 *     <li><code>FROM</code>: базовый образ (например, OpenJDK).</li>
 *     <li><code>COPY</code>: перенос JAR-файла в контейнер.</li>
 *     <li><code>ENTRYPOINT</code>: команда для запуска приложения.</li>
 * </ul>
 * </p>
 *
 * <h2>2. Docker Compose</h2>
 * <p>Инструмент для запуска многоконтейнерных приложений. С его помощью можно 
 * одной командой поднять и ваше Java-приложение, и базу данных (например, PostgreSQL).</p>
 *
 * <h2>3. Переменные окружения</h2>
 * <p>В контейнерах настройки (хост базы данных, пароли) обычно передаются 
 * через ENV-переменные, что делает образ универсальным.</p>
 *
 * @author Student
 */
@SpringBootApplication
@RestController
public class DockerNotes {

    @GetMapping("/")
    public String home() {
        return "Приложение запущено внутри Docker-контейнера!";
    }

    public static void main(String[] args) {
        System.out.println("=== Запуск модуля: Docker Infrastructure ===");
        SpringApplication.run(DockerNotes.class, args);
    }
}
