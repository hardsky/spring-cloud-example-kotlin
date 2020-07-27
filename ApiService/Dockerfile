FROM myorg/dto AS dto_build

FROM openjdk:8-jdk-alpine AS app_build
WORKDIR /workspace/app

ARG DTO_DIR=/workspace/app/target
COPY --from=dto_build ${DTO_DIR}/ApiDto-1.0-SNAPSHOT.jar .
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x /workspace/app/mvnw && \
        ./mvnw org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=ApiDto-1.0-SNAPSHOT.jar && \
        ./mvnw install -DskipTests && \
        mkdir -p target/dependency && \
        (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=app_build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=app_build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=app_build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.app.api.ApiServiceApplicationKt"]
