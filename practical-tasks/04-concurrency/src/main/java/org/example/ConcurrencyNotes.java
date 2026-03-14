package org.example;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * // ===== Многопоточность: Гонка потоков и Синхронизация =====
 * 
 * В этом файле я разбираюсь, почему 1 + 1 в потоках не всегда равно 2,
 * и как заставить потоки работать "дружно".
 */
public class ConcurrencyNotes {

    private static int unsafeCounter = 0;
    private static AtomicInteger safeCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Запуск практического модуля: Многопоточность ===");

        // Пул из 10 потоков
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                unsafeCounter++; // Риск Race Condition
                safeCounter.incrementAndGet(); // Потокобезопасно
            }
        };

        for (int i = 0; i < 10; i++) {
            executor.submit(task);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Результат unsafe: " + unsafeCounter); // Скорее всего < 10000
        System.out.println("Результат safe (Atomic): " + safeCounter.get()); // Ровно 10000

        System.out.println("\n[INFO] Помни про Deadlock: никогда не делай перекрестных блокировок!");
    }
}
