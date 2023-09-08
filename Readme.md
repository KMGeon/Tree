
## 애즈위메이크 백엔드 신입 개발자 채용 - 김무건

## 기술 스택

1. Spring Boot 2.7.15
2. JPA
3. MySQL 8.0.22
4. JUnit5, Mockito

---


## 서비스 요구사항

- [x] 마트 권한과 일반 사용자 권한이 구분되어있다. 
- [x] 상품에 대한 생성, 수정, 삭제는 마트 권한이 필요하다. 
- [x] 주문에 대한 총 금액을 계산할 수 있다. 
- [x] 특정 시점의 상품 가격을 조회할 수 있다.
- [x] 상품을 삭제할 수 있다.
- [x] 주문에 대한 필요 결제 금액을 계산할 수 있다.
- [x] Spring Security 권한 / JWT
 
---

## 프로젝트 구조도
```bash
src
├── global 
│   ├── exception # 도메인별 에러 정의
│   ├── config
│   ├── argument
│   ├── initializer
│   └── jwt 
│ 
└── domain   
         ├── controller # 도메인 컨트롤러
         │        └── dto # requst, response Dto
         │        └── exceptionHandler # RestControllerAdvice
         └── entity # 엔티티
         └── service #  application
         └── repostiory # repository
```

---

## 실행 방법

### 1. 프로젝트 CLONE
```git
$ git clone
```

### 2. docker-compose CLI
```docker
$ docker-compose -f docker-compose-local.yml up
```
### 3. 프로젝트 실행

- ``application.yml``은 개별 IDE 변수로 변경하지 않고 바로 실행이 가능하도록 작성

<br/>

## API 명세서
- [Postman API 명세서 Link](https://documenter.getpostman.com/view/23650109/2s9YBz3vJA)

<br/>


## 초기 데이터

> Member

| id | email                  | password                                                     | ROLE        |
|----|------------------------|--------------------------------------------------------------|-------------|
| 1  | market@market.com      | market@market.com                                                          | ROLE_MARKET |
| 2  | test1234@test.com      | test1234! | ROLE_USER   |

> Coupon

| id  | coupon_name    | coupon_sale_strategy | sale_price | member_id |
| --- | -------------- | -------------------- | ---------- | --------- |
| 1   | 고정 500원     | FIX                  | 500        | 1         |
| 2   | 비율 20%       | RATE                 | 20         | 1         |
| 3   | 고정 500원     | FIX                  | 500        | 2         |
| 4   | 비율 20%       | RATE                 | 20         | 2         |


> Product

| id | Created Timestamp        | Updated Timestamp        | Name    | Price  | Quantity | Strategy |
|----|--------------------------|--------------------------|---------|--------|----------|----------|
| 1  | 2023-09-08 07:44:31.379  | 2023-09-08 07:44:31.379  | 사과    | 1000   | 10       | TOTAL    |
| 2  | 2023-09-08 07:44:31.418  | 2023-09-08 07:44:31.418  | 바나나  | 2000   | 5        | TOTAL    |
| 3  | 2023-09-08 07:44:31.433  | 2023-09-08 07:44:31.433  | 책상    | 5000   | 30       | SPECIFIC |
| 4  | 2023-09-08 07:44:31.447  | 2023-09-08 07:44:31.447  | 마우스  | 10000  | 30       | SPECIFIC |
| 5  | 2023-09-08 07:44:31.461  | 2023-09-08 07:44:31.461  | 키보드  | 20000  | 10       | SPECIFIC |

> PriceHistory

| id | Product Price | Timestamp                  | Product ID |
|----|---------------|----------------------------|------------|
| 1  | 1111          | 2023-09-04 10:11:11.000000 | 1          |
| 2  | 2222          | 2023-09-05 05:30:00.000000 | 1          |
| 3  | 3333          | 2023-08-05 07:24:11.000000 | 1          |

 


---

## 테스트 코드 고민한 부분

### 테스트 코드
- 테스트 코드는 비즈니스 로직의 안전성을 검증하는 기능 이외에 다른 사람들에게 **코드를 설명하는 부분이라고 생각합니다**.
- ``Controller``에서 제일 중요한 포인트는 End Point의 데이터를 검증을 하는것이라고 생각합니다.
- 기존의 ```Controller```에서 HTTP 요청과 End Point의 데이터를 하나의 로직에서 관리를 하였습니다.
- 하지만 가독성, 재사용, SRP를 생각을 한다면 분리를 하는게 맞다고 생각을 하여서  ``MockMvcRequestBuilders ``(HTTP요청)와 End Point를 분리를 하여 Controller에서 가장
중요한 End Point를 확인이 가능하게 ```apiCaller```, ```Controller```를 분리를 하였습니다.

<br/>

>**1. Application Context 재활용**
- 각각의 테스트 코드에 ```@SpringBootTest```를 붙이기 보다는 하나의 ```ControllerTest```를 만들어서 공유
- 코드 중복 최소화, 일관성 유지, 유지보수 용이
- ``ControllerTest``를 공유하기 때문에 통합 테스트 시간 감소

<br/>

> **2. HTTP, 응답 분리**
- ```Controller```에서 주요 관심사는 End Point라고 생각을 합니다.
-  가독성, 재사용, SRP를 생각을 한다면 분리



 

<br/>

## ERD
![image](https://github.com/KMGeon/Aswemake/assets/103854287/31bbf9ad-c1ea-4e35-9eb7-fc9a1bc6d2d1)

<br/>
