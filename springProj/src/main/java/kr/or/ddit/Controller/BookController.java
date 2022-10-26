package kr.or.ddit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;

import lombok.extern.slf4j.Slf4j;


/*
 * Controller 어노테이션
 * 스프링 프레임워크에게 이 클래스는 웹 브라우저의 요청(request)을
 * 받아들이는 컨트롤러라고 알려주는 것임.
 * 스프링은 servlet-context.xml의 context:component-scan의 설정에 의해
 * 이 클래스를 자바빈 객체로 등록(메모리에 바인딩)
 */
@Slf4j
@Controller
public class BookController {
	@Autowired
	private BookService bs;

	// URI => http://localhost/create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		/*
		 * 1) Model : return할 데이터(String, int, List, Map, VO..)를 담당 2) View : 화면을
		 * 담당(뷰(view : JSP)의 경로) ViewResolver => prefix + jsp파일명 + suffix
		 */
		ModelAndView mav = new ModelAndView();
		/*
		 * <beans:property name="prefix" value="/WEB-INF/views/" /> <beans:property
		 * name="suffix" value=".jsp" />
		 */
		// WEB-INF/views/book/create.jsp
		mav.setViewName("/book/create");
		// forwarding
		return mav;
	}

	// URI : http://localhost/create
	// 요청 파라미터 : {"title":"개똥이월드", "category":"소설", "price":"10000"}
	// BookVO : {"bookId":0, "title":"개똥이월드", "category":"소설", "price":"10000",
	// "insertDate",""}
	// <form action="/create" method="post">
	/*
	 * @RequestMapping(value = "/create", method = RequestMethod.POST) public
	 * ModelAndView createPost(ModelAndView mav, @ModelAttribute BookVO vo) { //
	 * bookVO : BookVO [bookId=0, title=개똥이월드, category=소설, price=10000, //
	 * insertDate=null] log.info("bookVO : " + vo.toString());
	 * 
	 * int result = this.bs.insert(vo);
	 * 
	 * log.info("result : " + result); if (result < 1) { // 등록 실패 // /create(get방식)
	 * URI를 재요청 // 책 입력 화면으로 이동 mav.setViewName("redirect:/create"); } else { // 등록
	 * 성공 mav.setViewName("redirect:/detail?bookId=" + vo.getBookId()); }
	 * 
	 * 
	 * return mav; }
	 */

	@PostMapping("/create")
	public String createPost(@ModelAttribute BookVO vo) {
		int result = this.bs.insert(vo);
		String resultUrl = (result < 1) ? "redirect:/create" : "redirect:/detail?bookId=" + vo.getBookId();
		return resultUrl;
	}

	// 책 상세보기
	// 요청된 URI 주소 : /detail?bookId=1
	// URL : http://localhost/detail
	// 요청(HTTP) 파라미터, 쿼리 스트링 : book_id=1
	// bookVO(IN) => {"bookId":"1", "title":"", "category":"", "price":0,
	// "insertDate":""}

	@GetMapping(value = "/detail")
	public ModelAndView detail(ModelAndView mav, @ModelAttribute BookVO vo) {
		log.info("detail");
		log.info("bookVO : " + vo.toString());
		// select 결과 1행을 bookVO에 담을 것임
		BookVO data = this.bs.select_detail(vo);
		// forwarding => "WEB-INF/views/book/detail.jsp"를 찾아서
		// 해석/컴파일하여 응답.
		// 데이터(BookVO) 1행을 함께 응답.
		// but, redirect는 데이터를 응답해주지 못함.
		mav.setViewName("book/detail");
		mav.addObject("data", data);
		mav.addObject("bookId", data.getBookId());
		return mav;
	}

	@GetMapping(value = "/list")
//	 요청 uri => http://local/list
	public ModelAndView list(ModelAndView mav) {
		List<BookVO> list = this.bs.list();
		for (BookVO vo : list) {
			log.info("bookvo" + vo.toString());
		}
		mav.setViewName("book/list");
		mav.addObject("data", list);
		return mav;
	}

	// update
	// addAttribute는 1행
	@GetMapping("/update")
	public String update(BookVO bookvo, Model m) {
		BookVO data = this.bs.select_detail(bookvo);
		m.addAttribute("data", data);
		return "book/update";
	}

	@PostMapping("/update")
	public ModelAndView updatePost(BookVO bookVO, @ModelAttribute ModelAndView mav) {
		log.info("updatePost" + bookVO + toString());
		int result = this.bs.update(bookVO);
		if (result > 0) {
			mav.setViewName("redirect:/detail?bookId=" + bookVO.getBookId());
		} else {
			mav.setViewName("redirect:/update?bookId=" + bookVO.getBookId());
		}
		return mav;
	}

	// delete
	// 스프링에서는 요청 파라미터를 매개변수로 받을 수 있음
	// 매개변수 타입이 int타입으로 자동 형 변환 됨
	@PostMapping("/delete")
	public ModelAndView deletePost(ModelAndView mav, int bookId) {
		log.info("bookId" + bookId);

		int result = this.bs.delete(bookId);

		if (result > 0) {
			mav.setViewName("redirect:/list");
		} else {
			mav.setViewName("redirect:/detail?bookId" + bookId);
		}
		return mav;
	}

}