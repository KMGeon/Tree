package object;



public class UpDownCasting {
    public static void main(String[] args) {
        ani ani = new ani();
        ani.eat();

        ani a = new tiger();
        ((tiger) a).night(); //다운 캐스팅
        a.eat(); //업 캐스팅


    }
}

class  ani{
    void eat(){
        System.out.println("먹는다.");
    }


}

class tiger extends  ani{
    void night(){
        System.out.println("타이거는 밤에 눈이 빛이 난다.");
    }
    void eat(){
        System.out.println("타이거가 먹는다.");
    }

}
class elephant extends  ani{
    void like(){
        System.out.println("코끼리는 산책을 좋아한다.");
    }
}