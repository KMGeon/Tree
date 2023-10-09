package TCP.다형성;

public class TCP23 {
    public static void main(String[] args) {

        Animal dog = new Dog();
        display(dog);
        Animal cat = new Cat();
        display(cat);

    }
    //upcasting
    public static void display(Animal r){   //TCP.다형성 인수
        r.eat();
        //다운캐스팅 하는법법
        //   ((Cat)r).night();
        // display에서 animal로 받아서 고양이는 가능한데 개에서 불가능하다. 그래서 instanceof를 사용해서 조건부로 다운 캐스팅을 한다.
        if(r instanceof Cat){
            ((Cat)r).night();
        }
    }
}
class Dog extends Animal {


    public Dog(){
        super();
    }
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
        super.eat();
    }
}


    class Animal {

    private String name;
    private int age;



    void eat(){
        System.out.println("먹다");
    }
}
