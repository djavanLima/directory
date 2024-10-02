# Etapa 1: Usar uma imagem base para o Maven para compilar o código
FROM maven:3.8.6-openjdk-17

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o arquivo pom.xml e baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o restante do código do projeto e construir o aplicativo
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final usando uma imagem base do JDK
FROM openjdk:17-alpine

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expor a porta onde a API será executada
EXPOSE 8080

# Comando para executar a aplicação Spring
ENTRYPOINT ["java", "-jar", "app.jar"]
