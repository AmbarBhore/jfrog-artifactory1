FROM openjdk:17-jdk
COPY target/rmm-agent-3.1.jar /app/rmm-agent.jar
ENTRYPOINT ["java", "-jar", "/app/rmm-agent.jar"]
