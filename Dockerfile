
FROM azul/zulu-openjdk:17 as builder


WORKDIR /app


COPY pom.xml .
COPY src ./src


RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package


FROM azul/zulu-openjdk:17


WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
