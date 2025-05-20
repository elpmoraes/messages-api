# Message API

REST API developed in Grails to manage messages.

## Prerequisites
- Java 11 or higher installed.

## How to Run
1. Download the `messages-api.jar` file.
2. Run the command:
   ```bash
   java -jar messages-api.jar
   ```
3. The API will be available at `http://localhost:8080`.

## Endpoints
- **POST /messages**: Creates a new message.
    - Example payload:
      ```json
      {
          "content": "This is a test message with more than 30 characters",
          "author": "John Doe"
      }
      ```

  - Expected Response:
    - Status 201 Created
      ```json
          {
          "id": 1,
          "createdAt": "2025-05-20T20:46:39Z",
          "content": "This is a test message with more than 30 characters",
          "author": "Felipe"
          }
      ```
- **GET /messages**: 
  - Returns all messages
    - In reverse chronological order,
    - with `contentPreview` (30 characters + "...").
       - Example response:
         ```json
          [
          {
              "id": 3,
              "content": "This is another test message with more than 30 characters",
              "author": "Felipe Moraes",
              "createdAt": "2025-05-20T21:21:13Z",
              "contentPreview": "This is a second test message ..."
          },
             {
              "id": 2,
              "content": "This is a test message with more than 30 characters",
              "author": "Felipe",
              "createdAt": "2025-05-20T21:20:18Z",
              "contentPreview": "This is a test message with mo..."
          },
             {
              "id": 1,
              "content": "This is a test message",
              "author": "Felipe",
              "createdAt": "2025-05-20T21:14:33Z",
              "contentPreview": "This is a test message"
          }
          ]
         ```
- **GET /messages/summary**: Returns the total number of messages and the average message length (limited to 4.
  - Example response:
    ```json
    {
        "totalMessages": 3,
        "averageMessageLength": 43.3333
    }
    ```

## Testing
- Use tools like Postman/Insomnia or cURL to test the endpoints.
- Example with cURL:
  ```bash
  curl -X POST http://localhost:8080/messages -H "Content-Type: application/json" -d "{'content':'This is a test message','author':'Kevin'}"
  ```
  ```bash
  curl http://localhost:8080/messages
  ```
  ```bash
  curl http://localhost:8080/messages/summary
  ```
  
## Unit Test
- run the command:
  ```bash
  ./gradlew test
  ```
  
## Source Code
- The source code is in the `message-api-source.zip` file.
- Structure:
    - `grails-app/domain/com/moraes/Message.groovy`: Message model.
    - `grails-app/services/com/moraes/MessageRepository.groovy`: In-memory repository.
    - `grails-app/controllers/com/moraes/MessageController.groovy`: REST controller.
    - `grails-app/controllers/com/moraes/UrlMappings.groovy`: URL mappings.
    - `src/test/groovy/com/moraes/MessageSpec.groovy`: Unit tests for the message entity.
    - - `src/test/groovy/com/moraes/MessageRepositorySpec.groovy`: Unit tests for the message repository.

## Notes
- The createdAt field is automatically set to the current date and time when the message is created.
- The messages are sorted in reverse chronological order based on the createdAt field.
- The ID is auto-incremented.
- Messages are stored in memory and lost when the application restarts.
- To calculate the averageMessageLength, BigDecimal was used to ensure greater precision, with results limited to 4 decimal places.
- To save space in the executable and stay within scope, I chose not to use external databases or H2. Instead, I implemented a repository that simulates database persistence using an in-memory List.
- I opted for a synchronizedList to ensure thread safety for concurrent operations.
- I decided to maintain the pages as index.gsp and error.gsp.
- Content and Author are required fields.
- The content field and author field is limited to 255 characters and a minimum of 1 character.
- The author field only accepts letters and spaces.
- The summary endpoint returns the total number of messages and the average message length, calculated as the sum of all message lengths divided by the total number of messages.
- The average message length is rounded to 4 decimal places.
- The project uses Grails 5.3.2 and Gradle.








