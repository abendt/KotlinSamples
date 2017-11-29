#!/bin/bash -e

./gradlew test
mvn -f kotlin-springboot2-rest/pom.xml verify
cd kotlin-kobalt && ./kobaltw test