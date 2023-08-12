package Java;

public class super01 {
    public static void main(String[] args) {
        new Point2().method();
    }
}


class Point{
    int x,y;

    void method(){
        System.out.println("안녕하세요");

    }
}

class Point2 extends  Point{


}