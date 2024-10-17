# Build stage
FROM eclipse-temurin:21-alpine as build
WORKDIR /app
COPY . .
# Dá permissão de execução ao arquivo 'mvnw'
RUN chmod +x mvnw
# Executa o Maven para compilar o projeto
RUN ./mvnw package

# Runtime stage
FROM eclipse-temurin:21-alpine
VOLUME /tmp
ARG JAR_FILE=target/turismo-0.0.1-SNAPSHOT.jar
COPY --from=build /app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080
