# Library-Management-System

## Table of Contents
1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Setting Up the Application](#setting-up-the-application)
4. [Running the Application](#running-the-application)
5. [Interacting with API Endpoints](#interacting-with-api-endpoints)
6. [API Endpoints](#api-endpoints)
7. [Authentication](#authentication)
8. [Error Handling and Validation](#error-handling-and-validation)

### Introduction
This documentation provides information on how to set up, run, and interact with the Library Management System API. The application allows for managing books, patrons, and borrowing records.

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Apache Maven
- PostgreSQL database (or any preferred database)
- An IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor

### Setting Up the Application

1. **Clone the Repository:**
   ```bash
   git clone <repository-url>
   cd library-management-system

2. **Configure the Database:**

-Create a database in PostgreSQL (or any preferred database).
-Update the application.properties file located in src/main/resources with your database configuration:

spring.datasource.url=jdbc:postgresql://localhost:5432/librarydb
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update


3. **Build the Application:**
Copy code
mvn clean install

**Running the Application**

1. Start the Application:
2. 
mvn spring-boot:run

-The application will start on http://localhost:8080.

**Interacting with API Endpoints**
You can interact with the API endpoints using tools like Postman or cURL.

**API Endpoints**
Book Management
1.Retrieve all books:

  Endpoint: GET /api/books
  Response: JSON array of all books
  Example Request:
  curl -X GET http://localhost:8080/api/books
  
2.Retrieve a specific book by ID:

  Endpoint: GET /api/books/{id}
  Response: JSON object of the book
  Example Request:
  curl -X GET http://localhost:8080/api/books/1
  
3.Add a new book:

  Endpoint: POST /api/books
  Request Body:
  {
      "title": "Effective Java",
      "author": "Joshua Bloch",
      "isbn": "978-0134685991",
      "publicationYear": 2018
  }
  Example Request:
  curl -X POST -H "Content-Type: application/json" -d '{"title":"Effective     Java","author":"Joshua Bloch","isbn":"978-0134685991","publicationYear":2018}' http://localhost:8080/api/books
  
4.Update an existing book:

  Endpoint: PUT /api/books/{id}
  Request Body:
  {
      "title": "Effective Java - 2nd Edition",
      "author": "Joshua Bloch",
      "isbn": "978-0134685991",
      "publicationYear": 2018
  }
  Example Request:
  curl -X PUT -H "Content-Type: application/json" -d '{"title":"Effective Java - 2nd Edition","author":"Joshua Bloch","isbn":"978-0134685991","publicationYear":2018}' http://localhost:8080/api/books/1
  
5.Delete a book:

  Endpoint: DELETE /api/books/{id}
  Example Request:
  curl -X DELETE http://localhost:8080/api/books/1
  
Patron Management

1.Retrieve all patrons:

  Endpoint: GET /api/patrons
  Response: JSON array of all patrons
  Example Request:
  curl -X GET http://localhost:8080/api/patrons

2.Retrieve a specific patron by ID:
  Endpoint: GET /api/patrons/{id}
  Response: JSON object of the patron
  Example Request:
  curl -X GET http://localhost:8080/api/patrons/1
  
3.Add a new patron:

Endpoint: POST /api/patrons
  Request Body:
  {
      "name": "Alice Johnson",
      "contactInformation": "alice.johnson@example.com"
  }
  Example Request:
  curl -X POST -H "Content-Type: application/json" -d '{"name":"Alice Johnson","contactInformation":"alice.johnson@example.com"}' http://localhost:8080/api/patrons
  
3.Update an existing patron:

  Endpoint: PUT /api/patrons/{id}
  Request Body:
  {
      "name": "Alice Johnson",
      "contactInformation": "alice.johnson@example.com"
  }
  Example Request:
  curl -X PUT -H "Content-Type: application/json" -d '{"name":"Alice Johnson","contactInformation":"alice.johnson@example.com"}' http://localhost:8080/api/patrons/1

4.Delete a patron:

  Endpoint: DELETE /api/patrons/{id}
  Example Request:
  curl -X DELETE http://localhost:8080/api/patrons/1

Borrowing Endpoints
1.Borrow a book:

  Endpoint: POST /api/borrow/{bookId}/patron/{patronId}
  Example Request:
  curl -X POST http://localhost:8080/api/borrow/1/patron/1
  
2.Return a book:

  Endpoint: PUT /api/return/{bookId}/patron/{patronId}
  Example Request:
  curl -X PUT http://localhost:8080/api/return/1/patron/1
