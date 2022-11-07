package kr.or.ddit.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import kr.or.ddit.service.MemService;
import kr.or.ddit.vo.MemVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/previews")
@Controller
public class PreviewsController {
	// 요청 uri : /previews/write
	// method : get방식
	// PreviewsController.java( <-memVO -> write.jsp
	// modelattribute로 write.jsp 폼과 연결
	
	@Autowired
	MemService memService;
	
	
	@GetMapping("/write")
	public  String write(@ModelAttribute MemVO memVO) {
		return "previews/write";
	}

	// @modelattribute생략 가능
	// request객체에 요청 파라미터가 들어있음 -> validation
	// 결과 : BindResult(요청 파라미터 데이터의 바인딩 에러( 멤버변수에 없음)와 검증 에러 정보가 저장됨)

	@PostMapping("/writePost")
	public String writePost(@Validated MemVO memVO, BindingResult result) {
		log.info("memVo" + memVO.toString());

		/*
		 * 입력값 검증 후 bindingresult가 제공하는 메서드를 이용하여 검사 결과 확인
		 * 
		 * 1.hasError():오류가 발생한경우 참 반환 2.hasGlobalErrors() : 객체 라벨의 오류가 발생한 경우 참 반환
		 * 3.hasFiledErrors() : 필그(멤버변수) 레벨의 오류가 발생한 경우 참 반환 4.hasFiledErros(string) :
		 * 인수에 지정된 필드에 오류 발생 시 참 반환
		 */
		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErros = result.getGlobalErrors();
			List<FieldError> filedErros = result.getFieldErrors();

			for (int i = 0; i < allErrors.size(); i++) {
				ObjectError objectError = allErrors.get(i);
				log.info("allErrors", objectError);
			}

			for (int i = 0; i < globalErros.size(); i++) {
				ObjectError objectError = globalErros.get(i);
				log.info("globalErros", objectError);
			}

			for (int i = 0; i < filedErros.size(); i++) {
				FieldError filedErros2 = filedErros.get(i);
				log.info("filedErros", filedErros2);
				log.info("filedErros", filedErros2.getDefaultMessage());

			}
		}
		int rslt = this.memService.memInsert(memVO);
		log.info("result.hasErrors():" + result.hasErrors()); // 검증 오류가 생기면 참 리턴
		return "previews/write";
	}
	
	
	
	@GetMapping("/list")
	public String memList(Model model) {
		List<MemVO> memList = this.memService.memList2();
		model.addAttribute("memList",memList);
		log.info("memList" , memList);
		return "previews/list";
	}

}
