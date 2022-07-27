package Generics;

import java.util.ArrayList;
import java.util.List;

public class T06WildCardTest {
    /*
     * 와일드 카드에 대하여... 와일드 카드는 제너릭 타입을 이용한 타입 안전한 코드를 위해 사용되는 특별한 종류의 인수로서 , 함수선언 ,
     * 객체생성 및 메서드 정의할 때 사용된다. <? extends T> -> 와일드카드의 상한제한 , T와 그자손들만 가능 <? super T>
     * ->와일드카드의 하한제한. T와 그 조상들만 가능 <?> -> 모든타입이 가능 <? extends Object>와 동일
     */
//	public static void main(String[] args) {
//		List<String> lit = new ArrayList<>();
//	}
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<>();
        FruitBox<Apple> appleBox = new FruitBox<>();

        fruitBox.add(new Apple());
        fruitBox.add(new Grape());

        appleBox.add(new Apple());
        Juicer.makeJuice(fruitBox);
        Juicer.makeJuice(appleBox);
        // appleBox.add(new Grape());

        Garbage.add(new Garbage());
    }
}

class Garbage {

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "쓰레기";
    }

    public static void add(Garbage garbage) {
        // TODO Auto-generated method stub

    }
}

class Juicer {
    static <T extends Fruit> void makeJuice(FruitBox<?> box) {
        String fruitListStr = "";// 과일목록
        int cnt = 0;
        for (Object f : box.getFruitList()) {
            if (cnt == 0) {

                fruitListStr += f;
            } else {
                fruitListStr += "," + f;
            }
            cnt++;
        }
        System.out.println(fruitListStr + "->주스완성");

    }
}

class Fruit {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fruit(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "과일(" + name + ")";
    }

}

class Apple extends Fruit {

    public Apple() {
        super("사과");
        // TODO Auto-generated constructor stub
    }

}

class Grape extends Fruit {

    public Grape() {
        super("포도");
        // TODO Auto-generated constructor stub
    }

}

class FruitBox<T> {
    private List<T> fruitList = new ArrayList<>();

    public List<T> getFruitList() {
        return fruitList;
    }

    public void add(T fruit) {
        fruitList.add(fruit);
    }
}
