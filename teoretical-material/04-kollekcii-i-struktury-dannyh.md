# Коллекции в Java

Java Collections Framework предоставляет набор интерфейсов и классов для хранения и манипулирования группами объектов.

## Основные интерфейсы

1. **List**: Упорядоченная коллекция (может содержать дубликаты). Пример: `ArrayList`, `LinkedList`.
2. **Set**: Коллекция, не содержащая дубликатов. Пример: `HashSet`, `TreeSet`.
3. **Map**: Объект, отображающий ключи на значения (ключи уникальны). Пример: `HashMap`, `TreeMap`.

## Пример использования ArrayList

```java
import java.util.ArrayList;
import java.util.List;

List<String> fruits = new ArrayList<>();
fruits.add("Яблоко");
fruits.add("Банан");

for (String fruit : fruits) {
    System.out.println(fruit);
}
```
