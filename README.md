# PlayerDataService

## Description

PlayerDataService is a Spring Boot microservice designed to serve player data from a CSV file through REST API
endpoints. It provides an efficient way to access player information, offering two main endpoints: one for retrieving
all player data and another for fetching details of a specific player by ID.

## Features

- Load player data from a CSV file.
- REST API to get all players' data.
- REST API to get a specific player's data by ID.
- Unit and integration testing with JUnit and Mockito.

## System Requirements

- Java 17
- Maven (for dependency management and running the application)

## Installation and Running

### Step 2: Maven Dependencies

Navigate to the project directory and run the following command to install the necessary dependencies:

```bash
mvn clean install
```

### Step 3: Running the Application

Run the application using Maven with the following command:

```bash
mvn spring-boot:run
```

### Running as a WAR
To run the application as a WAR file:

Build the WAR file:
```bash
mvn clean package
```
Deploy the generated WAR file (target/player-data-service-0.0.1-SNAPSHOT.war) to a web server like Tomcat.

Start the Tomcat server to run the application.
```bash
java -jar "/path/to/war-file"
```

The service will start and listen on http://localhost:8090.
The csv file is located at data/Player.csv.

Note that the port and csv file is configurable in the application.properties file.

### API Endpoints

Retrieves a list of all players.

```bash
Get All Players: GET /api/players
```

Retrieves a specific player by their ID.

```bash
Get Player By ID: GET /api/players/{playerID}
```

## Testing

The application includes JUnit and Mockito based tests. To run the tests, use the following command:

```bash
mvn test
```