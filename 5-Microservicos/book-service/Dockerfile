# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim
# Copy the application JAR file into the container
COPY target/*.jar app.jar
#Creates an empty file named app.jar in the root directory (/) if it does not already exist.
#Updates the file's last modified timestamp if the file already exists.
RUN bash -c 'touch /app.jar'
# Specify the command to run the application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]