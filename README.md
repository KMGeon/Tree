# Git Action , AWS codeDeploy CI/CD

---

[학습한 내용](https://velog.io/@geon_km/Github-Actions-CI-CodeDeploy%EB%A1%9C-CICD-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-vum9u82d)

[기존의 젠킨스에서 Git Action으로 변경](https://github.com/CS-tudy/CStudy_BackEnd)

[] NGinx 무중단 배포

<br/>

# 정렬 알고리즘
- 관련 Youtube : 엔지니어 대한민국

---

### 퀵 정렬 
- 퀵 정렬이란
  -   분할 정복 알고리즘 중 하나로, 배열을 빠르게 정렬하는 데 사용한다.
  -   시작과 끝을 정하고 피벗과 비교를 하였을 때 스타트가 피벗보다 크면 멈추고 끝을 움직여 피벗보다 작은 값을 만나면 Start, End를 바꾼다.
  -   평균 : O(nlogN)
  -   최악 : O(n^2)
    - 최악인 경우는 매번 피벗을 선택을 하면 그 피벗의 값이 제일 작은 값을 선택을 하였을 때.


### 선택 정렬
- 배열을 돌면서 가장 작은 값을 하나씩 돈다.
    - 제일 앞의 값을 변수에 담고 모든 배열을 돌면서 가장 작은 값을 찾아서 앞에 위치를 시킨다.
- 하나의 임의의 변수를 만들어 가장 작은 값을 바꾼다.
- 평균 o(n^2) → 모든 방을 돌기 때문에

### 선택 정렬

### 거품 정렬
- 앞에서 2개씩 자기 옆의 변수와 비교하여 작은 값을 앞으로 꺼내 정렬하는 정렬
- 결국 가장 큰 수부터 뒤로 정렬한다.
- O(n^2)

<br/>

# Git Squash

---

- 참고 블로그 [hudi.blog](https://hudi.blog/git-merge-squash-rebase/)

## Merge (Fast-Forward)
![image](https://github.com/KMGeon/devProject/assets/103854287/678c8a5d-ffe2-4916-887a-ffeb8d813879)

## Merge (Recursive)
![image](https://github.com/KMGeon/devProject/assets/103854287/41cdba9c-9f30-4c4a-ac2b-39493c1a454b)

## Squash & Merge
![image](https://github.com/KMGeon/devProject/assets/103854287/91a779cb-1f20-4879-9e3f-b8ac272f9c72)

## Rebase & Merge
![image](https://github.com/KMGeon/devProject/assets/103854287/44ea7db6-2c2f-461d-925f-1fe3d39f11ac)

## [Cherry-pick](https://git-scm.com/docs/git-cherry-pick)
