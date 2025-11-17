FROM gradle:8.7-jdk17 AS build
WORKDIR /app

# Build.gradle və settings.gradle copy et
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Dependencies
RUN gradle dependencies --no-daemon

# Sonra qalan kodu copy et
COPY . .

# Gradlew-ı ayrıca copy et və icazə ver
COPY gradlew ./
RUN chmod +x gradlew

# BootJar build
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
