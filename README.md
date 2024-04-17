
# Blog Application

This is a Spring Boot application for managing blog posts, categories, comments, and users.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

- User authentication and authorization using JWT tokens.
- CRUD operations for managing blog posts, categories, comments, and users.
- Role-based access control for different endpoints.
- Exception handling for invalid requests and missing resources.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Hibernate
- JWT (JSON Web Tokens)
- mySql Database 

## Getting Started

To get started with this project, follow these steps:

1. Clone this repository to your local machine.
2. Open the project in your preferred IDE.
3. Configure your database settings in `application.properties`.
4. Run the application.

## Usage

Once the application is running, you can interact with it using RESTful API calls. You can use tools like Postman or Swagger UI to test the endpoints.

## Endpoints

The application exposes the following endpoints:

- `/api/users`: Endpoint for managing users (register, login, get all users).
- `/api/categories`: Endpoint for managing categories (get all categories, get category by ID, create category, update category, delete category).
- `/api/posts`: Endpoint for managing posts (get all posts, get post by ID, create post, update post, delete post).
- `/api/comments`: Endpoint for managing comments (get all comments by post ID, create comment, update comment, delete comment).

Each endpoint supports various HTTP methods (GET, POST, PUT, DELETE) for performing CRUD operations.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please create a GitHub issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
