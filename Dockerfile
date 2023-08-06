FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar e-commerce.jar
ENTRYPOINT ["java","-jar","/e-commerce.jar"]
EXPOSE 8080