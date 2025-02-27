FROM openjdk:17-jdk-bullseye

WORKDIR /app
COPY ./build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]