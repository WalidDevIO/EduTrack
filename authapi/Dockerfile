FROM openjdk:17-jdk-bullseye AS build-stage
COPY . /build
WORKDIR /build

RUN ./gradlew clean bootJar

FROM openjdk:17-jdk-bullseye AS final-stage

WORKDIR /app
COPY --from=build-stage /build/build/libs/authapi-1.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]