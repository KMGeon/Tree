- 페이징 ResponseDto로 page, size, totalCount, prev, next 값 반환케 함,
    * prev, next는 페이징 내 "<<" ">>" 를 말함.
> Pageable로 구현 경험이 있음 -> QueryDSL에서 
> Page<>를 통하여 프론트에서 처리를 해야된다고 생각.

- Security, JWT 조사
> 프로젝트에 적용

- JWT로 회원가입, 로그인 처리
> 프로젝트에 적용

- ADMIN, USER Role 권한에 따라, api 기능 접근제한
> 현재 프로젝트에 적용

- chellange❗Oauth2 인증 처리 추가하기