# Querydsl
- Spring Boot 3

## 프로젝트 설정
- Gradle 설정
```gradle
dependencies {
    ...
	//Querydsl
	implementation 'com.querydsl:querydsl-jpa:5.1.0:jakarta'
	annotationProcessor "com.querydsl:querydsl-apt:5.1.0:jakarta"
	annotationProcessor "jakarta.annotation:jakarta.annotation-api"
	annotationProcessor "jakarta.persistence:jakarta.persistence-api"
    ...
}
```
- Spring Boot 3.x 인 경우, Querydsl 설정이 필요
```gradle
// querydsl 설정
def querydslSrcDir = 'src/main/generated'
clean {
	delete file(querydslSrcDir)
}
tasks.withType(JavaCompile) {
	options.generatedSourceOutputDirectory = file(querydslSrcDir)
}
```
   - Q class 의 경우 형상관리에서 제외해야 하므로 .gitignore 에 추가
    ```gitignore
        ...
        **/generated/
    ```

