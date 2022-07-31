package Java정석;

/*
함수형 인터페이스는 람다식을 다루기 위해서 사용한다.
 */
public class lambdaFunctioninterface {
     public static void main(String[] args) {


          System.out.println(f1.max(5,2));
          System.out.println(temp);
     }
     @FunctionalInterface //함수의 인터페이스는 단 하나의 인터페이스만 가져야 한다.
     interface MyFunction {
          public abstract int max(int a, int b);
//          public abstract int min(int a, int b);
     }
     //같다
//     MyFunction f = new MyFunction() {
//          @Override
//          public int max(int a, int b) { //오버라이딩 - 접근제어자는 좁게 못바꾼다.
//               return a>b?a:b;
//          }
//     };
//
//     int value = f.max(3,4);

     static MyFunction f1 =(a, b)->a>b ? a:b;

     static int temp = f1.max(1,2);


     //new 조상이름 , 클래스 인터페이스 {멤버}
}
