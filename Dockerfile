# Build stage using JDK 21 and Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY student-portal/ .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]