package Generics;

import java.util.ArrayList;
import java.util.List;

public class T07WildCardTest {

    //장바구니 항목조회를 위한 메서드(모든 아이템)
    public static void displayCartItemInfo(Cart<?> cart) {
        System.out.println("=모든항목 담은 장바구니 항목 리스트");
        for (Object obj : cart.getList()) {
            System.out.println(obj.toString());
        }
        System.out.println("------------------------------------------------");
    }

    //장바구니 항목조회를 위한 메서드(음류 아이템)
    public static void displayCartItemInfo2(Cart<Drink> cart) {
        System.out.println("=음류 담은 장바구니 항목 리스트");
        for (Object obj : cart.getList()) {
            System.out.println(obj.toString());
        }
        System.out.println("------------------------------------------------");
    }

    //장바구니 항목조회를 위한 메서드(고기류나 그 상위 아이템)
    public static void displayCartItemInfo3(Cart<? super Meat> cart) {
        System.out.println("=고기류 담은 장바구니 항목 리스트");
        for (Object obj : cart.getList()) {
            System.out.println(obj.toString());
        }
        System.out.println("------------------------------------------------");
    }


    public static void main(String[] args) {
        Cart<Food> foodCart = new Cart<>();
        foodCart.add(new Meat("돼지고기", 5000));
        foodCart.add(new Meat("소고기", 12000));
        foodCart.add(new Meat("삼계탕", 15000));
        foodCart.add(new Juice("오렌지쥬스", 900));
        foodCart.add(new Coffe("아메리카노", 50000));

        Cart<Meat> meatCart = new Cart<>();
        meatCart.add(new Meat("돼지고기", 5000));
        meatCart.add(new Meat("소고기", 15000));
        Cart<Drink> drinkCart = new Cart<>();
        drinkCart.add(new Juice("오렌지주스", 50000));
        drinkCart.add(new Juice("아메리카노", 50));

        displayCartItemInfo(foodCart);

        displayCartItemInfo2(drinkCart);
    }
}

class Food {
    private String name;
    private int price;

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return this.name + "(" + this.price + "원" + ")";
    }
}

class Meat extends Food {

    public Meat(String name, int price) {
        super(name, price);
    }
}

class Drink extends Food {
    public Drink(String name, int price) {
        super(name, price);
    }
}

class Juice extends Drink {
    public Juice(String name, int price) {
        super(name, price);
    }
}

class Coffe extends Drink {
    public Coffe(String name, int price) {
        super(name, price);
    }
}

class Cart<T> {
    private List<T> list = new ArrayList<>();

    public Cart() {
        this.list = list;
    }

    public void add(T item) {
        list.add(item);
    }

    public Iterable<? extends Object> getList() {
        return list;
    }
}