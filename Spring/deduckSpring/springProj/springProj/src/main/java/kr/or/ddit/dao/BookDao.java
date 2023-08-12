package kr.or.ddit.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;

//매퍼xml(book_SQL.xml)을 실행시키는
//DAO(Data Access Object) 클래스
//Repository 어노테이션 : 데이터에 접근하는 자바빈 객체로 스프링에 등록해줌
//프링아, 이거 데이터를 관리하는 특별한 클래스야.
@Repository
public class BookDao {
	//1.DI(Dependency Injection) : 의존성 주입
	//new 키워드를 통해 직접 생성하지 않고!!
	//스프링이 미리 만들어 놓은(서버 실행 시 미리 root-context.xml을 읽어서
	//자바빈 객체로 인스턴스화 해놓음) sqlSessionTemplate 타입 객체를
	//BookDao 객체에 주입하여 사용함
	//2. IoC(Inversion of Control) : 제어의 역전
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//<insert id="insert" parameterType="kr.or.ddit.vo.BookVO">
	//BOOK 테이블에 insert
	public int insert(BookVO bookVO) {
		//book_SQL.xml 파일의 namespace가 book이고, id가 insert인
		//태그를 찾아 그 안에 들어있는 sql을 실행함
		//insert 성공 : 1이상, 실패면 0
		/*
		bookVO(전)=>[bookId=0, title=개똥이 월드, category=소설
			     , price=10000, insertDate=null]
		bookVO(후)=>[bookId=1, title=개똥이 월드, category=소설
					     , price=10000, insertDate=null]
		*/
		return this.sqlSessionTemplate.insert("book.insert",bookVO);
	}
	
	//책 상세보기
	public BookVO selectDetail(BookVO bookVO) {
		//sqlSessionTemplate : 쿼리를 실행해주는 객체(root-context.xml)
		//selectOne() 메소드 : 1행을 가져올 때 사용 / selectList()메소드 : 결과 집합 목록 변환(다중행)
		//결과 행 수가 0이면? null 반환
		//결과 행 수가 2 이상이면? TooManyResultsException 예외 발생
		//.selectOne("namespace.id", 파라미터)
		return this.sqlSessionTemplate.selectOne("book.select_detail",bookVO);
	}
	
	//책 목록보기
	public List<BookVO> list(String keyword){
		//select 결과를 목록으로 받음. selectList("namespace.id",파라미터)
		return this.sqlSessionTemplate.selectList("book.list",keyword);
	}
	
	//책 수정하기
	//매퍼xml에서 insert/update/delete의 resultType은 생략
	public int update(BookVO bookVO) {
		//.update("namespace.id",파라미터)
		return this.sqlSessionTemplate.update("book.update",bookVO);
	}
	
	//책 삭제하기
	public int delete(int bookId) {
		//.delete("namespace.id",파라미터)
		return this.sqlSessionTemplate.delete("book.delete", bookId);
	}
	
	//ATTACH 테이블에 다중 insert
	public int insertAttach(List<AttachVO> attachVOList) {
		return this.sqlSessionTemplate.insert("book.insertAttach",attachVOList);
	}
}









