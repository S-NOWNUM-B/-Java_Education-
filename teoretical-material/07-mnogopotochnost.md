# Многопоточность в Java

Java поддерживает выполнение нескольких потоков (threads) одновременно.

### 1. Создание потока
*   Наследование от `Thread`: `class MyThread extends Thread`.
*   Реализация `Runnable`: `new Thread(() -> { ... }).start()`.
*   Реализация `Callable`: возвращает результат через `Future`.

### 2. Синхронизация
Для предотвращения состояния гонки (Race Condition).
*   `synchronized`: Блокирует доступ к методу или блоку кода для других потоков.
*   `volatile`: Гарантирует, что значение переменной будет читаться из основной памяти, а не из кэша потока.

### 3. Продвинутые инструменты (java.util.concurrent)
*   **ExecutorService**: Пул потоков для эффективного управления ресурсами.
*   **Atomic types**: `AtomicInteger`, `AtomicBoolean` — потокобезопасные версии примитивов.
*   **Concurrent Collections**: `ConcurrentHashMap`, `CopyOnWriteArrayList` — коллекции, оптимизированные для многопоточной среды.

### 4. Состояния потока
`NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, `TERMINATED`.
