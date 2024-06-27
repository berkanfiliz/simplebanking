# Simple Banking

This is a Gradle project using Spring Boot and PostgreSQL. The project provides RESTful APIs and is documented using Swagger. Additionally, it has been tested using JUnit.

## Getting Started

Follow these steps to run the project on your local machine.

### Prerequisites

- JDK 17
- PostgreSQL
- Gradle

### Installation

1. Clone the Project:
    ```bash
    git clone https://github.com/berkanfiliz/simplebanking.git
    cd simplebanking
    ```

2. Configure the Database:
    - Install and run PostgreSQL.
    - Update `application.yml` with your database connection settings.

3. Run the Application:
    ```bash
    ./gradlew bootRun
    ```

   The application runs by default at http://localhost:8080.

## Usage

The project provides the following features:

- RESTful APIs
- Swagger API Documentation: Accessible at http://localhost:8080/swagger-ui/index.html
- Postman Collection: Use `SimpleBanking.postman_collection.json` to test the APIs.

## Testing

The project is tested using JUnit. Run the tests with the following command:

```bash
./gradlew test
```