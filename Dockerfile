FROM adoptopenjdk/openjdk14:jdk-14.0.1_7
VOLUME /tmp
ARG JAR_FILE
COPY build/libs/giraffe-blog-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]