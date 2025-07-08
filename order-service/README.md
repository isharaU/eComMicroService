# 📝 Order Service

This is the **Order Service** microservice for the eComMicroService project. It is responsible for handling order placement and persistence in a MySQL database.

---

## 🚀 Features

- **REST API** for placing orders
- **MySQL** database integration using Spring Data JPA
- **Testcontainers** for integration testing with real MySQL
- **Lombok** for boilerplate code reduction
- **Spring Boot** for rapid development

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- MySQL
- Lombok
- Testcontainers
- JUnit 5

---

## 📦 Project Structure

```
order-service/
├── src/
│   ├── main/
│   │   ├── java/com/ishara/order_service/
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── model/           # JPA Entities
│   │   │   ├── repository/      # Spring Data JPA Repositories
│   │   │   └── service/         # Business Logic
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/ishara/order_service/
│           └── OrderServiceApplicationTests.java
├── pom.xml
└── ...
```

---

## ⚙️ Configuration

Edit `src/main/resources/application.properties` for database and server settings:

```properties
spring.application.name=order-service
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/order_service
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

---

## 🏁 Running the Service

### Prerequisites

- Java 21+
- Maven
- MySQL running on `localhost:3306` with a database named `order_service`

### Build and Run

```sh
cd order-service
./mvnw clean package
java -jar target/order-service-0.0.1-SNAPSHOT.jar
```

The service will start on [http://localhost:8081](http://localhost:8081).

---

## 🧪 Testing

Integration tests use **Testcontainers** to spin up a MySQL container automatically.

To run tests:

```sh
./mvnw test
```

---

## 📚 API Endpoints

### Place Order

- **POST** `/api/order`
- **Request Body:**
    ```json
    {
      "orderLineItemsDtoList": [
        {
          "id": 1,
          "skuCode": "SKU123",
          "price": 100.00,
          "quantity": 2
        }
      ]
    }
    ```
- **Response:**  
  `Order is successfully Placed.`

---

## 📝 License

MIT License © 2025 ishara

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.
