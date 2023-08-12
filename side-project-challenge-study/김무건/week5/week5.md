# 과제 
```
- ERD (coupon, coupon_record 추가)
- API 명세서 (Notion)
- 페이징
- querydsl 동적쿼리 → coupon 조건 대체
- 다음 JWT 기술 대비, Spring Security 조사
```

- ERD 설계 (ㅇ)
- API 명세서(ㅇ)
- QUERY_DLS COUPON- https://github.com/KMGeon/coupon
- JWT 학습 - https://pos04167.tistory.com/165
- SPRING SECURITY

![image](https://user-images.githubusercontent.com/103854287/230638802-2d376bfd-4aa8-4974-801c-ed277169a7db.png)

- 기본적인 security 흐름도
1. AuthenticationFilter에서 request를 받아서 Spring Security에서 사용자의 인증 정보를 추출하고 인증 프로세스를 수행하여 인증에 성공한 경우 Authentication 객체를 생성하여 저장하는 역할을 담당하는 필터입니다. 인가 처리에는 저장된 Authentication 객체를 활용합니다.
2. 필터에서 Http Request를 받아 유저 아이디와 비밀번호를 저장한다.
3. Manager가 Provider에게 UsernamePassword를 넘겨서 Provider에서 UsernamePassword을 받아 검증하는 로직을 처리한다.
4. 만약에 참이면 Authentication 객체를 형성해서 contextholder에 있는 context에 저장한다.

- 만약에 비밀번호가 암호화가 되어있으면 db에서 값을 받아서 UsernamePassword에서 비밀번호를 암호화 하여 비교하여 참/거짓을 판별한다.
