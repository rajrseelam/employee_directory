# Employee Directory

This Employee Directory is a RESTful microservice designed to manage employee records. It allows for creating, retrieving, updating, and deleting employee information.

## Technologies Used

- **Spring Boot**: Simplifies the bootstrapping and development of new Spring applications.
- **Maven**: Dependency management and build automation.
- **Spring Data JPA**: Provides repository support for JPA to easily interact with database entities.
- **H2 Database**: In-memory database for quick setup and teardown. (The properties of the application are set for the db to save its instance locally, so that the data does not get lost each time the application terminates.)
- **Swagger/OpenAPI**: For API documentation and interaction.
- **JUnit/Mockito**: For unit and integration testing.
- **Spring Security**: For securing REST endpoints.

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17 or later
- Maven 3.8+

### Installing

1. **Clone the repository**

```bash
git clone https://github.com/rajrseelam/employee-directory.git
cd employee-directory
```

2. **Run the application**

#### With Maven:

```bash
mvn spring-boot:run
```

You can also run all the available tests and then start the application by using the below command:

```bash
mvn clean verify && mvn spring-boot:run
```

The application will start running at http://localhost:8080.


#### Using the Application:


- Open the project using Visual Studio Code and try running the above command in terminal or use Spring Boot Dashboard extension to run the application.

After starting the application, you can interact with the API through the Swagger UI:

- Access the Swagger UI at http://localhost:8080/swagger-ui.html
- This interface provides a comprehensive guide to the API's endpoints, including the ability to try out requests directly through the browser.

#### Running the tests

   Run the following command to execute the unit and integration tests:

```bash
mvn test
```

3. **Accessing the End-points using Postman**

Once the application starts, all the endpoints can be accessed using Postman. For eg, http://localhost:8080/{id}
- I have also added a authentication feature to access the end-points by simply creating user and passoword in application.properties.

   ```bash
      spring.security.user.name=rajseelam
      spring.security.user.password=test123
   ```

- So these credentials have to be provided while trying to access the end-points through postman authorization (Type: BasicAuth)




