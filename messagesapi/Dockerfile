FROM maven:3.8.5-openjdk-17-slim AS build-stage

COPY . /build
WORKDIR /build

RUN mvn clean package

FROM walkloly/tomcat-si:latest AS final-stage

EXPOSE 8080

COPY --from=build-stage /build/target/servlet.war /usr/local/tomcat/webapps/api.war