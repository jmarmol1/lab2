FROM openjdk:18
WORKDIR /app
COPY ./target/JuanMarmolejo-Lab2-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "JuanMarmolejo-Lab2-0.0.1-SNAPSHOT.jar"]