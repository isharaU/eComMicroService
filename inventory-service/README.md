# Inventory Service

A Spring Boot microservice for managing product inventory and checking stock status.

---

## Features

- REST API to check if a product SKU is in stock
- Uses MySQL as the database
- JPA-based persistence
- Testcontainers for integration testing

---

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- Testcontainers
- JUnit

---

## Setup & Running

### 1. Clone the repository

```bash
git clone <repo-url>
cd inventory-service
```

### 2. Configure Database

Update `src/main/resources/application.properties` and `secrets.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_service
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Example `secrets.properties`:

```properties
DB_USERNAME=root
DB_PASSWORD=yourpassword
```

### 3. Build and Run

Using Maven Wrapper:

```bash
./mvnw clean package
java -jar target/inventory_service-0.0.1-SNAPSHOT.jar
```

Or run directly from your IDE.

---

## API Usage

### Check if SKU is in stock

**Endpoint:**

```
GET /api/inventory/{sku-code}
```

**Response:**

- `true` if SKU exists in inventory
- `false` otherwise

---

## Project Structure

```
src/
├─ main/
│  ├─ java/com/ishara/inventory_service/
│  │  ├─ controller/InventoryController.java
│  │  ├─ service/InventoryService.java
│  │  ├─ repository/InventoryRepository.java
│  │  └─ model/Inventory.java
│  └─ resources/
│     ├─ application.properties
│     └─ secrets.properties
├─ test/
│  └─ java/com/ishara/inventory_service/InventoryServiceApplicationTests.java
```

---

## Testing

Integration tests use Testcontainers to spin up a MySQL instance automatically.

Run tests with:

```bash
./mvnw test
```

---

## Notes

- Keep `secrets.properties` out of version control for security.
