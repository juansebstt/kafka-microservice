# Kafka Microservice

## Project Description

The **Kafka Microservice** is part of a reactive microservices architecture designed to facilitate event-driven communication between various components of the application. This microservice leverages Apache Kafka as a messaging system to handle game creation events and eligibility processing efficiently. By using a pipeline approach, the microservice ensures that events are processed asynchronously, providing a responsive and scalable solution.

This application is built with **Spring Boot**, following modern software development practices to promote clean code, maintainability, and scalability. The microservice is designed to work seamlessly with three other services—**Game Service API**, **API Gateway Service**, and **Authentication API**—to form a cohesive system that responds to game-related events in real-time.

## Design Pattern

This microservice implements the **Hexagonal Design Pattern** (also known as Ports and Adapters) to ensure a clean separation between the application's core logic and external dependencies. This design pattern allows for easier testing and maintenance by isolating the business logic from the technical concerns of communication with external systems like databases and messaging services.

In this pattern:

- **Adapters** handle the communication between the application and the outside world (e.g., Kafka brokers).
- **Ports** define the interfaces that the core business logic uses to interact with external systems, ensuring that changes in external dependencies do not affect the core functionality.

By employing this pattern, the Kafka microservice achieves high modularity, making it easier to adapt to future changes or integrations.

## Prerequisites

To successfully run this Kafka microservice and test it with the other services, ensure you have the following prerequisites:

1. **Java 17** installed on your machine.
2. **Apache Kafka** installed and running. Follow the instructions to set up Kafka:
    - Start Zookeeper:

        ```bash
        bin/zookeeper-server-start.sh config/zookeeper.properties
        ```

    - Start Kafka:

        ```bash
        bin/kafka-server-start.sh config/server.properties
        ```

3. **Offset Explorer** installed to observe Kafka topics and events.
4. The following microservices should be installed and running:
    - **[Game Service API](https://github.com/juansebstt/game-api)**
    - **[API Gateway Service](https://github.com/juansebstt/api-gateway)**
    - **[Authentication API](https://github.com/juansebstt/auth-service)**

## Setup Instructions

1. Clone the repository:

    ```bash
    git clone <https://github.com/juansebstt/kafka-microservice>
    cd kafka-microservice
    
    ```

2. Ensure your PostgreSQL database is set up correctly and accessible using the credentials provided in `application.yml`.
3. Build and run the application:

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

4. Ensure all prerequisite microservices are running.

## Configuration

The microservice configuration is defined in `application.yml`. Key configurations include:

```yaml
server:
  port: 8084

spring:
  data:
    r2dbc:
      repositories:
        enabled: true
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/game_db
    username: postgres
    password: 1234
  cloud:
    function:
      definition: gameCreatedBinding
    stream:
      default-binder: kafka_default
      bindings:
        gameCreatedBinding-in-0:
          destination: event.game-created
          binder: kafka_default
          useNativeDecoding: true
        gameCreatedBinding-out-0:
          destination: event.game-eligible
          binder: kafka_default
          useNativeDecoding: true
      binders:
        kafka_default:
          type: kafka
          environment:
            spring:
              cloud:
                stream:
                  kafka:
                    binder:
                      brokers: localhost:9092

```

- Ensure that the database configuration matches your PostgreSQL setup.

## Running Kafka

To start the Kafka services on your machine, you can create scripts for convenience:

### Run Kafka

1. Create a file named `start-kafka.sh` with the following content:

    ```bash
    #!/bin/bash
    bin/zookeeper-server-start.sh config/zookeeper.properties &
    bin/kafka-server-start.sh config/server.properties 
    ```

2. Make the script executable:

    ```bash
    chmod +x start-kafka.sh
    ```


### Stop Kafka

1. Create a file named `stop-kafka.sh` with the following content:

    ```bash
    #!/bin/bash
    bin/kafka-server-stop.sh
    bin/zookeeper-server-stop.sh
    ```

2. Make the script executable:

    ```bash
    chmod +x stop-kafka.sh
    ```


### Observing Events in Offset Explorer

1. Open Offset Explorer and connect to your Kafka instance running on `localhost:9092`.
2. Subscribe to the following topics to observe events:
    - `event.game-created`
    - `event.game-eligible`
    - `event.file-started`
3. Use the other services to send events to Kafka, and monitor the changes in Offset Explorer.

### Example Test Workflow

1. Start Kafka and PostgreSQL using the provided scripts.
2. Run all microservices (Kafka microservice, Game Service API, API Gateway Service, and Authentication API).
3. Use Postman to send a request to create a game.
4. Use Offset Explorer to observe the events generated in the Kafka topics.

## Testing the Application

To test the Kafka microservice:

1. **Start the dependent microservices**: Make sure the Game Service API, API Gateway Service, and Authentication API are up and running.
2. **Send test events**: You can use a tool like Postman or cURL to send a POST request to the `/game-created` endpoint of the Kafka microservice with a sample payload. For example:

    ```json
    {
        "id": 1,
        "name": "Sample Game",
        "userId": "user123"
    }
    ```

   The endpoint to trigger this event should be defined in the Game Service API.

3. **Check the logs**: Monitor the logs of the Kafka microservice for processing events and any potential errors.

## Kafka Configuration

This microservice uses Kafka to handle events. The relevant Kafka settings are defined in the `application.yml` file. Ensure the Kafka broker is reachable at the specified address (`localhost:9092`).

## Usage

After running the application and sending test events, the microservice processes the events and publishes them to the `event.game-eligible` topic. You can configure additional bindings as needed for further event processing.

## Conclusion

This Kafka microservice serves as a critical component in an event-driven architecture, providing an efficient means of processing game-related events. The Hexagonal Design Pattern enhances the maintainability and testability of the service, allowing it to adapt to future requirements easily.

## Created By
**Juan Sebastian Ibarra**