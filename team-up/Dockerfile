FROM openjdk:11-jre-slim
WORKDIR  usr/src
ENV MONGO_URI=mongodb://localhost:27017/teamup_db
ADD ./target/team-up-0.0.1-SNAPSHOT.jar /usr/src/team-up-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","team-up-0.0.1-SNAPSHOT.jar"]