package object;

public class Polymorphism {
//    다향성
public static void main(String[] args) {
    Tv smartTv = new SmartTv();
    smartTv.test();
}
;}


class  Tv {
    boolean power;
    int channel;

    void power() {
        power = !power;
    }

    void test() {
        ++channel;
    }
}
class  SmartTv extends  Tv {
    String text;

    void caption() {
        System.out.println("다향성 테스트");
    }
}
//다향성이란 여러 가지 형태를 가질 수 있는 능력 , 조상 타입 참조 변수로 자손 타입 객체를 다루는 것