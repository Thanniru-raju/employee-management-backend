# Employee Management System — Backend (Spring Boot 3 + Java 21)

Production-ready RESTful API backend for the Employee Management System, built with **Spring Boot 3**, **Java 21**, **Spring Data JPA**, and **Hibernate**.

---

## 🚀 REST API Endpoints

| Method | Endpoint | Description | Status Code |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/employees` | Create a new employee | `201 Created` |
| `GET` | `/api/employees` | Retrieve all employees | `200 OK` |
| `GET` | `/api/employees?search={q}` | Search employees by keyword | `200 OK` |
| `GET` | `/api/employees/{id}` | Retrieve single employee by ID | `200 OK` / `404 Not Found` |
| `PUT` | `/api/employees/{id}` | Update existing employee details | `200 OK` / `409 Conflict` |
| `DELETE`| `/api/employees/{id}` | Delete employee by ID | `200 OK` / `404 Not Found` |
| `GET` | `/api/employees/count` | Retrieve total employee count | `200 OK` |

---

## 🛠️ Tech Stack & Architecture

- **Java 21 (LTS)**
- **Spring Boot 3.4.x**
- **Spring Web MVC** (REST APIs)
- **Spring Data JPA & Hibernate** (ORM & Persistence)
- **Bean Validation (Jakarta Validation)** (`@NotBlank`, `@Email`, `@Positive`)
- **MySQL Connector/J**
- **Maven Wrapper (`mvnw`)**

### Layered Architecture
```
src/main/java/com/ems/
├── config/       # Global CORS Configuration bean
├── controller/   # REST Controller handling HTTP requests & response entity
├── entity/       # Employee JPA Entity with validation annotations
├── exception/    # Custom Exceptions & @RestControllerAdvice Global Handler
├── repository/   # Spring Data JPA Repository with JPQL search queries
├── service/      # Service interface and business logic implementation
└── EmployeeManagementApplication.java
```

---

## ⚙️ Environment Configuration

Set environment variables in your deployment platform (e.g., Render) or specify in `.env`:

```properties
# Port
PORT=8080

# Database Configuration (Aiven Cloud MySQL example):
DB_URL=jdbc:mysql://mysql-yourproject-aivencloud.com:12345/defaultdb?sslMode=REQUIRED
DB_USERNAME=avnadmin
DB_PASSWORD=your_aiven_password
DB_DRIVER=com.mysql.cj.jdbc.Driver
```

---

## 💻 Running Locally

```powershell
# Build and run with Maven Wrapper
.\mvnw.cmd spring-boot:run
```
Backend will be live at `http://localhost:8080`.
