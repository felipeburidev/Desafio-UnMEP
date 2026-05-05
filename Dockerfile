# Estagio 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /build

COPY pom.xml .
COPY src ./src

# Compila e gera o super arquivo "jar-with-dependencies"
RUN mvn clean package -DskipTests

# Estagio 2: Execucao
FROM eclipse-temurin:21-jre-alpine

# Copia APENAS o arquivo que tem "dependencies" no nome para a raiz do sistema
COPY --from=build /build/target/*-jar-with-dependencies.jar /app.jar

# Define a pasta /app como local de trabalho (onde o seu volume esta mapeado)
WORKDIR /app

# Executa o super arquivo
ENTRYPOINT ["java", "-jar", "/app.jar"]