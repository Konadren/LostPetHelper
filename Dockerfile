# Используем базовый образ для Java
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/LostPetHelper-0.0.1-SNAPSHOT.jar app.jar
# Указываем порт, на котором будет работать приложение
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
