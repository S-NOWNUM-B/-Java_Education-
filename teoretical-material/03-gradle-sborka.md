# Gradle: Система сборки

Gradle — это современная система автоматизации сборки, использующая DSL на языке Groovy или Kotlin.

### 1. Основные файлы
*   `build.gradle`: Конфигурация проекта (зависимости, плагины, задачи).
*   `settings.gradle`: Определяет структуру проекта (название, модули).
*   `gradlew` / `gradlew.bat`: Gradle Wrapper. Позволяет запускать сборку без локальной установки самого Gradle (рекомендуемый способ).

### 2. Структура build.gradle
```groovy
plugins {
    id 'java' // Плагин для Java-проектов
}

group = 'org.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral() // Где искать библиотеки
}

dependencies {
    // Подключение библиотек
    implementation 'com.google.gson:gson:2.10.1' 
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.2'
}
```

### 3. Жизненный цикл сборки
1. **Initialization**: Определение участников проекта (проверка `settings.gradle`).
2. **Configuration**: Построение графа задач.
3. **Execution**: Выполнение конкретных задач.

### 4. Основные команды
*   `./gradlew build`: Полная сборка проекта (компиляция + тесты + jar).
*   `./gradlew clean`: Удаление папки `build`.
*   `./gradlew test`: Запуск тестов.
*   `./gradlew run`: Запуск приложения (если подключен плагин `application`).
