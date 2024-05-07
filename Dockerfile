From openjdk:17-jdk-alpine
ARG JAR_FILE =target/*.jar
COPY ./target/staffregistration-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["jar","-jar","app.jar"]