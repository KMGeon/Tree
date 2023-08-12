package Java;


public class Class01 {
    public static void main(String[] args) {
        //오버로딩
        OverLoad load = new OverLoad();
        load.add(1,2);
        load.add(1.1f,2);
        load.add(1.2f,2.2f);
    }
}

class OverLoad{
    public void add(int a , int b){
        System.out.println(a+b);
    }    public void add(float a , int b){
        System.out.println(a+b);
    } public void add(float a , float b){
        System.out.println(a+b);
    }
}

//오버로드는 메소드의 개수와 타입이 다르다.