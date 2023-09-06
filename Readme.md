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
         └── repostiory # 도메인 오브젝트
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

### 4. API 명세서
- [Postman API 명세서 Link]()

<br/>

---

## 테스트 코드 고민한 부분

### 테스트 코드
- 테스트 코드는 비즈니스 로직의 안전성을 검증하는 기능 이외에 다른 사람들에게 **코드를 설명하는 부분이라고 생각합니다**.

<br/>

- 코드의 속도, 유지보수를 위해서 Context를 재활용, 코드의 가독성을 위해서 Controller에서 Stub과 반환 값을 Mapping을 분리하여  실제 Controller 테스트에서는 가독성 높게 End Point만 관리를 하도록 리펙토링

<br/>

### Controller

- Controller는 Mock을 통하여 테스트를 진행을 하였습니다.

>**1. Application Context 재활용**
- 각각의 테스트 코드에 ```@SpringBootTest```를 붙이기 보다는 하나의 ```ControllerTest```를 만들어서 공유
- 코드 중복 최소화, 일관성 유지, 유지보수 용이
- ``ControllerTest``를 공유하기 때문에 통합 테스트 시간 감소

<br/>

> **2. Stub, End Point 분리**

- Controller Test에서 제일 중요한 부분은 Stub 보다는 End Point를 관리를 하는거라고 생각을 합니다.
- 그러면 제일 주요한 관심하는 End Point라고 생각하여 Stub과 End Point Mapping을 분리를 하였습니다.



###  리펙토링 테스트 코드 (Stub과 반환을 분리하여 Controller)
```java
==========================================================================================
apiCaller (Stub과 End Point Mapping)
==========================================================================================
    
public ApiResponse<OrderPayInfoResponse> getOrderInfoValid() throws Exception {

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/order")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + USER_TOKEN);

    MockHttpServletResponse response = mockMvc.perform(builder)
            .andReturn()
            .getResponse();

    OrderPayInfoResponse orderPayInfoResponse =
            objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), OrderPayInfoResponse.class);

    return new ApiResponse<>(response.getStatus(), orderPayInfoResponse);
}


==========================================================================================
Controller 테스트
==========================================================================================
@Test
public void 회원_주문목록_조회_성공() throws Exception{
        ApiResponse<OrderPayInfoResponse> response = orderMockApiCaller.getOrderInfoValid();


        // 상품 목록 검증
        List<ProductOrderResponse> actualProducts = response.getBody().getProductOrderResponses();
        assertThat(actualProducts).hasSize(4);

        // 응답 데이터 검증
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody().getTotalCost()).isEqualTo(30000);
        assertThat(response.getBody().getCouponStatus()).isEqualTo(TOTAL);


        // 각 상품별 검증
        assertAll(
        () -> assertThat(actualProducts.get(0).getProductId()).isEqualTo(1L),
        () -> assertThat(actualProducts.get(0).getProductName()).isEqualTo("사과"),
        () -> assertThat(actualProducts.get(0).getProductPrice()).isEqualTo(5000),
        () -> assertThat(actualProducts.get(0).getOrderProductQuantity()).isEqualTo(4)
        );

        ... 생략

    }
```


 

<br/>

## ERD
![image](https://github.com/KMGeon/Aswemake/assets/103854287/31bbf9ad-c1ea-4e35-9eb7-fc9a1bc6d2d1)

<br/>
