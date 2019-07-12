#!/bin/bash

version="$(./gradlew properties -q | grep "version:" | sed 's/version: //g')"
java -Dspring.profiles.active=docker -jar ./build/libs/needoff-${version}.jar