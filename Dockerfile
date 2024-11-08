# Этап сборки
FROM gradle:8.7-jdk17 AS build

# Устанавливаем рабочую директорию
WORKDIR /build/

# Копируем только файлы конфигурации Gradle, чтобы использовать кэш
COPY build.gradle settings.gradle ./

# Загружаем зависимости (кэшируем их)
RUN gradle build --no-daemon -x test --max-workers=12

# Копируем исходный код
COPY src ./src

# Собираем проект с многопоточностью
RUN gradle build --no-daemon -x test --max-workers=12

# Этап запуска
FROM openjdk:17-jdk-alpine

# Копируем собранный JAR-файл
COPY --from=build /build/libs/CarRentalDirectoryWeb-1.0-SNAPSHOT.jar app.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "/app.jar"]
