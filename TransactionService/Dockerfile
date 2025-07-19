# Utilise une image Maven officielle pour compiler l'application
# Étape de build
FROM maven:3.8.4-openjdk-17 AS build

# Définit le répertoire de travail pour le build
WORKDIR /app

# Copie le fichier pom.xml et les dépendances
COPY pom.xml .

# Télécharge les dépendances Maven
RUN mvn dependency:go-offline -B

# Copie le reste des fichiers de l'application
COPY src ./src

# Compile l'application
RUN mvn clean package -DskipTests

# Étape d'exécution
FROM openjdk:17-jdk-slim

# Définit le répertoire de travail pour l'exécution
WORKDIR /app

# Copie le fichier JAR de l'étape de build
COPY --from=build /app/target/TransactionService-0.0.1-SNAPSHOT.jar app.jar

# Définit le port sur lequel l'application écoutera les connexions
EXPOSE 8080

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
