# 🛒 Product Service

This is the **Product Service** microservice for the eComMicroService project. It manages product data, allowing you to create and retrieve products using a RESTful API. Product data is stored in MongoDB.

---

## 🚀 Features

- Create new products via REST API
- Retrieve all products
- MongoDB integration using Spring Data MongoDB
- Integration tests using Testcontainers
- Built with Spring Boot and Lombok

---

## 🛠️ Tech Stack

- Java 21+
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Lombok
- Testcontainers
- JUnit 5

---

## 📁 Project Structure

```
product-service/
├── src/
│   ├── main/
│   │   ├── java/com/ishara/product_service/
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── model/           # MongoDB Entities
│   │   │   ├── repository/      # Spring Data MongoDB Repositories
│   │   │   └── service/         # Business Logic
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/ishara/product_service/
│           └── ProductServiceApplicationTests.java
├── pom.xml
└── ...
```

---

## ⚙️ Configuration

Edit `src/main/resources/application.properties` for MongoDB and server settings:

```properties
spring.application.name=product-service
spring.data.mongodb.uri=mongodb://localhost:27017/product_service
server.port=8082
```

---

## 🏁 Running the Service

### Prerequisites

- Java 21+
- Maven
- MongoDB running on `localhost:27017` with a database named `product_service`

### Build and Run

```sh
cd product-service
./mvnw clean package
java -jar target/product-service-0.0.1-SNAPSHOT.jar
```

The service will start on [http://localhost:8082](http://localhost:8082).

---

## 🧪 Testing

Integration tests use **Testcontainers** to spin up a MongoDB container automatically.

To run tests:

```sh
./mvnw test
```

---

## 📚 API Endpoints

### Create Product

- **POST** `/api/products`
- **Request Body:**
    ```json
    {
      "name": "Samsung A55",
      "description": "The best phone in the world",
      "price": 93000
    }
    ```
- **Response:**  
  HTTP 201 Created

### Get All Products

- **GET** `/api/products`
- **Response:**  
  HTTP 200 OK  
  ```json
  [
    {
      "id": "string",
      "name": "Samsung A55",
      "description": "The best phone in the world",
      "price": 93000
    }
  ]
  ```

---

## 📝 License

MIT License © 2025 ishara

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.
