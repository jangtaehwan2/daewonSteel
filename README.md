# DaeWon Steel
> Spring Initializr
> * Gradle Project
> * OpenJDK@11
> * SpringBoot 2.6.3
>
> 
> library
> * MariaDB
> * JPA
> * Lombok

DaeWonApplication 을 통하여 실행한다.  
기본 포트 설정은 3000이다.

---

# API DOCUMENT

### 공통
Request
* Method : 개별 Api 참고
* Path
  * Host/api/{path}
  * Ex) Login Api PATH /auth/login -> localhost:3000/api/auth/login
* Header
  * Content-type : application/json
  * Authorization : Login Api Return 값 요구
  * Set-Cookie : Server Response Header 값 요구
* Body
    * json type Request
    * 요청 실패시 error page 또는 error message (json)이 반환 될 수 있음
---
## Auth
### 1. 로그인 (Login)
**Description**   

시스템에 로그인을 요청하기 위한 API 이다.
해당 API 의 응답으로 받는 값을 이후 다른 요청의 Header-Authorization 에 넣어 사용한다.
아래의 userPassword 값은 예시로, 해싱 된 값이 입력되도록 한다.  
**추후, response header 에 Json Web Token (JWT) 값이 들어갈 수 있음.**

*Request*  
* HttpMethod : POST
* Path : /auth/login
* Body
```json
{
  "userId" : "user01",
  "userPassword" : "1q2w3e4r!"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "key": "AuthorizationValue"
}
```


### 2. 로그아웃
**Description**  

시스템에 로그아웃을 요청하기 위한 API 이다.
입력 인자 없이 헤더의 값을 분석하여 서버에서 로그아웃 한다.

*Request*
* HttpMethod : POST
* Path : /auth/logout
* Header : Authorization, JWT(예정)
* Body
```json
{
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "status": "200, Logout Success"
}
```
---
## Station
### 1. Station 목록 읽기
**Description**

작업 개소의 목록을 불러온다.

*Request*
* HttpMethod : GET
* Path : /station
* Header : Authorization, JWT(예정)
* Body
```json
{
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "station" : [
    {
      "id" : "1",
      "name" : "햄토리공장"
    },
    {
      "id" : "2",
      "name" : "간장공장공장장"
    }
  ]
}
```
---
## Data
### 1. 데이터 추가
**Description**  

시스템에 데이터를 입력하기 위한 API, 스테이션별 고유 값인 id와 무게, 날짜를 입력 받는다.  
날짜는 "YYYY-MM-DD" 의 포맷을 따른다.  
responseBody 의 값은 해당 데이터 raw 의 고유 값에 해당한다.

*Request*
* HttpMethod : POST
* Path : /data
* Header : Authorization, JWT(예정)
* Body
```json
{
  "stationId" : "1",
  "weight" : "150",
  "date" : "2022-03-01"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "SteelDataId" : "1"
}
```
### 2. 데이터 보기
### *추후 입력 데이터에 따라 기능을 다르게 하여 하나의 API 로 통합 될 가능성이 있음*
   * ### 특정 연도 데이터 불러오기
**Description**   
   
   특정 연도에 해당하는 데이터를 모두 불러온다.

*Request*
* HttpMethod : GET
* Path : /data/year
* Header : Authorization, JWT(예정)
* Body
```json
{
  "year" : "2022"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "data" : [
    {"id" : "1","weight" :  "200", "date" :  "2022-03-01",
      "station" :  { "id" : "1", "name" : "햄토리공장"}},
    {"id" : "2","weight" :  "100", "date" :  "2022-05-05",
      "station" :  { "id" : "1", "name" : "햄토리공장"}},
    {"id" : "3","weight" :  "150", "date" :  "2022-08-15",
      "station" :  { "id" : "2", "name" : "간장공장공장장"}}
  ]
}
```
   * ### 특정 월 데이터 전체 불러오기
**Description**  
   
  특정 연, 월에 해당하는 데이터를 모두 불러온다.

*Request*
* HttpMethod : GET
* Path : /data/month
* Header : Authorization, JWT(예정)
* Body
```json
{
  "year" : "2022",
  "month" : "1"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "data" : [
  {"id" : "1","weight" :  "200", "date" :  "2022-01-01",
    "station" :  { "id" : "1", "name" : "햄토리공장"}},
  {"id" : "2","weight" :  "100", "date" :  "2022-01-13",
    "station" :  { "id" : "1", "name" : "햄토리공장"}},
  {"id" : "3","weight" :  "150", "date" :  "2022-01-18",
    "station" :  { "id" : "2", "name" : "간장공장공장장"}}
  ]
}
```
   * ### 특정 일 데이터 불러오기
**Description**   
   
   특정 연, 월, 일에 해당하는 데이터를 모두 불러온다.   

