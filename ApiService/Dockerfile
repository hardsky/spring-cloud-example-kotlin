FROM openjdk:8-jdk-alpine AS dto_build
WORKDIR /workspace/dto

COPY ../ApiDto/mvnw .
COPY ../ApiDto/.mvn .mvn
COPY ../ApiDto/pom.xml .
COPY ../ApiDto/src src

RUN ./mvnw install -DskipTests

FROM openjdk:8-jdk-alpine AS app_build
WORKDIR /workspace/app

ARG DTO_DIR=/workspace/dto/target
COPY --from=dto_build