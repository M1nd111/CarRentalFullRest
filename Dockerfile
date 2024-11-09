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

# Проверка, есть ли файл .jar в /build/build/libs/
RUN ls /build/build/libs/

# Этап запуска
FROM openjdk:17-jdk-alpine

ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/directory
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=Root89

# Копируем собранный JAR-файл
COPY --from=build /build/build/libs/*.jar app.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "/app.jar"]
