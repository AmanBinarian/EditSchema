FROM eclipse-temurin:17

LABEL mentainer="aman.kumar@binarysemantics.com"

WORKDIR /app

COPY target/Edit_Schema-0.0.1-SNAPSHOT.jar /app/Edit_Schema.jar

ENTRYPOINT [ "java", "-jar", "Edit_Schema.jar" ]