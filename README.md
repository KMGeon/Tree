# IOC & DI

## ⚔️IOC란

- 객체의 Life Cycle에 대한 관리를 프레임워크가 가져감

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/43c6697b-8a07-4e5d-81db-a59a677c9466/Untitled.png)

<aside>
💡 IOC 컨테이너란

</aside>

- 객체에 대한 Life Cycle 관리
- 객체의 생성을 책임지고 , 의존성을 관리
- Pojo의 생성 , 초기화 , 서비스 , 소멸에 대한 권한

## ⚔️DI

---

### IOC 분류

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9ad6f69e-3a61-4f8c-b31d-9573f0786a52/Untitled.png)

<aside>
💡 DL

</aside>

- 의존관계가 있는 객체를 외부에서 주입 받는 것이 아닌, 의존관계가 필요한 객체에서 직접 검색하는 방식

- 단점
1. 오브젝트 컨테이너 밖에서 실행 불가능
2. 테스트하기 어렵다

<aside>
💡 DI

</aside>

- 객체를 직접 생항하는 것이 아니라 외부에서 생성한 후 주입 시켜주는 방식
- DI를 통하여 모듈 간의 결합도가 낮아지며 유연성이 높아진다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ee028d41-6d6a-4bc9-bcd8-ffd34d86d265/Untitled.png)

> 방법1
> 
- A객체가 B , C를 NEW생성자를 통하여 직접 생성하는 방법

> 방법2
> 
- 외부에서 생성된 객체를 setter , 생성자를 통하여 주입하는 방식

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0e756ebf-cb1d-4a48-be86-1f63e857ec46/Untitled.png)

## ⚔️의존성

---

> 왜 의존성이 강하면 사용하기 어렵지??
> 
1. 테스트 하기 어려워진다.
    - 내부에서 직접 생성하는 객체에 대해서 Mocking을 할 방법이 없다.

1. Code의 변경이 어려워진다.

[Spring - Spring을 왜 사용하나요?(DI) - 1](https://galid1.tistory.com/493?category=769011)

## ⚔️***Injection 3 Ways***

---

## 1. ***<Field Injection>***

```java
@Component
public class ABean {
    
    @Autowired
    private BBean b;
    
    public void bMethod() {
        b.print();
    }
    
}
```

## 2. ***<Constructor Injection>***

```java
@Service
public class WholeProjectImpl implements WholeProjectService {

private ProjMapper projMapper;
private MyPageMapper myPageMapper;

    @Autowired
    public WholeProjectImpl(ProjMapper projMapper , MyPageMapper myPageMapper){
        this.projMapper = projMapper;
        this.myPageMapper = myPageMapper;
    }

}
```

- 만약에 생성자가 1개이면 @autowired를 생략이 가능하다.
- Lombok의 도움을 받는다면 더 간단한 코드의 작성이 가능한데

```java
@Service
@RequiredArgsConstructor
public class WholeProjectImpl implements WholeProjectService {

private final ProjMapper projMapper;

private final MyPageMapper myPageMapper;

}
```

> 생성자 주입은 호출 시점에 1번 호출이 되어서 주입받는 객체가 변하지 않거나 객체의 주입이 필요한 경우에 강제하기 위해 사용한다.
> 

<aside>
💡 생성자 주입을 사용해야 하는 이유

</aside>

### **[ 생성자 주입을 사용해야 하는 이유 ]**

최근에는 Spring을 포함한 DI 프레임워크의 대부분이 생성자 주입을 권장하고 있는데, 자세한 이유를 살펴보도록 하자.

1. 객체의 불변성 확보
2. 테스트 코드의 작성
3. final 키워드 작성 및 Lombok과의 결합
4. 스프링에 비침투적인 코드 작성
5. 순환 참조 에러 방지

## 3. ***<Setter Injection>***
