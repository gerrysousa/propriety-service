# propriety-service

How to run the project:
- Run the command below inside the root folder to create a jar file:

```
mvnw package && java -jar target/propriety-service-0.1.0.jar
```

- Run the command below to create the containers Web-service and DB:
```
docker-compose up --build
```

The project will starts on url: 
```
http://localhost:4000
```