#!/bin/bash

cd messagesapi
mvn clean tomcat7:redeploy

cd ../authapi
./gradlew clean bootJar

cd ../studentapi
./gradlew clean bootJar

cd ..
docker compose up --build