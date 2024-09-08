# Use an official OpenJDK runtime as a base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /task-manager

# Copy the JAR file from the host machine to the container
COPY target/task_manager-0.1.jar /task-manager/task_manager-0.1.jar

# Expose the port your Spring Boot application is running on
EXPOSE 8081

# Command to run the application
CMD ["java", "-jar", "task_manager-0.1.jar"]

