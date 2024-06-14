# Community Management Application
## Overview
This is a RESTful web application designed to manage users and communities. Users can create and join communities, which can then post content and engage in chats. Each user has a specific role within each community, such as creator, admin, or member. The application is built using Java and leverages the Spring Boot framework along with several other libraries and technologies.

## Features
1. **User Management:**

 - User registration and authentication.
  - User profile with avatar and balance management.
  - Secure password handling.

2. **Community Management:**
  - Creation of communities by users.
  - Users can join communities and have roles (creator, admin, member).
  - Communities can post content and have chats.

3. **Post and Chat Management:**
  - Communities can create posts.
  - Real-time chat functionality within communities.

## Technologies Used
### Core Frameworks and Libraries
  - Java 17: The programming language used for development.
  - Spring Boot 3.2.5: The framework providing various features to build production-ready applications.
    - Spring Boot Starter Data JPA: For ORM and database interactions.
    - Spring Boot Starter Security: For handling authentication and authorization.
    - Spring Boot Starter Thymeleaf: For server-side rendering of web pages.
    - Spring Boot Starter Data REST: To expose repositories as RESTful web services.
    - Spring Boot Starter Web: To build web applications, including RESTful services.
  - QueryDSL 5.0.0: For type-safe queries.
  - Lombok: To reduce boilerplate code by using annotations.
### Database
  - MySQL: Used as the relational database to store user, community, post, chat, and message data.
    - MySQL Connector J: JDBC driver for MySQL.
### Testing
  - Spring Boot Starter Test: Provides a comprehensive testing framework.
    -JUnit Platform: For running tests.
    - Mockito Core: For mocking objects in tests.
    - Spring Security Test: For testing Spring Security.
### API Documentation
  - Springdoc OpenAPI 2.5.0: For generating API documentation.
## Database Schema
### Users
Stores information about users including their credentials and profile details.

```sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    avatar_url VARCHAR(255)
);
``` 
### Communities
Stores information about communities including their owners and descriptive details.

```sql
CREATE TABLE communities (
    community_id INT AUTO_INCREMENT PRIMARY KEY,
    community_name VARCHAR(100) NOT NULL,
    owner_id INT NOT NULL,
    logo_url VARCHAR(255),
    banner_url VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES users(user_id) ON DELETE CASCADE
);
```
### Community Members
Associates users with communities and defines their roles within each community.

```sql
CREATE TABLE community_members (
    community_id INT NOT NULL,
    user_id INT NOT NULL,
    role VARCHAR(20) NOT NULL, -- 'creator', 'admin', 'member'
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (community_id, user_id),
    FOREIGN KEY (community_id) REFERENCES communities(community_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
```
### Chats
Stores information about chats within communities.

```sql
CREATE TABLE chats (
    chat_id INT AUTO_INCREMENT PRIMARY KEY,
    chat_name VARCHAR(100) NOT NULL,
    community_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (community_id) REFERENCES communities(community_id) ON DELETE CASCADE
);
```

### Messages
Stores messages sent within chats.

```sql
CREATE TABLE messages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    chat_id INT NOT NULL,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chat_id) REFERENCES chats(chat_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
```
### Posts
Stores posts made by users in communities.

``` sql
CREATE TABLE posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    community_id INT NOT NULL,
    user_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (community_id) REFERENCES communities(community_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
```
## Getting Started
### Prerequisites
- Java 17 or higher
- MySQL database
### Building and Running the Application
1. **Clone the repository:**

```bash
git clone <repository-url>
cd <repository-directory>
```
2. **Configure the database:**
- Update the application.properties file with your MySQL database credentials.

3. **Build the application:**

```bash
./gradlew build
Run the application:
```

4. **Run the application:**
```bash
./gradlew bootRun
```
## Accessing the Application
- The application will be accessible at http://localhost:8080.
- API documentation will be available at http://localhost:8080/swagger-ui.html.
## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.

This README.md provides a comprehensive overview of the project, including its features, technologies used, database schema, and instructions for getting started. This should help new developers understand the project and get it running on their local machines.
