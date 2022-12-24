FROM openjdk:8-jdk-slim
COPY "./out/artifacts/arqui_spring_pets_backend_jar/arqui_spring_pets_backend.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

