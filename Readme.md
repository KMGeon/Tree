## 애즈위메이크 백엔드 신입 개발자 채용 - 김무건

## 기술 스택

1. Spring Boot 2.7.15
2. Java 11
3. JPA
4. MySQL 8.0.22
5. JUnit5, Mockito

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


---

## 쿠폰 계산 로직

### 상품
- 상품의 전략은 TOTAL,SPECIFIC으로 분리를 하였습니다.

### 쿠폰
- 쿠폰의 전략은 RATE,FIX로 분리를 하였습니다.
- 만약에 상품이 TOTAL의 전략
  - FIX : 주문 상품에 배달비를 제외하고 고정된 가격을 할인
  - RATE : 주문 상품에 퍼센트로 가격을 할인

<BR/>

- 상품이 SPECIFIC의 전략
  - FIX : 주문 상품에서 상품의 전략이 SPECIFIC인 상품의 고정된 가격을 할인
  - RATE : 주문 상품에서 상품 전략이  SPECIFIC인 상품의 퍼센트로 가격을 할인


### 1. 모든 상품이 TOTAL , 쿠폰 FIX(고정) 할인

> 주문을 조회하면 사과 2개, 바나나 2개를 주문을 했다. 이때 상품의 전략은 TOTAL (즉. 상품에서 SPEC 상품이 없다.)

 ![image](https://github.com/KMGeon/Aswemake/assets/103854287/c9346bfd-664a-4834-9bd9-ea762eeb0328)


> 고정 500원 할인 쿠폰을 사용

![image](https://github.com/KMGeon/Aswemake/assets/103854287/5f02ea68-1bb5-4b17-8c1a-a81a1c01e99e)

- 모든 상품은 TOTAL이기 때문에 다음과 같은 할인 로직이 적용이 된다.
1. 사과 1000원 -500 = 500원 / 바나나 -500 = 1500원
2. 사과 *2 / 바나나 *3 = 1000 + 4500 = 5500원
4. 배달비(5000) : 5500 + 5000원 = 10500원

<br/>

### 2.  모든 상품이 TOTAL , 쿠폰 RATE(비율) 할인

![image](https://github.com/KMGeon/Aswemake/assets/103854287/2f51c6f9-e171-4bb2-af2f-d4d338f972eb)

- 모든 상품은 TOTAL이고 쿠폰은 RATE이기 때문에 다음과 같은 로직 적용
1. 사과 -20% = 1000 - (1000 *0.2) = 800원
2. 바나나 - 20% = 2000 - (2000 *0.2) = 1600원
3. 사과 * 개수 + 바나나 * 개수 = 800*2 + 1600 *3 = 6400
4. 배달비(5000)  :  6400 + 5000 = 11400

<br/>

### 3. 주문에 SPECIFIC 상품이 있고 쿠폰 FIX(고정) 할인

> 주문을 조회를 해보니 SPECIFIC 상품의 유무 확인
![image](https://github.com/KMGeon/Aswemake/assets/103854287/3d7ecef8-b6b5-4acd-a882-dcdd852812b4)

> 특정 상품에만 할인 (배달비 제외)
![image](https://github.com/KMGeon/Aswemake/assets/103854287/c4a7323b-11bd-43ab-a52b-e903b96dcba7)
- 책상 상품은 SPECIFIC이며 사과는 TOTAL이다. 책상에 고정 비용 할인
  1. 사과 : 1000 * 2 = 2000원
  2. (책상 - 할인) * 수량 = 4500*3 = 13500원
  3. 사과 + 책상 = 15500원
  4. 배달비(5000) =  20500원


<br/>

### 4. 주문에 SPECIFIC 상품이 있고 쿠폰 RATE(비율) 할인

![image](https://github.com/KMGeon/Aswemake/assets/103854287/dfb1d6f6-6331-4397-a8a9-4b76e1140c06)

> 특정 상품에만 비율 할인
  1. 사과 : 1000 * 2 = 2000원
  2. 책상 개별 할인 = 5000 * 0.2 = 1000원
  3. 책상 * 수량 = 4000 * 3 = 12000원
  4. 사과 + 책상 = 14000원
  5. 배달비(5000) = 19000원


## ERD
![image](https://github.com/KMGeon/Aswemake/assets/103854287/8d2c424c-a512-43f5-83d7-0e2e6715bd66)



<br/>
