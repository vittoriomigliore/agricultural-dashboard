# Build stage
FROM gradle:7.6-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/agricultural-dashboard-0.0.1-SNAPSHOT.jar /app/agricultural-dashboard.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "agricultural-dashboard.jar"]