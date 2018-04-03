#!/bin/bash -e

./gradlew build

cd kotlin-kobalt && ./kobaltw test
