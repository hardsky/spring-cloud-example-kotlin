FROM openjdk:8-jdk-alpine
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

#RUN chmod +x mvnw && ./mvnw install -DskipTests
