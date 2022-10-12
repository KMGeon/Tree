package day03;

public class OopTest {
    public static void main(String[] args) {
        Human hum = new Human();
        System.out.println(hum.hungry);
        System.out.println(hum.skillLang);
        hum.momTouch(5);
        hum.timegoby();
        System.out.println(hum.hungry);
        System.out.println(hum.skillLang);
    }
}
