package TCP.인터페이스;

public class TV implements RemoCon {

    @Override
    public void chUp() {
        System.out.println("업");
    }

    @Override
    public void chDown() {
        System.out.println("다운");
    }

    @Override
    public void internet() {
        System.out.println("인터넷이 된다.");
    }

    //추가적인 기능 구현

}
