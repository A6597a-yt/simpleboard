# 1. Java 21 이미지 사용
FROM eclipse-temurin:21-jdk AS builder

# 2. 작업 디렉토리
WORKDIR /app

# 3. 프로젝트 전체 복사
COPY . .

# 4. Gradle build 실행
RUN ./gradlew clean build -x test

# 5. 실행 이미지 (slim)
FROM eclipse-temurin:21-jre

WORKDIR /app

# 6. 빌드된 jar 복사 (이름 자동 검색)
COPY --from=builder /app/build/libs/*.jar app.jar

# 7. 포트 열기
EXPOSE 8080

# 8. Spring Boot 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
