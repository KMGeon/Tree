package TCP;

public class MemberVO {
    public String name;
    public int age;
    public String tel;
    public String addr;
//    public이여서 접근이 자유롭다.
    public MemberVO() {
    }

    public MemberVO(String name, int age, String tel, String addr) {
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.addr = addr;
    }
}
