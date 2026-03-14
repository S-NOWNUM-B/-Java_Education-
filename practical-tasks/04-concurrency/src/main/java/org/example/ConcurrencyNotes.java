package org.example;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>Многопоточность в Java (Concurrency)</h1>
 * 
 * <p>Многопоточность позволяет выполнять несколько задач одновременно,
 * эффективно используя ядра процессора. Однако это вносит сложности
 * в работу с общими данными.</p>
 *
 * <h2>1. Потоки (Threads)</h2>
 * В Java поток можно создать через наследование от <code>Thread</code>
 * или реализацию интерфейса <code>Runnable</code>. Современный подход —
 * использование <code>ExecutorService</code>.
 *
 * <h2>2. Состояние гонки (Race Condition)</h2>
 * Ситуация, когда несколько потоков одновременно модифицируют одну переменную,
 * что приводит к непредсказуемым результатам. Классический пример —
 * инкремент <code>count++</code> (он не атомарен).
 *
 * <h2>3. Атомарные переменные (Atomic)</h2>
 * Классы из пакета <code>java.util.concurrent.atomic</code> (например, <code>AtomicInteger</code>)
 * используют низкоуровневые инструкции процессора (CAS) для обеспечения
 * потокобезопасности без использования тяжелых блокировок.
 *
 * <h2>4. Синхронизация</h2>
 * Использование ключевого слова <code>synchronized</code> или объектов
 * <code>Lock</code> для организации исключительного доступа к ресурсу.
 *
 * @author Student
 */
public class ConcurrencyNotes {

    private static int unsafeCounter = 0;
    private static AtomicInteger safeCounter = new AtomicInteger(0);

    /**
     * Демонстрация разницы между небезопасным инкрементом и атомарным.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Запуск практического модуля: Многопоточность ===");

        // Пул из 10 потоков для выполнения задач
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                unsafeCounter++; // ОПАСНО: несколько потоков могут прочитать одно значение
                safeCounter.incrementAndGet(); // БЕЗОПАСНО: выполняется как одна неделимая операция
            }
        };

        // Запускаем 10 задач в пуле
        for (int i = 0; i < 10; i++) {
            executor.submit(task);
        }

        // Завершаем работу пула и ждем выполнения всех задач
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Общий счетчик (unsafe): " + unsafeCounter); 
        System.out.println("Общий счетчик (Atomic): " + safeCounter.get()); 

        System.out.println("\n[INFO] Всегда используйте атомики или синхронизацию для общих ресурсов!");
    }

    /**
     * Вспомогательный класс для тестов гонки потоков.
     */
    public static int runUnsafeTest(int threads, int iterations) throws InterruptedException {
        unsafeCounter = 0;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < iterations; j++) unsafeCounter++;
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        return unsafeCounter;
    }
}
