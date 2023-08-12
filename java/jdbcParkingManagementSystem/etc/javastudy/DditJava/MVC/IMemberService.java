package JavaMVCTest.Service;

import JavaMVCTest.MemberVO.MemberVO;

import java.sql.SQLException;
import java.util.List;

/*
    서비스 객체는 dao에 설정된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고
    받아온 데이터를 cntroller에게 보내주는 역활을 수행한다.
 */
public interface IMemberService {

    // insertMember
    // membervo에 담겨진 자료를 db에 insert하는 메서드
    // mv db에 insert할 자료가 저장된 vo객체 db작업이 성공하면 1 실패하면 0을 반환한다.
    public int registerMember(MemberVO mv) throws SQLException;

    /*
        checkMember
        주어진 회원id가 존재하는지 여부를 알아내기 위한 메서드
        memid 검색할 회원id 검색할 회원id
        해당 회원이 존재하면 참 , 없으면 false리턴함.
     */

    public boolean checkMember(String memId);

    /*
        updateMember
        하나의 memberVO자료를 이용하여 DB를 업데이트하는 메서드
        MV 수정할 회원정보가 들어있는 MEMBERVO 객체
        작업성공:1 , 작업 실패:0
     */
    public int modifyMember(MemberVO mv);

    /*
    deleteMember
    회원id를 매개변수로 받아서 그 회원정보를 삭제하는 메서드
    memid 삭제할 회원id
    작업 성공:1 , 작업 실패:0
     */
    public int removeMember(MemberVO mv);


    /*
        getAllmemberList
        mymember 테이블에 존재하는 모든 회원정보를 가져와 list에 담아서 반환하는 메서드
        membervo객체를 담고 있는 list객체
     */

    public List<MemberVO> getAllmemberList();

}
