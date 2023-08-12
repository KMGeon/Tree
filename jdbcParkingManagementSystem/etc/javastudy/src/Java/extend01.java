package Java;

public class extend01 {
    public static void main(String[] args) {
//        이런 방식으로 만드는건 불리함
        Animal animal = new Animal();
        Dog dog = new Dog();

        Animal ani=new Dog();
        ani.eat();
        ((Dog)ani).division();

        ani = new cat();
        ani.eat();

    }
}


class Animal{
    String name;
    int age;
    String part;

    public void eat(){
        System.out.println("먹다");
    }
//    public Animal(){}
}

class Dog extends Animal{
    public void division(){
    super.eat();
    }
    public void eat(){
        System.out.println("개처럼 먹다");
    }
    public Dog(){
        super();
    }
}

class cat extends Animal{
    public void eat(){
        System.out.println("고양이 처럼 먹다");
    }
    public cat(){
        super();
    }
}