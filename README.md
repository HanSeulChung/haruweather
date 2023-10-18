# haruweather

## 프로젝트 엔티티
Diary : id, text, DateWeather의 날씨 정보(Temperature, Weather, icon, date)가 저장된 엔티티

DateWeather : Open Api로 매일 새벽 1시마다 그 날의 날씨정보(Temperature, Weather, icon, date)를 가져와서 저장된 엔티티


## 제공하는 기능(API)

### 다이어리(Diary) 관련 기능
- **date의 형식은 모두 yyyy-MM-dd를 따릅니다.**
- **모든 경우에서(생성, 조회, 삭제, 수정) 너무 오래전 date나 너무 먼 미래의 date는 사용할 수 없습니다**
<ol>
<li>다이어리 생성</li>
  parameter > date, text
  
<li>다이어리 조회</li>
<ul>
  <li>단일 날짜에 대한 다이어리 조회 기능</li>
  parameter > date
  <li>여러 날짜에 대한 다이어리 조회 기능</li>
  parameter > startDate, endDate
</ul>
<li>다이어리 수정</li>
  parameter > date, text
<li>다이어리 삭제</li>
  <ul>
  <li>해당 날짜에 대한 특정 id에 대한 다이어리 삭제 기능</li>
  parameter > date, id
  <li>해당 날짜에 쓰여져 있는 모든 다이어리 삭제 기능</li>
  parameter > date
</ul>
</ol>

실행 후 http://localhost:8080/swagger-ui/index.html# url을 이용하면 API에 따른 더 많은 정보를 확인할 수 있습니다.

## 활용 기술
<div align=center>

![Windows](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)
![IntelliJ IDEA 2023.1.4](https://img.shields.io/badge/IntelliJ_IDEA_2023.1.4-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
</br>
![Spring Boot 2.7.4](https://img.shields.io/badge/Spring_Boot_2.7.4-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![JDK - Oracle OpenJDK 20.0.1](https://img.shields.io/badge/JDK-Oracle_OpenJDK_20.0.1-007396?style=for-the-badge&logo=java&logoColor=white)
![Java 17](https://img.shields.io/badge/Java_17-007396?style=for-the-badge&logo=java&logoColor=white)
</br>
![JUnit 5](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=jpa&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC4520?style=for-the-badge&logo=lombok&logoColor=white)
![Swagger 3.0.0](https://img.shields.io/badge/Swagger_3.0.0-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)


  
</div>
