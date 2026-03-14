package org.example;

import java.lang.annotation.*;
import java.lang.reflect.*;

/**
 * // ===== Reflection API и Аннотации =====
 * 
 * В этом файле я разбираюсь, как заглянуть "под капот" Java-классов.
 * Рефлексия позволяет видеть приватные поля и вызывать методы,
 * о которых мы не знали на этапе компиляции.
 */

// 1. Создаем свою аннотацию
@Retention(RetentionPolicy.RUNTIME) // Чтобы была видна во время работы программы
@Target(ElementType.METHOD)         // Вешаем только на методы
@interface MyTest {
    String info() default "Проверка";
}

class SecretCrate {
    @SuppressWarnings("unused")
    private String secretContent = "Золотые горы";

    @MyTest(info = "Очень важный метод")
    public void openCrate() {
        System.out.println("Сундук открыт!");
    }

    @SuppressWarnings("unused")
    private void stayHidden() {
        System.out.println("Меня никто не должен был вызвать...");
    }
}

public class ReflectionNotes {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Запуск практического модуля: Рефлексия ===");

        SecretCrate crate = new SecretCrate();
        Class<?> clazz = crate.getClass();

        System.out.println("Имя класса: " + clazz.getName());

        // --- 2. Достаем приватное поле ---
        Field field = clazz.getDeclaredField("secretContent");
        field.setAccessible(true); // "Взламываем" инкапсуляцию
        System.out.println("Секретное содержимое: " + field.get(crate));

        // --- 3. Ищем методы с нашими аннотациями ---
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyTest.class)) {
                MyTest annot = method.getAnnotation(MyTest.class);
                System.out.println("Найден метод с аннотацией: " + method.getName());
                System.out.println("Информация из аннотации: " + annot.info());
                method.invoke(crate); // Вызываем метод
            }
        }

        // --- 4. Вызов приватного метода ---
        Method privateMethod = clazz.getDeclaredMethod("stayHidden");
        privateMethod.setAccessible(true);
        privateMethod.invoke(crate);
        
        System.out.println("\n[ЗАМЕТКА] Рефлексия - это мощно, но она медленная и ломает защиту. ");
        System.out.println("Используй её только когда пишешь фреймворки или плагины!");
    }
}
