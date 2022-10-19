package day03;

public class Animal {
    int hungry = 5;

    public void timegoby() {
        if (hungry > 0) {
            hungry--;
        }
    }

    public void mattang() {
        hungry = 10;
    }

    public static void main(String[] args) {
        Animal ani = new Animal();
        System.out.println(ani.hungry);
        ani.timegoby();
        System.out.println(ani.hungry);
        ani.mattang();
        System.out.println(ani.hungry);
    }
}
