package kr.or.ddit.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;


// 매퍼 xml(book_SQL.xml)을 실행시키는
// DAO(Data Access Object) 클래스
// Repository 어노테이션 : 데이터에 접근하는 자바빈 객체로 스프링에 등록해준다.
@Repository
public class BoardDao {
   
   @Autowired
   SqlSessionTemplate sqlSessionTemplate;// 스프링은 객체 생성시 new를 사용하지않음 개발자가 주체가 아님
   
   
   public int insert(BoardVO boardVO) {

      return this.sqlSessionTemplate.insert("board.insert", boardVO);
   }
   
   // 책 목록보기
   public List<BoardVO> list(String keyword) {
	   // select 결과를 목록으로 받음. selectList("namespace.id", 파라미터)
	   return this.sqlSessionTemplate.selectList("board.list", keyword);
   } 
}