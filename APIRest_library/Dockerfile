FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/apirestlibrary-0.0.1.jar
COPY ${JAR_FILE} app_apiLibrary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_apiLibrary.jar"]
