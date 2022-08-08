package JavaMVCTest.MemberVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스이다.

<P>
    DB테이블의 컬럼이 이클래스의 멤버변수가 된다.<BR>
    DB테이블의 컬럼과 이 클래스의 멤버변수를 매핑하는 역할
<P>
 */


@Getter
@Setter
@AllArgsConstructor
@ToString
public class MemberVO extends Number {
    private String memID;
    private String memName;
    private String memAddr;
    private String memTel;

    public MemberVO() {

    }


    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}
