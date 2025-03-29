FROM maven:3.9.9-amazoncorretto-21-alpine AS build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.6-alpine3.21

WORKDIR /bibliotech

EXPOSE 8080

# First parameter means what you're copying 
# Second parameter means where to
COPY --from=build /build/target/bibliotech-0.0.1-SNAPSHOT.jar bibliotech.jar

ENTRYPOINT  ["java", "-jar", "bibliotech.jar"]