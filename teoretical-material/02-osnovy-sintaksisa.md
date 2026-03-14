# Основы синтаксиса Java

В этом разделе мы рассмотрим базовые конструкции языка.

## Типы данных

В Java есть две категории типов данных:
1. **Примитивные типы**: `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`.
2. **Ссылочные типы**: Объекты, массивы, интерфейсы.

## Переменные

```java
int number = 10;
String greeting = "Hello, Java!";
boolean isActive = true;
```

## Управляющие конструкции

### Условный оператор if-else
```java
if (number > 5) {
    System.out.println("Больше пяти");
} else {
    System.out.println("Меньше или равно пяти");
}
```

### Цикл for
```java
for (int i = 0; i < 5; i++) {
    System.out.println("Итерация: " + i);
}
```

## Методы

Метод — это блок кода, который выполняет определенную задачу.

```java
public int add(int a, int b) {
    return a + b;
}
```
