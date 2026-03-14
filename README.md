<div align="center">

# Java Education

### Путь от основ к полноценным приложениям на Java

**Java · OOP · Gradle**

</div>

---

## Содержание

- [О проекте](#о-проекте)
- [Теоретический материал](#теоретический-материал)
- [Практические задания](#практические-задания)
- [Быстрый старт](#быстрый-старт)
- [Команды](#команды)
- [Системные требования](#системные-требования)

---

## О проекте

> **От простых классов к мощным приложениям: Java, ООП и современная разработка**

Этот репозиторий предназначен для комплексного изучения Java с нуля. Основной упор сделан на понимание объектно-ориентированного программирования, структур данных, работы с коллекциями и построении реальных приложений. Каждый проект полностью независим с собственной конфигурацией Gradle.

---

## Теоретический материал

В папке `teoretical-material` собраны основные темы для изучения:

1. [Введение в Java](teoretical-material/01-vvedenie-v-java.md)
2. [Основы синтаксиса](teoretical-material/02-osnovy-sintaksisa.md)
3. [Концепции ООП](teoretical-material/03-oop-koncepcii.md)
4. [Коллекции и структуры данных](teoretical-material/04-kollekcii-i-struktury-dannyh.md)
5. [Обработка исключений](teoretical-material/05-isklyucheniya.md)

---

## Практические задания

Все практические задачи находятся в папке `practical-tasks`.

- [01-hello-world](practical-tasks/01-hello-world) — Первая программа на Java.
- [02-calculating-area](practical-tasks/02-calculating-area) — Использование Scanner и обработка исключений.

---

## Быстрый старт

### Запуск упражнений

```bash
# 1. Клонирование репозитория
git clone <repository-url>
cd -Java_Education-

# 2. Переход в упражнение
cd practical-tasks/nameProject

# 3. Сборка и запуск
gradle build && gradle run
```

### Запуск проектов

```bash
cd Practical_Tasks/nameProject
gradle build && gradle run
```

---

## Команды

```bash
# В папке каждого проекта/упражнения
gradle run          # Запуск проекта
gradle build        # Сборка проекта
gradle test         # Запуск тестов
gradle clean        # Очистка build-артефактов
gradle dependencies # Просмотр зависимостей
```

---

## Системные требования

<div align="center">

|  Компонент  |         Минимум          |  Рекомендуется   |
| :---------: | :----------------------: | :--------------: |
|   **JDK**   |           17+            |       21+        |
| **Gradle**  |           7.0+           |      Latest      |
|   **IDE**   | Любой текстовый редактор | IntelliJ IDEA    |
| **ОС**      |    Windows / macOS / Linux     | Любая  |

</div>

---
