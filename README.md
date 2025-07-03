# 🛒 Microservices Online Shop Application

This project demonstrates the **development of a modern e-commerce application** using a **Microservices Architecture** with **Spring Boot**, **Spring Cloud**, and supporting tools like **RabbitMQ**, **Kafka**, **MongoDB**, **MySQL**, **Vault**, and **Keycloak**.

It is designed as a practical hands-on tutorial emphasizing **scalability**, **robustness**, **resilience**, and **real-world architectural patterns**.

---

## ✨ Project Overview

The application simulates a simple online shop comprising multiple microservices with independent responsibilities and databases. It showcases:

* ✅ Microservices architecture using Spring Cloud
* 🔐 Secure authentication and authorization with **Keycloak**
* 📦 Distributed messaging with **RabbitMQ** and **Kafka**
* 📊 Centralized logging with the **ELK Stack**
* 🧭 Distributed tracing with **Zipkin**
* ⚙️ Config and secret management using **Config Server** and **Vault**

---

## 🧱 Core Microservices

| Service                  | Responsibility                           | DB      | Communication                          |
| ------------------------ | ---------------------------------------- | ------- | -------------------------------------- |
| **Product Service**      | Manages product catalog                  | MongoDB | -                                      |
| **Order Service**        | Places orders and triggers notifications | MySQL   | Sync (Inventory), Async (Notification) |
| **Inventory Service**    | Checks and updates stock availability    | MySQL   | Sync (Order)                           |
| **Notification Service** | Sends order confirmation emails          | -       | Async (Order via MQ)                   |

---

## 🧩 Key Architectural Patterns

* **Service Discovery** – Service registration and discovery
* **Centralized Configuration** – Using Spring Cloud Config Server
* **API Gateway** – Single entry point for all services
* **Event-Driven Communication** – RabbitMQ & Kafka
* **Centralized Logging** – Elasticsearch, Logstash, Kibana (ELK)
* **Distributed Tracing** – Zipkin
* **Circuit Breakers** – Resilient communication using Resilience4j
* **Authorization Server** – Keycloak for secure access
* **Secret Management** – HashiCorp Vault

---

## 🔧 Tech Stack

* **Java 17**, **Spring Boot 2.6.4**, **Spring Cloud**
* **MongoDB**, **MySQL**
* **RabbitMQ**, **Apache Kafka**
* **Keycloak**, **Vault**, **Zipkin**
* **Elasticsearch**, **Logstash**, **Kibana**
* **Testcontainers**, **JUnit 5**

---

## 🧪 Testing with Testcontainers

Integration testing is powered by **Testcontainers**, enabling real Dockerized environments for MongoDB, MySQL, RabbitMQ, and more. Tests validate endpoints using:

* `@Testcontainers` for lifecycle management
* `@MockMvc` for HTTP interaction simulation
* JSON serialization using `ObjectMapper`
* Runtime configuration using `@DynamicPropertySource`

---

## 📁 Multi-Module Maven Setup

The project follows a **multi-module Maven structure** for modularity and reuse:

```
microservices-new/
├── product-service/
├── order-service/
├── inventory-service/
├── notification-service/
└── pom.xml (parent)
```

* Common dependencies and plugin management are centralized in the **parent `pom.xml`**
* Each module is independently deployable

---

## ⚠️ Dev Mode Schema Reset

To avoid data duplication during dev:

```properties
spring.jpa.hibernate.ddl-auto=create-drop
```

This config is **temporary** and should be replaced by **Flyway or Liquibase** in production.

---

## 🚀 Running the Application

Each service can be started individually on different ports:

* Product Service: `8080`
* Order Service: `8081`
* Inventory Service: `8082`

Make sure supporting services like **Config Server**, **Eureka**, **Vault**, **RabbitMQ**, etc., are up and configured properly.

---

## 📚 Future Plans

* [ ] Add frontend UI using React
* [ ] Integrate with payment gateway
* [ ] Containerize with Docker and orchestrate with Kubernetes
* [ ] CI/CD with GitHub Actions or Jenkins

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

