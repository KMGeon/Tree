package kr.or.ddit.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.MemVO;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/form")
@Slf4j
@Controller
public class FormController {
	//<form:form modelAttribute="member" method="post" action="register">
	@GetMapping("/registerForm01")
	public String registerForm01(Model model) {
		log.info("registerForm01");
		
		//<form:errors path="memId" />
		model.addAttribute("member", new MemVO());
		
		return "form/registerForm";
	}
	
	//<form:form modelAttribute="MemVO" method="post" action="register">
	@GetMapping("/registerForm02")
	public String registerForm02(@ModelAttribute MemVO memVO) {
		log.info("registerForm02");
		
		
		return "form/registerForm02";
	}
	
	//<form:form modelAttribute="MamVO" method="post" action="register">
	//골뱅이ModelAttribute 애너테이션으로 폼 객체의 속성명을 직접 지정할 수 있음
	@GetMapping("/registerForm03")
	public String registerForm03(@ModelAttribute("memVO") MemVO memVO,
			Model model) {
		log.info("registerForm03");
		
		//폼 객체의 프로퍼티에 값을 미리지정해놓음
		memVO.setMemId("gaeddongi");
		memVO.setMemName("개똥이");
		//password는 값을 설정하여 view에 전달하더라도 password 필드에 반영이 안 됨
		memVO.setPassword("java");
		
		//체크박스
		Map<String,String> hobbyMap = new HashMap<String, String>();
		hobbyMap.put("Sports", "Sports");
		hobbyMap.put("Music", "Music");
		hobbyMap.put("Movie", "Movie");
		
		//라디오버튼
		Map<String,String> genderMap = new HashMap<String, String>();
		genderMap.put("Male", "Male");
		genderMap.put("Female", "Female");
		genderMap.put("Other", "Other");
		
		//셀렉트박스
		Map<String,String> nationalityMap = new HashMap<String, String>();
		nationalityMap.put("Korea", "Korea");
		nationalityMap.put("Germany", "Germany");
		nationalityMap.put("Australia", "Australia");

		
		model.addAttribute("hobbyMap", hobbyMap);
		model.addAttribute("genderMap", genderMap);
		model.addAttribute("nationalityMap", nationalityMap);
		
		
		
		return "form/registerForm03";
	}
	
	//요청URI : /form/register
	//방식 : post
	//파라미터 : {"memId":"gaeddongi","memName":"개똥이","password":"java","introduction":"소개글",
	//			"hobbyList":"[01, 03]"}
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPost(@Validated MemVO memVO,Model model,
			BindingResult result) {
//		log.info("memVO : " + memVO.toString());
		log.info("result.hasErrors() : " + result.hasErrors());
		
		//registerForm03.jsp에서 post 요청 시 Validated 확인 후 
		//문제 발생 시 폼 화면으로 돌아감
		if(result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			
			for(int i=0;i<allErrors.size();i++) {
				ObjectError objectError = allErrors.get(i);
				log.info("allError : " + objectError);
			}
			
			return "form/registerForm03";
		}
		
		String[] hobbyList = memVO.getHobbyList();
		
		if(hobbyList!=null) {
			for(String hobby : hobbyList) {
				log.info("hobby : " + hobby);
			}
		}
		
		model.addAttribute("hobbyList", hobbyList);
		
		return "form/success";
	}
}











