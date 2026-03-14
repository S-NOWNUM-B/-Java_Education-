package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>Spring Security: Защита приложений</h1>
 * 
 * <p>Spring Security — это мощный настраиваемый фреймворк для обеспечения 
 * аутентификации и авторизации в Java-приложениях.</p>
 *
 * <h2>1. Аутентификация vs Авторизация</h2>
 * <ul>
 *     <li><b>Аутентификация (Authentication)</b>: "Кто вы такой?" (логин/пароль, токен).</li>
 *     <li><b>Авторизация (Authorization)</b>: "Что вам разрешено делать?" (роли ADMIN, USER).</li>
 * </ul>
 *
 * <h2>2. Filter Chain (Цепочка фильтров)</h2>
 * <p>Безопасность в Spring реализована через цепочку фильтров сервлетов, 
 * которые перехватывают каждый запрос и проверяют права доступа.</p>
 *
 * <h2>3. JWT (JSON Web Token)</h2>
 * <p>Современный стандарт для передачи данных о сессии в виде компактного 
 * зашифрованного JSON. Позволяет делать приложения "без состояния" (stateless).</p>
 *
 * @author Student
 */
@SpringBootApplication
public class SecurityNotes {
    public static void main(String[] args) {
        System.out.println("=== Запуск модуля: Spring Security ===");
        SpringApplication.run(SecurityNotes.class, args);
    }
}

/**
 * REST-контроллер с разделением доступа.
 */
@RestController
class SecureController {

    @GetMapping("/public")
    public String publicAccess() {
        return "Это открытая страница!";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Привет, Администратор!";
    }
}

/**
 * Настройка безопасности (WebSecurityConfig).
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/public").permitAll() // Разрешено всем
                .requestMatchers("/admin").hasRole("ADMIN") // Только админам
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form.permitAll())
            .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
