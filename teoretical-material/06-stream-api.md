# Stream API и Лямбда-выражения

Инструменты для функционального программирования в Java, введены в Java 8.

### 1. Лямбда-выражения
Короткая запись анонимных классов, реализующих функциональный интерфейс (интерфейс с одним методом).
```java
// Было
new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello");
    }
}).start();

// Стало
new Thread(() -> System.out.println("Hello")).start();
```

### 2. Stream API
Позволяет обрабатывать коллекции в декларативном стиле.

**Основные этапы:**
1. **Creation**: `list.stream()`.
2. **Intermediate operations** (ленивые, возвращают Stream):
    *   `filter(predicate)`
    *   `map(function)` — преобразование типа
    *   `sorted()`
    *   `distinct()`
3. **Terminal operations** (завершают поток, возвращают результат):
    *   `collect(Collectors.toList())`
    *   `forEach(consumer)`
    *   `count()`
    *   `findFirst()`

### 3. Пример
```java
List<String> result = names.stream()
    .filter(name -> name.startsWith("A"))
    .map(String::toUpperCase)
    .sorted()
    .collect(Collectors.toList());
```

### 4. Optional
Контейнер для значения, которое может быть `null`. Помогает избежать `NullPointerException`.
```java
Optional<String> name = Optional.ofNullable(getName());
name.ifPresent(System.out::println);
String value = name.orElse("Default");
```
