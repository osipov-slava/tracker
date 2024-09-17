FROM maven:3.6.3-openjdk-17

RUN mkdir tracker

WORKDIR tracker

COPY . .

RUN mvn package -Dmaven.test.skip=true

CMD ["mvn", "liquibase:update", "-Pdocker"]

CMD ["java", "-jar", "target/tracker-1.0-SNAPSHOT.jar"]