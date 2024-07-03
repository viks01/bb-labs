# Stage 1: Build the application with a JDK
FROM eclipse-temurin:21-jdk-alpine as build

# Set the working directory in the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle /app/

# Copy the gradle wrapper files
COPY gradlew /app/gradlew
COPY gradle /app/gradle

# Grant execution rights to gradlew
RUN chmod +x ./gradlew

# Download the dependencies (this will also trigger the gradle wrapper download)
RUN ./gradlew build -x test --no-daemon

# Copy the source code
COPY src /app/src

# Build the application
RUN ./gradlew build -x test --no-daemon

# Stage 2: Run the application with a JRE
FROM eclipse-temurin:21-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built application from the previous stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the port that the application runs on
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
