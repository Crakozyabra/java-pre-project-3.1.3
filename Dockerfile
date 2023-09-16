# https://spring.io/guides/topicals/spring-boot-docker/
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "-DDB_URL=jdbc:mysql://db:3306/crud?createDatabaseIfNotExist=true&characterEncoding=UTF-8", "/app.jar"]