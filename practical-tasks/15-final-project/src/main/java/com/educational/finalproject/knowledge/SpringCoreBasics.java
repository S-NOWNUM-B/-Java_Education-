package com.educational.finalproject.knowledge;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * # Основы Spring
 *
 * ### IoC (Inversion of Control) — Инверсия управления
 * Принцип, при котором управление жизненным циклом объектов передается фреймворку (Spring-контейнеру).
 *
 * ### DI (Dependency Injection) — Внедрение зависимостей
 * Способ реализации IoC. Мы не создаем зависимости через `new`, а просим их у Spring.
 * *   **Через конструктор** (рекомендуется).
 * *   **Через сеттер**.
 * *   **Через поле** (аннотация `@Autowired`).
 *
 * ### Bean (Бин)
 * Объект, которым управляет Spring-контейнер. По умолчанию создается в одном экземпляре (**Singleton**).
 *
 * ### Полезные аннотации
 * *   `@Component`: Маркер, что класс является бином.
 * *   `@Service`, `@Repository`, `@Controller`: Специализированные версии `@Component`.
 * *   `@Configuration`: Обозначает класс с настройками.
 * *   `@Bean`: Создает бин внутри `@Configuration` класса.
 */
@Component
public class SpringCoreBasics {
    private String containerStatus;

    public SpringCoreBasics() {
        this.containerStatus = "INITIALIZING";
    }

    @PostConstruct
    public void init() {
        this.containerStatus = "READY";
        System.out.println("Spring Bean 'SpringCoreBasics' готов к работе.");
    }

    public String getContainerStatus() {
        return containerStatus;
    }

    public void setContainerStatus(String containerStatus) {
        this.containerStatus = containerStatus;
    }

    public void displaySpringConcept() {
        System.out.println("Spring Core обеспечивает связывание компонентов и управление их жизненным циклом.");
    }
}
