package kr.or.ddit.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import kr.or.ddit.service.MemService;
import kr.or.ddit.vo.MemVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("previews")
@Controller
public class PreviewsController {
	@Autowired
	MemService memService;

	// 요청 URI : /previews/write
	// 방식 : get
	// PreiviewsController.java <- memVO (오작교) -> write.jsp
	@GetMapping("write")
	public String write(@ModelAttribute MemVO memVO) {
		return "previews/write";
	}

	/*
	 * 요청 URI : /previews/writePost 방식 : post request 객체에 요청 파라미터가 들어있음
	 * -> @Validated -> 요청 파라미터 검증 VO => @ModelAtttribute(생략가능!!) String, Map, int
	 * -> @RequestParam
	 * 
	 * 입력값 검증 대상의 도메인 클래스(memVO) 직후에 BindingResult 를 정의함 BindingResult에는 요청 파라미터
	 * 데이터의 바인딩 에러(멤버변수에 없음)와 입력값 검증 에러(에너테이션 오류) 정보가 저장됨
	 */
	@PostMapping("/writePost")
	public String writePost(@Validated MemVO memVO, BindingResult result) {

		log.info("memVO : " + memVO.toString());
		// 입력값의 검증 오류 발생 시 true 반환 / 오류 없으면 false 반환!!
		log.info("result.hasErrors() : " + result.hasErrors());

		// 입력값 검증 후 BindingResult가 제공하는 메서드를 이용하여 검사 결과 확인
		/*
		 * 1. has Errors() : 오류가 발생한 경우 true 반환 2. hasGlobalErros() : 객체 레벨의 오류가 발생한 경우
		 * true 반환 3. hasFieldErros() : 필드(멤버변수) 레벤의 오류가 바생한 경우 true 반환 4.
		 * hasFieldErros(String) : 인수에 지정한 필드에 오류 발생 시 true 반환
		 */
		if (result.hasErrors()) { // 오류가 있다면
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErrors = result.getGlobalErrors(); // 객체 레벨
			List<FieldError> fieldErros = result.getFieldErrors(); // 멤버변수 레벨

			for (int i = 0; i < allErrors.size(); i++) {
				ObjectError objectError = allErrors.get(i);
				log.info("allError : " + objectError);
			}
			for (int i = 0; i < globalErrors.size(); i++) {
				ObjectError objectError = globalErrors.get(i);
				log.info("allError : " + objectError);
			}
			for (int i = 0; i < fieldErros.size(); i++) {
				FieldError filedError = fieldErros.get(i);
				log.info("filedError : " + filedError);
				log.info("filedError.getDefaultMessage() : " + filedError.getDefaultMessage());
			}
		}
		int rslt = this.memService.memInsert(memVO);

		// forwarding
		return "previews/write";
	}

	@GetMapping("/list")
	public String memList(Model model) {

		List<MemVO> memVOList = this.memService.memList2();

		log.info("memVOList : " + memVOList);

		model.addAttribute("memVOList", memVOList);

		// forwarding
		return "previews/list";
	}

	// 회원 상세보기
	// previews/detail?userNo=??
	// memVO 가 detail.jsp와 PreviewsController를 연결한다.
	@GetMapping("/detail")
	public String memDetail(@RequestParam String userNo, @ModelAttribute MemVO memVO, Model model) {
		memVO = this.memService.memDetail(userNo);
		model.addAttribute("memVO", memVO);
		return "previews/detail";
	}

	// 비밀번호 체크
	// json으로 보냄 : {userPw:"java"}
	// 리턴이 1이면 참 0이면 불일치
	@PostMapping("/detailPwCheck")
	public @ResponseBody Map<String, String> detailPwCheck(@RequestBody MemVO memVO) {
		log.info("map", memVO);
		
		
		// 비밀번호 확인
		int result = this.memService.detailPwCheck(memVO);
		

		// 결과 리턴
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", 1 + "");
		return resultMap;
	}
	
	//previews/updatePost
	//요청파라미터:memVO
	//방식 : POS4
	@PostMapping("/updatePost")
	public String updatePost(@ModelAttribute MemVO memVO) {
		log.info("memVO"+memVO.toString());
		
		//회원정보 변경
		int result=this.memService.memUpdate(memVO);
		log.info("updateResult"+result);
		return "redirect:/previews/detail?userNo="+memVO.getUserNo();
	}
	
	
}
