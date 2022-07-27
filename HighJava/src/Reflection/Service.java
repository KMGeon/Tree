package Reflection;

public class Service {

   // @PrintAnnotation
    public void mehod1() {
        System.out.println("1을 출력됨");
    }

   // @PrintAnnotation(value="%")
    public void mehod2() {
        System.out.println("2을 출력됨");
    }

  //  @PrintAnnotation(value = "#",count =25)
    public void mehod3() {
        System.out.println("3을 출력됨");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

/*
java reflection

 */
