package kr.or.ddit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@RequestMapping("/book")
@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/list")
	public String list(Model model) {

		
		List<BookVO> bookVOList = this.bookService.list();
		log.info("bookVOList : " + bookVOList);

		// 공통 약속
		model.addAttribute("bodyTitle", "도서 목록");
		model.addAttribute("bookVOList", bookVOList);

		// forwarding
		return "book/list";
	}

	// 요청URI : /book/detail?bookId=3
	// 요청파라미터 : bookId=3
	// 메소드 이름 : detail
	// 목록에서 title을 클릭 시 상세페이지로 이동
	// 부트스트랩 : https://adminlte.io/themes/v3/pages/forms/advanced.html
	@GetMapping("/detail")
	public String detail(@RequestParam int bookId, Model model) {
		List<BookVO> bookVO = this.bookService.detail(bookId);
		log.info("bookId" + bookId);
		log.info("bookasd" + bookVO.toString());
		model.addAttribute("bookVO", bookVO);
		return "book/detail";
	}

	@GetMapping("/update")
	public String updateGet(@RequestParam int bookId, Model model) {
		List<BookVO> bookVO = this.bookService.detail(bookId);
		model.addAttribute("bookVO", bookVO);
		return "book/update";
	}

	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public ModelAndView updatePost(BookVO bookVO, ModelAndView mav) {
		log.info("bookVO::::::::::::::::::::::::::" + bookVO.toString());
		int result = this.bookService.update(bookVO);
		log.info("result: " + result);
		if (result > 0) {
			mav.setViewName("redirect:/book/list");
		} else {
			mav.setViewName("redirect:/book/list");
		}

		return mav;
	}

	// 추가
	@GetMapping("/insert")
	public String insertView() {
		return "book/insert";
	}

	@PostMapping("/insert")
	public String insertPost(BookVO bookVO) {
		int result = this.bookService.insert(bookVO);
		log.info("result+++++++" + bookVO.toString());
		String url = "";
		if (result > 0) {
			url = "redirect:/book/list";
		} else {
			url = "redirect:/book/insert";
		}
		return url;
	}

}
