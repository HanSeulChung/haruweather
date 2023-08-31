# haruweather

## 프로젝트 엔티티
Diary : id, text, DateWeather의 날씨 정보(Temperature, Weather, icon, date)가 저장된 엔티티

DateWeather : Open Api로 매일 새벽 1시마다 그 날의 날씨정보(Temperature, Weather, icon, date)를 가져와서 저장된 엔티티


## 제공하는 기능(API)

### 다이어리(Diary) 관련 기능

<ol>
<li>다이어리 생성</li>
<li>다이어리 조회</li>
<li>다이어리 수정</li>
<li>다이어리 삭제</li>
</ol>

실행 후 http://localhost:8080/swagger-ui/index.html# url을 이용하면 API에 따른 더 많은 정보를 확인할 수 있습니다.

## 활용 기술

<li> Spring boot 2.7.4 </li>
<li> JDK : Oracle OpenJDK 20.0.1 </li>
<li> Gradle </li>
<li> JAVA 17 </li>
<li> Junit5 </li>
<li> mysql </li>
<li> JPA </li>
<li> Lombok </li>
<li> Swagger 3.0.0 </li>
