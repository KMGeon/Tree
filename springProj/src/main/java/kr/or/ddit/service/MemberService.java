package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;

public interface MemberService {

	//member , address, card 동시에 처리
	public int memberInsert(MemberVO memberVO);

}
