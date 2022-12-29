package HighJava.src.Lambda;

public class T02LambdaTest {
    public static void main(String[] args) {
        //람다식을 사용하지 않았을 경우
        LambdInterface1 lambdInterface1 = new LambdInterface1() {
            @Override
            public void test() {
                System.out.println("안녕하세요.");
                System.out.println("익명 클래스 구현 방식입니다.");
            }
        };
        //메서드 호출
        lambdInterface1.test();

        LambdInterface1 lam11 = () -> System.out.println("반갑습니다. 람다식입니다.");
        lam11.test();
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        /*
        람다식의 작성 방법
        기본형식) (자료형이름 매개변수명 , ...) -> {실행문}
        1.매개변수의 자료형이름은 생략 가능 ex) (int a) -> {sout(a);} (a) ->{sout(a);}

        2.매개변수가 1개일 경우에는 괄호()를 생략 가능  / 단 자료형이름을 지정할 경우에는 생략불가

        3.실행문이 1개일 경우에는 {},; 생략 가능

        4.매개변수가 하나도 없으면 () 생략할 수 없다.
        ()->sout("안녕")

        5.반환값이 있을 경우에는 return명령을 사용한다.
        ex) (a,b) -> {return a + b;}

        6.실행문에 return만 있므녕  return 명령과 {}를 생략 가능
        (a,b)->a + b
         */

        LambdInterface2 lam3 = (int z) -> {
            int result = z + 100;
            System.out.println("result:" + result);
        };
        lam3.test(30);

        LambdInterface2 lam4 = z -> {
            int result = z + 300;
            System.out.println("result: " + result);
        };
        lam4.test(90);

        System.out.println("----------------------------------------------------------------------");

        LambdInterface3 lam5 = (int a, int b) -> {
            int result = a + b;
            return result;
        };

        lam5.test(20, 30);

        LambdInterface3 lam6 = (int x, int y) -> {
            int r = x + y;
            return r;
        };
        int k = lam6.test(10, 20);
        System.out.println(k);

        LambdInterface3 lam7 = (x, y) -> {
            return x + y;
        };
        lam7.test(80, 50);

        LambdInterface3 lam8 = (x, y) -> x + y;
        k = lam8.test(100, 200);
        System.out.println("k" + k);

        LambdInterface3 LAM9 = (X, Y) -> X > Y ? X : Y;
        k = LAM9.test(100, 200);
        System.out.println('k' + k);

    }


}
