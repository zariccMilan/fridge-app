# Fridge App

A simple Spring Boot 4 application for managing fridge items, categories, and locations, with:

- REST API (CRUD, soft delete, partial update)
- Scheduled job that checks items which are close to their expiry date (currently logs in console)
- Minimal frontend built with plain HTML/CSS/JavaScript (no framework)
- Docker Compose setup for PostgreSQL
- Basic Web MVC tests for the controller

## Tech stack

- Java 21  
- Spring Boot 4 (Web, Data JPA, Validation, Scheduling)  
- PostgreSQL (via Docker Compose)  
- JUnit 5, Mockito (`@WebMvcTest` + `@MockitoBean`)  
- HTML, CSS, vanilla JavaScript (`fetch`)  

## How to run

### Prerequisites

- Docker + Docker Compose  
- Java 21+  
- Maven (or the bundled `mvnw` wrapper)  

### 1. Clone the repository
- git clone https://github.com/zariccMilan/fridge-app.git
- cd fridge-app

  
### 2. Start PostgreSQL with Docker Compose
- docker compose up -d
  
This starts a `postgres:16` container with:

- database: `fridge_db`  
- user: `fridge_user`  
- password: `fridge_pass`  

On first start it automatically executes `db/init.sql`, which:

- creates the tables: `categories`, `item_locations`, `fridge_items`
- inserts a few sample categories, locations, and fridge items

### 3. Start the Spring Boot application

- ./mvnw spring-boot:run
- or
- mvn spring-boot:run


The application uses `src/main/resources/application.properties` configured for the Docker database, for example:


(If you mapped a different host port in `docker-compose.yml`, adjust the URL accordingly.)

### 4. Access the API and frontend

- REST API examples:
  - `GET http://localhost:8080/api/fridge/fridge-items`
  - `GET http://localhost:8080/api/fridge/categories`
  - `GET http://localhost:8080/api/fridge/item-locations`

- Frontend (simple one-page app, no framework):
  - `http://localhost:8080/index.html`

The frontend allows you to:

- list categories (ID + name)
- list locations (ID + name)
- list fridge items (ID, name, quantity, bestBefore, category, location)
- create, update, and delete fridge items
- create and update categories and locations

All frontend actions use `fetch` to call the REST endpoints.

## Features

### Fridge items

- Full CRUD for the `FridgeItem` entity
- Soft delete:
  - DELETE endpoint does not physically remove records; it sets `isDeleted = true` and `deletedAt`
- Partial update (PATCH):
  - only non-null fields in the request are updated (e.g. `quantity`, `bestBefore`)

### Categories and locations

- CRUD for `Category` and `ItemLocation`
- `FridgeItem` references category and location via UUID foreign keys

### Scheduled job

- `@Scheduled` job periodically finds items that are close to their expiry date
- In this version it logs the result, but it can easily be extended to write notifications or send emails

### Testing

- Web MVC tests for `FridgeItemController` using:
  - `@WebMvcTest(FridgeItemController.class)`
  - `@MockitoBean` for mocking `FridgeItemService`
  - `MockMvc` for asserting HTTP status codes and JSON responses

## Configuration

- `application.properties`  
  - default configuration for the Docker PostgreSQL instance (matches `docker-compose.yml`)

- `application-example.properties`  
  - example configuration file that mirrors the Docker setup and can be used as a template


## Project structure

- `src/main/java/...`
  - entities (`FridgeItem`, `Category`, `ItemLocation`)
  - dtos
  - mappers
  - repositories
  - services
  - REST controllers (`FridgeItemController`, `CategoryController`, `ItemLocationController`)
  - scheduled job for checking expiry dates (also in Service)

- `src/main/resources/static/index.html`
  - simple one-page frontend (HTML/CSS/JS) that calls the REST API

- `src/test/java/...`
  - Web MVC tests for `FridgeItemController`

- `db/init.sql`
  - creates the database schema and inserts initial data
  - executed automatically when the Docker PostgreSQL container starts for the first time

- `docker-compose.yml`
  - defines the PostgreSQL service used by the application

## Postman collection

A Postman collection is available in `postman/fridge.postman_collection.json`.

You can import it into Postman and run requests against:
- the local backend (`http://localhost:8080`)
- the Docker-based PostgreSQL setup described above.











