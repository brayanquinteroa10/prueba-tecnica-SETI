# Se usa la imagen de openjdk 17
FROM openjdk:17

COPY target/PruebaTecnica-0.0.1-SNAPSHOT.jar /app.jar

# Se expone el puerto 8080
EXPOSE 8080

# Se ejecuta el comando para correr el jar
CMD ["java", "-jar", "/app.jar"]



