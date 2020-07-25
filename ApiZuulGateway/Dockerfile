FROM openjdk:8-jdk-alpine AS app_build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x /workspace/app/mvnw && \
        ./mvnw install -DskipTests && \
        mkdir -p target/dependency && \
        (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=app_build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=app_build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=app_build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.app.gateway.ApiZuulGatewayApplication"]
