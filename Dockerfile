FROM gradle:8.7-jdk17 AS build
WORKDIR /app

# Əvvəlcə gradlew-ı copy et və icazə ver
COPY gradlew ./
RUN chmod +x gradlew

# Sonra build.gradle və settings.gradle
COPY build.gradle settings.gradle ./

COPY gradle ./gradle

# Dependencies
RUN ./gradlew dependencies --no-daemon

# Sonra qalan kodu copy et
COPY . .

# BootJar build
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
