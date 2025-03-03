#!/bin/bash
#Script qui build les jar et war de nos 3 APIs

mvn -f messagesapi/pom.xml clean package
./authapi/gradlew -p authapi/ clean bootJar
./studentapi/gradlew -p studentapi/ clean bootJar