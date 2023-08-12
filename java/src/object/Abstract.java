package object;
//추살클래스는 불완전 객체로서 자기 자신을 생성하지 못하고 반드시 상속받는 하위 클래스에 추상 메서드를 재정의 해야된다.
public class Abstract {
    public static void main(String[] args) {
        Animal a = new dog();
        a.eat();

        a= new cat();
        a.eat();
    }
}


abstract class Animal{
    public  abstract  void eat();

    public Animal() {
    }
}

class dog extends Animal{
    void sound(){
        System.out.println("왈왈");
    }

    @Override
    public void eat() {
        System.out.println("개같이 먹다");
    }
}

class cat extends  Animal{
    void sound(){
        System.out.println("밤에 눈에서 빛이 난다.");
    }

    @Override
    public void eat() {
        System.out.println("고양이 처럼 먹다");
    }
}
