#!/bin/bash
#Script qui build les jar et war de nos 3 APIs + Installe les dépendances Node (nécessite pnpm)

mvn -f messagesapi/pom.xml clean package
./authapi/gradlew -p authapi/ clean bootJar
./studentapi/gradlew -p studentapi/ clean bootJar
pnpm -C ./expressApi i
pnpm -C ./front i