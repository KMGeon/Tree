package kr.or.ddit.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.MemService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.vo.MemVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemController {
	
	@Autowired
	MemService memService;
	
	//요청 URI : /board/list?currentPage=2&show=10
	//요청 파라미터 : currentPage=2
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public ModelAndView list(ModelAndView mav,
			@RequestParam(defaultValue="1",required=false) int currentPage,
			@RequestParam Map<String,String> map) {
		
		log.info("currentPage : " + currentPage);
		// /board/list 이렇게 요청되었을 경우처리
		String cPage = map.get("currentPage");
		String show = map.get("show");
		String keyword = map.get("keyword");
		if(cPage==null) {
			map.put("currentPage","1");
		}
		if(show==null) {
			map.put("show","10");
		}
		if(keyword==null) {
			map.put("keyword","");
		}
		
		//map : {show=25, keyword=개똥, currentPage=1}
		log.info("map : " + map);
		
		List<MemVO> list = this.memService.list(map);
		
		//MEM 테이블의 전체 행 수 구함
		//map : {show=25, keyword=개똥, currentPage=1}
		int total = this.memService.getTotal(map);
		
		//한 화면에 보여질 행 수
		int size = Integer.parseInt(map.get("show"));
		
		for(MemVO vo : list) {
			log.info("vo : " + vo.toString());
		}
		mav.setViewName("board/list");
		//(전체 글 수, 현재페이지, 한 화면에 보여질 행 수, select 결과 list)
		mav.addObject("data", new ArticlePage<MemVO>(total, currentPage, size, list));
		return mav;
	}
	
	@RequestMapping(value="/create2", method=RequestMethod.GET)
	public ModelAndView create2() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/create2");
		return mav;
	}
	
	
	@RequestMapping(value="/create2", method=RequestMethod.POST)
	public ModelAndView create2Post(ModelAndView mav, @ModelAttribute MemVO memVO) {
		int result = this.memService.insert(memVO);
		
		if(result < 1) {
			mav.setViewName("redirect:/create2");
		} else {
			mav.setViewName("redirect:/board/list");
		}
		return mav;
	}
	
}
