package kr.or.ddit.dao;

import java.awt.print.Book;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import kr.or.ddit.vo.BookVO;

//매퍼xml(book_SQL.xml)을 실행시키는
//DAO(Data Access Object) 클래스
//Repository 어노테이션 : 데이터에 접근하는 자바빈 객체로 스프링에 등록해줌
//프링아, 이거 데이터를 관리하는 특별한 클래스야.
@Repository
public class BookDao {
	// 1.DI(Dependency Injection) : 의존성 주입
	// new 키워드를 통해 직접 생성하지 않고!!
	// 스프링이 미리 만들어 놓은(서버 실행 시 미리 root-context.xml을 읽어서
	// 자바빈 객체로 인스턴스화 해놓음) sqlSessionTemplate 타입 객체를
	// BookDao 객체에 주입하여 사용함
	// 2. IoC(Inversion of Control) : 제어의 역전
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	// <insert id="insert" parameterType="kr.or.ddit.vo.BookVO">
	// BOOK 테이블에 insert
	public int insert(BookVO bookVO) {
		// book_SQL.xml 파일의 namespace가 book이고, id가 insert인
		// 태그를 찾아 그 안에 들어있는 sql을 실행함
		// insert 성공 : 1이상, 실패면 0
		/*
		 * bookVO(전)=>[bookId=0, title=개똥이 월드, category=소설 , price=10000,
		 * insertDate=null] bookVO(후)=>[bookId=1, title=개똥이 월드, category=소설 ,
		 * price=10000, insertDate=null]
		 */
		return this.sqlSessionTemplate.insert("book.insert", bookVO);
	}

	// 책 상세보기
	public BookVO select_detail(BookVO bookVO) {
		// sqlSessionTemplate : 쿼리를 실행해주는 객체(root-context.xml)
		// 하나 : selectOne , 다중 : selectList()
		// 결과 행의 수가 0이면 null반환
		// 2이상 : ToManyResultException : 예외발상
		// .selectOne("namespace.id",파라미터)
		return this.sqlSessionTemplate.selectOne("book.select_detail", bookVO);
	}

	// 책 목록보기
	// 여러행을 출력하니깐 list로 하는게 좋음
	public java.util.List<BookVO> list() {
		// select 결과를 목록으로 받음
		return this.sqlSessionTemplate.selectList("book.list");
	}

	public int update(BookVO bookVO) {
		return this.sqlSessionTemplate.update("book.update", bookVO);
	}

	public int delete(int bookId) {
		return this.sqlSessionTemplate.delete("book.delete", bookId);
	}
}
