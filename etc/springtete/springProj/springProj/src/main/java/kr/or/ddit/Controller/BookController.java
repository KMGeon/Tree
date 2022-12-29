package kr.or.ddit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;



/*
 Controller 어노테이션
 스프링 프레임워크에게 "프링아, 이 클래스는 웹 브라우저의 요청(request)를
 받아들이는 컨트롤러야"라고 알려주는 것임.
 스프링은 servlet-context.xml의 context:component-scan의 설정에 의해
 이 클래스를 자바빈 객체로 등록(메모리에 바인딩)
 */
@Slf4j
@Controller
public class BookController {
	//도서관리프로그램
	//BookService 서비스를 호출하기 위해 의존성 주입(DI)
	@Autowired
	BookService bookService;
	
	//URI => http://localhost/create
	//Request : client가 server에 URI를 요청
	//Mapping : create() 메소드를 실행
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create() {
		/*
		 ModelAndView
		 1) Model : return할 데이터(String, int, List, Map, VO..)를 담당
		 2) View  : 화면을 담당(뷰(view : JSP)의 경로)
		    ViewResolver => prefix + jsp파일명 + suffix
		 */
		ModelAndView mav = new ModelAndView();
		
		/*
		 <beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
		 */
		///WEB-INF/views/book/create.jsp
		mav.setViewName("book/create");
		//forwarding
		return mav;
	}
	
	//URI : http://localhost/create
	//요청 파라미터 : {"title":"개똥이월드","category":"소설","price":"10000"}
	//BookVO   : {"bookId":0,"title":"개똥이월드","category":"소설","price":"10000","insertDate",""}
	//<form action="/create" method="post">
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public ModelAndView createPost(ModelAndView mav,
			@ModelAttribute BookVO bookVO) {
		//bookVO : BookVO [bookId=0, title=개똥이 월드, category=소설
		//			     , price=10000, insertDate=null]
		log.info("bookVO : " + bookVO.toString());
		
		int result = this.bookService.insert(bookVO);
		
		log.info("result : " + result);
		
		if(result<1) {//등록 실패
			// /create (get방식) URI를 재요청
			// 책 입력 화면으로 이동
			mav.setViewName("redirect:/create");
		}else {//등록 성공
			mav.setViewName("redirect:/detail?bookId="+bookVO.getBookId());
		}
		
		return mav;
	}
}











