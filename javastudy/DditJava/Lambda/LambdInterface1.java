package HighJava.src.Lambda;

@FunctionalInterface
public interface LambdInterface1 {
    //반환값이 없고 매개변수도 추상메서드 선언
    public void test();
}

@FunctionalInterface
interface LambdInterface2 {
    public void test(int a);
}

@FunctionalInterface
interface LambdInterface3 {
    public int test(int a, int b);
}
