# Hieraquia de diretórios

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


## Endpoints

### 1. Listar Todos os Diretórios

- **Método**: `GET`
- **Endpoint**: `/directories`
- **Descrição**: Retorna todos os diretórios disponíveis.
- **Resposta**:
  - **Código**: 200 OK
  - **Corpo**: Conjunto de objetos `DirectoryDTO`.

### 2. Listar Diretórios Pais

- **Método**: `GET`
- **Endpoint**: `/directories/parents`
- **Descrição**: Retorna todos os diretórios que são pais (não têm um diretório pai).
- **Resposta**:
  - **Código**: 200 OK
  - **Corpo**: Lista de objetos `DirectoryDTO`.

### 3. Encontrar Diretório pelo ID

- **Método**: `GET`
- **Endpoint**: `/directories/{id}`
- **Descrição**: Retorna um diretório específico pelo seu ID.
- **Parâmetros**:
  - `id`: ID do diretório a ser encontrado.
- **Resposta**:
  - **Código**: 200 OK
  - **Corpo**: Objeto `DirectoryDTO`.

### 4. Criar um Novo Diretório

- **Método**: `POST`
- **Endpoint**: `/directories`
- **Descrição**: Cria um novo diretório.
- **Corpo da Requisição**:
  - Objeto `DirectoryDTO` que contém as informações do diretório a ser criado.
- **Resposta**:
  - **Código**: 201 Created
  - **Corpo**: Objeto `DirectoryDTO` criado.
  - **Cabeçalho**: Localização do novo recurso no cabeçalho `Location`.

### 5. Atualizar um Diretório

- **Método**: `PUT`
- **Endpoint**: `/directories/{id}`
- **Descrição**: Atualiza as informações de um diretório existente.
- **Parâmetros**:
  - `id`: ID do diretório a ser atualizado.
- **Corpo da Requisição**:
  - Objeto `DirectoryDTO` com as novas informações do diretório.
- **Resposta**:
  - **Código**: 204 No Content

### 6. Excluir um Diretório

- **Método**: `DELETE`
- **Endpoint**: `/directories/{id}`
- **Descrição**: Remove um diretório pelo seu ID.
- **Parâmetros**:
  - `id`: ID do diretório a ser excluído.
- **Resposta**:
  - **Código**: 204 No Content

### 7. Encontrar Diretório com Arquivos pelo ID do Diretório Pai

- **Método**: `GET`
- **Endpoint**: `/directories/directory-with-file/{id}`
- **Descrição**: Retorna um diretório específico e seus arquivos associados pelo ID do diretório pai.
- **Parâmetros**:
  - `id`: ID do diretório pai.
- **Resposta**:
  - **Código**: 200 OK
  - **Corpo**: Objeto `DirectoryWithFile` que contém informações sobre o diretório e seus arquivos.

### 8. Encontrar Hierarquia de Diretórios e Arquivos

- **Método**: `GET`
- **Endpoint**: `/directories/directory-with-file/hierarchy`
- **Descrição**: Retorna todos os diretórios e arquivos em uma estrutura hierárquica.
- **Resposta**:
  - **Código**: 200 OK
  - **Corpo**: Lista de objetos `DirectoryWithFile` que representam a hierarquia de diretórios e arquivos.
