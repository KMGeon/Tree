package TCP.다형성;
//다형성 배열
//dog,cat 저장할 배열을 생성하시오.

public class TCP24 {
    public static void main(String[] args) {
        Animal[] animals = new Animal[2];
        animals[0] = new Dog();
        animals[1] = new Cat();

        //출력
        for (Animal animal : animals) {
            animal.eat();

            //오류 상위클래스 animal이 강아지도 판단해서
            //((Cat)animal).night();

            if(animal instanceof Cat) {
                ((Cat)animal).night();
            }
        }
        displays(animals);
    }

    private static void displays(Animal[] animals) { // 다형성 배열
        for (Animal ani : animals) {
            ani.eat();
            if(ani instanceof Cat) {
                ((Cat)ani).night();
            }

        }
    }
}
