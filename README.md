# Hieraquia de diretorios

## Descrição

Esta é uma aplicação desenvolvida em Spring Boot que fornece uma API RESTful para gerenciar diretórios e arquivos. A aplicação suporta operações como listagem, criação, atualização e exclusão de diretórios e arquivos.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Pré-requisitos

Antes de executar a aplicação, verifique se você possui:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior instalado, se não estiver usando docker.
- [Maven](https://maven.apache.org/download.cgi) instalado.
- [Docker](https://www.docker.com/products/docker-desktop) (opcional, se você quiser usar o Docker).
- [PostgreSQL](https://www.postgresql.org/download/) instalado, se você não estiver usando Docker para o banco de dados.

## Configuração do Banco de Dados

Caso deseje executar a aplicação localmente sem Docker, crie um banco de dados PostgreSQL e configure as seguintes propriedades no arquivo `application.properties`:

```properties

spring.datasource.url=jdbc:postgresql://localhost:5768/testedb
spring.datasource.username=admin
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
