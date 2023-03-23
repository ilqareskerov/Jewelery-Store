FROM openjdk:11 AS build


COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package


FROM openjdk:11
WORKDIR jewelrystore
COPY --from=build target/jewelrystore.jar jewelrystore.jar
ENTRYPOINT ["java", "-jar", "jewelrystore.jar"]