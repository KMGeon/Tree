package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/* 
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 받아와 서비스에 전달하는 DAO의 인터페이스
*/
public interface IMemberDAO {

	// MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	// mv DB에 insert할 자료가 저장된 MemberVO객체 DB작업이
	// 성공하면 1 이상의 값이 반환되고, 실패하면 0이 반환됨
	public int insetMember(MemberVO mv);

	// 주어진 회원 ID가 존재하는지 여부를 알아내기 위한 메서드
	// memId 검색할 회원ID
	// 해당 회원이 존재하면 true, 없으면 false 리턴
	public boolean checkMember(String memId);

	// 하나의 MemberVO자료를 이용하여 DB를 update하는 메서드
	// mv 수정할 회원정보가 들어있는 MemberVO객체
	// 작업성공 1 반환, 실패하면 0 반환
	public int updateMember(MemberVO mv);
	
	// 회원ID를 매개변수로 받아서 그 회원정보를 삭제하는 메서드
	// 삭제할 회원ID
	// 작업성공 1 반환, 실패하면 0 반환
	public int deleteMember(MemberVO mv);
	
	// mymember 테이블에 존재하는 모든 회원정보를 가져와 List에 담아서 반환하는 메서드
	// MemberVO객체를 담고 있는 List객체
	public List<MemberVO> getAllMemberList();
	
	// 검색할 회원 정보를 담은 MemberVO객체를 이용하여 회원정보를 조회하는 메서드
	// 검색할 회원 정보를 담은 MemberVo객체
	// 검색된 회원정보를 담은 List객체
	public List<MemberVO> searchMemberList(MemberVO mv);

}
