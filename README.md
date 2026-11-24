
# Quiz Application — Java Web Application

A Java web application for managing quizzes, questions,
users, and test results.

The project demonstrates backend development with Java,
Jakarta Servlets, JSP, PostgreSQL, and a layered
application architecture.

## Features

The application includes pages and components for:

- User registration and ID-based session selection (academic demo)
- Quiz and test management
- Adding and listing questions
- Test selection and quiz participation
- Displaying quiz results
- User management
- Statistics and search interfaces

## Technology Stack

| Category | Technologies |
|---|---|
| Language | Java 11 |
| Backend | Jakarta Servlet |
| Frontend | JSP, JSTL, HTML, CSS |
| Database | PostgreSQL |
| Database Access | JDBC |
| Application Server | Apache Tomcat 10 |
| Build Tool | Apache Maven |
| Packaging | WAR |
| Logging | SLF4J |

## Project Architecture

The application uses a layered architecture to separate
database access, business logic, and HTTP request handling.

    src/main/
    ├── java/
    │   ├── config/
    │   ├── db/
    │   ├── infr/
    │   ├── model/
    │   ├── repository/
    │   ├── service/
    │   ├── servlet/
    │   └── util/
    └── webapp/
        ├── css/
        ├── WEB-INF/
        ├── login.jsp
        ├── register.jsp
        ├── quiz.jsp
        ├── results.jsp
        ├── statistics.jsp
        └── ...

## Technical Highlights

- Layered application structure
- HTTP request handling with Jakarta Servlets
- Dynamic web pages using JSP and JSTL
- PostgreSQL integration through JDBC
- Separation of data access and business logic
- Maven-based project configuration
- Database credentials loaded from environment variables

## Getting Started

### Prerequisites

- JDK 11 or compatible
- Apache Maven
- PostgreSQL
- Apache Tomcat 10

### Database Configuration

Configure the following environment variables:

| Variable | Description |
|---|---|
| DB_URL | PostgreSQL JDBC connection URL |
| DB_USER | PostgreSQL username |
| DB_PASSWORD | PostgreSQL password |

Example JDBC URL:

    jdbc:postgresql://localhost:5432/quizdb

The PostgreSQL database and required tables must be
created separately.

### Build

Clone the repository and run:

    mvn clean package

The build should generate a WAR file in the `target`
directory when compilation succeeds.

### Deployment

Deploy the generated WAR file to a compatible
Tomcat server.

Ensure that the database environment variables are
available to the Tomcat process.

## Security Limitations

This is an academic demonstration project, not a
production-ready application.

The current login mechanism identifies users by their
database ID without password authentication.

It must not be used for real-world authentication or
deployed publicly with sensitive user information.

Future improvements include secure password hashing,
proper authentication, authorization checks, and
stronger input validation.


## Project Status
## Project Status

Academic software engineering project.

The project compiles successfully with Maven and
generates a WAR file.

Database initialization and runtime compatibility
with PostgreSQL and Tomcat have not yet been verified.

No automated tests are currently included.

## Author

Orphée Axel Oladjouhon BADA

Software Engineering Graduate

GitHub: https://github.com/axelorphee
