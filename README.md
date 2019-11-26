# Kotlin Spring MVC
Demo project demonstrating the use of Kotlin, Spring MVC and Mustache to build a mock blog on the subject of 
[giraffes](https://en.wikipedia.org/wiki/Giraffe). JUnit Jupiter is used for building the testing framework. 

## Running locally
```
./gradlew build
java -jar build/libs/giraffe-blog-0.1.0.jar
```

## Running on Docker
```
docker build -t "kotlin-spring-mvc" .
docker run -p 8080:8080 kotlin-spring-mvc
```

### Support Kapt in IntelliJ IDEA
```
./gradlew kaptKotlin
```

### Resources
* Home page: http://localhost:8080
* Article: http://localhost:8080/article/west-african-giraffe

### References
Based upon: https://spring.io/guides/tutorials/spring-boot-kotlin