*Request*
* HttpMethod :GET
* Path : /data/day
* Header : Authorization, JWT(예정)
* Body
```json
{
  "year" : "2022",
  "month" : "1",
  "day" : "17"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "data" : [
    {"id" : "1","weight" :  "200", "date" :  "2022-01-17",
      "station" :  { "id" : "1", "name" : "햄토리공장"}},
    {"id" : "2","weight" :  "100", "date" :  "2022-01-17",
      "station" :  { "id" : "1", "name" : "햄토리공장"}},
    {"id" : "3","weight" :  "150", "date" :  "2022-01-17",
      "station" :  { "id" : "2", "name" : "간장공장공장장"}}
  ]
}
```
### 3. 데이터 수정(관리자)
**Description**

해당하는 자료의 weight, date, station 을 수정한다.  
관리자만 사용 가능하다.  
**Request 데이터와 동일하게 값이 바뀌기 때문에 변경을 원하지 않는 데이터는 기존 값을 그대로 요청하도록 한다.
id 값은 수정이 불가능하다.**

*Request*
* HttpMethod : PUT
* Path : /data
* Header : Authorization, JWT(예정)
* Body
```json
{
  "id" : "1",
  "stationId" : "2",
  "weight" : "2000",
  "date" : "2022-01-01"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "id" : "1",
  "weight" : "2000",
  "date" : "2022-01-01",
  "station" : {
    "id" : "2",
    "name" : "간장공장공장장"
  }
}
```
### 4. 데이터 삭제(관리자)
**Description**

해당하는 자료를 삭제한다.  
관리자만 사용 가능하다.

*Request*
* HttpMethod : DELETE
* Path : /data
* Header : Authorization, JWT(예정)
* Body
```json
{
  "id" : "12"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "message" : "1"
}
```
---

## Admin
#### 이 문서에 해당하는 기능들은 관리자만 사용 가능하며, 관리자는 요청시 Header 를 통해 구분한다.
### 1. 유저 추가
**Description**

유저를 생성한다.  
userPassword 는 해싱된 값이 입력 되어야 한다.  
성공시 유저의 고유 id, userName 을 반환한다.  
**userName 은 로그인 시의 id를 의미하며, 상세한 규칙은 추가바람 (글자 수, 특수문자 제한 등)**

*Request*
* HttpMethod : /admin/user
* Path : POST
* Header : Authorization, JWT(예정)
* Body
```json
{
  "userName" : "user01",
  "userPassword" : "password"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "id" : "1",
  "userName" : "user01"
}
```
### 2. 유저 비밀번호 변경
**Description**

해당하는 유저의 비밀번호를 변경한다.  
유저 스스로 변경하는 기능은 지원하지 않고, 관리자를 통해서만 변경이 가능하므로, 기존 비밀번호를 요구하지 않는다.
변경이 가능한 부분은 password 뿐이다.

*Request*
* HttpMethod : /admin/user
* Path : PUT
* Header : Authorization, JWT(예정)
* Body
```json
{
  "id" : "1",
  "userPassword" : "newPassword"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "id" : "1",
  "userName" : "user01"
}
```
### 3. 유저 리스트 읽기
**Description**

모든 유저의 정보를 가져온다.
성공시 모든 유저의 고유 id, userName 을 반환한다.

*Request*
* HttpMethod : /admin/user
* Path : GET
* Header : Authorization, JWT(예정)
* Body
```json
{
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "user" : [
    {
      "id" : "1",
      "userName" : "user01"
    },
    {
      "id" : "2",
      "userName" : "햄토리"
    },
    {
      "id" : "3",
      "userName" : "Surface Owner"
    }
  ]
}
```
### 4. 유저 삭제
**Description**

해당하는 유저를 삭제한다.  
정상적으로 삭제가 완료된다면, 성공메세지 (1)을 반환한다.

*Request*
* HttpMethod : DELETE
* Path : /admin/user
* Header : Authorization, JWT(예정)
* Body
```json
{
  "id" : "1"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "message" : "1"
}
```
### 5. 스테이션 추가
**Description**

작업 개소를 추가한다.  
성공시 고유 id, name 을 반환한다.  
**수정 및 삭제는 추후 개발 또는 관리자 문의로 작성 예정**

*Request*
* HttpMethod : POST
* Path : /admin/station
* Header : Authorization, JWT(예정)
* Body
```json
{
  "name" : "DE JAVA"
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
  "id" : "3",
  "name" : "DE JAVA"
}
```

---

#API Template
**Description**

*Request*
* HttpMethod : 
* Path : 
* Header : Authorization, JWT(예정)
* Body
```json
{
}
```
*Response*
* Header
  * Content-Type : application/json
* Body
```json
{
}
```