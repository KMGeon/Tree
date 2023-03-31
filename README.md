# Description

### 서버 사이드 프로젝트 챌린지 스터디

### Project Sturucture
```bash
src
├── domain 
│   ├── controller
│   ├── application
│   ├── repository
│   └── dto
│  
└── global
     └── exception

test
├── coupon 
│   ├── application
│   └── controller
│  
└── resource
     └── application.yml
```

### Tech Stacks
- ### Spring Boot 2.7.10, Java11
- ### Data JPA
- ### H2 DataBase
- ### curl


## 개발 필수요건 
- [x] C: 쿠폰 등록
- [x] 쿠폰 조회 (전체조회(검색조건)/상세조회)
- [x] 쿠폰 수정
- [x] 쿠폰 삭제

### 개발 추가요건
- [x] 검색조건시, 쿠폰코드가 한글이 포함되는지를 찾아야 한다. 파라미터 codeType = "kor" 입력시, 요구사항에 맞는 응답값을 나오게 해주세요!



