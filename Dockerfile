# Etapa de construção
FROM eclipse-temurin:21-alpine AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:21-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/turismo-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
