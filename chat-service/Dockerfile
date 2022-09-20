FROM openjdk:11-jre-slim
WORKDIR  usr/src
ENV MONGO_URI=mongodb://34.201.191.117:27017/chat_db
ADD ./target/chat-service-0.0.1-SNAPSHOT.jar /usr/src/chat-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","chat-service-0.0.1-SNAPSHOT.jar"]