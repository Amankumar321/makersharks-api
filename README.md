# Makersharks API

## Overview

This project provides a RESTful API for searching manufacturers based on customized requirements. Built with Spring Boot, it includes endpoints to query suppliers by location, nature of business, and manufacturing processes.

## Docs

http://localhost:8080/swagger-ui/index.html

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17** or higher
- **Maven** or **Gradle** (depending on your build tool)
- **Database** (H2, PostgreSQL, MySQL, etc. depending on your configuration)

## Getting Started

### Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/Amankumar321/makersharks-api.git
cd makersharks-api
```

### Configuration

1. **Database Configuration**:
   Update the `application.properties` or `application.yml` file in the `src/main/resources` directory to configure your database settings. Example configuration for H2 database:

   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   ```

   For other databases, update the URL, username, password, and dialect accordingly.

2. **Application Properties**:
   You can customize other properties such as server port, logging level, etc., in `application.properties` or `application.yml`.

### Building the Application

**With Maven**:

```bash
mvn clean install
```

**With Gradle**:

```bash
gradle build
```

### Running the Application

**With Maven**:

```bash
mvn spring-boot:run
```

**With Gradle**:

```bash
gradle bootRun
```

### Testing the Application

You can run unit tests using Maven or Gradle:

**With Maven**:

```bash
mvn test
```

**With Gradle**:

```bash
gradle test
```

### API Endpoints

Here are the key endpoints available in the application:

- **Search Suppliers**: `POST /api/supplier/query`
  - **Request Body**:
    ```json
    {
      "location": "India",
      "natureOfBusiness": "small_scale",
      "manufacturingProcesses": "3d_printing",
      "page": 0,
      "size": 10
    }
    ```
  - **Response**:
    ```json
    {
      "content": [
        {
          "supplierId": 1,
          "companyName": "Company A",
          "website": "www.companya.com",
          "location": "India",
          "natureOfBusiness": "small_scale",
          "manufacturingProcesses": "3d_printing"
        }
      ],
      "pageable": {
        "sort": {
          "empty": true,
          "sorted": false,
          "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
      },
      "totalPages": 1,
      "totalElements": 1,
      "last": true,
      "size": 10,
      "number": 0,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "numberOfElements": 1,
      "first": true,
      "empty": false
    }
    ```

### Error Handling

The application uses a global exception handler to manage errors:

- **Resource Not Found**: Returns `404 Not Found` for missing resources.
- **Generic Error**: Returns `500 Internal Server Error` for unexpected errors.