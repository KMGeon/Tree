package kr.or.ddit.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;

@Repository
public class MemberDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//Member 테이블에 insert
	public int memberInsert(MemberVO memberVO) {
		return this.sqlSessionTemplate.insert("member.memberInsert",memberVO);
	}
	
	//address 테이블에 insert
	public int addressInsert(MemberVO memberVO) {
		return this.sqlSessionTemplate.insert("member.memberInsert",memberVO);
	}
	
	//CARD테이블에 INSERT
	public int insertCard(List<CardVO>cardVOList) {
		return this.sqlSessionTemplate.insert("member.insertCard",cardVOList);
	}

}









