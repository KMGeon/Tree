package TCP.TCP20;

public class 객체형변환 {
    public static void main(String[] args) {

        //upcasting
//        Object ani = new Cat();
        Animala   ani = new Cat();
        ani.eat(); // 컴파일 시점 :animala // 실행시점에서는 cat

        Cat cat = (Cat)ani;
        ((Cat)cat).night(); //DownCasting

        //다형성
        //상위클래스가 하위 클래스에게 동일한 메세지로 서로 다르게 동작시키는 원리..

        Object  o =new Cat();
        //o.eat(); //불가능
        ((Cat)o).eat(); // 다운캐스틱

    }
}


class Cat extends Animala {
    void night(){
        System.out.println("밤에 눈에서 빛이 난다.");
    }

    @Override
    void eat() {
        super.eat();
    }
}

class Animala {
    void eat(){
        System.out.println("먹는다.");
    }
}