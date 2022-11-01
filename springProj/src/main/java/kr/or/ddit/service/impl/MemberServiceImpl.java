package kr.or.ddit.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.dao.BookDao;
import kr.or.ddit.dao.MemberDao;
import kr.or.ddit.service.BookService;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;

//"프링아 이 클래스는 서비스 클래스야"라고 스프링에게 알려줌. 
//프링이가 자바빈으로 등록해줌
@Service
public class MemberServiceImpl implements MemberService {
	// di
	@Inject
	MemberDao memberDao;

	// Member 테이블에 insert
	
	
	/*@transactional
	 * 회원 정보를 저장하다가 실행하거나 주소 정보를 저장하다가 실패하거나
	 * 카드 정보를 저장하다가 실패하면 모두 저장이 되지 않고 모두 rollback이 된다.
	 */
	@Transactional
	@Override
	public int memberInsert(MemberVO memberVO ) {
		 this.memberDao.memberInsert(memberVO);
		 this.memberDao.addressInsert(memberVO);
		 List<CardVO>cardVOList = memberVO.getCardlist();
		 List<CardVO>cardVOList2 =	new ArrayList<>();//userid가 채워짐
		 for(CardVO vo :cardVOList) {
			 
			 vo.setUserId(memberVO.getUserId());
			
		 }
		 
		 return this.memberDao.insertCard(cardVOList);
	}


}
