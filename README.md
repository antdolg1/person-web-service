# **Person Web Service**

It is a simple Spring Boot application that demonstrates basic CRUD operations on a Person entity. The application uses a H2 database and Logback for logging. This application can be used as a starting point for building more complex web services in the future.
The application was created as a test task.

## Technologies used
- Java 17
- Spring Boot
- Hibernate
- MapStruct
- H2 Database
- Logback
- Lombok
- JUnit

## Features
The **_person-web-service_** application allows you to perform basic CRUD operations on a **Person** entity. The following endpoints are available:
- `GET /persons`: Returns a list of all **Person** entities.
- `POST /persons`: Creates a new **Person** entity.
- `GET /persons/name/{name}`: Returns a list of **Person** entities that have a first name that matches the given **name**.
- `GET /persons/birth-date/{dateOfBirth}`: Returns a list of **Person** entities that have a date of birth that matches the given **date**.
- `PUT /persons/{id}`: Updates the **Person** entity with the given **ID**.
- `DELETE /persons/{id}`: Deletes the **Person** entity with the given **ID**.

## Logging
The application uses Logback as the logging framework, and logs are stored in a file named `person-web-service.log`. When the log file exceeds 10 MB in size, it is automatically archived and a new log file is created.
## Database
The application uses the H2 database, which is an in-memory database. The database schema is created automatically when the application is started.

## Setup
1. Clone the repository
2. Open the project in your preferred IDE
3. Run the `PersonWebServiceApplication` class to start the application
4. Run the application
5. The application will be available at http://localhost:8080