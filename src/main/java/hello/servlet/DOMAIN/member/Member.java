package hello.servlet.DOMAIN.member;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member{
    private long id;
    private  String username;
    private int age;

    public Member() {
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(long id, int age) {
        this.id = id;
        this.age = age;
    }


}
