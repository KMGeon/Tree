package JavaMVCTest.Service;

import JavaMVCTest.MemberVO.MemberVO;

import java.sql.SQLException;
import java.util.List;

public class MemberServiceImpl implements IMemberService{
    @Override
    public int registerMember(MemberVO mv) throws SQLException {
        return 0;
    }

    @Override
    public boolean checkMember(String memId) {
        return false;
    }

    @Override
    public int modifyMember(MemberVO mv) {
        return 0;
    }

    @Override
    public int removeMember(MemberVO mv) {
        return 0;
    }

    @Override
    public List<MemberVO> getAllmemberList() {
        return null;
    }
}
