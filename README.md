# Trading_Application

## Requirements

- JDK 11
- Maven

## To run

```
./mvnw spring-boot:run
```

## To test

```
./mvnw test
```

## URL for testing

- To fire a Signal
  ```
  curl --location --request POST 'http://localhost:8080/api/signal/1'
  ```
- To configure Strategy 
    - Create Strategy
      ```
      curl --location --request POST 'http://localhost:8080/api/strategy/4'
      ```

    - Add Steps to the Strategy

      ```
      curl --location --request PUT 'http://localhost:8080/api/strategy/4' \
      --header 'Content-Type: application/json' \
      --data '{
          "step" : "REVERSE",
          "param": 0,
          "value": 0
      }'
      ```

