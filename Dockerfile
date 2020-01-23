FROM openjdk:8-jdk-alpine as final
RUN adduser -D barath
USER barath
ADD target/cucumber-spring-integration-*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]