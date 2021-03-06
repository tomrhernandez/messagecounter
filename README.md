# Getting Started
This is a demo project illustrating a single REST endpoint.
It is equipped with an in-memory database (H2) for ease-of-distribution.

# H2 Database
The H2 Database has the following implications:
1. Documents (messages) are stored during the lifecycle of this application.
2. If this application is restarted the database is wiped.

Despite the implications I chose an H2 Database for the following reasons:
1. Ease of distribution.
2. Code demonstrates the same strategies as if a database server is used.

# Running this Application
An executable .JAR file has been prepared with the current compilation of the app. It was compiled using Java 11.

To run this execute the following command from the project directory

`java -jar messagecounter-0.0.1-SNAPSHOT.jar`

Alternatively, to run with Maven execute the following command from the project directory

`mvn spring-boot:run`

Verify this application is running by visiting the root URL

`localhost:8080`

# Using this Application
With the app running, you can send a POST request to this application on

`localhost:8080/document`

The body should be a JSON document containing an `id` field and a `message` field. Ex:
```
{
	"id":"1228",
	"message":"Hello world"
}
```

This application complies with the following rules:
1. The endpoint will return a JSON document with a single field "count" that contains the total number of words contained in all the messages received to that point. For example, if the first message contains 3 words it would respond with "count = 3". If the next message contains 5 words it would respond with "count = 8".
2. The service will ignore messages with duplicate ids. (i.e. ids that have already been processed).

An example response from this application would look like this
```
{
    "count": 3
}
```

# Building this Application
Should you need to build the app, execute the following command from the project directory
`mvn clean package`