

---
## Project Setup(Back End)
- Receive Redis Image
```docker
 docker image pull redis
```
- Receive Redis Create
```docker
  docker network create redis-network
```
- Running the Redis server
```docker
  docker run --name local-redis -p 6379:6379 --network redis-network -v redis_temp:/data -d redis:latest redis-server --appendonly yes
```
- Redis-cli access
```docker
  docker run -it --network redis-network --rm redis:latest redis-cli -h local-redis
```

## Project Setup(Front End)
```npm
 cd FrontEnd/
```
```npm
 npm install
```
```npm
 npm run lint
```
```npm
 npm run dev
```

## Tech Stack
- Java 11
- Spring Boot 2.7.11
- Data JPA, QueryDSL, Mybatis
- MySQL, Redis
- JWT, Spring Security
- GeoIP2

### 프로젝트 구조도
```bash
src
├── global 
│   ├── exception # 도메인별 에러 정의
│   ├── config
│   ├── redis
│   ├── util
│   ├── initializer
│   └── jwt 
│ 
└── Domain   
       └── <도메인> 
             └── controller # 도메인 컨트롤러
             └── entity # 도메인 오브젝트
             └── dto # 도메인 오브젝트
             └── repostiory # 도메인 리포지토리
             └── application # 도메인 서비스, 도메인 이벤트 핸들러
```


## 요구 사항
-[ ]  application.yml을 local, dev, prod 환경을 나누어 개발
-[ ] logback을 통한 error 설정 - slack으로 선택
-[ ] local 환경에서 console 찍히고 dev, prod 환경
-[ ] spring security를 이용한 jwt 인증, 인가
-[ ] 게시판 crud
-[ ] 게시판 image, 동영상이 root 폴더에 저장
-[ ] 파일 다운로드 모든 확장자가 가능하게 설정
-[ ] vue.js를 이용해서 view를 구성
-[ ] back office를 mybatis로 구현
