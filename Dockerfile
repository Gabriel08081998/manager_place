FROM openjdk:11-jdk-alpine

COPY target/manager-barber-shop-1.0.0.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./manager-barber-shop-1.0.0.jar"]