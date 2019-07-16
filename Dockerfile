FROM openjdk:11.0.3-stretch AS build
COPY . /application
RUN /application/gradlew build -x test -p /application

FROM openjdk:11.0.3-jre-slim AS run
ARG APPLICATION_FILENAME
COPY --from=build application/build/libs/$APPLICATION_FILENAME /application/$APPLICATION_FILENAME
WORKDIR /application
EXPOSE 8080
ENV APPLICATION_FILENAME $APPLICATION_FILENAME
ENTRYPOINT java -Dspring.profiles.active=docker -jar $APPLICATION_FILENAME
