# transactions-api

Projeto de exemplo de API de transações.

Requisitos:
- Java 17
- Maven 3.8+
- Docker (para criação da imagem e docker-compose)

Como rodar localmente (maven):

1. Compilar e empacotar:

```bash
mvn -DskipTests package
```

2. Executar jar:

```bash
java -jar target/transactions-api.jar
```

A aplicação estará disponível em http://localhost:8005

Swagger/OpenAPI:
- UI: http://localhost:8005/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8005/v3/api-docs

Docker (build + run com Postgres):

1. Build da imagem (multi-stage Dockerfile está na raiz):

```bash
docker-compose build
```

2. Subir a stack (app + postgres):

```bash
docker-compose up --build
```

O serviço web ficará exposto na porta 8005.

Observações:
- Migração para Spring Boot 3 (Jakarta) já aplicada; imports `javax.*` foram migrados para `jakarta.*`.
- Testes unitários e de integração existentes foram executados localmente com sucesso.


