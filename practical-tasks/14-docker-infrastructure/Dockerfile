# Используем официальный образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем JAR-файл (после сборки gradle build он появится в build/libs)
COPY build/libs/*.jar app.jar

# Порт, который будет слушать приложение
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
