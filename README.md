# Simple Banking

This is a Gradle project using Spring Boot and PostgreSQL. The project provides RESTful APIs and is documented using Swagger. Additionally, it has been tested using JUnit.

## Getting Started

Follow these steps to run the project on your local machine.

### Prerequisites

- JDK 17
- Docker
- Gradle

### Installation

1. Clone the Project:
    ```bash
    git clone https://github.com/berkanfiliz/simplebanking.git
    cd simplebanking
    ```

2. Configure the Database using Docker Compose:
   - Ensure Docker is installed and running on your machine.
   - Use the provided `docker-compose.yml` file to start the PostgreSQL database:
     ```bash
     docker-compose up -d
     ```

3. Update `application.yml` if necessary to match the database settings in `docker-compose.yml`.

4. Run the Application:
    ```bash
    ./gradlew bootRun
    ```

   The application runs by default at [http://localhost:8080](http://localhost:8080).

## Usage

The project provides the following features:

- RESTful APIs
- Swagger API Documentation: Accessible at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Postman Collection: Use `SimpleBanking.postman_collection.json` to test the APIs.

## Testing

The project is tested using JUnit. Run the tests with the following command:

```bash
./gradlew test