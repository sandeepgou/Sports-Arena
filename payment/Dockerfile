FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/payment-0.0.1-SNAPSHOT.jar /usr/src/payment-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","payment-0.0.1-SNAPSHOT.jar"]
