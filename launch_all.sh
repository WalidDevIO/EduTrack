#!/bin/bash
mvn -f messagesapi/pom.xml clean package
./authapi/gradlew -p authapi/ clean bootJar
./studentapi/gradlew -p studentapi/ clean bootJar
docker compose up --build