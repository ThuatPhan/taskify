# Taskify

Taskify is a modern, RESTful task management application built with Spring Boot. It provides a robust backend for managing tasks (todos), and tags, with a focus on security, clean code, and best practices.

---
## ✨ Features

- **Social Login**: Integration with Google for OAuth 2.0 authentication.
- **Role-Based Access Control (RBAC)**: Pre-configured `ADMIN` and `USER` roles to manage access to different APIs.
- **Task (Todo) Management**: Full CRUD (Create, Read, Update, Delete) functionality for user-specific todos.
- **Tagging System**: Create and assign tags to todos for better organization.
- **API Documentation**: Interactive API documentation powered by Swagger/OpenAPI.
- **Containerized**: Comes with `Dockerfile` and `docker-compose.yml` for easy setup and deployment.
- **Clean Code**: Enforced code style and formatting using Spotless with Palantir Java Format.
- **Pagination**: Paginated API responses for lists of resources.

---

## 🛠️ Tech Stack

| Layer            | Technology                          |
|------------------|--------------------------------------|
| Language         | Java 17                              |
| Framework        | Spring Boot 3, Spring Security       |
| ORM              | Spring Data JPA + Hibernate          |
| Auth             | JWT, OAuth2 (Google)                 |
| API Docs         | SpringDoc (OpenAPI 3)                |
| DB               | PostgreSQL                           |
| Build Tool       | Maven                                |
| Containerization | Docker, Docker Compose               |
| Code Quality     | Lombok, MapStruct, Spotless          |

---

## 🚀 Getting Started

Follow these instructions to get the project up and running on your local machine.


### Prerequisites

- Java 17
- Maven 3.9+
- Docker & Docker Compose

### Configuration

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/ThuatPhan/taskify.git
    cd taskify
    ```
2. **Create .env file**
    ```env
    # App Database (JPA)
    DB_URL=jdbc:postgresql://db:5432/<your-db-name>
    DB_USERNAME=<your-db-username>
    DB_PASSWORD=<your-db-password>
    
    # PostgreSQL Container
    POSTGRES_DB=<your-db-name>
    POSTGRES_USERNAME=<your-db-username>
    POSTGRES_PASSWORD=<your-db-password>
    
    # Google OAuth 2.0
    IDENTITY_CLIENT_ID=<your-google-client-id>
    IDENTITY_CLIENT_SECRET=<your-google-client-secret>
    IDENTITY_REDIRECT_URI=http://localhost:3000/authenticate
    
    # JWT Configuration
    JWT_SECRET=<your-jwt-secret>
    JWT_ISSUER=<your-jwt-issuer>
    JWT_EXPIRY_SECONDS=3600
    
    # Initial Admin Account
    ADMIN_USERNAME=<admin-username>
    ADMIN_PASSWORD=<admin-password>
    ```

3. **Run Docker Compose**
    ```bash
    docker-compose up -d --build
    ```

4. **API Documentation**

   Once the app is running, access the auto-generated Swagger UI at: http://localhost:8080/swagger-ui/index.html