# Description

### 백엔드 연습 프로젝트


### Project Sturucture

```bash
src
├── global 
│   ├── exception
│   ├── config
│   ├── interceptor
│   └── jwt
│  
├── domain
│   ├── user
│   ├── board
│   └── Reply
└── validations
```
# Tech Stacks
- ### Spring Boot 2.7.10
- ### Data JPA, QeuryDSL
- ### H2 DataBase
- ### Rest Docs
- ### git, Slack


### 개발 필수요건

### USER
- [x] 회원가입
- [x] 회원 수정
- [x] 회원 삭제

### Board
- [ ] 게시글 작성
- [ ] QueryDSL 페이징
- [ ] 댓글
- [ ] 동시성 Lock

### 개발 추가요건
- [ ] JWT
- [ ] Spring Security
- [ ] 단위 테스트
- [ ] 테스트 커버리지 100%
- [x] 예외처리
- [x] SLACK을 통한 에러 모니터링


