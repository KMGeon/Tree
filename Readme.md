## 1. wanted-pre-onboarding-backend
지원자의 성명 : 김무건

기술 스택 및 버전

Java : 11

Spring Boot : 2.7.14

Mysql : 8.0.22

### API
- [x] 회원가입
- [x] 로그인
- [x] 게시글 목록 조회 페이징
- [x] 특정 게시글 조회
- [x] 게시글 작성
- [x] 게시글 수정 - AOP 권한 체크
- [x] 게시글 삭제 - AOP 권한 체크

### 추가 사항
- [x] docker-compose
- [x] Controller, Service Layer Unit Test (JUnit5, Mockito)
- [x] Postman API 문서


## 2. 애플리케이션의 실행 방법

- Docker Setup
```docker
docker-compose -f docker-compose.local.yml up
```

2-1. 엔드 포인트 호출 방법

| description | method | url        | permission |
| ---- | ---- |------------| ----|
| 회원가입 | `POST` | /user      | `AllowAny` |
| 로그인 | `POST` | /login     | `AllowAny` |
| 게시글 목록 조회 | `GET` | /todo      | `AllowAny` |
| 게시글 작성 | `POST` | /todo      | `IsAuthenticated` |
| 특정 게시글 조회 | `GET` | /todo/{id} | `IsAuthenticated` , `IsAdminUser` |
| 특정 게시글 수정 | `PUT` | /todo      | `IsAuthenticated` |
| 특정 게시글 삭제 | `DELETE` | /todo/{id} | `IsAuthenticated` |

## 3. 데이터베이스 테이블 구조
![img.png](img.png)


## 4. 구현한 API의 동작을 촬영한 데모 영상 링크
https://youtu.be/18110EaCV94

## 5. 구현 방법 및 이유에 대한 간략한 설명

### 1. 회원가입
- 유효성 체크를 하여 유효성 체크 , 정합성
```java
{
    "code": "400",
    "message": "정규식에 적합하지 않습니다.",
    "validation": {
        "account": "유효한 이메일 주소를 입력하세요"
    }
}


{
    "code": "400",
    "message": "정규식에 적합하지 않습니다.",
    "validation": {
        "password": "비밀번호는 8자 이상 20자 이하로 입력하세요"
    }
}
```
### 2. 로그인
- 유효성 체크를 하여 데이터의 정합성
- 인증에 성공하면 JWT 생성
- Refresh Token은 Cookie에 저장하고 ``XSS``, ``CSRF`` 공격을 대비하여 ``HttpOnly``, ```SameSite```

```json
> JWT Decoded
{
  "sub": "test1234@test.com",
  "userId": 3,
  "roles": [
    "ROLE_USER"
  ],
  "iat": 1692171872,
  "exp": 1692173672
}
```

### 3.게시글 페이징
- 페이징 처리
- 한 페이지에 5개씩 보여준다.

### 4. 게시글 수정, 삭제 
- 특정 회원을 검증하는 횡단 관심사 AOP 분리
```java
  @Around("@annotation(todoAuthCheck)")
    public Object checkTodoAuthority(ProceedingJoinPoint joinPoint, TodoAuthCheck todoAuthCheck) throws Throwable {
        Object[] args = joinPoint.getArgs();

        if (args.length > 1 && args[1] instanceof LoginUserDto) {
            long id;
            if (args[0] instanceof Long) {
                id = (Long) args[0];
                log.info("Long Id :{}", id);
            } else if (args[0] instanceof UpdateTodoRequestDto) {
                id = ((UpdateTodoRequestDto) args[0]).getId();
                log.info("UpdateTodoRequestDto:{}", id);
            } else {
                throw new IllegalArgumentException("잘못된 메소드 인자입니다.");
            }

            LoginUserDto loginUserDto = (LoginUserDto) args[1];
            log.info("LoginUserDto:{}", loginUserDto.getUserId());

            if (todoRepository.findByIdAndUserId(id, loginUserDto.getUserId()).isEmpty()) {
                log.info("권한 체크 error 발생");
                throw new NotMatchUserAndTodoException(loginUserDto.getUserId());
            }
        }
        return joinPoint.proceed();
    }
```
```java
{
    "code": "2001",
    "message": "해당 회원이 작성한 Todo가 아닙니다. 2",
    "validation": {
        "UserException": "회원 관련 Exception"
    }
}
```

## 6.API 명세(request/response 포함)

https://documenter.getpostman.com/view/23650109/2s9Xy6rqJZ
