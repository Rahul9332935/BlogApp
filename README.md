# Blog Application

This is a Spring Boot-based Blog Application that allows users to perform various operations such as registering, logging in, creating, updating, and deleting posts, categories, comments, and users. The application is secured using Spring Security and JWT authentication.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Authorization](#authorization)
- [Contributing](#contributing)
- [License](#license)

## Features

- User registration and authentication.
- CRUD operations for posts, categories, comments, and users.
- Role-based access control (Admin and User roles).
- JWT token-based authentication.
- Exception handling for error responses.

## Technologies Used

- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Tokens)
- Hibernate
- MySql Database
- Maven (for dependency management)
- Swagger UI (for API documentation)

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/Rahul9332935/BlogApp.git
    ```

2. Navigate to the project directory:

    ```bash
    cd blog-application
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

## Usage

1. Once the application is running, you can access it at `http://localhost:8081`.

2. Register a new user using the `/api/users/register` endpoint.

3. Log in using the `/api/users/login` endpoint to obtain a JWT token.

4. Use the obtained JWT token to access other endpoints.

## Endpoints

- **User Endpoints**:
  - `/api/users/register`: Register a new user.
  - `/api/users/login`: User login.
  - `/api/users/welcome`: Welcome message.
  - `/api/users/all`: Get all users (Admin role required).
  - `/api/users/{id}`: Get user by ID (User role required).

- **Post Endpoints**:
  - `/api/posts`: Get all posts.
  - `/api/posts/{postId}`: Get post by ID.
  - `/api/posts`: Create a new post.
  - `/api/posts/{postId}`: Update post by ID.
  - `/api/posts/{postId}`: Delete post by ID.

- **Category Endpoints**:
  - `/api/categories`: Get all categories.
  - `/api/categories/{categoryId}`: Get category by ID.
  - `/api/categories`: Create a new category.
  - `/api/categories/{categoryId}`: Update category by ID.
  - `/api/categories/{categoryId}`: Delete category by ID.

- **Comment Endpoints**:
  - `/api/comments/{postId}`: Get all comments for a post.
  - `/api/comments/post/{postId}`: Create a new comment for a post.
  - `/api/comments/{postId}/{commentId}`: Update comment for a post by ID.
  - `/api/comments/{postId}/{commentId}`: Delete comment for a post by ID.

## Authorization

- Admin role (`ADMIN_ROLES`) is required for accessing endpoints related to users.
- User role (`USER_ROLES`) is required for accessing endpoints related to posts, categories, comments, and retrieving user details.

## License

This project is licensed under the [MIT License](LICENSE).

