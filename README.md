# Spring Boot 4 App Template
Empty, ready-to-use Spring Boot 4 app template with a set of predefined libraries and example data.

## Features:
- Spring Boot 4
- Java 25
- Gradle
- Lombok
- Liquibase
- Spock testing framework
- Testcontainers for Postgres
- OpenAPI generator
  - with IntelliJ IDEA HTTP request generation
- GitHub Actions build on PR workflow

## Setting up the app for local development
These instructions assume you haven't changed the configuration in the `application.yaml` file.
1. Install PostgreSQL database/use Docker container
   - For Docker container:
     1. Download `postgres` image
     2. `docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres`
     3. `docker exec -it postgres psql -U postgres`
     4. `CREATE DATABASE demo;`
     5. `exit`
2. Run the app

## Liquibase
There are two solutions for Liquibase migrations. One of them is a classic, standard way of using SQL-formatted SQL
scripts to perform migrations. The second one is using YAML-defined changesets pointing to separate SQL files containing
migration scripts and rollbacks. You may want to remove one of them depending on your decision.

## Spock testing framework
A small predefined set of Spock tests is provided along with a Postgres test container and a base class for controller
end-to-end tests. They are dependent on the Liquibase migrations.

## API Generator
API generator is a Gradle plugin that generates API-related classes based on OpenAPI specification. Additionally, there
is also a Gradle task that generates HTTP requests based on the API specification.

### Generating API classes
Project uses OpenAPI generator. Each change in API requires changing the API specification first, located in
`/api` folder. Before launching the service in IntelliJ, make sure to run `./gradlew build` first to make sure
all the API-related classes are generated. The current plugin configuration generates interfaces that need to be implemented
by controllers and DTO classes. It can be changed so it would also generate controller classes by removing `interfacesOnly`
property from the plugin configuration located in `build.gradle`.

### Generating HTTP Requests
`./gradlew build` also generates ready-to-use HTTP requests based on the API specification for [IntelliJ HTTP client](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html).
This makes sure we always have an up-to-date HTTP requests collection.

### Using environments in generated HTTP requests
Environment JSON files should be located under `/api` folder. Public `http-client.env.json` is tracked by Git.
For other environments like `PROD`, you'll have to create a `http-client.private.env.json` file which will be ignored by
Git. For more details on how IntelliJ HTTP client public and private files work, see [JetBrains docs](https://www.jetbrains.com/help/idea/http-client-variables.html#example-working-with-environment-files).

## GitHub Actions build workflow
There's a simple GitHub Actions workflow that checks app build on every PR to `master` branch.

## Docker compose
Run `docker-compose up --build` to start the service along with Postgres DB.

### Debugging with Compose
You can debug the app when running with Docker compose. Follow these steps:
1. In IntelliJ, click Edit configurations...
2. Click Add New Configuration and select Remote JVM Debug
3. Leave the default settings as they are aligned to compose configuration
4. If the app is running, select the new configuration and click Debug
5. Place your breakpoints wherever you like
