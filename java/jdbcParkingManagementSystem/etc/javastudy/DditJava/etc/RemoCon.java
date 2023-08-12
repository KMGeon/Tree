package TCP.인터페이스;

public interface RemoCon {
    //인터페이스는 상수랑 추상메서드만 올 수 있다.
    public static final int max = 100;
    public abstract void chUp();
    public abstract void chDown();
    public abstract void internet();
}
