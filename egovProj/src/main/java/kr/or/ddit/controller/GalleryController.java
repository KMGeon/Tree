package kr.or.ddit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.GalleryService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/gallery")
@Slf4j
public class GalleryController {
	@Autowired
	GalleryService galleryService;

	@GetMapping("/list")
	public String galleryWelcome(@ModelAttribute BookVO bookVO, Model model) {
		bookVO = this.galleryService.list(bookVO);
		model.addAttribute("bookVO", bookVO);
		log.info("testtesttest", bookVO);
		return "gallery/list";
	}

	
	//도서 목록 가져와서 select에 추가하기
	//json 데이터로 리턴
	@GetMapping("/bookList")
	public @ResponseBody List<BookVO> bookList(Model model) {
		List<BookVO> list = this.galleryService.bookList();
		return list;
	}

}
