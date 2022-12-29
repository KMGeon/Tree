package TCP.추상;
//poly
public class TCP25 {
    public static void main(String[] args) {
        Animal ani = new Dog();
        ani.eat();

        Animal cat = new Cat();
        cat.eat();

        ani.move();
        cat.move();
        ((Cat)cat).night();//재정의가 안된 하위클래스의 메소드를 이용하고 싶으면 형변환을 하고 실행


    }
}
class Dog extends Animal {
    @Override
    void eat() {

        System.out.println("개가 먹다");
    }
}

class Cat extends Animal {

    public void night() {
        System.out.println("밤에 눈에서 빛이 난다.");
    }
    @Override
    void eat() {
        System.out.println("고양이가 먹다");
    }
}

abstract class Animal { // 추상클래스 ->animal ani = new Animal(); 불가능 혼자 객체 생성을 못한다. 무조건 하위 클래스를 이용해서 사용한다.
    abstract void eat() ;

    public void move(){
        System.out.println("움직이다.");
    }
}
