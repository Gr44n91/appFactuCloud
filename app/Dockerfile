# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY target/app-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto en el que la aplicación está escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicia el contenedor
CMD ["java", "-jar", "app.jar"]
