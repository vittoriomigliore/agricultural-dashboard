# Use the OpenJDK 17 Slim image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the build files (e.g., build.gradle, settings.gradle, src, etc.)
COPY . .

# Make the gradlew script executable
RUN chmod +x gradlew

# Build the project
RUN ./gradlew build

# Copy the built JAR file to the /app directory
COPY build/libs/agricultural-dashboard-0.0.1-SNAPSHOT.jar /app/agricultural-dashboard.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "agricultural-dashboard.jar"]