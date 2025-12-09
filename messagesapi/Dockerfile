FROM eclipse-temurin:17-jdk-alpine AS build-stage
RUN apt update && apt install -y maven && rm -rf /var/lib/apt/lists/*

COPY . /build
WORKDIR /build

RUN mvn clean package

FROM walkloly/tomcat-si:latest AS final-stage

EXPOSE 8080

COPY --from=build-stage /build/target/servlet.war /usr/local/tomcat/webapps/api.war