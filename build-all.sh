#!/bin/bash -e

./gradlew --no-daemon build

cd kotlin-kobalt && ./kobaltw test
