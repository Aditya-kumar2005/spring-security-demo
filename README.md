# Spring Security Exploration Project

A hands-on reference implementation for exploring Spring Security authentication, authorization, and CSRF protection mechanism. This project showcases user registration, custom user details services, and role-based route protection.

## 🚀 Quick Start

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd spring-security-exploration
   ```
2. **Configure Database:** Update `src/main/resources/application.properties` with your database credentials.
3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🔑 Core Security Architecture

### 1. User Entity & Role Mapping
The project uses a custom `User` entity implementing Spring Security's `UserDetails` interface.
* **Fields:** `id`, `username`, `password`, `enabled`, and `roles`.
* **Roles supported:** `ROLE_USER`, `ROLE_ADMIN`.

### 2. User Service Layer
* **`CustomUserDetailsService`:** Implements `UserDetailsService` to fetch user data from the database during authentication.
* **`UserService`:** Handles user registration logic and securely encodes raw passwords using `BCryptPasswordEncoder` before database persistence.

---

## 🛣️ API Route Directory

| Endpoint | HTTP Method | Access Level | Description |
| :--- | :--- | :--- | :--- |
| `/` | GET | Public | Landing page / Welcome banner. |
| `/csrf` | GET | Public | Fetches the active CSRF token for stateless clients. |
| `/register` | POST | Public | Creates a new user account (forces BCrypt encryption). |
| `/login` | POST | Public | Authenticates credentials and starts a session. |
| `/products` | GET | Authenticated | Fetches product list (Requires `USER` or `ADMIN` role). |
| `/products` | POST | Restricted | Adds a new product (Requires `ADMIN` role). |

---

## 🛡️ CSRF Handling Guide

Spring Security enables CSRF protection by default for unsafe HTTP methods (`POST`, `PUT`, `DELETE`).

### Fetching the Token
Send a `GET` request to `/csrf` to receive the token payload:
```json
{
  "parameterName": "_csrf",
  "headerName": "X-CSRF-TOKEN",
  "token": "4f5e6a7b-c8d9-e0f1-a2b3-c4d5e6f7a8b9"
}
```

### Sending Authenticated Write Requests
When making `POST` requests to `/register`, `/login`, or `/products`, include the token in your HTTP header:
```http
POST /products HTTP/1.1
Host: localhost:8080
X-CSRF-TOKEN: 4f5e6a7b-c8d9-e0f1-a2b3-c4d5e6f7a8b9
Content-Type: application/json

{
  "name": "Secure Product Item",
  "price": 99.99
}
```

---

## 🛠️ Technology Stack

* **Framework:** Spring Boot 3.x
* **Security:** Spring Security 6.x
* **Data Layer:** Spring Data JPA
* **Database:** H2 (In-Memory) / PostgreSQL / MySQL
* **Crypto:** BCrypt Password Hashing
