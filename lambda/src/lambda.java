package Java정석;

public class lambda {
    public static void main(String[] args) {
        //   Object ojb=(a,b) -> a>b ? a:b; //람다식 : 익명객체
        Object o = new Object() {
            int max(int a, int b) {
                return a > b ? a : b;
            }
        };
//    int value = max(o.max(3,5));
        //에러가 나는 이우 object가 max라는 객체를 가지고 있지만 메소드는 없다.(리모컨에 버튼이 없다) 이걸 해결하기 위해서
        //함수형 인터페이스를 사용한다.
    }
}

/*
람다 : 함수(메서드)를 간단히 표현
작성하기
1.메서드의 이름과 반환타입 제거 -> 추가
2.반환겂이 있는 경우 값만 적고 return 생략 끝에 ;안 붙임
3.매개변수의 타입이 추론 가능하면 생략 가능(대부분의 경우 생략 가능)


주의사항
1.매개변수 1개면 () 생략가능 , 타입이 있으면 생략 불가능
2.문장에 하나면 {} 생략 가능

(int a, int b) ->{
return a>b ? a:b
}

 */