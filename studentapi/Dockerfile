FROM eclipse-temurin:17-jdk-alpine AS build-stage
COPY . /build
WORKDIR /build

RUN ./gradlew clean bootJar

FROM eclipse-temurin:17-jdk-alpine AS final-stage

WORKDIR /app
COPY --from=build-stage /build/build/libs/studentapi-1.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]